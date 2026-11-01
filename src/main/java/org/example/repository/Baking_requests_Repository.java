package org.example.repository;

import org.example.entity.Baking_requests_Entity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class Baking_requests_Repository
{
    private final Map<Integer, Baking_requests_Entity> BakingEntityMap =
            new HashMap<>();
    private int nextId = 1;

    public List<Baking_requests_Entity> findAll()
    {
        return new ArrayList<>(BakingEntityMap.values());
    }

    public Baking_requests_Entity findById(int id)
    {
        return BakingEntityMap.get(id);
    }

    public Baking_requests_Entity deleteById(int id)
    {
        return BakingEntityMap.remove(id);
    }

    public Baking_requests_Entity create(Baking_requests_Entity baking)
    {
        baking.setBakingRequestsId(nextId);
        nextId++;
        BakingEntityMap.put(baking.getBakingRequestsId(),baking);
        return baking;
    }

    public Baking_requests_Entity update(Baking_requests_Entity baking)
    {
        BakingEntityMap.put(baking.getBakingRequestsId(),baking);
        return baking;
    }

    public List<Baking_requests_Entity>findByUserId(int userId)
    {
        List<Baking_requests_Entity> all =
                new ArrayList<>(BakingEntityMap.values());
        List<Baking_requests_Entity> result = new ArrayList<>();
        for (int i = 0; i < all.size();i++)
        {
            Integer value = all.get(i).getBakingRequestsUserId();
            if(value != null && value == userId)
            {
                result.add(all.get(i));
            }
        }
        return result;
    }

    public List<Baking_requests_Entity> findByRecipeId(int recipeId)
    {
        List<Baking_requests_Entity> all = new ArrayList<>(BakingEntityMap.values());
        List<Baking_requests_Entity> result = new ArrayList<>();
        for (int i = 0; i < all.size(); i++)
        {
            Integer value = all.get(i).getBakingRequestsRecipesId();
            if (value != null && value == recipeId)
            {
                result.add(all.get(i));
            }
        }
        return result;
    }

    public List<Baking_requests_Entity> findByBatchId(int batchId)
    {
        List<Baking_requests_Entity> all = new ArrayList<>(BakingEntityMap.values());
        List<Baking_requests_Entity> result = new ArrayList<>();
        for (int i = 0; i < all.size(); i++)
        {
            Integer value = all.get(i).getBakingRequestsBatchId();
            if (value != null && value == batchId)
            {
                result.add(all.get(i));
            }
        }
        return result;
    }


}