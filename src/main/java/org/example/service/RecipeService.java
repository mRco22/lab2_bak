package org.example.service;

import java.util.HashMap;
import java.util.Map;
import org.example.entity.RecipeEntity;
import org.springframework.stereotype.Service;


@Service
public class RecipeService
{
    private final Map<Integer, RecipeEntity> recipes = new HashMap<>();

    public Map<Integer,RecipeEntity> getAllRecipes()
    {
        return recipes;
    }
    public RecipeEntity getRecipeById(int id)
    {
        return recipes.get(id);
    }
    public RecipeEntity addRecipe(RecipeEntity recipe)
    {
        recipes.put(recipe.getId(), recipe);
        return recipe;
    }

    public RecipeEntity updateRecipe (int id, RecipeEntity recipe)
    {
        RecipeEntity old = recipes.get(id);
        if (old == null)
        {
            return null;
        }

        old.setNameFood(recipe.getNameFood());
        old.setItems(recipe.getItems());
        return old;

    }

    public RecipeEntity deleteRecipe(int id)
    {
        return recipes.remove(id);
    }
}