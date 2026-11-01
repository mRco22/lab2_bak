package org.example.repository;

import org.example.entity.Production_batches_Entity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class Production_batches_Repository
{
    private final Map<Integer, Production_batches_Entity> batchesEntityMap =
            new HashMap<>();
    private int nextId = 1;

    public List<Production_batches_Entity> findAll()
    {
        return new ArrayList<>(batchesEntityMap.values());
    }

    public Production_batches_Entity findById(int id)
    {
        return batchesEntityMap.get(id);
    }

    public Production_batches_Entity deleteById(int id)
    {
        return batchesEntityMap.remove(id);
    }

    public Production_batches_Entity create(Production_batches_Entity batch)
    {
        batch.setProductionBatchesId(nextId);
        nextId++;
        batchesEntityMap.put(batch.getProductionBatchesId(),batch);
        return batch;
    }

    public Production_batches_Entity update(Production_batches_Entity batch)
    {
        batchesEntityMap.put(batch.getProductionBatchesId(),batch);
        return batch;
    }

    public List<Production_batches_Entity> findByRecipeId(int recipeId)
    {
        List<Production_batches_Entity> all = new ArrayList<>(batchesEntityMap.values());
        List<Production_batches_Entity> result = new ArrayList<>();
        for (int i = 0; i < all.size(); i++)
        {
            if (all.get(i).getProductionBatchesRecipesId() == recipeId)
            {
                result.add(all.get(i));
            }
        }
        return result;
    }

}