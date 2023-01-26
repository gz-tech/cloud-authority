package com.upuphone.cloudplatform.authority.business.converter;

import com.upuphone.cloudplatform.authority.mybatis.entity.BizRolePo;
import com.upuphone.cloudplatform.authority.mybatis.po.bizuser.BizUserRoleDetailPo;
import com.upuphone.cloudplatform.authority.vo.response.role.RoleDetailVo;
import com.upuphone.cloudplatform.authority.vo.response.user.UserRoleDetailVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BizRoleConverter {

    BizRoleConverter INSTANCE = Mappers.getMapper(BizRoleConverter.class);

    UserRoleDetailVo bizUserRoleDetailPo2Vo(BizUserRoleDetailPo po);

    List<UserRoleDetailVo> bizUserRoleDetailPoList2VoList(List<BizUserRoleDetailPo> poList);

    RoleDetailVo bizRolePo2DetailVo(BizRolePo po);

    List<RoleDetailVo> bizRolePoList2DetailVoList(List<BizRolePo> poList);

}
