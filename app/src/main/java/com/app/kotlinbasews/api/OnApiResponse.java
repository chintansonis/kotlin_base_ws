package com.app.kotlinbasews.api;

public interface OnApiResponse {
    void OnSuccessfulResponse(Object object);
    void OnFailure(String msg);
    void OnServerError(String error);
}
