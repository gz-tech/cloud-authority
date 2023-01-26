package com.upuphone.cloudplatform.authority.business.service.user;

import com.upuphone.cloudplatform.authority.business.converter.BizRoleConverter;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRoleMapper;
import com.upuphone.cloudplatform.authority.mybatis.po.bizuser.BizUserRoleDetailPo;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserRoleListRequest;
import com.upuphone.cloudplatform.authority.vo.response.user.BizUserRoleListResponse;
import com.upuphone.cloudplatform.authority.vo.response.user.UserRoleDetailVo;
import com.upuphone.cloudplatform.common.component.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BizUserRoleListService extends BaseService<BizUserRoleListRequest, BizUserRoleListResponse> {

    @Autowired
    private BizRoleMapper bizRoleMapper;

    @Override
    protected void validate(BizUserRoleListRequest request) {

    }

    @Override
    protected BizUserRoleListResponse processCore(BizUserRoleListRequest soaRequest) throws Exception {
        String userId = soaRequest.getId();
        Long systemId = Long.valueOf(soaRequest.getSystemId());
        List<BizUserRoleDetailPo> detailPoList = bizRoleMapper.getBizUserRoleDetailsByUserIdAndSystemId(userId, systemId);
        List<UserRoleDetailVo> voList = BizRoleConverter.INSTANCE.bizUserRoleDetailPoList2VoList(detailPoList);
        BizUserRoleListResponse response = new BizUserRoleListResponse();
        response.setList(voList);
        return response;
    }
}
