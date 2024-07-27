package com.group.samrt.um.domain.uml;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
@Data
@Entity
@Table(name = "user")
public class AdminUser {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="identifier")
    private String identifier;
    @Column(name="password")
    private String password;
    @Column(name="fullname")
    private String fullname;
    @Column(name="username")
    private String username;
    @Column(name="address")
    private String address;
    @Column(name="email")
    private String email;
    @Column(name="phone")
    private String phone;
    @Column(name="role")
    private String role;
    @Column(name="repre_fullname")
    private String repreFullName;
    @Column(name="company")
    private String company;
    @Column(name="company_address")
    private String companyAddress;
    @Column(name="company_phone")
    private String companyPhone;
    @Column(name="created_by")
    private String createdBy;
    @Column(name="created_dt")
    private Instant createdDt;
    @Column(name="updated_by")
    private String updatedBy;
    @Column(name="updated_dt")
    private Instant updatedDt;
    @Column(name="status")
    private String status;

}
