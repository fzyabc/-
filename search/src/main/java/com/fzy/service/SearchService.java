package com.fzy.service;

import com.fzy.vo.PageInfo;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.math.BigDecimal;

public interface SearchService {
PageInfo list(Integer pageNo, Integer pageSize,String name) throws IOException;
boolean add(String id, String name, BigDecimal price,String src) throws IOException;
    boolean update(String id, String name, BigDecimal price,String src) throws IOException;
    boolean del(String id) throws IOException;
}
