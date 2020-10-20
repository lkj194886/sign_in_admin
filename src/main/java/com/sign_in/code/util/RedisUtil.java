package com.sign_in.code.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.TimeUnit;

/**
 * @Classname RedisClient
 * @Description Redis操作类
 * @Date 2020/8/11 19:08
 * @Created by wgg
 */
@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JedisPool jedisPool;
    private Jedis jedis = null;

    /**
     * 向redis中设值
     *
     * @param key   使用 a:b:id的形式在使用rdm进行查看redis情况时会看到分层文件夹的展示形式，便于管理
     * @param value
     * @return
     */
    public boolean set(String key, String value) {
        boolean result = false;
        jedis = jedisPool.getResource();
        try {

            jedis.set(key,value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            jedis.close();
        }
        return result;
    }

    public boolean set(byte[] key, byte[] object) {
        boolean result = false;
        jedis = jedisPool.getResource();
        try {
            jedis.set(key, object);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jedis.close();
        }
        return result;
    }



    /**
     * 向redis中设置，同时设置过期时间
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean set(String key, String value, int time) {
        boolean result = false;
        jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            expric(key, time);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            jedis.close();
        }
        return result;
    }

    /**
     * 获取redis中的值,byte[]
     *
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        byte[] result = null;
        jedis=jedisPool.getResource();
        try {
            result = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            jedis.close();
        }
        return result;
    }

    /**
     * 获取redis中的值,String
     *
     * @param key
     * @return
     */
    public String get(String key) {
        String result = null;
        Jedis jedis=jedisPool.getResource();
        try {
            result = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jedis.close();
        }
        return result;
    }

    /**
     * 设置key的过期时间
     *
     * @param key
     * @param time
     * @return
     */
    public boolean expric(String key, int time) {
        boolean result = false;
        jedis = jedisPool.getResource();
        try {
            if (time > 0) {
//                redisTemplate.expire(key, time, TimeUnit.SECONDS);
                jedis.expire(key,  time);
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据key删除对应value
     *
     * @param key
     * @return
     */
    public boolean remove(String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
