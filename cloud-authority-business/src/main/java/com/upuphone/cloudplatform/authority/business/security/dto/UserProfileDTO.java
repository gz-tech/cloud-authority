package com.upuphone.cloudplatform.authority.business.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserProfileDTO {

    private UserAttributeDTO attributes;

    @JsonProperty("id")
    private String uid;

}
