package com.task.management.base;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BaseResponse {
    private int responseCode;
    private boolean isError;
    private List<String> errorMsg;
    private Object response;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public boolean getIsError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public List<String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(List<String> errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "responseCode=" + responseCode +
                ", isError=" + isError +
                ", errorMsg=" + errorMsg +
                ", response=" + response +
                '}';
    }
}
