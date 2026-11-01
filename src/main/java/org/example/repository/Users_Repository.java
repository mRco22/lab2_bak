package org.example.repository;

import org.example.entity.Users_Entity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class Users_Repository
{
    private final Map<Integer, Users_Entity> usersEntityMap =
            new HashMap<>();
    private int nextId = 1;

    public List<Users_Entity> findAll()
    {
        return new ArrayList<>(usersEntityMap.values());
    }

    public Users_Entity findById(int id)
    {
        return usersEntityMap.get(id);
    }

    public Users_Entity deleteById(int id)
    {
        return usersEntityMap.remove(id);
    }

    public Users_Entity create(Users_Entity user)
    {
        user.setUserId(nextId);
        nextId++;
        usersEntityMap.put(user.getUserId(),user);
        return user;
    }

    public Users_Entity update(Users_Entity user)
    {
        usersEntityMap.put(user.getUserId(),user);
        return user;
    }


}