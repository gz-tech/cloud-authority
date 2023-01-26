package com.upuphone.cloudplatform.authority.business.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.google.common.base.MoreObjects;
import com.upuphone.cloudplatform.authority.business.remote.QueryUserInfoByIdsService;
import com.upuphone.cloudplatform.authority.business.service.basic.util.SystemConvertUtil;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysSystemPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysRoleUserMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysSystemMapper;
import com.upuphone.cloudplatform.authority.vo.request.system.SystemListRequest;
import com.upuphone.cloudplatform.authority.vo.response.system.SystemVo;
import com.upuphone.cloudplatform.common.component.BaseService;
import com.upuphone.cloudplatform.internal.admin.api.dto.request.QueryUserInfoReq;
import com.upuphone.cloudplatform.internal.admin.api.dto.response.InternalUserInfoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname SystemListService
 * @Description
 * @Date 2022/3/25 4:48 下午
 * @Created by gz-d
 */
@Service
public class SystemListService extends BaseService<SystemListRequest, PageDTO<SystemVo>> {

    @Autowired
    QueryUserInfoByIdsService queryUserInfoByIdsService;

    @Autowired
    private SysSystemMapper sysSystemMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    protected void validate(SystemListRequest systemListRequest) {

    }

    @Override
    protected PageDTO<SystemVo> processCore(SystemListRequest request) throws Exception {
        List<SysSystemPo> sysSystemPoList = sysSystemMapper.selectByCodeAndName(request.getCode(), request.getName());
        sysSystemPoList = MoreObjects.firstNonNull(sysSystemPoList, new ArrayList<>(0));
        List<SystemVo> systemVos  = sysSystemPoList.stream().map(sysSystemPo ->
                SystemConvertUtil.convertFromPo(sysSystemPo)).collect(Collectors.toList());
        List<String>ownerIdList = new ArrayList<>();
        for (SysSystemPo sysSystemPo:sysSystemPoList) {
            ownerIdList.add(sysSystemPo.getOwnerUserId());
        }

        QueryUserInfoReq queryUserInfoReq = new QueryUserInfoReq();
        queryUserInfoReq.setIdList(ownerIdList);
        List<InternalUserInfoRes> processList = queryUserInfoByIdsService.process(queryUserInfoReq);
        HashMap idNameMap = new HashMap();
        for (InternalUserInfoRes internalUserInfoRes : processList) {
            idNameMap.put(internalUserInfoRes.getOperator(), internalUserInfoRes.getOperatorName());
        }

        for (SystemVo systemVo : systemVos) {
            Object ownerName = idNameMap.getOrDefault(systemVo.getOwnerUserId(), "null");
            systemVo.setOwnerName(ownerName.toString());
        }
        Page<SystemVo> page = Page.of(request.getPageNum(), request.getPageSize());
        PageDTO<SystemVo> pageDTO = new PageDTO<>(
                page.getCurrent(),
                page.getSize(),
                page.getTotal()
        );
        pageDTO.setRecords(systemVos);
        return pageDTO;
    }
}
