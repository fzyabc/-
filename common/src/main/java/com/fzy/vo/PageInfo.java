package com.fzy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {
    private List<?> list;
    private Integer pageNo;
    private Integer pageSize;
    private Long total;
}
