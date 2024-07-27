package com.group.samrt.um.domain.dto.uml;

import com.group.samrt.um.domain.uml.AdminUser;
import lombok.Data;

import java.time.Instant;

@Data
public class UserAdminDetailDTO {
    private Long id;
    private String username;
    private String name;
    private String avatar;
    private String phoneNumber;
    private byte status;
    private Instant createdAt;
    private Instant updatedAt;
    public UserAdminDetailDTO(){

    }

    public UserAdminDetailDTO(Long id, String username, String name, String avatar, String phoneNumber, byte status, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.avatar = avatar;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserAdminDetailDTO(AdminUser adminUser) {

    }
}
