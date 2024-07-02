package com.fzy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fzy.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author fzy
 * @since 2024-06-03
 */
@TableName("pms_file")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PmsFile extends BaseEntity {

    private static final long serialVersionUID = 1L;



    /**
     * 文件md5
     */
    private String md5;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件路径
     */
    private String path;


}
