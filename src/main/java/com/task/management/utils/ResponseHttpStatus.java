package com.task.management.utils;

import com.task.management.base.BaseResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ResponseHttpStatus {

    private  BaseResponse response =new BaseResponse();;


    public BaseResponse getBaseResponseForHttpStatusOK(Object object) {
        response.setResponse(object);
        return response;
    }
    public BaseResponse getBaseResponseForHttpStatusOK(String successMsg) {
        response.setResponse(successMsg);
        return response;
    }

    public BaseResponse getBaseResponseForHttpStatusBADREQUEST( List<String> errorMsg) {
        response.setError(true);
        response.setResponseCode(1);
        response.setErrorMsg(errorMsg);
        response.setResponse(null);
        return response;
    }
    public BaseResponse getBaseResponseForHttpStatusINTERNALSERVERERROR( ) {
        List<String> errMsg = new ArrayList<>();
        errMsg.add("#500, Sorry for inconvenience we are working to fix this.");
        response.setErrorMsg(errMsg);
        response.setResponse(null);
        return response;
    }

}
