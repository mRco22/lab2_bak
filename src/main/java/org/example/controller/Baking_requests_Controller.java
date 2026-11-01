package org.example.controller;

import org.example.entity.Baking_requests_Entity;
import org.example.service.Baking_requests_Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/baking-requests")
public class Baking_requests_Controller
{
    private final Baking_requests_Service bakingService;

    public Baking_requests_Controller(Baking_requests_Service bakingService)
    {
        this.bakingService = bakingService;
    }

    @GetMapping
    public ResponseEntity<List<Baking_requests_Entity>> getAll()
    {
        return ResponseEntity.ok(bakingService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Baking_requests_Entity> getById(@PathVariable int id)
    {
        Baking_requests_Entity request = bakingService.getById(id);
        if (request == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(request);
    }

    @PostMapping
    public ResponseEntity<Baking_requests_Entity> create(@RequestBody Baking_requests_Entity request)
    {
        Baking_requests_Entity created = bakingService.create(request);
        if (created == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Baking_requests_Entity> update(@PathVariable int id,
                                                         @RequestBody Baking_requests_Entity data)
    {
        if (bakingService.getById(id) == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Baking_requests_Entity updated = bakingService.update(id, data);
        if (updated == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id)
    {
        Baking_requests_Entity deleted = bakingService.delete(id);
        if (deleted == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
}
