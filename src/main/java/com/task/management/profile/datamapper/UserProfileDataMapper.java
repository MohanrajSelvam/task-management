package com.task.management.profile.datamapper;

import com.task.management.profile.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileDataMapper extends JpaRepository<UserProfile,Long> {
}
