package org.example.repository;

import org.example.entity.Ingredients_Entity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class Ingredients_Repository
{
    private final Map<Integer, Ingredients_Entity> ingredienntsEntityMap =
            new HashMap<>();
    private int nextId = 1;

    public List<Ingredients_Entity> findAll()
    {
        return new ArrayList<>(ingredienntsEntityMap.values());
    }

    public Ingredients_Entity findById(int id)
    {
        return ingredienntsEntityMap.get(id);
    }

    public Ingredients_Entity deleteById(int id)
    {
        return ingredienntsEntityMap.remove(id);
    }

    public Ingredients_Entity create(Ingredients_Entity user)
    {
        user.setIngredientId(nextId);
        nextId++;
        ingredienntsEntityMap.put(user.getIngredientId(),user);
        return user;
    }

    public Ingredients_Entity update(Ingredients_Entity user)
    {
        ingredienntsEntityMap.put(user.getIngredientId(),user);
        return user;
    }


}