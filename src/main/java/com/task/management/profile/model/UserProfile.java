package com.task.management.profile.model;

import com.task.management.task.model.Task;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long user_id;
    @Column(name="email" ,unique = true)
    @NotEmpty(message="Email is mandatory")
    private String email;
    @Column(name="password")
    @NotEmpty(message="Password is mandatory")
    private String password;
    @NotEmpty(message="First Name is mandatory")
    @Column(name="first_name")
    private String firstName;
    @NotEmpty(message="Last Name is mandatory")
    @Column(name="lastname")
    private String lastName;
    @Column(name="mobile")
    @Size(min = 10)
    @NotEmpty(message="Mobile is mandatory")
    private String mobile;
    @Column(name="admin_status")
    private String isAdmin;
    @OneToMany(mappedBy = "userProfile"  )
    private List<Task> tasks;

    public long getUser_id() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "user_id=" + user_id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", isAdmin='" + isAdmin + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}