package com.task.management.service;

import com.task.management.profile.datamapper.UserProfileDataMapper;
import com.task.management.profile.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthenticationService extends UserProfile{
    @Autowired
    private UserProfileDataMapper userProfileDataMapper;

    protected  UserProfile userProfile = null;

    public void startLoginSession(UserProfile userProfile) {
        this.userProfile = userProfile;
        return;
    }

    public void closeLogedInSession() {
        this.userProfile = null;
        return;
    }

    public boolean checkAuthStatus() {
        if (null != this.userProfile) {
            return true;
        }
        else {
            return false;
        }
    }

    public UserProfile getLoggedInUser() {
        return this.userProfile;
    }

    public UserProfile auth(UserProfile userProfile) {
        List<UserProfile> userProfiles = userProfileDataMapper.findAll();
        for (UserProfile user: userProfiles) {
            if (userProfile.getEmail().trim().equalsIgnoreCase(user.getEmail().trim())
                    && userProfile.getPassword().trim().equalsIgnoreCase(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

}
