package com.upuphone.cloudplatform.authority.business.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserAttributeDTO {

    @JsonProperty("account_no")
    private String accountNo;
    @JsonProperty("token_expired")
    private String tokenExpired;
    @JsonProperty("token_gtime")
    private Long tokenGenerateTime;
    @JsonProperty("user_detail_info")
    private UserDetailInfoDTO userDetailInfo;

}
