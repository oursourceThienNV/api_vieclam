package com.group.samrt.um.client.client.request.uml;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
@Data
@AllArgsConstructor
public class UserRegisterRequest {
    private Long id;
    private String identifier;
    private String password;
    private String fullname;
    private String username;
    private String address;
    private String email;
    private String phone;
    private String role;
    private String repreFullName;
    private String company;
    private String companyPhone;
    private String companyAddress;
    private String createdBy;
    private Instant createdDt;
    private String updatedBy;
    private Instant updatedDt;
    private String status;
}
