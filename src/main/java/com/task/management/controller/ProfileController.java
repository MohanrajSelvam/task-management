package com.task.management.controller;

import com.task.management.profile.datamapper.UserProfileDataMapper;
import com.task.management.profile.model.UserProfile;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProfileController {
    @Autowired
    UserProfileDataMapper userProfileDataMapper;

    @GetMapping(value = "/welcome")
    public ResponseEntity<String> welcome() {
        JSONObject jsonObject= new JSONObject();
        jsonObject.put("result","success");
        return new ResponseEntity<String>(jsonObject.toJSONString(), HttpStatus.OK);
    }
    @PostMapping(value = "/createuser")
    public UserProfile createNewProfile(@Valid @RequestBody UserProfile userProfile,BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return userProfileDataMapper.saveAndFlush(userProfile);
        }
        return null;
    }
}
