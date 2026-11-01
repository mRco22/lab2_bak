package org.example.controller;

import org.example.entity.Ingredients_Entity;
import org.example.service.Ingredients_Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class Ingredients_Controller
{
    private final Ingredients_Service ingredientsService;

    public Ingredients_Controller(Ingredients_Service ingredientsService)
    {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping
    public ResponseEntity<List<Ingredients_Entity>> getAll()
    {
        return ResponseEntity.ok(ingredientsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredients_Entity> getById(@PathVariable int id)
    {
        Ingredients_Entity ingredient = ingredientsService.getById(id);
        if (ingredient == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @PostMapping
    public ResponseEntity<Ingredients_Entity> create(@RequestBody Ingredients_Entity ingredient)
    {
        Ingredients_Entity created = ingredientsService.create(ingredient);
        if (created == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ingredients_Entity> update(@PathVariable int id, @RequestBody Ingredients_Entity data)
    {
        if (ingredientsService.getById(id) == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Ingredients_Entity updated = ingredientsService.update(id, data);
        if (updated == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id)
    {
        Ingredients_Entity deleted = ingredientsService.delete(id);
        if (deleted == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
}