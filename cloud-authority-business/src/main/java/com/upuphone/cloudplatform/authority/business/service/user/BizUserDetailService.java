package com.upuphone.cloudplatform.authority.business.service.user;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.upuphone.cloudplatform.authority.business.converter.BizUserConverter;
import com.upuphone.cloudplatform.authority.errorcode.AuthErrorCode;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizUserPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizUserMapper;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserDetailRequest;
import com.upuphone.cloudplatform.authority.vo.response.user.UserDetailVo;
import com.upuphone.cloudplatform.common.component.BaseService;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BizUserDetailService extends BaseService<BizUserDetailRequest, UserDetailVo> {

    @Autowired
    private BizUserMapper bizUserMapper;

    @Override
    protected void validate(BizUserDetailRequest request) {

    }

    @Override
    protected UserDetailVo processCore(BizUserDetailRequest soaRequest) throws Exception {
        BizUserPo bizUserPo = bizUserMapper.selectOne(Wrappers.<BizUserPo>lambdaQuery()
                .eq(BizUserPo::getId, Long.valueOf(soaRequest.getId()))
                .eq(BizUserPo::getSystemId, Long.valueOf(soaRequest.getSystemId())));
        if (null == bizUserPo) {
            throw new BusinessException(AuthErrorCode.USER_NOT_FOUND);
        }
        return BizUserConverter.INSTANCE.po2DetailVo(bizUserPo);
    }
}
