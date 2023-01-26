package com.upuphone.cloudplatform.authority.business.converter;

import com.upuphone.cloudplatform.authority.mybatis.po.bizuser.BizUserPermissionDetailPo;
import com.upuphone.cloudplatform.authority.vo.response.user.BizUserPermissionDetailVo;
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
public class BizPermissionConverterImpl implements BizPermissionConverter {

    @Override
    public BizUserPermissionDetailVo bizUserPermissionDetailPo2Vo(BizUserPermissionDetailPo po) {
        if ( po == null ) {
            return null;
        }

        BizUserPermissionDetailVo bizUserPermissionDetailVo = new BizUserPermissionDetailVo();

        bizUserPermissionDetailVo.setType( po.getResType() );
        if ( po.getId() != null ) {
            bizUserPermissionDetailVo.setId( String.valueOf( po.getId() ) );
        }
        bizUserPermissionDetailVo.setCode( po.getCode() );
        bizUserPermissionDetailVo.setDescription( po.getDescription() );
        bizUserPermissionDetailVo.setPath( po.getPath() );

        return bizUserPermissionDetailVo;
    }

    @Override
    public List<BizUserPermissionDetailVo> bizUserPermissionDetailPoList2VoList(List<BizUserPermissionDetailPo> poList) {
        if ( poList == null ) {
            return null;
        }

        List<BizUserPermissionDetailVo> list = new ArrayList<BizUserPermissionDetailVo>( poList.size() );
        for ( BizUserPermissionDetailPo bizUserPermissionDetailPo : poList ) {
            list.add( bizUserPermissionDetailPo2Vo( bizUserPermissionDetailPo ) );
        }

        return list;
    }
}
