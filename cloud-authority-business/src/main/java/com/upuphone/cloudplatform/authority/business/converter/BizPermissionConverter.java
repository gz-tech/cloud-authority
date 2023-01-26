package com.upuphone.cloudplatform.authority.business.converter;

import com.upuphone.cloudplatform.authority.mybatis.po.bizuser.BizUserPermissionDetailPo;
import com.upuphone.cloudplatform.authority.vo.response.user.BizUserPermissionDetailVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BizPermissionConverter {

    BizPermissionConverter INSTANCE = Mappers.getMapper(BizPermissionConverter.class);

    @Mappings({
            @Mapping(source = "resType", target = "type")
    })
    BizUserPermissionDetailVo bizUserPermissionDetailPo2Vo(BizUserPermissionDetailPo po);

    List<BizUserPermissionDetailVo> bizUserPermissionDetailPoList2VoList(List<BizUserPermissionDetailPo> poList);

}
