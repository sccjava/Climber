package com.test.climber.activity;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.test.climber.R;
import com.test.climber.databinding.ActivityDashboardBinding;
import com.test.climber.viewmodel.DashboardViewModel;

public class DashboardActivity extends AppCompatActivity {
    private final int STEP_PROGRESS_RATIO = 400;

    private DashboardViewModel mViewModel;
    private ActivityDashboardBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        initBlurBackground();
        initProfile();
        initCircleProgress(mViewModel.getPreviousSteps(), mViewModel.getCurrentSteps());
    }

    private void initProfile() {
        mBinding.userName.setText(mViewModel.getUserName());
        if (!TextUtils.isEmpty(mViewModel.getProfilePicUrl())) {
            // TODO, update profile picture
        }
    }

    private void initCircleProgress(int startSteps, final int endSteps) {
        mBinding.steps.setText(String.valueOf(startSteps));

        int start = startSteps / STEP_PROGRESS_RATIO;
        int end = endSteps / STEP_PROGRESS_RATIO;

        ValueAnimator anim = ValueAnimator.ofInt(start, end);
        anim.setDuration(1000);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBinding.circleProgressBar.setProgress((Integer) animation.getAnimatedValue());
                mBinding.steps.setText(String.valueOf(((Integer) animation.getAnimatedValue()).intValue() * STEP_PROGRESS_RATIO));
            }
        });
        anim.start();
    }

    private void initBlurBackground() {
        Bitmap bg = BitmapFactory.decodeResource(getResources(), R.mipmap.bg);
        Bitmap blurred = mViewModel.createBackgroundBitmap(bg);
        bg.recycle();

        Drawable drawable = new BitmapDrawable(getResources(), blurred);
        mBinding.relativeLayout.setBackground(drawable);
    }
}
