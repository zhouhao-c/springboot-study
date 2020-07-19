package com.zh.springbootcache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 一.基本环境搭建
 * 1.建数据库，建表
 * 2.创建JavaBean封装数据
 * 3.整合MyBatis操作数据库
 *      1.配置数据源信息:
 *          (1).@MapperScan指定需要扫描的mapper接口所在包
 * 二.缓存实验
 *      步骤：
 *      1.开启基于注解的缓存 @EnableCaching
 *      2.标注缓存注解
 *          @Cachecble
 *          @CacheEvict
 *          @CachePut
 * 三.整合Redis作为缓存
 *      1.安装redis：使用docker
 *      2.引入redis的starter
 *      3.配置redis
 *      4.测试缓存
 *          原理:
 *          1.引入redis的starter后,容器中保存的就是RedisCacheManager
 *          2.RedisCacheManager帮助创建RedisCache作为缓存组件,RedisCache通过redis来操作缓存数据
 *          3.默认保存数据 k-v 都是对象时利用jdk自带序列化保存数据
 *          4.自定义CacheManager
 *
 */
@MapperScan("com.zh.springbootcache.mapper")
@SpringBootApplication
@EnableCaching
public class SpringbootCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCacheApplication.class, args);
    }

}
