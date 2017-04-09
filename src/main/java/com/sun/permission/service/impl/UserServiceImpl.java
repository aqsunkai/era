package com.sun.permission.service.impl;

import com.sun.permission.mapper.UserMapper;
import com.sun.permission.model.User;
import com.sun.permission.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by sun on 2017-1-15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Cacheable(value="common",key = "'id_'+#id")
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    //测试删除缓存
    @CacheEvict(value="common",key = "'id_'+#id")
    public User selectByPrimaryKey1(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    //添加缓存
    //@CachePut(value="common",key="#user.getUserName()")
    public void insertSelective(User user) {
        userMapper.insertSelective(user);
    }

    //删除缓存
    //@CacheEvict(value="common",key="'id_'+#id")
    public void deleteByPrimaryKey(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /*public void insertUsers(Map<String, Object> param){
        userMapper.insertUsers(param);
    }*/

    @Override
    public void test() {
        System.out.println("测试成功");
    }
}
