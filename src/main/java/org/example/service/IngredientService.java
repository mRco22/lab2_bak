package org.example.service;
import org.example.entity.IngredientEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientService
{
    private final Map<Integer,IngredientEntity> ingredients = new HashMap<>();

    public Map<Integer,IngredientEntity> getAllIngredients()
    {
      return ingredients;
    }

    public IngredientEntity getIngredientById(int id)
    {
        return ingredients.get(id);
    }

    public IngredientEntity addIngredient(IngredientEntity ingredient)
    {
        ingredients.put(ingredient.getId(), ingredient);
        return ingredient;
    }

    public IngredientEntity updateIngredient(int id, IngredientEntity ingredient)
    {
        IngredientEntity old = ingredients.get(id);
        if (old == null)
        {
            return null;
        }
        old.setNameIngredients(ingredient.getNameIngredients());
        old.setUnit(ingredient.getUnit());
        old.setCountNow(ingredient.getCountNow());
        return old;
    }
    public IngredientEntity deleteIngredient(int id)
    {
        return ingredients.remove(id);
    }

}

