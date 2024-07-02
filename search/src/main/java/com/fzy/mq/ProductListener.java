package com.fzy.mq;


import com.alibaba.fastjson.JSONObject;
import com.fzy.canal.CanalData;
import com.fzy.entity.PmsProduct;
import com.fzy.service.SearchService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ProductListener {
    @Resource
    SearchService searchService;
    @RabbitListener(queues = "productqueue")
    public void ListenerProduct(Message message) {
        String body=new String(message.getBody());
        CanalData canalData= JSONObject.parseObject(body,CanalData.class);
      if ("update".equals(canalData.getType())){
          //取得老数据（只有被更新的字段）
          List<String> olds=canalData.getOld();
          //取得新数据（全部字段）
          List<String> datas=canalData.getData();
          for (int i=0;i<olds.size();i++){
              PmsProduct old=JSONObject.parseObject(olds.get(i),PmsProduct.class);
              PmsProduct pmsProduct=JSONObject.parseObject(datas.get(i),PmsProduct.class);
          }
//          if (null != olds.get){
//
//          }
      }

    }
}
