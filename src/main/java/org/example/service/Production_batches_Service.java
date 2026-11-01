package org.example.service;

import org.example.entity.Baking_requests_Entity;
import org.example.entity.Production_batches_Entity;
import org.example.repository.Baking_requests_Repository;
import org.example.repository.Production_batches_Repository;
import org.example.repository.Recipes_Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Production_batches_Service
{
    private final Production_batches_Repository batchesRepository;
    private final Recipes_Repository recipesRepository;
    private final Baking_requests_Repository bakingRepository;

    public Production_batches_Service(Production_batches_Repository batchesRepository,
                                      Recipes_Repository recipesRepository,
                                      Baking_requests_Repository bakingRepository)
    {
        this.batchesRepository = batchesRepository;
        this.recipesRepository = recipesRepository;
        this.bakingRepository = bakingRepository;
    }

    public List<Production_batches_Entity> getAll()
    {
        return batchesRepository.findAll();
    }

    public Production_batches_Entity getById(int id)
    {
        return batchesRepository.findById(id);
    }

    public Production_batches_Entity create(Production_batches_Entity batch)
    {
        if (batch.getProductionBatchesQuantity() <= 0)
        {
            return null;
        }
        if (recipesRepository.findById(batch.getProductionBatchesRecipesId()) == null)
        {
            return null;
        }
        batch.setProductionBatchesStatus("Planned");
        return batchesRepository.create(batch);
    }

    public Production_batches_Entity update(int id, Production_batches_Entity data)
    {
        Production_batches_Entity batch = batchesRepository.findById(id);
        if (batch == null)
        {
            return null;
        }
        if (data.getProductionBatchesQuantity() != 0)// 0 = не прислали
        {
            if (data.getProductionBatchesQuantity() < 0)
            {
                return null;
            }
            batch.setProductionBatchesQuantity(data.getProductionBatchesQuantity());
        }
        if (data.getProductionBatchesRecipesId() != 0)
        {
            if (recipesRepository.findById(data.getProductionBatchesRecipesId()) == null)
            {
                return null;
            }
            batch.setProductionBatchesRecipesId(data.getProductionBatchesRecipesId());
        }
        return batchesRepository.update(batch);
    }

    public Production_batches_Entity delete(int id)
    {
        Production_batches_Entity deleted = batchesRepository.deleteById(id);
        if (deleted == null)
        {
            return null;
        }
        List<Baking_requests_Entity> requests = bakingRepository.findByBatchId(id);
        for (int i = 0; i < requests.size(); i++)
        {
            Baking_requests_Entity request = requests.get(i);
            request.setBakingRequestsBatchId(null);
            request.setBakingRequestsStatus("New");
            bakingRepository.update(request);
        }
        return deleted;
    }
}