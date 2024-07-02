package com.fzy;

import com.alibaba.fastjson.JSONObject;
import com.fzy.search.SearchProduct;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchResponseSections;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchTest

{
    @Resource
    RestHighLevelClient client;
    @Test
    public void createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("product");

        request.mapping("{\n" +
                "    \"properties\": {\n" +
                "      \"id\": {\n" +
                "        \"type\": \"keyword\"\n" +
                "      },\n" +
                "      \"name\": {\n" +
                "        \"type\": \"text\",\n" +
                "        \"analyzer\": \"ik_max_word\"\n" +
                "      },\n" +
                "      \"price\": {\n" +
                "        \"type\": \"float\"\n" +
                "      },\n" +
                "      \"src\": {\n" +
                "        \"type\": \"keyword\"\n" +
                "      }\n" +
                "    }\n" +
                "  }", XContentType.JSON);
client.indices().create(request, RequestOptions.DEFAULT);
    }
    //测试添加数据
    @Test
    public void insert() throws IOException {
        IndexRequest request = new IndexRequest("product");
        SearchProduct product = new SearchProduct(
                "1",
                "OPPO Find X7 Ultra 16GB+512GB 海阔天空 1英寸双潜望四主摄 哈苏影像 第三代骁龙8 5.5G 拍照 AI手机",
                new BigDecimal("6499.00"),
                "aaa");
        request.id("1").source(JSONObject.toJSONString(product), XContentType.JSON);
        client.index(request, RequestOptions.DEFAULT);
    }
    //测试修改数据
    @Test
    public void update() throws IOException {
        UpdateRequest request = new UpdateRequest("product","1");
        SearchProduct product = new SearchProduct();
        product.setPrice(new BigDecimal("4999.00"));
        request.doc(JSONObject.toJSONString(product),XContentType.JSON);
        client.update(request,RequestOptions.DEFAULT);

    }
    //测试批量添加数据
    @Test
    public void batchAdd () throws IOException {

        BulkRequest request = new BulkRequest();
        List<SearchProduct> list = new ArrayList<>();
        list.add(new SearchProduct("1",
                "OPPO K10x 极光 8GB+256GB 67W超级闪充 5000mAh长续航 120Hz高帧屏 6400万三摄 高通骁龙695 拍照 5G手机",
                new BigDecimal("1499.00"),
                "abc"));
        list.add(new SearchProduct("2",
                "荣耀X40 120Hz OLED硬核曲屏 5100mAh 快充大电池 7.9mm轻薄设计 5G手机 8GB+128GB 彩云追月",
                new BigDecimal("1599.00"),
                "abc"));
        list.add(new SearchProduct("3",
                "荣耀90 2亿像素写真相机 零风险调光护眼屏 5000mAh轻薄长续航 12GB+256GB 星钻银 5G",
                new BigDecimal("1599.00"),
                "abc"));
        list.add(new SearchProduct("4",
                "Redmi Note 11 5G 天玑810 33W Pro快充 5000mAh大电池 6GB +128GB 神秘黑境 智能手机 小米 红米",
                new BigDecimal("899.00"),
                "abc"));
        list.add(new SearchProduct("5",
                "vivo X90s 12GB+256GB 青漾 天玑9200+旗舰芯片 新一代自研影像芯片V2 120W双芯闪充 蔡司影像 5G 拍照 手机",
                new BigDecimal("4299.00"),
                "abc"));
        list.add(new SearchProduct("6",
                "vivo iQOO Neo8 12GB+256GB 夜岩 第一代骁龙8+ 自研芯片V1+ 120W超快闪充 144Hz高刷 5G游戏电竞性能手机",
                new BigDecimal("2499.00"),
                "abc"));
        list.add(new SearchProduct(
                "7",
                "vivo X90s 12GB+256GB  彩云青漾 天玑9200+旗舰芯片 新一代自研影像芯片V2 120W双芯闪充 蔡司影像追月 5G 拍照 手机",
                new BigDecimal("4299.00"),
                "jfkdjka"
        ));

        for (SearchProduct product : list) {

            IndexRequest indexRequest = new IndexRequest("product");
            indexRequest.id(product.getId());
            indexRequest.source(JSONObject.toJSONString(product),XContentType.JSON);
            request.add(indexRequest);

        }
        client.bulk(request,RequestOptions.DEFAULT);

    }
    //测试查询数据
    @Test
    public void query() throws IOException {
        SearchRequest request = new SearchRequest("product");
        //构建查询方式
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery("name","彩云追月"));
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHit[] hits= response.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());

        }
    }
    //高亮显示
    @Test
    public void highlight() throws IOException {
        SearchRequest request = new SearchRequest("product");
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name").preTags("<strong style='color:#ff0000'>").postTags("</strong>");
    SearchSourceBuilder builder = new SearchSourceBuilder();
    builder.query(QueryBuilders.termQuery("name","彩云追月")).highlighter(highlightBuilder);
    builder.highlighter(highlightBuilder);
    request.source(builder);
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    SearchHit[] hits= response.getHits().getHits();
    for (SearchHit hit : hits) {
        System.out.println(hit.getSourceAsString());
        System.out.println(hit.getHighlightFields().get("name").getFragments()[0].toString());
    }
    }

}
