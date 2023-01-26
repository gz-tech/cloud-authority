package com.upuphone.cloudplatform.authority.business.service.bizauthorize;

import com.upuphone.cloudplatform.authority.mybatis.entity.BizRolePermissionPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRolePermissionMapper;
import com.upuphone.cloudplatform.authority.vo.request.rolepermission.RolePermissionAddRequest;
import com.upuphone.cloudplatform.common.component.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname RolePermissionAddService
 * @Description
 * @Date 2022/3/30 5:59 下午
 * @Created by gz-d
 */
@Service
@Slf4j
public class RolePermissionAddService extends BaseService<RolePermissionAddRequest, Integer> {
    @Autowired
    private BizRolePermissionMapper bizRolePermissionMapper;

    @Autowired
    private RolePermissionIServiceImpl rolePermissionIService;

    @Override
    protected void validate(RolePermissionAddRequest request) {

    }

    @Override
    protected Integer processCore(RolePermissionAddRequest request) throws Exception {
        List<BizRolePermissionPo> insertList = descartes(request.getAuthRoleIdList(), request.getAuthPermissionIdList());
        rolePermissionIService.saveBatch(insertList);
        return 1;
    }

    private List<BizRolePermissionPo> descartes(List<String> roleList, List<String> permissionList) {
        List<BizRolePermissionPo> result = new ArrayList<>();
        for (String roleId:
             roleList) {
            for (String permissionId:
                 permissionList) {
                BizRolePermissionPo insertOne = new BizRolePermissionPo();
                insertOne.setPermissionId(Long.parseLong(permissionId));
                insertOne.setRoleId(Long.parseLong(roleId));
                result.add(insertOne);
            }
        }
        return result;
    }
}
