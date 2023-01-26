package com.upuphone.cloudplatform.authority.business.security;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.upuphone.cloudplatform.authority.business.config.Oauth2Settings;
import com.upuphone.cloudplatform.authority.business.repo.ISysUserResourceService;
import com.upuphone.cloudplatform.authority.business.security.dto.RemoveSessionRespDTO;
import com.upuphone.cloudplatform.authority.common.constants.RedisKeys;
import com.upuphone.cloudplatform.authority.common.constants.SysRelationEnum;
import com.upuphone.cloudplatform.authority.common.utils.AuthUtils;
import com.upuphone.cloudplatform.authority.errorcode.AuthErrorCode;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysUserResourcePo;
import com.upuphone.cloudplatform.authority.vo.response.auth.AccessTokenVo;
import com.upuphone.cloudplatform.common.context.RequestContext;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;
import com.upuphone.cloudplatform.redis.repository.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.upuphone.cloudplatform.authority.common.constants.Constants.HASH_KEY_USER_ACCESS_TOKEN;

@Service
@Slf4j
@SuppressWarnings("ALL")
public class SecurityUtil {

    @Autowired
    private RedisRepository redisRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Oauth2Settings oauth2Settings;

    @Autowired
    private ISysUserResourceService sysUserResourceService;

    /**
     * 拼接获取授权码链接
     *
     * @param state state
     */
    public String getCode(String state) {
        if (StringUtils.isEmpty(state)) {
            throw new BusinessException(CommonErrorCode.PARAM_ERROR, "state cannot be null");
        }
        return AuthUtils.getAuthorizeUrl(oauth2Settings.getAuthorizationUri(), oauth2Settings.getClientId(),
                oauth2Settings.getRedirectUri(), state);
    }

    /**
     * 根据授权码认证
     *
     * @param code  授权码
     * @param state state
     * @return AccessToken refreshToken state
     */
    public AccessTokenVo authenticate(String code, String state) {
        // 缓存
        // Long userId = sysUserService.createCache(RequestContext.getOperator(), accessToken, expiresIn);
        return null;
    }


    /**
     * 登出（需登录态）
     */
    public void logout() {
        String userId = RequestContext.getOperator();
        if (null == userId) {
            throw new BusinessException(AuthErrorCode.NOT_LOGGED_IN);
        }
        // 调用sso注销token
        String userKey = RedisKeys.getUserDetailKey(userId);
        String accessToken = (String) redisRepository.opsForHash().get(userKey, HASH_KEY_USER_ACCESS_TOKEN);
        HttpHeaders headers = new HttpHeaders();
        headers.add("access_token", accessToken);
        HttpEntity<?> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<RemoveSessionRespDTO> responseEntity = restTemplate
                .exchange(oauth2Settings.getCheckAccessTokenUri(), HttpMethod.GET, httpEntity, RemoveSessionRespDTO.class);
        RemoveSessionRespDTO removeSessionRespDTO = responseEntity.getBody();
        if (null == removeSessionRespDTO || !"0".equals(removeSessionRespDTO.getErrorCode())) {
            log.error("注销accessToken失败, accessToken=[{}]", accessToken);
            throw new BusinessException(AuthErrorCode.AUTH_REQUEST_FAILED);
        }
        // sysUserService.removeCache(userId);
    }

    /**
     * 获取指定ID用户该资源ID的type类型（可用于判断该用户在该系统中的角色，不能判断超管，因超管非系统角色）
     *
     * @param userId 用户ID
     * @param resId  资源ID
     * @return SysResourceEnum
     */
    public Set<SysRelationEnum> getUserResTypesByResId(Long userId, Long resId) {
        if (null == userId || null == resId) {
            throw new BusinessException(CommonErrorCode.PARAM_ERROR);
        }
        List<SysUserResourcePo> list = sysUserResourceService.list(Wrappers.<SysUserResourcePo>lambdaQuery()
                .eq(SysUserResourcePo::getResourceId, resId)
                .eq(SysUserResourcePo::getUserId, userId));
        if (CollectionUtils.isEmpty(list)) {
            return new HashSet<>();
        }
        return list.stream().map(o -> SysRelationEnum.getByType(o.getRelationType())).collect(Collectors.toSet());
    }

}
