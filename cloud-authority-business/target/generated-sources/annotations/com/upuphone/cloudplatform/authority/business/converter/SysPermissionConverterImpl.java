package com.upuphone.cloudplatform.authority.business.converter;

import com.upuphone.cloudplatform.authority.mybatis.entity.SysPermissionPo;
import com.upuphone.cloudplatform.authority.vo.response.security.SysPermissionVo;
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
public class SysPermissionConverterImpl implements SysPermissionConverter {

    @Override
    public SysPermissionVo po2Vo(SysPermissionPo po) {
        if ( po == null ) {
            return null;
        }

        SysPermissionVo sysPermissionVo = new SysPermissionVo();

        sysPermissionVo.setType( po.getResType() );
        sysPermissionVo.setDescription( po.getDescription() );
        sysPermissionVo.setIcon( po.getIcon() );
        sysPermissionVo.setPath( po.getPath() );

        return sysPermissionVo;
    }

    @Override
    public List<SysPermissionVo> poList2VoList(List<SysPermissionPo> pos) {
        if ( pos == null ) {
            return null;
        }

        List<SysPermissionVo> list = new ArrayList<SysPermissionVo>( pos.size() );
        for ( SysPermissionPo sysPermissionPo : pos ) {
            list.add( po2Vo( sysPermissionPo ) );
        }

        return list;
    }
}
