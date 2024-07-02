package com.fzy.service;

import com.fzy.entity.PmsFile;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author fzy
 * @since 2024-06-03
 */
public interface IPmsFileService extends IService<PmsFile> {

    PmsFile get(String md5,Long size,String type);
}
