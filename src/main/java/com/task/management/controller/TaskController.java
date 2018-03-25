package com.task.management.controller;

import com.task.management.base.BaseResponse;
import com.task.management.profile.model.UserProfile;
import com.task.management.service.AuthenticationService;
import com.task.management.task.datamapper.TaskDataMapper;
import com.task.management.task.model.Task;
import com.task.management.utils.ResponseHttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskDataMapper taskDataMapper;
    @Autowired
    private ResponseHttpStatus responseHttpStatus;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value =  "/addNewTask")
    public BaseResponse addNewTask(@Valid @RequestBody Task task, BindingResult bindingResult) {
        BaseResponse baseResponse = null;
        List<String> errorMessages = new ArrayList<>();
        if(authenticationService.checkAuthStatus()) {
            if (!bindingResult.hasErrors()) {
                task.setUserProfile(authenticationService.getLoggedInUser());
                task.setCreatedDate(new Date());
                taskDataMapper.save(task);
                baseResponse= responseHttpStatus.getBaseResponseForHttpStatusOK("Task created successfully");
                return baseResponse;
            }
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error:errors) {
                errorMessages.add("@" +error.getField().toUpperCase() + ":" + error.getDefaultMessage());
            }
            baseResponse =responseHttpStatus.getBaseResponseForHttpStatusBADREQUEST(errorMessages);
            return baseResponse;
        }
        errorMessages.add("Please login to add task");
        baseResponse =responseHttpStatus.getBaseResponseForHttpStatusUnAuthorized(errorMessages);
        return baseResponse;
    }

}
