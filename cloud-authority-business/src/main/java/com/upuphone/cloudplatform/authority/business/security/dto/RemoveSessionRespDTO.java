package com.upuphone.cloudplatform.authority.business.security.dto;

import lombok.Data;

@Data
public class RemoveSessionRespDTO {

    private String errorCode;

    private String errorMsg;

    private String version;

    private Long timestamp;

}
