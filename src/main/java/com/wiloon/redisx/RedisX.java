package com.wiloon.redisx;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

import java.util.Set;

/**
 * Created by Administrator on 2015/7/16.
 */
public class RedisX {
    public static void main(String[] args) {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            /// ... do stuff here ... for example
            jedis.set("foo", "bar");
            String foobar = jedis.get("foo");
            System.out.println("foobar=" + foobar);
            jedis.zadd("sose", 0, "car");
            jedis.zadd("sose", 0, "bike");
            Set<String> sose = jedis.zrange("sose", 0, -1);

            jedis.watch ("foo");
            Transaction t = jedis.multi();
            t.set("foo", "bar");
            t.exec();

        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
/// ... when closing your application:
        pool.destroy();


//        Jedis jedis = new Jedis("localhost");
//        //jedis.set("foo", "bar");
//        String value = jedis.get("foo");
//        System.out.println("value=" + value);
    }
}
