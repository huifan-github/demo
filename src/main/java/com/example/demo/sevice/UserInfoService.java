package com.example.demo.sevice;


import com.example.demo.domain.UserInfo;

public interface UserInfoService {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}