package com.upuphone.cloudplatform.authority.business.service.basic;

import com.upuphone.cloudplatform.authority.business.remote.QueryUserInfoByIdsService;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysSystemPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysSystemMapper;
import com.upuphone.cloudplatform.authority.vo.request.basic.BasicQueryRequest;
import com.upuphone.cloudplatform.authority.vo.response.system.SystemVo;
import com.upuphone.cloudplatform.authority.vo.response.systembasicinfo.BasicQueryResposne;
import com.upuphone.cloudplatform.common.component.BaseService;
import com.upuphone.cloudplatform.internal.admin.api.dto.request.QueryUserInfoReq;
import com.upuphone.cloudplatform.internal.admin.api.dto.response.InternalUserInfoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GetBasicInfoService extends BaseService<BasicQueryRequest, BasicQueryResposne> {

    @Autowired
    QueryUserInfoByIdsService queryUserInfoByIdsService;

    @Autowired
    private SysSystemMapper sysSystemMapper;

    @Override
    protected void validate(BasicQueryRequest basicQueryRequest) {

    }

    @Override
    protected BasicQueryResposne processCore(BasicQueryRequest basicQueryRequest) throws Exception {
        //拿项目信息
        SysSystemPo sysSystemPo = sysSystemMapper.selectOwnerIdById(basicQueryRequest.getSystemId());
        SystemVo systemVo = new SystemVo();
        systemVo.setId(basicQueryRequest.getSystemId());
        systemVo.setCode(sysSystemPo.getCode());
        systemVo.setName(sysSystemPo.getName());
        systemVo.setDescription(sysSystemPo.getDescription());
        systemVo.setOwnerUserId(sysSystemPo.getOwnerUserId());

        List<String>ownerIdList = new ArrayList<>();
        ownerIdList.add(sysSystemPo.getOwnerUserId());
        QueryUserInfoReq queryUserInfoReq = new QueryUserInfoReq();
        queryUserInfoReq.setIdList(ownerIdList);

        List<InternalUserInfoRes> processList = queryUserInfoByIdsService.process(queryUserInfoReq);
        HashMap idNameMap = new HashMap();
        for (InternalUserInfoRes internalUserInfoRes : processList) {
            idNameMap.put(internalUserInfoRes.getOperator(), internalUserInfoRes.getOperatorName());
        }

        Object ownerName = idNameMap.getOrDefault(systemVo.getOwnerUserId(), "null");
        systemVo.setOwnerName(ownerName.toString());

        BasicQueryResposne basicQueryResposne = new BasicQueryResposne();
        basicQueryResposne.setSystemVo(systemVo);
        return basicQueryResposne;
    }
}
