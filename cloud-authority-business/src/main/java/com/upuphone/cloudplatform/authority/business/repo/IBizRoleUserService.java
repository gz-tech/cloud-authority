package com.upuphone.cloudplatform.authority.business.repo;

import com.baomidou.mybatisplus.extension.service.IService;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizRoleUserPo;

import java.util.Set;

public interface IBizRoleUserService extends IService<BizRoleUserPo> {

    /**
     * 根据userId获取角色ID列表
     *
     * @param userId userId
     * @return 角色ID set
     */
    Set<Long> getRoleIdsByUserId(String userId);

    /**
     * 根据userId获取角色ID列表 (带锁)
     *
     * @param userId userId
     * @return 角色ID set
     */
    Set<Long> getRoleIdsByUserIdForUpdate(String userId);

    /**
     * 检查外部用户角色关联关系操作合法性
     *
     * @param roleUserId biz_role_user.id
     * @param systemId   biz_system.id
     */
    void checkValidBizRoleUserAssociation(String roleUserId, Long systemId);

    /**
     * 给指定用户批量增加角色
     *
     * @param roleIds  角色set
     * @param userId   userId
     * @param systemId 系统ID
     * @return 结果
     */
    boolean addUserRole(Set<Long> roleIds, String userId, Long systemId);

}
