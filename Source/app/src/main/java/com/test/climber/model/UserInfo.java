package com.test.climber.model;

public class UserInfo {

    private boolean isLoginSuccess;
    private String sessionKey;
    private String userName;
    private int previousSteps;
    private int currentSteps;
    private String profilePicUrl;

    public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        isLoginSuccess = loginSuccess;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPreviousSteps() {
        return previousSteps;
    }

    public void setPreviousSteps(int previousSteps) {
        this.previousSteps = previousSteps;
    }

    public int getCurrentSteps() {
        return currentSteps;
    }

    public void setCurrentSteps(int currentSteps) {
        this.currentSteps = currentSteps;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }
}
