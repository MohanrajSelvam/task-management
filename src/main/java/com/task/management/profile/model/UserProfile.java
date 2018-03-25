package com.task.management.profile.model;

import com.task.management.base.BaseResponse;
import com.task.management.task.model.Task;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="email"))
public class UserProfile extends BaseResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    @Column(name = "email", unique = true, nullable = false)
    @NotEmpty(message = "Email is mandatory")
    private String email;
    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Password is mandatory")
    private String password;
    @NotEmpty(message = "First Name is mandatory")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @NotEmpty(message = "Last Name is mandatory")
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "mobile", nullable = false)
    @Size(min = 10, max = 10)
    @NotEmpty(message = "Mobile is mandatory")
    private String mobile;
    @Column(name = "user_role", nullable = false)
    private Role role;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}