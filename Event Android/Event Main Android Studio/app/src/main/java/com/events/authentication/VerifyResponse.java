package com.events.authentication;

/**
 * Created by Cruze on 12/12/2016.
 */

public class VerifyResponse {
    Boolean success;

    public VerifyResponse(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
