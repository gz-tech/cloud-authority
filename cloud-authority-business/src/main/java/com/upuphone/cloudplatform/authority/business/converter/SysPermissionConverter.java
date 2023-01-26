package com.upuphone.cloudplatform.authority.business.converter;

import com.upuphone.cloudplatform.authority.mybatis.entity.SysPermissionPo;
import com.upuphone.cloudplatform.authority.vo.response.security.SysPermissionVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SysPermissionConverter {

    SysPermissionConverter INSTANCE = Mappers.getMapper(SysPermissionConverter.class);

    @Mappings({
            @Mapping(source = "resType", target = "type")
    })
    SysPermissionVo po2Vo(SysPermissionPo po);

    List<SysPermissionVo> poList2VoList(List<SysPermissionPo> pos);

}
