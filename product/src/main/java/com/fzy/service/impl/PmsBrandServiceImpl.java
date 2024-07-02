package com.fzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fzy.entity.PmsBrand;
import com.fzy.fegin.FileService;
import com.fzy.mapper.PmsBrandMapper;
import com.fzy.service.IPmsBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author fzy
 * @since 2024-05-27
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements IPmsBrandService {

    @Resource
    PmsBrandMapper pmsBrandMapper;

    @Resource
    FileService fileService;

    @Override
    public IPage<PmsBrand> getPmsBrandList(Integer pageNo, Integer pageSize, String name) {

        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<>();

        if(StringUtils.isNotBlank(name)){ //排除掉空串和空值
            wrapper.like("name",name.trim()); //根据品牌名称进行模糊查询

        }

        wrapper.orderByDesc("sort");

        return this.page(new Page<>(pageNo,pageSize),wrapper);
    }

    @Override
    public Boolean save(String name, String description, MultipartFile file) {

        //在minio中保存图片
        String logo = fileService.impageUpload(file);

        PmsBrand pmsBrand = new PmsBrand(name,description);
        pmsBrand.setLogo(logo);

        //在mysql保存基本信息（包含异常处理过的logo）
        return this.save(pmsBrand);
    }

    @Override
    public Boolean update(String id, String name, String description, MultipartFile file) {

        PmsBrand pmsBrand = new PmsBrand(id,name,description);

        if(null != file){
            String logo = fileService.impageUpload(file);
            pmsBrand.setLogo(logo);
        }

        return this.updateById(pmsBrand);
    }

    @Override
    public Boolean delete(String id, Boolean active) {

        return this.updateById(new PmsBrand(id,active));
    }

    @Override
    public List getActive() {
        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<>();
        wrapper.eq("active",1);
        return this.list(wrapper);
    }

    @Override
    public String getNameById(String brandId) {
    return this.getById(brandId).getName();
    }
}
