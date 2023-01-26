package com.upuphone.cloudplatform.authority.business.repo.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.upuphone.cloudplatform.authority.business.repo.ISysPermissionService;
import com.upuphone.cloudplatform.authority.common.constants.SysPermissionTypeEnum;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysPermissionPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysPermissionMapper;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermissionPo> implements ISysPermissionService {

    @Override
    public List<SysPermissionPo> getPermissions(String sysUserId, String systemId, SysPermissionTypeEnum... types) {
        if (null == sysUserId) {
            throw new BusinessException(CommonErrorCode.PARAM_ERROR);
        }
        Set<Integer> permissionTypes = new HashSet<>();
        if (types.length > 0) {
            permissionTypes = Arrays.stream(types).map(SysPermissionTypeEnum::getResType).collect(Collectors.toSet());
        }
        return baseMapper.getListByUserIdAndTypes(sysUserId, systemId, permissionTypes);
    }
}
