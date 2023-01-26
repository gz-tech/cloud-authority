package com.upuphone.cloudplatform.authority.controller;

import com.upuphone.cloudplatform.authority.api.SecurityClient;
import com.upuphone.cloudplatform.authority.business.security.SecurityUtil;
import com.upuphone.cloudplatform.authority.business.service.security.SysUserDetailService;
import com.upuphone.cloudplatform.authority.common.annotations.Authorization;
import com.upuphone.cloudplatform.authority.vo.response.security.SysUserDetailVo;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author hanzhumeng
 * Created: 2022/2/28
 */
@RestController
@Api(tags = "内部认证相关 API")
public class SecurityController implements SecurityClient {

    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private SysUserDetailService sysUserDetailService;

    @Authorization
    public CommonResponse<Void> logout() {
        securityUtil.logout();
        return CommonResponse.success();
    }

    @Authorization
    public CommonResponse<SysUserDetailVo> getUserDetails() {
        return CommonResponse.success(sysUserDetailService.process());
    }

    public String hello() {
        return "hello";
    }

}
