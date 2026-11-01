package org.example.repository;

import org.example.entity.Recipes_Entity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class Recipes_Repository
{
    private final Map<Integer, Recipes_Entity> recipesEntityMap =
            new HashMap<>();
    private int nextId = 1;

    public List<Recipes_Entity> findAll()
    {
        return new ArrayList<>(recipesEntityMap.values());
    }

    public Recipes_Entity findById(int id)
    {
        return recipesEntityMap.get(id);
    }

    public Recipes_Entity deleteById(int id)
    {
        return recipesEntityMap.remove(id);
    }

    public Recipes_Entity create(Recipes_Entity recipe)
    {
        recipe.setRecipesId(nextId);
        nextId++;
        recipesEntityMap.put(recipe.getRecipesId(),recipe);
        return recipe;
    }

    public Recipes_Entity update(Recipes_Entity recipe)
    {
        recipesEntityMap.put(recipe.getRecipesId(),recipe);
        return recipe;
    }


}