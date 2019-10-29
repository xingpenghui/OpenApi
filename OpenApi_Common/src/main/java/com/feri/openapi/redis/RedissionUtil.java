package com.feri.openapi.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @program: OpenApi
 * @description: 基于Redission Redis数据库
 * @author: Feri
 * @create: 2019-10-29 14:13
 */
public class RedissionUtil {
    private static RedissonClient redisson;
    static {
        //1、创建配置对象
        Config config=new Config();
        //2、设置地址和密码 还有连接池信息
        config.useSingleServer().setAddress("39.105.189.141:6380").setPassword("qfjava").setConnectionPoolSize(5);
        //3、实例化客户端对象
        redisson=Redisson.create(config);
    }
    //新增-字符串
    public static void setStr(String key,String msg,long seconds){
        if(seconds>0) {
            redisson.getBucket(key).set(msg, seconds, TimeUnit.SECONDS);
        }else {
            redisson.getBucket(key).set(msg);
        }
    }
    //新增--Hash
    public static void setHash(String key, Map<String,Object> map){
        redisson.getMap(key).putAll(map);
    }
    //新增-List
    public static void setList(String key, List<Object> list){
        redisson.getList(key).addAll(list);
    }
    //新增Set
    public static void setSet(String key, Set<Object> set){
        redisson.getSet(key).addAll(set);
    }
    //新增ZSet
    public static void setZset(String key,Map<Object,Double> map){
        redisson.getScoredSortedSet(key).addAll(map);
    }

    //删除
    public static void removeStr(String key){
        redisson.getKeys().delete(key);
    }
    //删除-Hash中的某个元素
    public static void removeHash(String key,String field){
        redisson.getMap(key).remove(field);
    }
    //查询
    public static String getStr(String key){
        return  (String) redisson.getBucket(key).get();
    }
    //查询集合
    public static List<Object> getList(String key){
        return redisson.getList(key).readAll();
    }
    //其他
    //设置有效期
    public static void expire(String key,long seconds){
        redisson.getKeys().expire(key,seconds,TimeUnit.SECONDS);
    }
    //校验指定的Key存在
    public static boolean checkKey(String key){
        return redisson.getKeys().countExists(key)>0;
    }
    //setnx
    //加锁
    public static RLock lock(String key){
        RLock rLock=redisson.getLock(key);
        return rLock;
    }
    //解锁
    public static void unlock(RLock lock){
        lock.unlock();
    }
}
