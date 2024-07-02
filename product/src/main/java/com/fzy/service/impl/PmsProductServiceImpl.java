package com.fzy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzy.entity.PmsProduct;
import com.fzy.entity.PmsSkuStore;
import com.fzy.fegin.FileService;
import com.fzy.mapper.PmsProductMapper;
import com.fzy.service.IPmsBrandService;
import com.fzy.service.IPmsSkuStoreService;
import com.fzy.service.PmsCategoryService;
import com.fzy.service.PmsProductService;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {
@Resource
    FileService fileService;
@Resource
    IPmsBrandService pmsBrandService;
@Resource
    PmsCategoryService pmsCategoryService;
@Resource
    IPmsSkuStoreService pmsSkuStoreService;
    @Transactional
    @Override
    public Boolean save(String name, BigDecimal price, String brandId, String[] categoryId, String[] spu, String[] sku, String[] skuArray, MultipartFile[] files, String description) throws IOException {
       PmsProduct pmsProduct = new PmsProduct();
       pmsProduct.setName(name);
       pmsProduct.setPrice(price);
       pmsProduct.setBrandId(brandId);
       pmsProduct.setDescription(description);
       //保存数组相关的字段，转成字符串才能保存
        pmsProduct.setCategoryId(JSONObject.toJSONString(categoryId));
        pmsProduct.setSpu(JSONObject.toJSONString(spu));
        pmsProduct.setSku(JSONObject.toJSONString(sku));
    //保存相册
        List<String> albumList = new ArrayList<>();

        for (MultipartFile file : files) {
            //System.out.println(file.getName() + " -------------------------------------- ");
            String album = fileService.impageUpload(new MockMultipartFile("file",
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getInputStream()));
            albumList.add(album);
        }
        pmsProduct.setAlbum(JSONObject.toJSONString(albumList));
//保存品牌名称和分类名称
       String brandName = pmsBrandService.getNameById(brandId);
       pmsProduct.setBrandName(brandName);
       String categoryName = pmsCategoryService.getNameByIds(categoryId);
       pmsProduct.setCategoryName(categoryName);
       this.save(pmsProduct);
       //保存商品库存
        List<PmsSkuStore> storeList = new ArrayList<>();
        for (String store: skuArray){
PmsSkuStore pmsSkuStore = JSONObject.parseObject(store, PmsSkuStore.class);
pmsSkuStore.setProductId(pmsProduct.getId());
storeList.add(pmsSkuStore);
        }
        return pmsSkuStoreService.saveBatch(storeList);
    }

    @Override
    public IPage list(Integer pageNo, Integer pageSize) {
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        return this.page(new Page<>(pageNo,pageSize),queryWrapper);
    }

    @Override
    public Boolean changeStatus(String id, Boolean isPublish) {


        return this.updateById(new PmsProduct(id,isPublish));
    }
}
