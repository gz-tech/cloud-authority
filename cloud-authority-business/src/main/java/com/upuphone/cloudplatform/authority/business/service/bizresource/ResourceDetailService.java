package com.upuphone.cloudplatform.authority.business.service.bizresource;

import com.upuphone.cloudplatform.authority.common.utils.OrikaUtil;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizResourcePo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizResourceMapper;
import com.upuphone.cloudplatform.authority.vo.response.resources.ResourceVo;
import com.upuphone.cloudplatform.common.component.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname ResourceDetailService
 * @Description
 * @Date 2022/3/25 3:09 下午
 * @Created by gz-d
 */
@Service
public class ResourceDetailService extends BaseService<String, ResourceVo> {
    @Autowired
    private BizResourceMapper bizResourceMapper;

    @Override
    protected void validate(String s) {

    }

    @Override
    protected ResourceVo processCore(String s) throws Exception {
        BizResourcePo detailPo = bizResourceMapper.selectById(s);
        return OrikaUtil.map(detailPo, ResourceVo.class);
    }
}
