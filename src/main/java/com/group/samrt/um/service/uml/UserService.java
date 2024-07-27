package com.group.samrt.um.service.uml;

import com.group.samrt.um.client.Common.Constant;
import com.group.samrt.um.client.Common.Util.OptimizedPage;
import com.group.samrt.um.client.client.request.QueryBaseRequestById;
import com.group.samrt.um.client.client.request.uml.ChangePassWordRequest;
import com.group.samrt.um.client.client.request.uml.UmApplicationRequestSearch;
import com.group.samrt.um.client.client.request.uml.UserRegisterRequest;
import com.group.samrt.um.client.client.response.uml.UserInfoResponse;
import com.group.samrt.um.client.client.response.uml.UserResponse;
import com.group.samrt.um.domain.dto.uml.UserAdminDetailDTO;
import com.group.samrt.um.domain.dto.uml.UserDetailDTO;
import com.group.samrt.um.domain.uml.AdminUser;
import com.group.samrt.um.domain.uml.AdminUser_;
import com.group.samrt.um.respository.uml.AdminUserRepository;
import io.github.jhipster.service.QueryService;
import io.github.jhipster.service.filter.StringFilter;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class UserService extends QueryService<AdminUser> implements UserDetailsService {

    @Autowired
    private AdminUserRepository userRepository;
    @Autowired
    private AdminUserRepository adminUserRepository;


    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        AdminUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetail(user);
    }
    public AdminUser getUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        AdminUser user = userRepository.findByUsername(username);
        return user;
    }

    @Transactional(readOnly = false)
    public Boolean changePasswordByUser(ChangePassWordRequest changePassWordRequest){
        // Lấy thông tin user
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AdminUser user = adminUserRepository.findByUsername(userDetails.getUsername());

        String hashed = BCrypt.hashpw(changePassWordRequest.getOldPassword(), BCrypt.gensalt());

        if (user != null && BCrypt.checkpw(changePassWordRequest.getOldPassword(),user.getPassword()) && changePassWordRequest.getNewPassword().equals(changePassWordRequest.getRePassword())){
            user.setPassword(new BCryptPasswordEncoder().encode(changePassWordRequest.getNewPassword()));
            adminUserRepository.save(user);
            return true;
        }else {
            return false;
        }
    }

    @Transactional(readOnly = false)
    public UserAdminDetailDTO getUserDetail(){
        // Lấy thông tin user
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AdminUser user = adminUserRepository.findByUsername(userDetails.getUsername());

        ModelMapper modelMapper = new ModelMapper();
        UserAdminDetailDTO userAdminDetailDTO = modelMapper.map(user,UserAdminDetailDTO.class);

        // Lay Role tu user

        return userAdminDetailDTO;
    }
    @Transactional(readOnly = false)
    public Boolean createUser(UserRegisterRequest user){
        Optional<AdminUser> check = adminUserRepository.findByUsernameOrIdentifier(user.getUsername(),user.getIdentifier());
        String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hash);
        if(!check.isPresent()){
            ModelMapper modelMapper = new ModelMapper();
            AdminUser adminUser = modelMapper.map(user,AdminUser.class);
            adminUser.setCreatedBy(user.getIdentifier());
            adminUser.setCreatedDt(Instant.now());
            adminUser.setRole(Constant.ROLE_USER.INVESTOR);
            adminUser.setStatus(Constant.STATUS_USER.PENDING);
            adminUser.setUsername(adminUser.getIdentifier());
            adminUserRepository.save(adminUser);
            return true;
        }

        // Lay Role tu user

        return false;
    }
    public Boolean updatePassWord(UserRegisterRequest user){

            Optional<AdminUser> check = adminUserRepository.findByUsernameOrIdentifier(user.getUsername(),user.getUsername());
            if(check.isPresent()){
                String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
                check.get().setPassword(hash);
                adminUserRepository.save(check.get());
                return true;
            }
            return false;


        // Lay Role tu user

    }
    public Boolean createUserForAdmin(UserRegisterRequest user){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AdminUser session = adminUserRepository.findByUsername(userDetails.getUsername());
        if (user.getId()!=null){
            Optional<AdminUser> check = adminUserRepository.findById(user.getId());
            if(check.isPresent()){
                check.get().setPhone(user.getPhone());
                check.get().setRole(user.getRole());
                check.get().setStatus(user.getStatus());
                check.get().setRepreFullName(user.getRepreFullName());
                check.get().setFullname(user.getFullname());
                check.get().setEmail(user.getEmail());
                check.get().setAddress(user.getAddress());
                check.get().setCompany(user.getCompany());
                check.get().setCompanyPhone(user.getCompanyPhone());
                check.get().setCompanyAddress(user.getCompanyAddress());
                check.get().setUpdatedBy(session.getUsername());
                check.get().setUpdatedDt(Instant.now());
                adminUserRepository.save(check.get());
                return true;
            }else {
                return false;
            }
        }
        Optional<AdminUser> check = adminUserRepository.findByUsernameOrIdentifier(user.getUsername(),user.getIdentifier());
        String hash = BCrypt.hashpw("123456aA@", BCrypt.gensalt());
        user.setPassword(hash);
        if(!check.isPresent()){
            ModelMapper modelMapper = new ModelMapper();
            AdminUser adminUser = modelMapper.map(user,AdminUser.class);
            adminUser.setCreatedBy(session.getUsername());
            adminUser.setCreatedDt(Instant.now());
            adminUser.setUpdatedBy(session.getUsername());
            adminUser.setUpdatedDt(Instant.now());
            adminUser.setUsername(user.getIdentifier());
            adminUser.setStatus(Constant.STATUS_USER.APPROVED);
            adminUserRepository.save(adminUser);
            return true;
        }

        // Lay Role tu user

        return false;
    }
    @Transactional(readOnly = false)
    public Boolean deleteUser(QueryBaseRequestById query){
        Optional<AdminUser> session = adminUserRepository.findById(query.getId());
        if(session.isPresent()){
            this.userRepository.delete(session.get());
            return true;
        }// Lay Role tu user
        return false;
    }

    public Boolean getByUserName(String username){
        Optional<AdminUser> check = adminUserRepository.findByUsernameAndStatus(username, Constant.STATUS_USER.APPROVED);
        if(check.isPresent()){
            return true;
        }
        return false;
    }
    @Transactional(readOnly = false)
    public List<UserDetailDTO> listUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AdminUser user = adminUserRepository.findByUsername(userDetails.getUsername());
        if(user==null)
            return null;
        if(!user.getRole().equals(Constant.ROLE_USER.ADMIN))
            return null;
        List<AdminUser> list=userRepository.findAll();

        List<UserDetailDTO> lts=new ArrayList<>();
        for(AdminUser um: list){
            Optional<UserDetailDTO> u =Optional.of(Optional.of(um)).map(Optional::get)
                    .map(
                            um2->
                            {
                                return um2;
                            }).map(UserDetailDTO::new);
            lts.add(u.get());
        }
        return lts;
    }
    @Transactional(readOnly = false)
    public UserInfoResponse getUserInfo(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfoResponse response=new UserInfoResponse();
        Optional<AdminUser> session = adminUserRepository.findByUsernameAndStatus(userDetails.getUsername(), Constant.STATUS_USER.APPROVED);
        if (session.isPresent()){
            UserDetailDTO userDetailDTO=session.map(UserDetailDTO::new).get();
            response.setUserInfo(userDetailDTO);
        }else {
            return null;
        }

        return response;
    }
    @Transactional(readOnly = true)
    public UserResponse pageSearch(UmApplicationRequestSearch request) throws ServiceException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AdminUser user = adminUserRepository.findByUsername(userDetails.getUsername());
        if(user==null)
            return null;
        if(!user.getRole().equals(Constant.ROLE_USER.ADMIN)){
            StringFilter userlogin=new StringFilter();
            userlogin.setEquals(userDetails.getUsername());
            request.setUsername(userlogin);
        }
        try {
            if (request == null)
                return null;

            if (request.getPageNumber() < Constant.PAGE.PAGE_NUMBER)
                request.setPageNumber(Constant.PAGE.PAGE_NUMBER);

            if (request.getPageSize() < 1)
                request.setPageSize(Constant.PAGE.PAGE_SIZE);
            Specification<AdminUser> specification = createSpecification(request);
            Sort sort = Sort.by(Sort.Order.desc("id"));
            Page<UserDetailDTO> queryResults = this.userRepository.findAll(
                    specification, PageRequest.of(request.getPageNumber(), request.getPageSize(), sort)
            ).map(UserDetailDTO::new);
            UserResponse response = new UserResponse();
            response.setPage(OptimizedPage.convert(queryResults));

            return response;
        } catch (Exception e) {
            throw e;
        }
    }
    protected Specification<AdminUser> createSpecification(UmApplicationRequestSearch criteria) {

        Specification<AdminUser> specification = Specification.where(null);
        if(criteria.getFullname()!=null){
            specification = specification.and(buildStringSpecification(criteria.getFullname(), AdminUser_.fullname));
        }
        if(criteria.getUsername()!=null){
            specification = specification.and(buildStringSpecification(criteria.getUsername(), AdminUser_.username));
        }
        if(criteria.getPhone()!=null){
            specification = specification.and(buildStringSpecification(criteria.getPhone(), AdminUser_.phone));
        }
        if(criteria.getRole()!=null){
            specification = specification.and(buildStringSpecification(criteria.getRole(), AdminUser_.role));
        }
        if(criteria.getStatus()!=null){
            specification = specification.and(buildStringSpecification(criteria.getStatus(), AdminUser_.status));
        }
        return specification;
    }


    

}
