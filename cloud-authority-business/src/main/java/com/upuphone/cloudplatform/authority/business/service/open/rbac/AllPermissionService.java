package com.upuphone.cloudplatform.authority.business.service.open.rbac;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.upuphone.cloudplatform.authority.api.vo.ResourceVo;
import com.upuphone.cloudplatform.authority.api.vo.errorcode.AuthSdkErrorCode;
import com.upuphone.cloudplatform.authority.api.vo.request.AllPermissionRequest;
import com.upuphone.cloudplatform.authority.api.vo.response.AllPermissionResponse;
import com.upuphone.cloudplatform.authority.common.constants.BizActionTypeEnum;
import com.upuphone.cloudplatform.authority.common.constants.BizResourceTypeEnum;
import com.upuphone.cloudplatform.authority.errorcode.AuthErrorCode;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizResourcePo;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysSystemPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizPermissionMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizResourceMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRoleUserMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysSystemMapper;
import com.upuphone.cloudplatform.common.component.BaseService;
import com.upuphone.cloudplatform.common.context.RequestContext;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;
import com.upuphone.cloudplatform.common.utils.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllPermissionService extends BaseService<AllPermissionRequest, AllPermissionResponse> {

    @Autowired
    private BizResourceMapper bizResourceMapper;

    @Autowired
    private BizPermissionMapper bizPermissionMapper;

    @Autowired
    private BizRoleUserMapper bizRoleUserMapper;
    @Autowired
    private SysSystemMapper sysSystemMapper;

    @Override
    protected void validate(AllPermissionRequest request) {
        BizResourceTypeEnum resourceType = BizResourceTypeEnum
                .getByTypeStr(request.getResourceType());
        if (resourceType == null) {
            throw new BusinessException(CommonErrorCode.PARAM_ERROR, "resource type no found");
        }
    }

    @Override
    protected AllPermissionResponse processCore(AllPermissionRequest request) throws Exception {
        List<SysSystemPo> sysSystemPos = sysSystemMapper.selectList(Wrappers.<SysSystemPo>lambdaQuery()
                .eq(SysSystemPo::getCode, request.getSystemCode()));
        if (!ListUtil.any(sysSystemPos)) {
            throw new BusinessException(AuthErrorCode.SYSTEM_NOT_FOUND);
        }
        List<Long> roleIds = bizRoleUserMapper.getRoleIdsByUserUid(RequestContext.getOperator(), sysSystemPos.get(0).getId());

        if (!ListUtil.any(roleIds)) {
            throw new BusinessException(AuthSdkErrorCode.PERMISSION_NO_FOUND);
        }

        BizActionTypeEnum actionType = this.getActionType(request.getAction());
        List<Long> resourceIds = bizPermissionMapper
                .getPermtResIdByRole(Sets.newHashSet(roleIds), actionType.getIntType());

        BizResourceTypeEnum resourceType = BizResourceTypeEnum
                .getByTypeStr(request.getResourceType());

        List<BizResourcePo> resources = null;
        if (!CollectionUtils.isEmpty(resourceIds)) {
            resources = bizResourceMapper.selectList(Wrappers.<BizResourcePo>lambdaQuery()
                    .eq(BizResourcePo::getSystemId, sysSystemPos.get(0).getId())
                    .eq(BizResourcePo::getResType, resourceType.getResType())
                    .in(BizResourcePo::getId, resourceIds));
        }
        resources = MoreObjects.firstNonNull(resources, new ArrayList<>(0));

        List<ResourceVo> resourceVos = resources.stream().map(resource -> {
            ResourceVo resourceVo = new ResourceVo();
            resourceVo.setCode(resource.getCode());
            resourceVo.setResourceType(BizResourceTypeEnum.getByTypeStr(request.getResourceType()).getTypeStr());
            resourceVo.setResourceValue(resource.getPath());
            resourceVo.setDescription(resource.getDescription());
            return resourceVo;
        }).collect(Collectors.toList());

        AllPermissionResponse result = new AllPermissionResponse();
        result.setResources(resourceVos);
        List<String> roles = bizRoleUserMapper.getRoleCodesByUidAndSystemId(RequestContext.getOperator(), sysSystemPos.get(0).getId());
        result.setRoles(roles);
        return result;
    }


    private BizActionTypeEnum getActionType(String action) {
        BizActionTypeEnum result = BizActionTypeEnum.QUERY;

        if (!Strings.isNullOrEmpty(action)) {

            BizActionTypeEnum actionTypeLocal = BizActionTypeEnum.getByTypeStr(action);
            if (actionTypeLocal != null) {
                result = actionTypeLocal;
            }
        }
        return result;
    }

}
