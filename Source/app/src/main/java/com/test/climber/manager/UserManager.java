package com.test.climber.manager;

import com.test.climber.model.UserInfo;

public class UserManager {

    private static UserManager mUserManager;
    private UserInfo mCurrentUser;

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (mUserManager == null) {
            mUserManager = new UserManager();
        }
        return mUserManager;
    }

    /**
     * Asynchronous login, returns @see UserInfo via callback.
     *
     * @param email
     * @param password
     * @param callback
     */
    public void login(String email, String password, IBaseCallback callback) {
        // request backend to do login with username & password, then callback login status to caller
        // TODO

        // get response from backend, set current user info if login successfully
        mCurrentUser = new UserInfo();
        mCurrentUser.setLoginSuccess(true);
        mCurrentUser.setPreviousSteps(8000);
        mCurrentUser.setCurrentSteps(16000);
        mCurrentUser.setSessionKey("session key");
        mCurrentUser.setUserName("Climber");

        if (callback != null) {
            callback.onResult(mCurrentUser.isLoginSuccess(), mCurrentUser);
        }
    }

    public UserInfo getCurrentUser() {
        return mCurrentUser;
    }
}
