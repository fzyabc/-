package com.fzy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fzy.entity.PmsBrand;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author fzy
 * @since 2024-05-27
 */
public interface IPmsBrandService extends IService<PmsBrand> {

    IPage<PmsBrand> getPmsBrandList(Integer pageNo, Integer pageSize, String name);

    Boolean save(String name, String description, MultipartFile file);

    Boolean update(String id, String name,String description, MultipartFile file);

    Boolean delete(String id, java.lang.Boolean active);

    List getActive();

    String getNameById(String brandId);
}
