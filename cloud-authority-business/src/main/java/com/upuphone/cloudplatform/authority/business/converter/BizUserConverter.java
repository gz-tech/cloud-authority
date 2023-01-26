package com.upuphone.cloudplatform.authority.business.converter;

import com.upuphone.cloudplatform.authority.mybatis.entity.BizUserPo;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserAddRequest;
import com.upuphone.cloudplatform.authority.vo.response.user.UserDetailVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BizUserConverter {

    BizUserConverter INSTANCE = Mappers.getMapper(BizUserConverter.class);

    UserDetailVo po2DetailVo(BizUserPo po);

    List<UserDetailVo> poList2DetailVoList(List<BizUserPo> poList);

    BizUserPo addRequest2Po(BizUserAddRequest request);

}
