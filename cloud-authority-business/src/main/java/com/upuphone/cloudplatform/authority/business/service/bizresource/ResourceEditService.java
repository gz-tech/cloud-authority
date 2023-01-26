package com.upuphone.cloudplatform.authority.business.service.bizresource;

import com.upuphone.cloudplatform.authority.common.utils.OrikaUtil;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizResourcePo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizResourceMapper;
import com.upuphone.cloudplatform.authority.vo.request.resources.ResourceEditRequest;
import com.upuphone.cloudplatform.common.component.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname ResourceEditService
 * @Description
 * @Date 2022/3/25 3:06 下午
 * @Created by gz-d
 */
@Service
public class ResourceEditService extends BaseService<ResourceEditRequest, Integer> {
    @Autowired
    private BizResourceMapper bizResourceMapper;

    @Override
    protected void validate(ResourceEditRequest resourceEditRequest) {

    }

    @Override
    protected Integer processCore(ResourceEditRequest request) throws Exception {
        BizResourcePo editPo = OrikaUtil.map(request, BizResourcePo.class);
        return bizResourceMapper.updateById(editPo);
    }
}
