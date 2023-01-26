package com.upuphone.cloudplatform.authority.business.service.open.rbac;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.base.Strings;
import com.upuphone.cloudplatform.authority.api.vo.errorcode.AuthSdkErrorCode;
import com.upuphone.cloudplatform.authority.api.vo.request.CheckAllowRequest;
import com.upuphone.cloudplatform.authority.api.vo.response.CheckAllowResponse;
import com.upuphone.cloudplatform.authority.business.service.open.rbac.model.SystemInfoVo;
import com.upuphone.cloudplatform.authority.business.service.open.rbac.util.ContextUtil;
import com.upuphone.cloudplatform.authority.common.constants.BizActionTypeEnum;
import com.upuphone.cloudplatform.authority.common.constants.BizResourceTypeEnum;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizPermissionPo;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizResourcePo;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizRolePermissionPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizPermissionMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizResourceMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRolePermissionMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRoleUserMapper;
import com.upuphone.cloudplatform.common.component.BaseService;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;
import com.upuphone.cloudplatform.common.utils.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckAllowService extends BaseService<CheckAllowRequest, CheckAllowResponse> {

    @Autowired
    private BizResourceMapper bizResourceMapper;

    @Autowired
    private BizPermissionMapper bizPermissionMapper;

    @Autowired
    private BizRoleUserMapper bizRoleUserMapper;

    @Autowired
    private BizRolePermissionMapper bizRolePermissionMapper;


    @Override
    protected void validate(CheckAllowRequest request) {
        BizResourceTypeEnum resourceType = BizResourceTypeEnum
                .getByTypeStr(request.getResourceRequest().getResourceType());
        if (resourceType == null) {
            throw new BusinessException(CommonErrorCode.PARAM_ERROR, "resource type no found");
        }

        if(Strings.isNullOrEmpty(request.getResourceRequest().getCode())
                && Strings.isNullOrEmpty(request.getResourceRequest().getResourceValue())){
            throw new BusinessException(CommonErrorCode.PARAM_ERROR, "code and value cannot both be null ");
        }
    }

    @Override
    protected CheckAllowResponse processCore(CheckAllowRequest request) throws Exception {
        SystemInfoVo systemInfoVo = ContextUtil.getSystemInfoVo();
        if (systemInfoVo == null) {
            throw new BusinessException(AuthSdkErrorCode.NOT_PRIVILEGE);
        }

        BizPermissionPo bizPermissionPo = this.getPermission(request);

        List<Long> roleIds = bizRoleUserMapper.getRoleIdsByUserUid(request.getUserId(), systemInfoVo.getSystemId());

        List<BizRolePermissionPo> rolePermissionPos = bizRolePermissionMapper.selectList(Wrappers.<BizRolePermissionPo>lambdaQuery()
                .in(BizRolePermissionPo::getRoleId, roleIds)
                .eq(BizRolePermissionPo::getPermissionId, bizPermissionPo.getId()));

        CheckAllowResponse result = new CheckAllowResponse();
        result.setAllow(true);
        if (!ListUtil.any(rolePermissionPos)) {
            result.setAllow(false);
        }

        return result;
    }

    private BizPermissionPo getPermission(CheckAllowRequest request) {
        BizResourceTypeEnum resourceType = BizResourceTypeEnum
                .getByTypeStr(request.getResourceRequest().getResourceType());
        List<BizResourcePo> resourcePos = bizResourceMapper.selectList(Wrappers.<BizResourcePo>lambdaQuery()
                .eq(BizResourcePo::getResType, resourceType.getResType())
                .eq(BizResourcePo::getResType, resourceType.getResType())
                .eq(BizResourcePo::getSystemId, ContextUtil.getSystemInfoVo().getSystemId())
                .and(wrapper ->
                        wrapper.eq(BizResourcePo::getCode, request.getResourceRequest().getCode())
                                .or()
                                .eq(BizResourcePo::getPath, request.getResourceRequest().getResourceValue())));

        if (!ListUtil.any(resourcePos)) {
            throw new BusinessException(AuthSdkErrorCode.RESOURECE_NO_FOUND);
        }
        BizResourcePo resourcePo = resourcePos.get(0);

        BizActionTypeEnum actionType = this.getActionType(request.getAction());

        List<BizPermissionPo> bizPermissionPos = bizPermissionMapper.selectList(Wrappers.<BizPermissionPo>lambdaQuery()
                .eq(BizPermissionPo::getResId, resourcePo.getId())
                .eq(BizPermissionPo::getAction, actionType.getIntType()));

        if (!ListUtil.any(bizPermissionPos)) {
            throw new BusinessException(AuthSdkErrorCode.RESOURECE_NO_FOUND);
        }
        return bizPermissionPos.get(0);
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
