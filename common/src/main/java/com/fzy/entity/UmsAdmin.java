package com.fzy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fzy.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author fzy
 * @since 2024-05-27
 */
@TableName("ums_admin")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UmsAdmin extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public UmsAdmin(String name, String phone, String email, Integer gender, String password, String icon) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.icon = icon;
        this.active = true;
    }
    //修改用
    public UmsAdmin(String id,String name, String phone, String email, Integer gender) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.icon = icon;
        this.active = true;
    }

    //启用禁用 用 构造方法
    public UmsAdmin(String id, Boolean active) {
        super(id);
        this.active = active;
    }
    /**
     * 用户姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 1-男 0-女
     */
    private Integer gender;

    /**
     * 登录密码
     */
    @JsonIgnore
    private String password;

    /**
     * 状态
     */
    private Boolean active;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 头像路径
     */
    private String icon;




}
