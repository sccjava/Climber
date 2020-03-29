package com.test.climber.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.test.climber.R;
import com.test.climber.databinding.ActivityLoginBinding;
import com.test.climber.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel mViewModel;
    private ActivityLoginBinding mBinding;
    private boolean mIsLoginReady;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mBinding.setLifecycleOwner(this);

        mBinding.userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int end, int count) {
                if (start == 0 && end == 0 && count == 1) {
                    mBinding.textViewUsername.setVisibility(View.VISIBLE);
                } else if (start == 0 && end == 1 && count == 0) {
                    mBinding.textViewUsername.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                highlightLoginButton();
            }
        });

        mBinding.password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int end, int count) {
                if (start == 0 && end == 0 && count == 1) {
                    mBinding.textViewPassword.setVisibility(View.VISIBLE);
                } else if (start == 0 && end == 1 && count == 0) {
                    mBinding.textViewPassword.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int end, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                highlightLoginButton();
            }
        });

        mBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIsLoginReady) {
                    login();
                }
            }
        });

        subscribeToModel();
    }

    private void highlightLoginButton() {
        if (mBinding.userName.getText().length() > 0
                && mBinding.password.getText().length() > 0) {
            mBinding.login.setAlpha(1);
            mIsLoginReady = true;
        } else {
            mBinding.login.setAlpha(0.5f);
            mIsLoginReady = false;
        }
    }

    private void login() {
        String userName = mBinding.userName.getText().toString();
        String password = mBinding.password.getText().toString();

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, R.string.enter_user_pwd, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!mViewModel.isValidEmail(userName)) {
            Toast.makeText(this, R.string.invalid_email, Toast.LENGTH_SHORT).show();
            return;
        }

        mViewModel.login(userName, password);
    }

    private void subscribeToModel() {
        mViewModel.loginStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoginSuccess) {
                if (isLoginSuccess) {
                    gotoDashboard();
                }
            }
        });
    }

    private void gotoDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}
