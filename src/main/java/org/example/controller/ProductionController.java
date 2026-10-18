package org.example.controller;

import org.example.entity.ProductionEntity;
import org.example.service.ProductionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/production-batches")
public class ProductionController
{
    private final ProductionService batchService;

    public ProductionController(ProductionService batchService)
    {
        this.batchService = batchService;
    }

    @GetMapping
    public Map<Integer, ProductionEntity> getAllBatches()
    {
        return batchService.getAllBatches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductionEntity> getBatchById(@PathVariable int id)
    {
        ProductionEntity batch = batchService.getBatchById(id);

        if (batch == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(batch);
    }

    @PostMapping
    public ResponseEntity<ProductionEntity> addBatch(@RequestBody ProductionEntity batch)
    {
        ProductionEntity created = batchService.addBatch(batch);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductionEntity> updateBatch(@PathVariable int id, @RequestBody ProductionEntity batch)
    {
        ProductionEntity updated = batchService.updateBatch(id, batch);

        if (updated == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductionEntity> deleteBatch(@PathVariable int id)
    {
        ProductionEntity deleted = batchService.deleteBatch(id);

        if (deleted == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}