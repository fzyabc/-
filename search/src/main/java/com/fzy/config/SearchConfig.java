package com.fzy.config;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpPost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SearchConfig {
    @Value("${elasticsearch.host}")
   String host;
    @Value("${elasticsearch.port}")
   int port;
    @Bean
    public RestHighLevelClient getRestHighLevelClient () {

        return new RestHighLevelClient(RestClient.builder(new HttpHost(host,port,"http")));

    }
}
