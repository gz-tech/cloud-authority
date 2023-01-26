package com.upuphone.cloudplatform.authority.business.repo;

import com.baomidou.mybatisplus.extension.service.IService;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizRolePo;

public interface IBizRoleService extends IService<BizRolePo> {

    /**
     * 增加角色
     *
     * @param systemId    系统ID
     * @param code        角色code
     * @param description 角色表述
     * @return 结果
     */
    boolean addRole(Long systemId, String code, String description);

    /**
     * 修改角色信息
     *
     * @param roleId      角色ID
     * @param systemId    系统ID
     * @param code        角色code
     * @param description 角色描述
     * @return 结果
     */
    boolean modifyRole(Long roleId, Long systemId, String code, String description);

    /**
     * 删除角色
     *
     * @param roleId   角色ID
     * @param systemId 系统ID
     * @return 结果
     */
    boolean deleteRole(Long roleId, Long systemId);

}
