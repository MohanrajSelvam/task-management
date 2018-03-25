package com.task.management.controller;

import com.task.management.base.BaseResponse;
import com.task.management.profile.datamapper.UserProfileDataMapper;
import com.task.management.profile.model.Role;
import com.task.management.profile.model.UserProfile;
import com.task.management.service.AuthenticationService;
import com.task.management.utils.ResponseHttpStatus;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProfileController {
    @Autowired
    private UserProfileDataMapper userProfileDataMapper;
    @Autowired
    private ResponseHttpStatus responseHttpStatus;
    @Autowired
    private AuthenticationService authenticationService;

    /**
     *
     * @param userProfile
     * @param bindingResult
     * @return BaseResponse
     */
    @PostMapping(value = "/createuser")
    public BaseResponse createNewProfile(@Valid @RequestBody UserProfile userProfile, BindingResult bindingResult) {
        List<String> errorMessages = null;
        List<UserProfile > userProfiles = userProfiles();
        boolean unique = true;
        for (UserProfile user: userProfiles) {
            if (userProfile.getEmail().trim().equalsIgnoreCase(user.getEmail().trim())) {
                errorMessages = new ArrayList<>();
                userProfile.setError(true);
                errorMessages.add("Email already registered");
                userProfile.setErrorMsg(errorMessages);
                unique = false;
            }
        }
        if (!bindingResult.hasErrors() &&unique) {
            userProfile.setRole(Role.USER);
            userProfileDataMapper.save(userProfile);
            BaseResponse response = responseHttpStatus.getBaseResponseForHttpStatusOK("Profile created successfully");
            return response;
        }
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error:errors) {
            errorMessages.add("@" +error.getField().toUpperCase() + ":" + error.getDefaultMessage());
        }
        BaseResponse baseResponse =responseHttpStatus.getBaseResponseForHttpStatusBADREQUEST(errorMessages);
        return baseResponse;
    }

    /**
     *
     * @return list of user.
     */
    @GetMapping(value = "/findall")
    public List<UserProfile> userProfiles() {
        return userProfileDataMapper.findAll();
    }

    @PostMapping(value = "/login")
    public BaseResponse login(@RequestBody UserProfile userProfile) {
        userProfile = authenticationService.auth(userProfile);
        List<String> error = new ArrayList<>();
        BaseResponse baseResponse = null;
        if (!authenticationService.checkAuthStatus()) {
            if (  null != userProfile) {
                authenticationService.startLoginSession(userProfile);
                baseResponse = responseHttpStatus.getBaseResponseForHttpStatusOK("Logged in Successfully");
                return baseResponse;
            }
            error.add("Please check your credentials");
            baseResponse = responseHttpStatus.getBaseResponseForHttpStatusUnAuthorized(error);
            return baseResponse;
        }
        error.add("You already logged in");
        baseResponse = responseHttpStatus.getBaseResponseForHttpStatusUnAuthorized(error);
        return baseResponse;
    }
    @GetMapping(value = "/logout")
    public BaseResponse logout() {
        BaseResponse response = null;
        List<String> error = new ArrayList<>();
        if (authenticationService.checkAuthStatus()) {
            authenticationService.closeLogedInSession();
            response = responseHttpStatus.getBaseResponseForHttpStatusOK("You have been logged out successfully");
            return response;
        }
        error.add("No active logged in session");
        response = responseHttpStatus.getBaseResponseForHttpStatusUnAuthorized(error);
        return response;
    }

}
