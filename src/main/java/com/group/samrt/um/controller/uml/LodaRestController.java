package com.group.samrt.um.controller.uml;

import com.group.samrt.um.client.Common.Constant;
import com.group.samrt.um.client.client.request.QueryBaseRequestById;
import com.group.samrt.um.client.client.request.uml.LoginRequest;
import com.group.samrt.um.client.client.request.uml.UmApplicationRequestSearch;
import com.group.samrt.um.client.client.request.uml.UserRegisterRequest;
import com.group.samrt.um.client.client.response.BaseDataResponse;
import com.group.samrt.um.client.client.response.uml.LoginResponse;
import com.group.samrt.um.client.client.response.uml.UserInfoResponse;
import com.group.samrt.um.client.client.response.uml.UserResponse;
import com.group.samrt.um.filter.JwtTokenProvider;
import com.group.samrt.um.service.uml.CustomUserDetail;
import com.group.samrt.um.service.uml.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
public class LodaRestController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Boolean check=userService.getByUserName(loginRequest.getUsername());
        if (!check){
            return new LoginResponse("");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }
    @PostMapping("/createUser")
    public ResponseEntity<BaseDataResponse<Boolean>> createUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        BaseDataResponse baseDataResponse=new BaseDataResponse();

        Boolean response = null;
        try {
            response = userService.createUserForAdmin(userRegisterRequest);
            baseDataResponse.setResponseCode(Constant.HTTP_ERROR.ERROR_SUCCESS);
            baseDataResponse.setResponseMessage("Luu Thanh Cong");
            baseDataResponse.setBody(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(baseDataResponse, HttpStatus.OK);
    }
    @PostMapping("/change-pass-word")
    public ResponseEntity<BaseDataResponse<Boolean>> updateUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        BaseDataResponse baseDataResponse=new BaseDataResponse();

        Boolean response = null;
        try {
            response = userService.updatePassWord(userRegisterRequest);
            baseDataResponse.setResponseCode(Constant.HTTP_ERROR.ERROR_SUCCESS);
            baseDataResponse.setResponseMessage("Luu Thanh Cong");
            baseDataResponse.setBody(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(baseDataResponse, HttpStatus.OK);
    }
    @PostMapping("/delete")
    public ResponseEntity<BaseDataResponse<Boolean>> createUser(@Valid @RequestBody QueryBaseRequestById userRegisterRequest) {
        BaseDataResponse baseDataResponse=new BaseDataResponse();

        Boolean response = null;
        try {
            response = userService.deleteUser(userRegisterRequest);
            baseDataResponse.setResponseCode(Constant.HTTP_ERROR.ERROR_SUCCESS);
            baseDataResponse.setResponseMessage("Xóa thành công");
            baseDataResponse.setBody(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(baseDataResponse, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<BaseDataResponse<Boolean>> authenticateUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        BaseDataResponse baseDataResponse=new BaseDataResponse();

        Boolean response = null;
        try {
            response = userService.createUser(userRegisterRequest);
            baseDataResponse.setResponseCode(Constant.HTTP_ERROR.ERROR_SUCCESS);
            baseDataResponse.setResponseMessage("Luu Thanh Cong");
            baseDataResponse.setBody(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(baseDataResponse, HttpStatus.OK);
    }
    @PostMapping("/user/on-search")
    public ResponseEntity<BaseDataResponse<UserResponse>> onsearch(@Valid @RequestBody UmApplicationRequestSearch request){
        BaseDataResponse baseDataResponse = new BaseDataResponse();

        UserResponse response=userService.pageSearch(request);
        baseDataResponse.setResponseCode(Constant.HTTP_ERROR.ERROR_SUCCESS);
        baseDataResponse.setResponseMessage("Detail Student By Class");
        baseDataResponse.setBody(response);
        return new ResponseEntity<>(baseDataResponse, HttpStatus.OK);
    }
    @PostMapping("/user/get-user-info")
    public ResponseEntity<BaseDataResponse<UserInfoResponse>> userInfo(){
        BaseDataResponse baseDataResponse = new BaseDataResponse();

        UserInfoResponse response=userService.getUserInfo();
        baseDataResponse.setResponseCode(Constant.HTTP_ERROR.ERROR_SUCCESS);
        baseDataResponse.setResponseMessage("Detail Student By Class");
        baseDataResponse.setBody(response);
        return new ResponseEntity<>(baseDataResponse, HttpStatus.OK);
    }

}
