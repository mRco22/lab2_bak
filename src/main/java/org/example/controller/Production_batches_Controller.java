package org.example.controller;

import org.example.entity.Production_batches_Entity;
import org.example.service.Production_batches_Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/production-batches")
public class Production_batches_Controller
{
    private final Production_batches_Service batchesService;

    public Production_batches_Controller(Production_batches_Service batchesService)
    {
        this.batchesService = batchesService;
    }

    @GetMapping
    public ResponseEntity<List<Production_batches_Entity>> getAll()
    {
        return ResponseEntity.ok(batchesService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Production_batches_Entity> getById(@PathVariable int id)
    {
        Production_batches_Entity batch = batchesService.getById(id);
        if (batch == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(batch);
    }

    @PostMapping
    public ResponseEntity<Production_batches_Entity> create(@RequestBody Production_batches_Entity batch)
    {
        Production_batches_Entity created = batchesService.create(batch);
        if (created == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Production_batches_Entity> update(@PathVariable int id,
                                                            @RequestBody Production_batches_Entity data)
    {
        if (batchesService.getById(id) == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Production_batches_Entity updated = batchesService.update(id, data);
        if (updated == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id)
    {
        Production_batches_Entity deleted = batchesService.delete(id);
        if (deleted == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
}