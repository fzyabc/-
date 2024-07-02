package com.fzy.test01;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fzy.AdminApp;
import com.fzy.entity.UmsAdmin;
import com.fzy.service.UmsAdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApp.class)
public class MyBatisTest {

    @Resource
    UmsAdminService umsAdminService;
@Resource(name = "MyRedisTemplate")
    RedisTemplate<String,Object> redisTemplate;
    @Test
    public void handler () {

        List<UmsAdmin> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {

            /*
                this.name = name;
                this.phone = phone;
                this.email = email;
                this.gender = gender;
                this.password = password;
                this.icon = icon;
             */
            /*list.add(new UmsAdmin(
                    "动力节点" + i,
                    "123" + i,
                    i + "@qq.com",
                    i % 2,
                    "123456",
                    i % 2 == 0,
                    "pic-url"
            ));*/

        }
        umsAdminService.saveBatch(list);
    }
@Test
    public void test01() {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    System.out.println(bCryptPasswordEncoder.encode("123456"));
    }
    @Test
    public void test02() {
      String token=JWT.create().withClaim("id",123456)
                .sign(Algorithm.HMAC256("fzy"));
        System.out.println(token);
    }
    @Test
    public void test03() {
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",17629584677L);
   umsAdminService.remove(queryWrapper);


    }
}

















