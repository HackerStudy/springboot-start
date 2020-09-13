package com.hackerstudy.adminclient.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @class: RedisUtil
 * @description:
 * @author: Administrator
 * @date: 2019-04-13 17:08
 */
@Component
public class RedisUtil {

//    @Autowired
//    RedisTemplate<String,Object> redisTemplate;

//    @Autowired
//    RedisTemplate redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    StringRedisTemplate redisTemplate;


    //=============================common============================
    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key,long time){
        try {
            if(time>0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String ... key){
        if(key!=null&&key.length>0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    //============================String=============================
    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public <T> T get(String key,Class<T> clazz){
        return key==null?null:JSON.toJavaObject(JSON.parseObject(redisTemplate.opsForValue().get(key)),clazz);
    }

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key,Object value) {
        try {
            String val = JSON.toJSONString(value);
            if(val==null||val.length()<=0){
                throw new Exception("传入的value为空");
            }
            redisTemplate.opsForValue().set(key, val);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }

    }

    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key,Object value,long time){
        try {
            if(time>0){
                String val = JSON.toJSONString(value);
                if(val==null||val.length()<=0){
                    throw new Exception("传入的value为空");
                }
                redisTemplate.opsForValue().set(key, val, time, TimeUnit.SECONDS);
            }else{
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 递增
     * @param key 键
     * @param delta 要增加几(大于0)
     * @return
     */
    public long incr(String key, long delta){
        if(delta<0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key 键
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta){
        if(delta<0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    //================================Map=================================
    /**
     * HashGet
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key,String item){
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object,Object> hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String,Object> map){
        try {
            Map<String,String> sMap = new HashMap<>();
            for(Map.Entry<String,Object> entry:map.entrySet()){
                sMap.put(entry.getKey(),JSON.toJSONString(entry.getValue()));
            }
            redisTemplate.opsForHash().putAll(key, sMap);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     * @param key 键
     * @param map 对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String,Object> map, long time){
        try {
            Map<String,String> sMap = new HashMap<>();
            for(Map.Entry<String,Object> entry:map.entrySet()){
                sMap.put(entry.getKey(),JSON.toJSONString(entry.getValue()));
            }
            redisTemplate.opsForHash().putAll(key, sMap);
            if(time>0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key,String item,Object value) {
        try {
            String val = JSON.toJSONString(value);
            redisTemplate.opsForHash().put(key, item, val);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @param time 时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key,String item,Object value,long time) {
        try {
            String val = JSON.toJSONString(value);
            redisTemplate.opsForHash().put(key, item, val);
            if(time>0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 删除hash表中的值
     * 删除变量中的键值对，可以传入多个参数，删除多个键值对
     * @param key 键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item){
        redisTemplate.opsForHash().delete(key,item);
    }

    /**
     * 判断hash表中是否有该项的值
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item){
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @param key 键
     * @param item 项
     * @param by 要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item,double by){
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     * @param key 键
     * @param item 项
     * @param by 要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item,double by){
        return redisTemplate.opsForHash().increment(key, item,-by);
    }

    //============================set=============================
    /**
     * 根据key获取Set中的所有值
     * @param key 键
     * @return
     */
    public <T> Set<T> sGet(String key,Class<T> clazz){
        try {
            Set<String> set = redisTemplate.opsForSet().members(key);
            Set<T> vSet = new HashSet<>();
            for(String s:set){
                vSet.add(JSON.parseObject(s,clazz));
            }
            return vSet;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     * @param key 键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key,Object value){
        try {
            String val = JSON.toJSONString(value);
            return redisTemplate.opsForSet().isMember(key, val);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     * @param key 键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object...values) {
        try {
            String[] vals = new String[values.length];
            int i=0;
            for(Object obj:values){
                vals[i] = JSON.toJSONString(obj);
                i++;
            }
            return redisTemplate.opsForSet().add(key, vals);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     * @param key 键
     * @param time 时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key,long time,Object...values) {
        try {
            String[] vals = new String[values.length];
            int i=0;
            for(Object obj:values){
                vals[i] = JSON.toJSONString(obj);
                i++;
            }
            Long count = redisTemplate.opsForSet().add(key, vals);
            if(time>0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     * @param key 键
     * @return
     */
    public long sGetSetSize(String key){
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return 0;
        }
    }

    /**
     * 移除值为value的
     * @param key 键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object ...values) {
        try {
            String[] vals = new String[values.length];
            int i=0;
            for(Object obj:values){
                vals[i] = JSON.toJSONString(obj);
                i++;
            }
            Long count = redisTemplate.opsForSet().remove(key, vals);
            return count;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return 0;
        }
    }
    //===============================list=================================

    /**
     * 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束  0 到 -1代表所有值
     * @return
     */
    public <T> List<T> lGet(String key,long start, long end,Class<T> tClass){
        try {
            List<String> stringList = redisTemplate.opsForList().range(key, start, end);
            List<T> tList = new ArrayList<T>();
            for(String s:stringList){
                tList.add(JSON.parseObject(s,tClass));
            }
            return tList;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     * @param key 键
     * @return
     */
    public long lGetListSize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     * @param key 键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key,long index){
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

//    /**
//     * 将list放入缓存
//     * @param key 键
//     * @param value 值
//     * @return
//     */
//    public boolean lSet(String key, Object value) {
//        try {
//            String val = JSON.toJSONString(value);
//            redisTemplate.opsForList().rightPush(key, val);
//            return true;
//        } catch (Exception e) {
//            logger.error(e.getMessage(),e);
//            return false;
//        }
//    }
//
//    /**
//     * 将list放入缓存
//     * @param key 键
//     * @param value 值
//     * @param time 时间(秒)
//     * @return
//     */
//    public boolean lSet(String key, Object value, long time) {
//        try {
//            String val = JSON.toJSONString(value);
//            redisTemplate.opsForList().rightPush(key, val);
//            if (time > 0) expire(key, time);
//            return true;
//        } catch (Exception e) {
//            logger.error(e.getMessage(),e);
//            return false;
//        }
//    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public <T> boolean lSet(String key, List<T> value) {
        try {
            List<String> stringList = new ArrayList<>();
            for(T obj:value){
                stringList.add(JSON.toJSONString(obj));
            }
            redisTemplate.opsForList().rightPushAll(key, stringList);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public <T> boolean lSet(String key, List<T> value, long time) {
        try {
            List<String> stringList = new ArrayList<>();
            for(T obj:value){
                stringList.add(JSON.toJSONString(obj));
            }
            redisTemplate.opsForList().rightPushAll(key, stringList);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index,Object value) {
        try {
            String val = JSON.toJSONString(value);
            redisTemplate.opsForList().set(key, index, val);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key,long count,Object value) {
        try {
            String val = JSON.toJSONString(value);
            Long remove = redisTemplate.opsForList().remove(key, count, val);
            return remove;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return 0;
        }
    }

}
