package org.example.service;
import  org.example.entity.Ingredients_Entity;
import org.example.entity.Recipe_item_Entity;
import org.example.entity.Recipes_Entity;
import org.example.repository.Ingredients_Repository;
import org.example.repository.Recipes_Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class Ingredients_Service
{
    private final Ingredients_Repository ingredientsRepository;
    private final Recipes_Repository recipesRepository;

    public Ingredients_Service(Ingredients_Repository ingredientsRepository,
                               Recipes_Repository recipesRepository) {
        this.ingredientsRepository = ingredientsRepository;
        this.recipesRepository = recipesRepository;
    }

    public List<Ingredients_Entity> getAll()
    {
        return ingredientsRepository.findAll();
    }

    public Ingredients_Entity getById(int id)
    {
        return ingredientsRepository.findById(id);
    }

    public Ingredients_Entity create(Ingredients_Entity ingredient)
    {
        if (ingredient.getIngredientName()==null)
        {
            return null;
        }

        return ingredientsRepository.create(ingredient);
    }

    public Ingredients_Entity update(int id, Ingredients_Entity data)
    {
        Ingredients_Entity ingredient = ingredientsRepository.findById(id);
        if (ingredient == null)
        {
            return null;
        }
        if (data.getIngredientName() != null)
        {
            if (data.getIngredientName().isBlank())
            {
                return null;
            }
            ingredient.setIngredientName(data.getIngredientName());
        }
        return ingredientsRepository.update(ingredient);
    }

    public Ingredients_Entity delete(int id)
    {
        Ingredients_Entity deleted = ingredientsRepository.deleteById(id);
        if (deleted == null)
        {
            return null;
        }
        List<Recipes_Entity> recipes = recipesRepository.findAll();
        for (int i = 0; i < recipes.size(); i++)
        {
            Recipes_Entity recipe = recipes.get(i);
            List<Recipe_item_Entity> kept = new ArrayList<>();
            for (int j = 0; j < recipe.getRecipesList().size(); j++)
            {
                Recipe_item_Entity item = recipe.getRecipesList().get(j);
                if (item.getIngredientId() != id)
                {
                    kept.add(item);
                }
            }
            recipe.setRecipesList(kept);
            recipesRepository.update(recipe);
        }
        return deleted;
    }
}