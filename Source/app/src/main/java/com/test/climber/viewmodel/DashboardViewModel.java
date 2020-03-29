package com.test.climber.viewmodel;

import android.app.Application;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.test.climber.manager.UserManager;
import com.test.climber.model.UserInfo;

public class DashboardViewModel extends AndroidViewModel {
    public DashboardViewModel(@NonNull Application application) {
        super(application);
    }

    public int getCurrentSteps() {
        UserInfo currentUser = UserManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            return currentUser.getCurrentSteps();
        }
        return 0;
    }

    public int getPreviousSteps() {
        UserInfo currentUser = UserManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            return currentUser.getPreviousSteps();
        }
        return 0;
    }

    public String getUserName() {
        UserInfo currentUser = UserManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            return currentUser.getUserName();
        }
        return "";
    }

    public String getProfilePicUrl() {
        UserInfo currentUser = UserManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            return currentUser.getProfilePicUrl();
        }
        return null;
    }

    public Bitmap createBackgroundBitmap(Bitmap b) {
        int scaleRatio = 18;
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(b,
                b.getWidth() / scaleRatio,
                b.getHeight() / scaleRatio,
                false);
        Bitmap blurred = blurBitmap(scaledBitmap);
        scaledBitmap.recycle();
        return blurred;
    }

    private Bitmap blurBitmap(Bitmap bitmap) {
        Bitmap inputBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);
        RenderScript rs = RenderScript.create(getApplication());
        final Allocation input = Allocation.createFromBitmap(rs, outputBitmap);
        final Allocation output = Allocation.createTyped(rs, input.getType());
        final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius(15F);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(outputBitmap);
        rs.destroy();
        return outputBitmap;
    }
}
