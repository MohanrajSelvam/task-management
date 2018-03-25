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
import java.util.List;

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
        List<UserProfile > userProfiles = userProfiles();
        boolean unique = true;
        for (UserProfile user: userProfiles) {
            if (userProfile.getEmail().equalsIgnoreCase(user.getEmail())) {
                userProfile.setError(true);
                userProfile.setUser_id(null);
                userProfile.setErrorMsg("Email already registered");
                unique = false;
            }
        }
        if (unique) {
            return userProfileDataMapper.saveAndFlush(userProfile);
        }
        return userProfile;
    }
    @GetMapping(value = "/findall")
    public List<UserProfile> userProfiles() {
        return userProfileDataMapper.findAll();
    }
}
