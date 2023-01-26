package com.upuphone.cloudplatform.authority.business.security;

import com.upuphone.cloudplatform.authority.business.repo.ISysPermissionService;
import com.upuphone.cloudplatform.authority.common.constants.RedisKeys;
import com.upuphone.cloudplatform.authority.common.constants.SysPermissionTypeEnum;
import com.upuphone.cloudplatform.authority.errorcode.AuthErrorCode;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysPermissionPo;
import com.upuphone.cloudplatform.common.context.RequestContext;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.redis.repository.RedisRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.upuphone.cloudplatform.authority.common.constants.Constants.HASH_KEY_USER_PERMISSION;

/**
 * Description: 授权服务
 *
 * @author hanzhumeng
 * Created: 2022/3/2
 */
@Component
@SuppressWarnings("ALL")
public class PermissionUtil {

    @Autowired
    private ISysPermissionService sysPermissionService;
    @Autowired
    private RedisRepository redisRepository;

    /**
     * 判断是否有访问相关接口的权限
     *
     * @param permissionCode permissionCode
     * @param systemId systemId
     * @return true/false
     */
    public boolean hasSysPermission(String permissionCode, String systemId) {
        if (null == RequestContext.getOperator()) {
            throw new BusinessException(AuthErrorCode.NOT_LOGGED_IN);
        }
        List<SysPermissionPo> permissionsList = sysPermissionService.getPermissions(RequestContext.getOperator(), systemId, SysPermissionTypeEnum.INTERFACE);
        Set<String> permissions = permissionsList.stream().map(SysPermissionPo::getCode).collect(Collectors.toSet());
        return permissions.contains(permissionCode);
    }
}
