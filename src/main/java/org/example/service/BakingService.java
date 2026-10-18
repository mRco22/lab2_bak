package org.example.service;

import org.example.entity.BakingEntity;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
public class BakingService
{
    private final Map<Integer,BakingEntity> bakings = new HashMap<>();

    public Map<Integer, BakingEntity> getAllBaking()
    {
        return bakings;
    }

    public BakingEntity getBakingById(int id)
    {
        return bakings.get(id);
    }

    public BakingEntity addBaking(BakingEntity request)
    {
        bakings.put(request.getId(), request);
        return request;
    }

    public BakingEntity updateBaking(int id, BakingEntity request)
    {
        BakingEntity old = bakings.get(id);

        if (old == null)
        {
            return null;
        }
        old.setRecipeId(request.getRecipeId());
        old.setQuantity(request.getQuantity());
        old.setBatchId(request.getBatchId());
        old.setStatus(request.getStatus());
        return old;
    }

    public BakingEntity deleteBaking(int id)
    {
        return bakings.remove(id);
    }
}
