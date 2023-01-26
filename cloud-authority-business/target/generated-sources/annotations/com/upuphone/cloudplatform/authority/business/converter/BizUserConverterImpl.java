package com.upuphone.cloudplatform.authority.business.converter;

import com.upuphone.cloudplatform.authority.mybatis.entity.BizUserPo;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserAddRequest;
import com.upuphone.cloudplatform.authority.vo.response.user.UserDetailVo;
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
public class BizUserConverterImpl implements BizUserConverter {

    @Override
    public UserDetailVo po2DetailVo(BizUserPo po) {
        if ( po == null ) {
            return null;
        }

        UserDetailVo userDetailVo = new UserDetailVo();

        if ( po.getId() != null ) {
            userDetailVo.setId( String.valueOf( po.getId() ) );
        }
        userDetailVo.setUid( po.getUid() );
        userDetailVo.setName( po.getName() );
        userDetailVo.setMobile( po.getMobile() );
        userDetailVo.setEmail( po.getEmail() );

        return userDetailVo;
    }

    @Override
    public List<UserDetailVo> poList2DetailVoList(List<BizUserPo> poList) {
        if ( poList == null ) {
            return null;
        }

        List<UserDetailVo> list = new ArrayList<UserDetailVo>( poList.size() );
        for ( BizUserPo bizUserPo : poList ) {
            list.add( po2DetailVo( bizUserPo ) );
        }

        return list;
    }

    @Override
    public BizUserPo addRequest2Po(BizUserAddRequest request) {
        if ( request == null ) {
            return null;
        }

        BizUserPo bizUserPo = new BizUserPo();

        bizUserPo.setUid( request.getUid() );
        if ( request.getSystemId() != null ) {
            bizUserPo.setSystemId( Long.parseLong( request.getSystemId() ) );
        }
        bizUserPo.setName( request.getName() );
        bizUserPo.setMobile( request.getMobile() );
        bizUserPo.setEmail( request.getEmail() );

        return bizUserPo;
    }
}
