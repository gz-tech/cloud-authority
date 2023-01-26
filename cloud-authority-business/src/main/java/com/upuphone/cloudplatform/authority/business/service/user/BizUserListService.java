package com.upuphone.cloudplatform.authority.business.service.user;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.upuphone.cloudplatform.authority.business.converter.BizUserConverter;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizUserPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizUserMapper;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserListRequest;
import com.upuphone.cloudplatform.authority.vo.response.user.UserDetailVo;
import com.upuphone.cloudplatform.authority.vo.response.user.BizUserListResponse;
import com.upuphone.cloudplatform.common.component.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BizUserListService extends BaseService<BizUserListRequest, BizUserListResponse> {

    @Autowired
    private BizUserMapper bizUserMapper;

    @Override
    protected void validate(BizUserListRequest bizUserListRequest) {

    }

    @Override
    protected BizUserListResponse processCore(BizUserListRequest bizUserListRequest) throws Exception {
        List<BizUserPo> pos = bizUserMapper.selectList(Wrappers.<BizUserPo>lambdaQuery()
                .eq(BizUserPo::getSystemId, Long.valueOf(bizUserListRequest.getSystemId()))
                .like(StringUtils.isNotBlank(bizUserListRequest.getEmail()), BizUserPo::getEmail, bizUserListRequest.getEmail())
                .like(StringUtils.isNotBlank(bizUserListRequest.getMobile()), BizUserPo::getMobile, bizUserListRequest.getMobile())
                .like(StringUtils.isNotBlank(bizUserListRequest.getName()), BizUserPo::getName, bizUserListRequest.getName()));
        List<UserDetailVo> voList = BizUserConverter.INSTANCE.poList2DetailVoList(pos);
        BizUserListResponse response = new BizUserListResponse();
        response.setList(voList);
        return response;
    }
}
