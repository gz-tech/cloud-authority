package com.upuphone.cloudplatform.authority.business.service.user;

import com.upuphone.cloudplatform.authority.business.converter.BizPermissionConverter;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizPermissionMapper;
import com.upuphone.cloudplatform.authority.mybatis.po.bizuser.BizUserPermissionDetailPo;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserPermissionListRequest;
import com.upuphone.cloudplatform.authority.vo.response.user.BizUserPermissionDetailVo;
import com.upuphone.cloudplatform.authority.vo.response.user.BizUserPermissionListResponse;
import com.upuphone.cloudplatform.common.component.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BizUserPermissionListService extends BaseService<BizUserPermissionListRequest, BizUserPermissionListResponse> {

    @Autowired
    private BizPermissionMapper bizPermissionMapper;

    @Override
    protected void validate(BizUserPermissionListRequest bizUserPermissionListRequest) {

    }

    @Override
    protected BizUserPermissionListResponse processCore(BizUserPermissionListRequest bizUserPermissionListRequest) throws Exception {
        String userId = bizUserPermissionListRequest.getId();
        Long systemId = Long.valueOf(bizUserPermissionListRequest.getSystemId());
        List<BizUserPermissionDetailPo> poList = bizPermissionMapper.getBizUserPermissionDetailsByUserIdAndSystemId(userId, systemId);
        List<BizUserPermissionDetailVo> voList = BizPermissionConverter.INSTANCE.bizUserPermissionDetailPoList2VoList(poList);
        BizUserPermissionListResponse response = new BizUserPermissionListResponse();
        response.setList(voList);
        return response;
    }
}
