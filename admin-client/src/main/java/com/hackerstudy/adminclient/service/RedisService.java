package com.hackerstudy.adminclient.service;

/**
 * @class: RedisService
 * @description: 简单的缓存操作service
 * @author: Administrator
 * @date: 2019-04-13 16:16
 */
public interface RedisService {
    /**
     * 保存数据到redis
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    <T> boolean set(String key ,T value) throws Exception;

    /**
     * 从redis获取数据
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T get(String key,Class<T> clazz) throws Exception;

    /**
     * 删除指定key的值
     * @param keys
     * @throws Exception
     */
    void delete(String ... keys) throws Exception;
}
