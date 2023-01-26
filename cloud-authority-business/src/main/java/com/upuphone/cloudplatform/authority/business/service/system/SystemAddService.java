package com.upuphone.cloudplatform.authority.business.service.system;

import com.upuphone.cloudplatform.authority.business.setting.Setting;
import com.upuphone.cloudplatform.authority.business.util.AESUtil;
import com.upuphone.cloudplatform.authority.common.constants.SysRelationEnum;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysSystemPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysSystemMapper;
import com.upuphone.cloudplatform.authority.vo.request.system.SystemAddRequest;
import com.upuphone.cloudplatform.common.component.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname SystemAddService
 * @Description
 * @Date 2022/3/25 3:14 下午
 * @Created by gz-d
 */
@Service
public class SystemAddService extends BaseService<SystemAddRequest, Integer> {
    @Autowired
    private SysSystemMapper sysSystemMapper;

    @Autowired
    private Setting setting;

    @Autowired
    private SystemUtil systemUtil;

    @Override
    protected void validate(SystemAddRequest request) {

    }
    /**
     * 当sys_system新增一个应用时，需要同时在sys_role_user里添加数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 5)
    protected Integer processCore(SystemAddRequest request) throws Exception {
        SysSystemPo sysSystemPo = new SysSystemPo();
        sysSystemPo.setCode(request.getCode());
        sysSystemPo.setName(request.getName());
        sysSystemPo.setDescription(request.getDescription());
        sysSystemPo.setSecret(AESUtil.encrypt(request.getCode(), setting.getSystemSecretAesKey(), setting.getSystemSecretAesIV()));
        sysSystemMapper.insert(sysSystemPo);
        systemUtil.addUserRole(request.getOwnerUserId(), SysRelationEnum.SYSTEM_OWNER.getType(), sysSystemPo.getId());
        return 1;
    }
}
