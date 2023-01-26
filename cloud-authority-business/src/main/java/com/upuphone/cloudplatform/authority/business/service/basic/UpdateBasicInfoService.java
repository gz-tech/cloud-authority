package com.upuphone.cloudplatform.authority.business.service.basic;

import com.google.common.primitives.Longs;
import com.upuphone.cloudplatform.authority.business.remote.QueryUserInfoByIdsService;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysSystemPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRoleUserMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysRoleUserMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysSystemMapper;
import com.upuphone.cloudplatform.authority.vo.request.basic.BasicUpdateRequest;
import com.upuphone.cloudplatform.authority.vo.response.system.SystemVo;
import com.upuphone.cloudplatform.authority.vo.response.systembasicinfo.BasicUpdateResposne;
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
import java.util.HashMap;
import java.util.List;

@Service
public class UpdateBasicInfoService extends BaseService<BasicUpdateRequest, BasicUpdateResposne> {

    @Autowired
    private InternalUserClientApi internalUserClientApi;

    @Autowired
    QueryUserInfoByIdsService queryUserInfoByIdsService;

    @Autowired
    private SysSystemMapper sysSystemMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private BizRoleUserMapper bizRoleUserMapper;

    @Override
    protected void validate(BasicUpdateRequest basicUpdateRequest) {

    }

    @Override
    protected BasicUpdateResposne processCore(BasicUpdateRequest basicUpdateRequest) throws Exception {
        //拿项目信息
        SysSystemPo sysSystemPo = sysSystemMapper.selectOwnerIdById(basicUpdateRequest.getSystemId());
        if (sysSystemPo == null) {
            throw new BusinessException(CommonErrorCode.PARAM_ERROR, "system not exist");
        }
        SysSystemPo sysSystemPoUpdater = new SysSystemPo();
        sysSystemPoUpdater.setId(Longs.tryParse(basicUpdateRequest.getSystemId()));
        sysSystemPoUpdater.setDescription(basicUpdateRequest.getSystemDescription());
        sysSystemMapper.updateById(sysSystemPoUpdater);

        SystemVo systemVo = new SystemVo();
        systemVo.setId(basicUpdateRequest.getSystemId());
        systemVo.setDescription(basicUpdateRequest.getSystemDescription());
        systemVo.setName(sysSystemPo.getName());
        systemVo.setOwnerUserId(sysSystemPo.getOwnerUserId());
        systemVo.setCode(sysSystemPo.getCode());

        List<String>ownerIdList = new ArrayList<>();
        ownerIdList.add(sysSystemPo.getOwnerUserId());
        QueryUserInfoReq queryUserInfoReq = new QueryUserInfoReq();
        queryUserInfoReq.setIdList(ownerIdList);
        List<InternalUserInfoRes> processList = queryUserInfoByIdsService.process(queryUserInfoReq);
        HashMap idNameMap = new HashMap();
        for (InternalUserInfoRes internalUserInfoRes : processList) {
            idNameMap.put(internalUserInfoRes.getOperator(), internalUserInfoRes.getOperatorName());
        }

        Object ownerName = idNameMap.getOrDefault(sysSystemPo.getOwnerUserId(), "null");
        systemVo.setOwnerName(ownerName.toString());
        BasicUpdateResposne basicUpdateResposne = new BasicUpdateResposne();
        basicUpdateResposne.setSystemVo(systemVo);
        return basicUpdateResposne;
    }
}
