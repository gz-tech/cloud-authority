package com.upuphone.cloudplatform.authority.business.service.bizresource;

import com.upuphone.cloudplatform.authority.business.service.bizpermission.Permission;
import com.upuphone.cloudplatform.authority.common.constants.BizResourceTypeEnum;
import com.upuphone.cloudplatform.authority.common.utils.OrikaUtil;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizResourcePo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizResourceMapper;
import com.upuphone.cloudplatform.authority.vo.request.resources.ResourceAddRequest;
import com.upuphone.cloudplatform.common.component.BaseService;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.regex.Pattern;

import static com.upuphone.cloudplatform.authority.constant.ApiConstant.RES_TYPE_REGX;

/**
 * @Classname ResourceAddService
 * @Description
 * @Date 2022/3/25 2:51 下午
 * @Created by gz-d
 */
@Service
public class ResourceAddService extends BaseService<ResourceAddRequest, Integer> {

    @Autowired
    private BizResourceMapper bizResourceMapper;

    @Autowired
    private Permission permission;

    @Override
    protected void validate(ResourceAddRequest request) {
        if(!Pattern.compile(RES_TYPE_REGX).matcher(request.getResType().toString()).matches()) {
            throw new BusinessException(CommonErrorCode.PARAM_ERROR, "资源类型错误");
        }
        if (Objects.equals(BizResourceTypeEnum.INTERFACE.getResType(), request.getResType())
                && StringUtil.isBlank(request.getPath())) {
            throw new BusinessException(CommonErrorCode.PARAM_ERROR, "接口类型资源路径不为空");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected Integer processCore(ResourceAddRequest request) throws Exception {
        BizResourcePo insertPo = OrikaUtil.map(request, BizResourcePo.class);
        bizResourceMapper.insert(insertPo);
        permission.add(insertPo);
        return 1;
    }
}
