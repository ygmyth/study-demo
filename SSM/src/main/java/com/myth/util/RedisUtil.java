//package com.myth.util;
//
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.com.myth.annotation.Autowired;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//import java.util.List;
//
////@Service
//@Slf4j
//public class RedisUtil {
//    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
//
//    @Autowired
//    private JedisPool jedisPool;
//
//    public  String setEx(String key,String value,int exTime){
//        Jedis jedis = null;
//        String result = null;
//        try {
//            jedis = jedisPool.getResource();
//            result = jedis.setex(key,exTime,value);
//            return result;
//        } catch (Exception e) {
//            log.error("setex key:{} value:{} error",key,value,e);
//        } finally {
//            release(jedis);
//        }
//
//        return result;
//    }
//
//    public Long expire(String key, int expireTime) {
//        Jedis jedis = null;
//        Long result = null;
//        try {
//            jedis = jedisPool.getResource();
//            result = jedis.expire(key, expireTime);
//        } catch (Exception e) {
//            log.info("expire key {} error", key, e);
//        } finally {
//            release(jedis);
//        }
//        return result;
//    }
//
//    public Long del(String key) {
//        Jedis jedis = null;
//        Long result = null;
//        try {
//            jedis = jedisPool.getResource();
//            result = jedis.del(key);
//        } catch (Exception e) {
//            logger.error("del key Error:" + e);
//        } finally {
//            release(jedis);
//        }
//        return result;
//    }
//
//    public String get(String key) {
//        Jedis jedis = null;
//        String result = null;
//        try {
//            jedis = jedisPool.getResource();
//            result = jedis.get(key);
//            return result;
//        } catch (Exception e) {
//            logger.error("del key Error:" + e);
//        } finally {
//            release(jedis);
//        }
//        return result;
//    }
//    public boolean set(String key, String value) {
//        //todo 添加字符为空判断
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            key = jedis.set(key, value);
//            return "OK".equalsIgnoreCase(key);
//        } catch (Exception e) {
//            logger.error("Write String Value To Redis Error:" + e);
//        } finally {
//            release(jedis);
//        }
//        return false;
//    }
//
//    public long sadd(String key, String value) {
//        //todo 添加字符为空判断
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            long t = jedis.sadd(key, value);
//            return t;
//        } catch (Exception e) {
//            logger.error("Add String Value To Redis Error:" + e);
//        } finally {
//            release(jedis);
//        }
//        return 0;
//    }
//    public long srem(String key, String value) {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            return jedis.srem(key, value);
//        } catch (Exception e) {
//            logger.error("srem to redis error" + e.getMessage());
//        } finally {
//            release(jedis);
//        }
//        return 0;
//    }
//    public long scard(String key) {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            return jedis.scard(key);
//        } catch (Exception e) {
//            logger.error("scard error" + e.getMessage());
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//        return 0;
//    }
//
//    public boolean sismember(String key, String value) {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            return jedis.sismember(key, value);
//        } catch (Exception e) {
//            logger.error("发生异常" + e.getMessage());
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//        return false;
//    }
//
//    public List<String> brpop(int timeout, String key) {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            return jedis.brpop(timeout, key);
//        } catch (Exception e) {
//            logger.error("brpop error" + e.getMessage());
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//        return null;
//    }
//
//    public long lpush(String key, String value) {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            return jedis.lpush(key, value);
//        } catch (Exception e) {
//            logger.error("lpush" + e.getMessage());
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//        return 0;
//    }
//    private void release(Jedis jedis) {
//        if(jedis != null) {
//            jedis.close();
//        }
//    }
//}
