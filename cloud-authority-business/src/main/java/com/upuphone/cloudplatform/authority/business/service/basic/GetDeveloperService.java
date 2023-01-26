package com.upuphone.cloudplatform.authority.business.service.basic;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.common.primitives.Longs;
import com.upuphone.cloudplatform.authority.business.remote.QueryUserInfoByIdsService;
import com.upuphone.cloudplatform.authority.business.service.basic.util.SystemRoleUserConvertUtil;
import com.upuphone.cloudplatform.authority.business.service.basic.util.SystemUserConvertUtil;
import com.upuphone.cloudplatform.authority.common.constants.SysRelationEnum;
import com.upuphone.cloudplatform.authority.common.utils.OrikaUtil;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysRoleUserPo;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysSystemPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysRoleUserMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysUserMapper;
import com.upuphone.cloudplatform.authority.vo.request.basic.DeveloperQueryRequest;
import com.upuphone.cloudplatform.authority.vo.response.DeveloperDetailVo;
import com.upuphone.cloudplatform.authority.vo.response.systembasicinfo.DeveloperQueryResposne;
import com.upuphone.cloudplatform.common.component.BaseService;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import com.upuphone.cloudplatform.internal.admin.api.InternalUserClientApi;
import com.upuphone.cloudplatform.internal.admin.api.dto.request.QueryUserInfoReq;
import com.upuphone.cloudplatform.internal.admin.api.dto.response.InternalUserInfoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetDeveloperService extends BaseService<DeveloperQueryRequest, DeveloperQueryResposne> {

    @Autowired
    QueryUserInfoByIdsService queryUserInfoByIdsService;

    @Autowired
    private InternalUserClientApi internalUserClientApi;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    protected void validate(DeveloperQueryRequest developerQueryRequest) {

    }

    @Override
    protected DeveloperQueryResposne processCore(DeveloperQueryRequest request) throws Exception {
        Long systemId = Long.valueOf(request.getSystemId());
        if (systemId == null) {
            throw new BusinessException(CommonErrorCode.PARAM_ERROR, "systemId error");
        }

        String operator = request.getOperator();
        List<SysRoleUserPo> sysRoleUserPos = sysRoleUserMapper.selectDevelopersBySystemIdAndOperator(systemId, operator);
        List<String>operatorList = new ArrayList<>();
        for (SysRoleUserPo sysRoleUserPo: sysRoleUserPos) {
            operator = sysRoleUserPo.getUserId();
            operatorList.add(operator);
        }

        QueryUserInfoReq queryUserInfoReq = new QueryUserInfoReq();
        queryUserInfoReq.setIdList(operatorList);
        CommonResponse<List<InternalUserInfoRes>> listCommonResponseList = internalUserClientApi.queryByIdList(queryUserInfoReq);
        List<DeveloperDetailVo>developers = new ArrayList<>();

        for (InternalUserInfoRes internalUserInfoRes:listCommonResponseList.getData()) {
            DeveloperDetailVo detailVo = new DeveloperDetailVo();
            detailVo.setId(internalUserInfoRes.getOperator());
            detailVo.setMobile(internalUserInfoRes.getMobile());
            detailVo.setEmail(internalUserInfoRes.getEmail());
            detailVo.setName(internalUserInfoRes.getOperatorName());
            detailVo.setUid(internalUserInfoRes.getOperator());
            developers.add(detailVo);
        }

        DeveloperQueryResposne response = new DeveloperQueryResposne();

        response.setDevelopers(developers);

        return response;
    }
}
