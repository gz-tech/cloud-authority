package com.upuphone.cloudplatform.authority.business.service.basic;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.upuphone.cloudplatform.authority.business.service.basic.repo.AddDeveloperRepo;
import com.upuphone.cloudplatform.authority.common.constants.SysRelationEnum;
import com.upuphone.cloudplatform.authority.common.constants.SysRoleEnum;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysUserPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysRoleUserMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysUserMapper;
import com.upuphone.cloudplatform.authority.vo.request.basic.DeveloperAddRequest;
import com.upuphone.cloudplatform.authority.vo.response.DeveloperDetailVo;
import com.upuphone.cloudplatform.authority.vo.response.systembasicinfo.DeveloperAddResposne;
import com.upuphone.cloudplatform.common.component.BaseService;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import com.upuphone.cloudplatform.internal.admin.api.InternalUserClientApi;
import com.upuphone.cloudplatform.internal.admin.api.dto.request.QueryUserInfoReq;
import com.upuphone.cloudplatform.internal.admin.api.dto.response.InternalUserInfoRes;
import com.upuphone.cloudplatform.internal.admin.starter.anno.DisableSso;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Date 2022/5/29 4:47 下午
 * @Created by min.jiang
 */
@Slf4j
@Service
public class AddDeveloperService extends BaseService<DeveloperAddRequest, DeveloperAddResposne> {

    @Autowired
    private InternalUserClientApi internalUserClientApi;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private AddDeveloperRepo addDeveloperRepo;

    @Override
    protected void validate(DeveloperAddRequest developerAddRequest) {

        for (String developerId : developerAddRequest.getDeveloperIds()) {
            if (Strings.isNullOrEmpty(developerId)) {
                throw new BusinessException(CommonErrorCode.PARAM_ERROR, "developerId error");
            }

            String id = developerId.trim();
            if (id == null) {
                throw new BusinessException(CommonErrorCode.PARAM_ERROR, "developerId error");
            }
        }
    }

    @Override
    protected DeveloperAddResposne processCore(DeveloperAddRequest developerAddRequest) throws Exception {

        Long systemId = Long.parseLong(developerAddRequest.getSystemId());
        if (systemId == null) {
            throw new BusinessException(CommonErrorCode.PARAM_ERROR, "systemId error");
        }
        // 拿到idList,，有错
        Set<String> developerIds = this.getAllRequestDeveloperIds(developerAddRequest);

        // 无需develop是否存在
//        List<SysUserPo> sysUserPos = this.checkDeveloperExisted(developerIds);
        List<DeveloperDetailVo> developers = new ArrayList<>();

        // system的develop是否存在
//        this.checkDeveloperBounded(systemId, developerIds);

        for (String developerId : developerIds) {
            // may have developer deleted in a short time exception
            try {
                // 插入数据到sys_role_user
                addDeveloperRepo.checkRoleAndDevlprToSys(developerId, systemId, SysRoleEnum.SYS_DEVELOPER);
            } catch (Exception e) {
                log.error("add dev to sys error developerid: " + developerId + " systmeId: " + systemId, e);
            }
        }
        // 直接通过developlist 拿到用户数据值，将其给Sysuser   返回
        QueryUserInfoReq queryUserInfoReq = new QueryUserInfoReq();
        List<String> developerIdList = new ArrayList<>();
        developerIdList.addAll(developerIds);
        queryUserInfoReq.setIdList(developerIdList);
        CommonResponse<List<InternalUserInfoRes>> listCommonResponseList = internalUserClientApi.queryByIdList(queryUserInfoReq);
        // 用户数据值 在listCommonResponseList，如何将其给sysUser

        for (InternalUserInfoRes internalUserInfoRes:listCommonResponseList.getData()) {
            DeveloperDetailVo detailVo = new DeveloperDetailVo();
            detailVo.setId(internalUserInfoRes.getOperator());
            detailVo.setMobile(internalUserInfoRes.getMobile());
            detailVo.setEmail(internalUserInfoRes.getEmail());
            detailVo.setName(internalUserInfoRes.getOperatorName());
            detailVo.setUid(internalUserInfoRes.getOperator());
            developers.add(detailVo);
        }

        DeveloperAddResposne response = new DeveloperAddResposne();
        response.setDevelopers(developers);

        return response;
    }

    private void checkDeveloperBounded(Long systemId, Set<String> developerIds) {
        // 现在只要查看sys_role_user
        List<SysUserPo> sysUserPos = sysUserMapper.selectDevelopersBySystemId(systemId, null, null,
                SysRelationEnum.SYSTEM_DEVELOPER.getType());

        sysUserPos = MoreObjects.firstNonNull(sysUserPos, new ArrayList<>(0));
        //
        Set<String> developerIdsBounded = sysUserPos.stream().map(SysUserPo::getId).collect(Collectors.toSet());
        boolean existFlag = developerIds.stream().anyMatch(developerIdsBounded::contains);
        if (existFlag) {
            throw new BusinessException(CommonErrorCode.PARAM_ERROR, "developer already add to system");
        }
    }

    private List<SysUserPo> checkDeveloperExisted(Set<String> developerIds) {
        List<SysUserPo> sysUserPosExisted = MoreObjects.firstNonNull(sysUserMapper.selectBatchIds(developerIds), new ArrayList<>(0));
        if (sysUserPosExisted.size() != developerIds.size()) {
            throw new BusinessException(CommonErrorCode.PARAM_ERROR, "developer not exist");
        }

        return sysUserPosExisted;
    }

    private Set<String> getAllRequestDeveloperIds(DeveloperAddRequest developerAddRequest) {
        Set<String> developerIds = new HashSet<>();
        for (String developerId : developerAddRequest.getDeveloperIds()) {
            String id = developerId.trim();
            developerIds.add(id);
        }
        return developerIds;
    }
}
