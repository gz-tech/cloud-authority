package com.upuphone.cloudplatform.authority.business.service.bizresource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.upuphone.cloudplatform.authority.common.utils.OrikaUtil;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizResourcePo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizResourceMapper;
import com.upuphone.cloudplatform.authority.vo.request.resources.ResourceListRequest;
import com.upuphone.cloudplatform.authority.vo.response.resources.ResourceListResponse;
import com.upuphone.cloudplatform.authority.vo.response.resources.ResourceVo;
import com.upuphone.cloudplatform.common.component.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname ResourceManageListService
 * @Description
 * @Date 2022/3/25 1:41 下午
 * @Created by gz-d
 */
@Service
public class ResourceListService extends BaseService<ResourceListRequest, ResourceListResponse> {
    @Autowired
    private BizResourceMapper bizResourceMapper;

    @Override
    protected void validate(ResourceListRequest resourceListRequest) {

    }

    @Override
    protected ResourceListResponse processCore(ResourceListRequest request) throws Exception {
        QueryWrapper<BizResourcePo> query = new QueryWrapper<>();
        query.like(StringUtils.isNotEmpty(request.getCode()),"code", request.getCode());
        query.like(StringUtils.isNotEmpty(request.getDescription()), "description", request.getDescription());
        query.eq(null != request.getResType(), "res_type", request.getResType());
        query.eq("system_id", request.getSystemId());
        Page<BizResourcePo> page = Page.of(request.getPageNum(), request.getPageSize());
        IPage<BizResourcePo> queryResult = bizResourceMapper.selectPage(page, query);
        List<ResourceVo> resourceVoList = OrikaUtil.mapAsList(queryResult.getRecords(), ResourceVo.class);
        PageDTO<ResourceVo> pageDTO = new PageDTO<>(
                page.getCurrent(),
                page.getSize(),
                page.getTotal()
        );
        pageDTO.setRecords(resourceVoList);
        ResourceListResponse response = new ResourceListResponse();
        response.setResourceList(pageDTO);
        return response;
    }
}
