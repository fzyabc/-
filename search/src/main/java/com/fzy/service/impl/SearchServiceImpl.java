package com.fzy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fzy.search.SearchProduct;
import com.fzy.service.SearchService;
import com.fzy.vo.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {
    @Resource
    RestHighLevelClient client;
    @Override
    public PageInfo list(Integer pageNo, Integer pageSize, String name) throws IOException {
        List<SearchProduct> list = new ArrayList<>();
        Integer from=0;
        Integer size=0;
        from=(pageNo-1)*pageSize;
        size=pageSize;
        Long total=0L;
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name").preTags("<strong style='color: #ff0000'>").postTags("</strong>");
        SearchRequest searchRequest = new SearchRequest("product");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.highlighter(highlightBuilder).from(from).size(size);
        if (StringUtils.isNotBlank(name)){
            builder.query(QueryBuilders.matchQuery("name",name));
        }else {
            builder.query(QueryBuilders.matchAllQuery());
        }
        searchRequest.source(builder);
        SearchResponse searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits searchHits = searchResponse.getHits();
        total=searchHits.getTotalHits().value;
        SearchHit[] hitArray = searchHits.getHits();
        for (SearchHit hit : hitArray) {
            SearchProduct content = JSONObject.parseObject(hit.getSourceAsString(), SearchProduct.class);
            Map<String, HighlightField> map = hit.getHighlightFields();
            if(map.containsKey("name")){
                HighlightField field = map.get("name");
                String highlightName = field.getFragments()[0].toString();
                content.setName(highlightName);
            }
            list.add(content);
        }

        return new PageInfo(list,pageNo,pageSize,total);
    }

    @Override
    public boolean add(String id, String name, BigDecimal price, String src) throws IOException {
        IndexRequest request = new IndexRequest("product");

        SearchProduct product = new SearchProduct(id, name, price, src);

        request.id(id);
        request.source(JSONObject.toJSONString(product), XContentType.JSON);
        client.index(request,RequestOptions.DEFAULT);

        return true;
    }

    @Override
    public boolean update(String id, String name, BigDecimal price, String src) throws IOException {
        SearchProduct product = new SearchProduct(id, name, price, src);

        UpdateRequest request = new UpdateRequest("product",id);
        request.doc(JSONObject.toJSONString(product),XContentType.JSON);
        client.update(request,RequestOptions.DEFAULT);

        return true;
    }

    @Override
    public boolean del(String id) throws IOException {
        DeleteRequest request = new DeleteRequest("product",id);
        client.delete(request,RequestOptions.DEFAULT);

        return true;
    }
}
