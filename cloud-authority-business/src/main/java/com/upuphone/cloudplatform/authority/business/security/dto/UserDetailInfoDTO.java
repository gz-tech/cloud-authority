package com.upuphone.cloudplatform.authority.business.security.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDetailInfoDTO {

    private List<UserJobDTO> jobs;

    private List<UserTypeDTO> userTypes;

    private List<UserOrgDTO> orgs;

}
