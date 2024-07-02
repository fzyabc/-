package com.fzy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fzy.entity.UmsAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fzy.entity.UmsRole;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author zs
 * @since 2024-05-27
 */
public interface UmsAdminService extends IService<UmsAdmin> {

    IPage<UmsAdmin> getUmsAdminList(Integer pageNo, Integer pageSize,String name);

    //Boolean save(String name,String phone,String email,Integer gender,String password,String icon);
    Boolean save(String name, String phone, String email, Integer gender, String password, MultipartFile file);

    Boolean update(String id,String name, String phone, String email, Integer gender, MultipartFile file);

    Boolean delete(String id, Boolean active);
    List<UmsAdmin> getActive();
    UmsAdmin getByUsername(String username, String password);
    String login(String username, String password);
}
