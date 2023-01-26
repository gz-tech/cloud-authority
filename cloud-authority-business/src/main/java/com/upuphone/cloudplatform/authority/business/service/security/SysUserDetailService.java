package com.upuphone.cloudplatform.authority.business.service.security;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.upuphone.cloudplatform.authority.business.converter.SysPermissionConverter;
import com.upuphone.cloudplatform.authority.business.remote.QueryUserInfoByIdsService;
import com.upuphone.cloudplatform.authority.business.repo.ISysPermissionService;
import com.upuphone.cloudplatform.authority.common.constants.SysPermissionTypeEnum;
import com.upuphone.cloudplatform.authority.common.constants.SysRoleEnum;
import com.upuphone.cloudplatform.authority.errorcode.AuthErrorCode;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysPermissionPo;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysRoleUserPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysRoleUserMapper;
import com.upuphone.cloudplatform.authority.vo.response.security.SysUserDetailVo;
import com.upuphone.cloudplatform.common.context.RequestContext;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.internal.admin.api.dto.request.QueryUserInfoReq;
import com.upuphone.cloudplatform.internal.admin.api.dto.response.InternalUserInfoRes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SysUserDetailService {
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;
    @Autowired
    private ISysPermissionService sysPermissionService;
    @Autowired
    private QueryUserInfoByIdsService queryUserInfoByIdsService;

    public SysUserDetailVo process() {
        String userId = RequestContext.getOperator();
        QueryUserInfoReq queryUserInfoReq = new QueryUserInfoReq();
        queryUserInfoReq.setIdList(Arrays.asList(userId));
        List<InternalUserInfoRes> userInfoResList = queryUserInfoByIdsService.process(queryUserInfoReq);
        if (CollectionUtils.isEmpty(userInfoResList) || null == userInfoResList.get(0)) {
            throw new BusinessException(AuthErrorCode.USER_NOT_FOUND);
        }
        InternalUserInfoRes user = userInfoResList.get(0);
        List<SysPermissionPo> permissionPoList =
                sysPermissionService.getPermissions(userId, null, SysPermissionTypeEnum.ROUTE, SysPermissionTypeEnum.MENU);
        List<Object> roleUserPoList = sysRoleUserMapper.selectObjs(Wrappers.<SysRoleUserPo>lambdaQuery()
                .select(SysRoleUserPo::getRoleType)
                .eq(SysRoleUserPo::getUserId, userId));
        Set<String> roles = CollectionUtils.isNotEmpty(roleUserPoList)
                ? roleUserPoList.stream().map(roleType -> SysRoleEnum.getSysRoleByType((Integer) roleType).name()).collect(Collectors.toSet())
                : null;
        SysUserDetailVo vo = new SysUserDetailVo();
        vo.setId(user.getOperator());
        vo.setUid(user.getOperator());
        vo.setName(user.getOperatorName());
        vo.setMobile(user.getMobile());
        vo.setEmail(user.getEmail());
        vo.setPermissionList(new HashSet<>(SysPermissionConverter.INSTANCE.poList2VoList(permissionPoList)));
        vo.setRoles(roles);
        return vo;
    }

}
