package com.grouptwo.isrp.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 张洪志
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // 设置key的序列化方式
        template.setKeySerializer(RedisSerializer.string());
        // 设置value的序列化方式
        template.setValueSerializer(RedisSerializer.json());
        // 设置hash的key的序列化方式
        template.setHashKeySerializer(RedisSerializer.string());
        // 设置hash的value的序列化方式
        template.setHashValueSerializer(RedisSerializer.json());

        template.afterPropertiesSet();
        return template;
    }


    public class RedisConfiguration {

        private Integer maxTotal=20;
        @Value("${spring.redis.host}")
        private String host;
        @Value("${spring.redis.port}")
        private Integer port;

        public JedisPoolConfig jedisPoolConfig(){    //这个是修改redis性能的时候需要的对象
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(maxTotal);
            return jedisPoolConfig;
        }

        @Bean  //这个注解注入工厂的名称是方法名
        public JedisPool jedisPool(){
            JedisPoolConfig jedisPoolConfig = jedisPoolConfig();
            return new JedisPool(jedisPoolConfig,host,port);
        }
    }


}
