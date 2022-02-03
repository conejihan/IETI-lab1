package com.example.springboot.service.impl;

import com.example.springboot.data.User;

import com.example.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class UserServiceHashMap implements UserService {
    private HashMap<String, User> users = new HashMap<String, User>();
    private Integer IdGenerate = 1;
    @Override
    public User create(User user) {
        user.setId(IdGenerate.toString());
        users.put(IdGenerate.toString(),user);
        return user;
    }

    @Override
    public User findById(String id) {
        return users.get(id);
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<User>();
        for(String id : users.keySet()){
            allUsers.add(users.get(id));
        }
        return allUsers;
    }

    @Override
    public void deleteById(String id) {
        users.remove(id);
        
    }

    @Override
    public User update(User user, String userId) {
        return users.replace(userId,user);
    }
}
