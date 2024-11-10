package com.redis.springboot_redis.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Real_User implements Serializable {

    private String userId;
    private String name;
    private String phone;
    private String email;

}
