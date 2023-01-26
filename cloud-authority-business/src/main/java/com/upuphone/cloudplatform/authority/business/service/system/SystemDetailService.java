package com.upuphone.cloudplatform.authority.business.service.system;

import com.upuphone.cloudplatform.authority.business.remote.QueryUserInfoByIdsService;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysSystemPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysSystemMapper;
import com.upuphone.cloudplatform.authority.vo.response.system.SystemVo;
import com.upuphone.cloudplatform.common.component.BaseService;
import com.upuphone.cloudplatform.internal.admin.api.dto.request.QueryUserInfoReq;
import com.upuphone.cloudplatform.internal.admin.api.dto.response.InternalUserInfoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Classname SystemDetailService
 * @Description
 * @Date 2022/3/25 4:48 下午
 * @Created by gz-d
 */
@Service
public class SystemDetailService extends BaseService<String, SystemVo> {

    @Autowired
    QueryUserInfoByIdsService queryUserInfoByIdsService;

    @Autowired
    private SysSystemMapper sysSystemMapper;

    @Override
    protected void validate(String s) {

    }

    @Override
    protected SystemVo processCore(String s) throws Exception {
        SysSystemPo sysSystemPo = sysSystemMapper.selectOwnerIdById(s);
        SystemVo systemVo = new SystemVo();
        systemVo.setId(s);
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

        return systemVo;
    }
}
