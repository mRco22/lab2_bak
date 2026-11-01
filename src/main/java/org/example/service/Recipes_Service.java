package org.example.service;

import org.example.entity.Baking_requests_Entity;
import org.example.entity.Production_batches_Entity;
import org.example.entity.Recipe_item_Entity;
import org.example.entity.Recipes_Entity;
import org.example.repository.Baking_requests_Repository;
import org.example.repository.Ingredients_Repository;
import org.example.repository.Production_batches_Repository;
import org.example.repository.Recipes_Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Recipes_Service
{
    private final Recipes_Repository recipesRepository;
    private final Ingredients_Repository ingredientsRepository;
    private final Production_batches_Repository batchesRepository;
    private final Baking_requests_Repository bakingRequestsRepository;

    public Recipes_Service(Recipes_Repository recipesRepository,
                           Ingredients_Repository ingredientsRepository,
                           Production_batches_Repository batchesRepository,
                           Baking_requests_Repository bakingRepository)
    {
        this.recipesRepository = recipesRepository;
        this.ingredientsRepository = ingredientsRepository;
        this.batchesRepository = batchesRepository;
        this.bakingRequestsRepository = bakingRepository;
    }

    public List<Recipes_Entity> getAll()
    {
        return recipesRepository.findAll();
    }

    public Recipes_Entity getById(int id)
    {
        return recipesRepository.findById(id);
    }

    public Recipes_Entity create(Recipes_Entity recipe)
    {
        if (recipe.getRecipesName() == null )
        {
            return null;
        }
        if (recipe.getRecipesList() == null)
        {
            recipe.setRecipesList(new ArrayList<>());
        }
        if (!itemsAreValid(recipe.getRecipesList()))
        {
            return null;
        }
        return recipesRepository.create(recipe);
    }

    public Recipes_Entity update(int id, Recipes_Entity data)
    {
        Recipes_Entity recipe = recipesRepository.findById(id);
        if (recipe == null)
        {
            return null;
        }
        if (data.getRecipesName() != null)
        {
            if (data.getRecipesName().isBlank())
            {
                return null;
            }
            recipe.setRecipesName(data.getRecipesName());
        }
        if (data.getRecipesList() != null)
        {
            if (!itemsAreValid(data.getRecipesList()))
            {
                return null;
            }
            recipe.setRecipesList(data.getRecipesList());
        }
        return recipesRepository.update(recipe);
    }

    public Recipes_Entity delete(int id)
    {
        Recipes_Entity deleted = recipesRepository.deleteById(id);
        if (deleted == null)
        {
            return null;
        }
        List<Production_batches_Entity> batches = batchesRepository.findByRecipeId(id);
        for (int i = 0; i < batches.size(); i++)
        {
            batchesRepository.deleteById(batches.get(i).getProductionBatchesId());
        }
        List<Baking_requests_Entity> requests = bakingRequestsRepository.findByRecipeId(id);
        for (int i = 0; i < requests.size(); i++)
        {
            bakingRequestsRepository.deleteById(requests.get(i).getBakingRequestsId());
        }
        return deleted;
    }

    private boolean itemsAreValid(List<Recipe_item_Entity> items)
    {
        for (int i = 0; i < items.size(); i++)
        {
            Recipe_item_Entity item = items.get(i);
            if (item == null )
            {
                return false;
            }
            if (ingredientsRepository.findById(item.getIngredientId()) == null)
            {
                return false;
            }
        }
        return true;
    }
}