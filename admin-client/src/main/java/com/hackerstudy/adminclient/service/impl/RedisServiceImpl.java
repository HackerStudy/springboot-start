package com.hackerstudy.adminclient.service.impl;

import com.alibaba.fastjson.JSON;
import com.hackerstudy.adminclient.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @class: RedisServiceImpl
 * @description:
 * @author: Administrator
 * @date: 2019-04-13 16:17
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public <T> boolean set(String key, T value) throws Exception{
        //任意类型转换成String
        String val = JSON.toJSONString(value);

        if(val==null||val.length()<=0){
            return false;
        }

        stringRedisTemplate.opsForValue().set(key,val);
        return true;
    }

    @Override
    public <T> T get(String key, Class<T> clazz) throws Exception{
        String value = stringRedisTemplate.opsForValue().get(key);
        return JSON.toJavaObject(JSON.parseObject(value),clazz);
    }

    @Override
    public void delete(String... keys) throws Exception {
        if(keys!=null&&keys.length>0){
            if(keys.length==1){
                stringRedisTemplate.delete(keys[0]);
            }else{
                stringRedisTemplate.delete(CollectionUtils.arrayToList(keys));
            }
        }
    }
}
