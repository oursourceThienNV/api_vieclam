package com.group.samrt.um.domain.dto.uml;

import com.group.samrt.um.domain.uml.AdminUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDTO {
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

    public UserDetailDTO(AdminUser adminUser) {
        this.id = adminUser.getId();
        this.identifier = adminUser.getIdentifier();
        this.password = adminUser.getPassword();
        this.fullname = adminUser.getFullname();
        this.username = adminUser.getUsername();
        this.address = adminUser.getAddress();
        this.email = adminUser.getEmail();
        this.phone = adminUser.getPhone();
        this.role = adminUser.getRole();
        this.repreFullName = adminUser.getRepreFullName();
        this.company = adminUser.getCompany();
        this.companyPhone = adminUser.getCompanyPhone();
        this.companyAddress = adminUser.getCompanyAddress();
        this.createdBy = adminUser.getCreatedBy();
        this.createdDt = adminUser.getCreatedDt();
        this.updatedBy = adminUser.getUpdatedBy();
        this.updatedDt = adminUser.getUpdatedDt();
        this.status = adminUser.getStatus();
    }
}
