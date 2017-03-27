package com.sun.springBoot.service.Impl;

import com.sun.springBoot.mapper.UserMapper;
import com.sun.springBoot.model.User;
import com.sun.springBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by sun on 2017-1-15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //@Cacheable(value="common",key="'id_'+#id")
    public User selectByPrimaryKey(Integer id) {
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

    public void insertUsers(Map<String, Object> param){
        userMapper.insertUsers(param);
    }

    @Override
    public void test() {
        System.out.println("测试成功");
    }
}
