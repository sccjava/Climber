package com.test.climber.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.test.climber.manager.IBaseCallback;
import com.test.climber.manager.UserManager;

public class LoginViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> mLoginStatus;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mLoginStatus = new MutableLiveData<>();
    }

    public void login(String email, String password) {
        UserManager.getInstance().login(email, password, new IBaseCallback() {
            @Override
            public void onResult(boolean isSuccess, Object value) {
                mLoginStatus.postValue(isSuccess);
            }
        });
    }

    public boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    /**
     * Expose login status to UI
     *
     * @return
     */
    public MutableLiveData<Boolean> loginStatus() {
        return mLoginStatus;
    }
}
