package com.redis.springboot_redis.repository;

import com.redis.springboot_redis.entity.Real_User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    public UserRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    public final RedisTemplate<String, Object> redisTemplate;

    private static final String KEY = "REAL_USER";

    //save user
    public Real_User saveUser(Real_User realUser){
        redisTemplate.opsForHash().put(KEY, realUser.getUserId(), realUser);
        return realUser;
    }

    //fetch all user
    public List<Real_User> findAll(){
        Map<Object, Object> userdata = redisTemplate.opsForHash().entries(KEY);
        Collection<Object> objectCollection = userdata.values();
        return objectCollection.stream().map(Real_User.class::cast).toList();
    }


    //fetch single user
    public Real_User getUser(String userId){
        return (Real_User) redisTemplate.opsForHash().get(KEY, userId);
    }

    //delete user
    public void deleteUser(String userId){
        redisTemplate.opsForHash().delete(KEY, userId);
    }
}
