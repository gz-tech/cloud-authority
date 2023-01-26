package com.upuphone.cloudplatform.authority.business.service.bizpermission;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.upuphone.cloudplatform.authority.business.entity.PermissionActionEnum;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizPermissionPo;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizResourcePo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizPermissionMapper;
import com.upuphone.cloudplatform.authority.mybatis.po.bizpermission.BizPermissionListPo;
import com.upuphone.cloudplatform.authority.vo.response.rolepermission.PermissionDetailVo;
import com.upuphone.cloudplatform.authority.vo.response.rolepermission.PermissionListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname PermissionUtil
 * @Description
 * @Date 2022/3/29 4:58 下午
 * @Created by gz-d
 */
@Component("permission")
public class Permission {

    @Autowired
    private  BizPermissionMapper permissionMapper;

    public void add(BizResourcePo resourcePo) {
        BizPermissionPo permission = new BizPermissionPo();
        permission.setResId(resourcePo.getId());
        permission.setAction(PermissionActionEnum.QUERY.getAction());
        permissionMapper.insert(permission);
    }

    public void deleteByResourceId(String resourceId) {
        permissionMapper.delete(Wrappers.<BizPermissionPo>lambdaQuery()
                .eq(BizPermissionPo::getResId, Long.parseLong(resourceId)));
    }

    public List<PermissionListVo> permissionPo2Vo(List<BizPermissionListPo> permissionPoList) {
        List<PermissionListVo> permissionVoList = new ArrayList<>();
        for (BizPermissionListPo bizPermissionPo:
                permissionPoList) {
            PermissionListVo oneVo = new PermissionListVo();
            oneVo.setId(bizPermissionPo.getId());
            oneVo.setName(bizPermissionPo.getResourceName());
            permissionVoList.add(oneVo);
        }
        return permissionVoList;
    }

    public List<PermissionDetailVo> permissionPo2DetailVo(List<BizPermissionListPo> permissionPoList) {
        List<PermissionDetailVo> permissionVoList = new ArrayList<>();
        for (BizPermissionListPo bizPermissionPo:
                permissionPoList) {
            PermissionDetailVo oneVo = new PermissionDetailVo();
            oneVo.setId(bizPermissionPo.getId());
            oneVo.setName(bizPermissionPo.getResourceName() + PermissionActionEnum.getActionNameByAction(bizPermissionPo.getAction()));
            oneVo.setResourceDescp(bizPermissionPo.getResourceDescp());
            oneVo.setResourcePath(bizPermissionPo.getResourcePath());
            oneVo.setResourceType(bizPermissionPo.getResourceType());
            permissionVoList.add(oneVo);
        }
        return permissionVoList;
    }
}
