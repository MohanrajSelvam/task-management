package com.task.management.controller;

import com.task.management.base.BaseResponse;
import com.task.management.profile.datamapper.UserProfileDataMapper;
import com.task.management.profile.model.Role;
import com.task.management.profile.model.UserProfile;
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
    UserProfileDataMapper userProfileDataMapper;
    @Autowired
    private ResponseHttpStatus responseHttpStatus;
    @GetMapping(value = "/welcome")
    public ResponseEntity<String> welcome() {
        JSONObject jsonObject= new JSONObject();
        jsonObject.put("result","success");
        return new ResponseEntity<String>(jsonObject.toJSONString(), HttpStatus.OK);
    }
    @PostMapping(value = "/createuser")
    public BaseResponse createNewProfile(@Valid @RequestBody UserProfile userProfile, BindingResult bindingResult) {
        List<String> errorMessages = new ArrayList<>();
        List<UserProfile > userProfiles = userProfiles();
        if (!bindingResult.hasErrors() ) {
            boolean unique = true;
            for (UserProfile user: userProfiles) {
                if (userProfile.getEmail().equalsIgnoreCase(user.getEmail())) {
                    userProfile.setError(true);
                    errorMessages.add("Email already registered");
                    userProfile.setErrorMsg(errorMessages);
                    unique = false;
                }
            }
            if (unique) {
                userProfile.setRole(Role.USER);
                userProfileDataMapper.save(userProfile);
                BaseResponse response = responseHttpStatus.getBaseResponseForHttpStatusOK("Profile created successfully");
                return response;
            }
        }
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error:errors) {
            errorMessages.add("@" +error.getField().toUpperCase() + ":" + error.getDefaultMessage());
        }
        BaseResponse baseResponse =responseHttpStatus.getBaseResponseForHttpStatusBADREQUEST(errorMessages);
        return baseResponse;
    }
    @GetMapping(value = "/findall")
    public List<UserProfile> userProfiles() {
        return userProfileDataMapper.findAll();
    }

}
