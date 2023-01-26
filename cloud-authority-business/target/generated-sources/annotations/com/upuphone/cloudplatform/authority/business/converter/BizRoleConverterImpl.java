package com.upuphone.cloudplatform.authority.business.converter;

import com.upuphone.cloudplatform.authority.mybatis.entity.BizRolePo;
import com.upuphone.cloudplatform.authority.mybatis.po.bizuser.BizUserRoleDetailPo;
import com.upuphone.cloudplatform.authority.vo.response.role.RoleDetailVo;
import com.upuphone.cloudplatform.authority.vo.response.user.UserRoleDetailVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-18T10:22:47+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_312 (Azul Systems, Inc.)"
)
@Component
public class BizRoleConverterImpl implements BizRoleConverter {

    @Override
    public UserRoleDetailVo bizUserRoleDetailPo2Vo(BizUserRoleDetailPo po) {
        if ( po == null ) {
            return null;
        }

        UserRoleDetailVo userRoleDetailVo = new UserRoleDetailVo();

        if ( po.getId() != null ) {
            userRoleDetailVo.setId( String.valueOf( po.getId() ) );
        }
        userRoleDetailVo.setCode( po.getCode() );
        userRoleDetailVo.setDescription( po.getDescription() );
        userRoleDetailVo.setCreateTime( po.getCreateTime() );

        return userRoleDetailVo;
    }

    @Override
    public List<UserRoleDetailVo> bizUserRoleDetailPoList2VoList(List<BizUserRoleDetailPo> poList) {
        if ( poList == null ) {
            return null;
        }

        List<UserRoleDetailVo> list = new ArrayList<UserRoleDetailVo>( poList.size() );
        for ( BizUserRoleDetailPo bizUserRoleDetailPo : poList ) {
            list.add( bizUserRoleDetailPo2Vo( bizUserRoleDetailPo ) );
        }

        return list;
    }

    @Override
    public RoleDetailVo bizRolePo2DetailVo(BizRolePo po) {
        if ( po == null ) {
            return null;
        }

        RoleDetailVo roleDetailVo = new RoleDetailVo();

        if ( po.getId() != null ) {
            roleDetailVo.setId( String.valueOf( po.getId() ) );
        }
        roleDetailVo.setCode( po.getCode() );
        roleDetailVo.setDescription( po.getDescription() );
        roleDetailVo.setCreateTime( po.getCreateTime() );

        return roleDetailVo;
    }

    @Override
    public List<RoleDetailVo> bizRolePoList2DetailVoList(List<BizRolePo> poList) {
        if ( poList == null ) {
            return null;
        }

        List<RoleDetailVo> list = new ArrayList<RoleDetailVo>( poList.size() );
        for ( BizRolePo bizRolePo : poList ) {
            list.add( bizRolePo2DetailVo( bizRolePo ) );
        }

        return list;
    }
}
