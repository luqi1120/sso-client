package com.ohaotian.ssoclientrest.service;

import com.ohaotian.ssoclientrest.dao.User;
import com.ohaotian.ssoclientrest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created: luQi
 * Date:2018-4-27 10:32
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username) {
//        User user = new User(3, "root", "63a9f0ea7bb98050796b649e85481845", "333@33.com", "18379823719");
        return userMapper.select(username);
    }
}
