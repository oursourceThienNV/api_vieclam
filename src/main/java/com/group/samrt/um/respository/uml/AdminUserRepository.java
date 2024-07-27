package com.group.samrt.um.respository.uml;

import com.group.samrt.um.domain.uml.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser,String>, JpaSpecificationExecutor<AdminUser> {
    AdminUser findByUsername(String username);
    Optional<AdminUser> findByUsernameAndStatus(String username,String status);
    Optional<AdminUser> findByUsernameOrIdentifier(String username, String identifier);

    Optional<AdminUser> findById(Long id);
}
