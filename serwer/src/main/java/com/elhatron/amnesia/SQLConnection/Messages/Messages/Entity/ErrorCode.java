package com.elhatron.amnesia.SQLConnection.Messages.Messages.Entity;

public class ErrorCode {
    private String user;
    private int errorCode;

    public String getUser() {
        return user;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public ErrorCode(String user, int errorCode) {
        this.user = user;
        this.errorCode = errorCode;
    }
}
