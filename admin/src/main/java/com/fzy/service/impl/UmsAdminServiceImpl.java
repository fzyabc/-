package com.fzy.service.impl;

//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fzy.core.FException;
import com.fzy.entity.UmsAdmin;
import com.fzy.entity.UmsRole;
import com.fzy.feign.FileService;
import com.fzy.mapper.UmsAdminMapper;
import com.fzy.service.UmsAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzy.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author zs
 * @since 2024-05-27
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements UmsAdminService {

    @Resource
    UmsAdminMapper umsAdminMapper;
@Resource
    PasswordEncoder passwordEncoder;
    @Resource
    FileService fileService;

    @Override
    public IPage<UmsAdmin> getUmsAdminList(Integer pageNo, Integer pageSize,String name) {

        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();

        if(StringUtils.isNotBlank(name)){ //排除掉空串和空值
            wrapper.like("name",name.trim()); //根据姓名进行模糊查询

        }

        wrapper.orderByDesc("sort");

        return this.page(new Page<>(pageNo,pageSize),wrapper);
    }

    /*@Override
    public Boolean save(String name, String phone, String email, Integer gender, String password, String icon) {

        return this.save(new UmsAdmin(name,phone,email,gender,password,icon));
    }*/

    @Override
    public Boolean save(String name, String phone, String email, Integer gender, String password, MultipartFile file) {

        //在minio中保存图片
        String icon = fileService.impageUpload(file);

        //在mysql保存基本信息（包含异常处理过的icon）
        return this.save(new UmsAdmin(name,phone,email,gender,passwordEncoder.encode(password),icon));
    }

    @Override
    public Boolean update(String id, String name, String phone, String email, Integer gender, MultipartFile file) {

        UmsAdmin umsAdmin = new UmsAdmin(id,name,phone,email,gender);

        if(null != file){
            String icon = fileService.impageUpload(file);
            umsAdmin.setIcon(icon);
        }

        return this.updateById(umsAdmin);
    }

    @Override
    public Boolean delete(String id, Boolean active) {
return this.updateById(new UmsAdmin(id,active));
}

    @Override
    public List<UmsAdmin> getActive() {
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("active",true);
     return this.list(queryWrapper);
    }

    @Override
    public UmsAdmin getByUsername(String username, String password) {
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",username).or().eq("email",username);
        UmsAdmin umsAdmin = this.getOne(queryWrapper);
        if(null == umsAdmin || !passwordEncoder.matches(password,umsAdmin.getPassword())){
            throw new FException("用户名或密码错误");
        }
       if (!umsAdmin.getActive()){
throw new FException("用户被禁用,请联系管理员");
       }

return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        UmsAdmin umsAdmin = this.getByUsername(username,password);
        return JwtUtils.createToken(umsAdmin.getId());
    }


}


















