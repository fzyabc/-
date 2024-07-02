package com.fzy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fzy.entity.PmsProduct;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

public interface PmsProductService extends IService<PmsProduct> {

    Boolean save(
            String name,
            BigDecimal price,
            String brandId,
            String[] categoryId,
            String[] spu,
            String[] sku,
            String[] skuArray,
            MultipartFile[] files,
            String description
    ) throws IOException;
    IPage list(Integer pageNo, Integer pageSize);

    Boolean changeStatus(String id, Boolean isPublish);
}