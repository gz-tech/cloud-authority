package com.upuphone.cloudplatform.authority.business.service.role;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.upuphone.cloudplatform.authority.business.converter.BizRoleConverter;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizRolePo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRoleMapper;
import com.upuphone.cloudplatform.authority.vo.request.role.BizRoleListRequest;
import com.upuphone.cloudplatform.authority.vo.response.role.BizRoleListResponse;
import com.upuphone.cloudplatform.authority.vo.response.role.RoleDetailVo;
import com.upuphone.cloudplatform.common.component.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BizRoleListService extends BaseService<BizRoleListRequest, BizRoleListResponse> {

    @Autowired
    private BizRoleMapper bizRoleMapper;

    @Override
    protected void validate(BizRoleListRequest bizRoleListRequest) {

    }

    @Override
    protected BizRoleListResponse processCore(BizRoleListRequest soaRequest) throws Exception {
        List<BizRolePo> poList = bizRoleMapper.selectList(Wrappers.<BizRolePo>lambdaQuery()
                .eq(BizRolePo::getSystemId, Long.valueOf(soaRequest.getSystemId()))
                .like(StringUtils.isNotBlank(soaRequest.getCode()), BizRolePo::getCode, soaRequest.getCode())
                .like(StringUtils.isNotBlank(soaRequest.getDescription()), BizRolePo::getDescription, soaRequest.getDescription()));
        List<RoleDetailVo> voList = BizRoleConverter.INSTANCE.bizRolePoList2DetailVoList(poList);
        BizRoleListResponse response = new BizRoleListResponse();
        response.setList(voList);
        return response;
    }
}
