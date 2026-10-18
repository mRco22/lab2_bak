package org.example.service;
import org.example.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService
{
    private final Map<Integer,UserEntity> users = new HashMap<>();

    public Map<Integer, UserEntity> getAllUsers()
    {
        return users;
    }

    public UserEntity getUserById(int id)
    {
        return users.get(id);
    }

    public UserEntity addUser(UserEntity user)
    {
        users.put(user.getId(), user);
        return user;
    }

    public UserEntity updateUser(int id, UserEntity user)
    {
        UserEntity oldUser = users.get(id);

        if (oldUser == null)
        {
            return null;
        }

        oldUser.setName(user.getName());
        return oldUser;
    }
    public UserEntity deleteUser(int id)
    {
        return users.remove(id);
    }

}