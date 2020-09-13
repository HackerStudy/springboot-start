package com.hackerstudy.adminclient.controller.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hackerstudy.adminclient.common.vo.InData;
import com.hackerstudy.adminclient.common.vo.JSONResult;
import com.hackerstudy.adminclient.pojo.User;
import com.hackerstudy.adminclient.service.RedisService;
import com.hackerstudy.adminclient.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @class: RedisController
 * @description:
 * @author: Administrator
 * @date: 2019-04-13 15:33
 */
@Api(tags = "Redis数据接口")
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisService redisService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value="添加(key-value)", notes="添加(key-value)", produces="application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "键",required = true),
            @ApiImplicitParam(name = "value", value = "值", required = true)}
    )
    @PostMapping("/addKeyValue")
    public JSONResult<?> addKeyValue(@RequestParam("key") String key, @RequestParam("value") String value){
        stringRedisTemplate.opsForValue().set(key,value);
        return JSONResult.ok("添加成功");
    }

    @ApiOperation(value="添加用户信息", notes="添加用户信息", produces="application/json")
    @PostMapping("/addUser")
    public JSONResult<?> addUser(){
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setAge(24);
        user.setPassword("123456");
        user.setBirthday(new Date());
        user.setDesc("用户张三的信息");
        stringRedisTemplate.opsForValue().set("admin-client:user",JSON.toJSONString(user)); //多目录的key的设置
        Object getUser = JSON.parse(stringRedisTemplate.opsForValue().get("admin-client:user"));
        return JSONResult.ok("添加成功",getUser);
    }

    /**
     * 获取redis的数据通过key
     * @param key
     * @return
     * @throws Exception
     */
    @ApiOperation(value="获取Redis的键值对", notes="获取Redis的键值对", produces="application/json")
    @ApiImplicitParam(name = "key", value = "键", required = true)
    @GetMapping("/getRedisDataByKey")
    public JSONResult<?> getRedisData(String key) throws Exception{
        Object obj = redisService.get(key,Object.class);
        return JSONResult.ok("获取成功",obj);
    }

    @ApiOperation(value="添加Redis的键值对", notes="添加Redis的键值对", produces="application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "键",required = true),
            @ApiImplicitParam(name = "obj", value = "值", required = true)}
    )
    @PostMapping("/setRedisData")
    public JSONResult<?> setRedisData(@RequestBody Object obj, @RequestParam("key") String key ) throws Exception{
        boolean flag = redisService.set(key,obj);
        if(!flag){
            return JSONResult.error("添加失败");
        }
        return JSONResult.ok("添加成功");
    }

    @ApiOperation(value="添加Redis的键值对(json)", notes="添加Redis的键值对(json)", produces="application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "obj", value = "json值",required = true)}
    )
    @PostMapping("/setRedisDataByJson")
    public JSONResult<?> setRedisDataByJson(@RequestBody JSONObject obj) throws Exception{
        JSONObject inMap = obj.getJSONObject("inMap");
        JSONObject data = inMap.getJSONObject("data");
        String key = data.getString("key");
        User user = data.getObject("user",User.class);
        boolean flag = redisService.set(key,user);
        if(!flag){
            return JSONResult.error("添加失败");
        }
        return JSONResult.ok("添加成功");
    }

    @ApiOperation(value="添加Redis的键值对(InData)", notes="添加Redis的键值对(InData)", produces="application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inData", value = "inData",required = true)}
    )
    @PostMapping("/setRedisDataByInData")
    public JSONResult<?> setRedisDataByInData(@RequestBody InData inData) throws Exception{
        Map<String,Object> inMap = inData.getInMap();
        Map<String,Object> data = (Map<String,Object>) inMap.get("data");
        String key = (String) data.get("key");
        Object obj = data.get("user");
        boolean flag = redisService.set(key,obj);
        if(!flag){
            return JSONResult.error("添加失败");
        }
        return JSONResult.ok("添加成功");
    }

    @ApiOperation(value="删除Redis的值(批量)", notes="删除Redis的值(批量)", produces="application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "key",required = true)}
    )
    @DeleteMapping("/DeleteRedisDataByKey")
    public JSONResult<?> DeleteRedisDataByKey(@RequestBody String ... key) throws Exception{
        redisUtil.del(key);
        return JSONResult.ok("删除成功");
    }

    @DeleteMapping("/DeleteRedisDataByKeys")
    public JSONResult<?> DeleteRedisDataByKeys(@RequestBody String ... keys) throws Exception{
        redisService.delete(keys);
        return JSONResult.ok("删除成功");
    }

    /**
     * 获取指定key的过期时间
     * @return
     */
    @ApiOperation(value="获取Redis的到期时间", notes="获取Redis的到期时间", produces="application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "key",required = true)}
    )
    @GetMapping("/getExpire")
    public JSONResult<?> getExpire(@RequestParam("key") String key){
        return JSONResult.ok("过期时间",redisUtil.getExpire(key));
    }

    /**
     * 普通缓存放入并设置时间
     * @return
     */
    @ApiOperation(value="设置Redis的到期时间", notes="设置Redis的到期时间", produces="application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "key",required = true),
            @ApiImplicitParam(name = "time", value = "时间",required = true),
            @ApiImplicitParam(name = "obj", value = "值",required = true)
    }
    )
    @PostMapping("/setKeyValueAndTime")
    public JSONResult<?> setKeyValueAndTime(@RequestParam("key") String key,@RequestParam("time") long time,@RequestBody Object obj){
        return JSONResult.ok("保存成功",redisUtil.set(key,obj,time));
    }

    /**
     * 递增
     * @return
     */
    @PostMapping("/incr")
    public JSONResult<?> incr(@RequestParam("key") String key,@RequestParam("delta") long delta){
        return JSONResult.ok("增加",redisUtil.incr(key,delta));
    }

}
