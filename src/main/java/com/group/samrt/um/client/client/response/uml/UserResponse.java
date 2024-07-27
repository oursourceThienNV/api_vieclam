package com.group.samrt.um.client.client.response.uml;

import com.group.samrt.um.client.Common.Util.OptimizedPage;
import com.group.samrt.um.domain.dto.uml.UserDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private OptimizedPage<UserDetailDTO> page;

    public UserResponse() {

    }
}
