package com.upuphone.cloudplatform.authority.business.security.dto;

import lombok.Data;

@Data
public class CheckTokenRespDTO {

    private String errorCode;

    private String errorMsg;

    private String data;

}
