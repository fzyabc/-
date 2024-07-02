package com.fzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzy.entity.PmsFile;
import com.fzy.mapper.PmsFileMapper;
import com.fzy.service.IPmsFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author fzy
 * @since 2024-06-03
 */
@Service
public class PmsFileServiceImpl extends ServiceImpl<PmsFileMapper, PmsFile> implements IPmsFileService {

    @Override
    public PmsFile get(String md5, Long size, String type) {
        QueryWrapper<PmsFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5",md5).eq("size",size).eq("type",type);
        return this.getOne(queryWrapper);
    }
}
