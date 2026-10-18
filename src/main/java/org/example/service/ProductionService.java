package org.example.service;
import org.example.entity.ProductionEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductionService {
    private final Map<Integer, ProductionEntity> batches = new HashMap<>();

    public Map<Integer, ProductionEntity> getAllBatches() {
        return batches;
    }

    public ProductionEntity getBatchById(int id) {
        return batches.get(id);
    }

    public ProductionEntity addBatch(ProductionEntity batch) {
        batches.put(batch.getId(), batch);
        return batch;
    }

    public ProductionEntity updateBatch(int id, ProductionEntity batch) {
        ProductionEntity old = batches.get(id);

        if (old == null) {
            return null;
        }

        old.setRecipeId(batch.getRecipeId());
        old.setQuantity(batch.getQuantity());
        old.setStatus(batch.getStatus());
        return old;
    }

    public ProductionEntity deleteBatch(int id)
    {
        return batches.remove(id);
    }


}