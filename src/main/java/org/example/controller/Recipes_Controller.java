package org.example.controller;

import org.example.entity.Recipes_Entity;
import org.example.service.Recipes_Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class Recipes_Controller
{
    private final Recipes_Service recipesService;

    public Recipes_Controller(Recipes_Service recipesService)
    {
        this.recipesService = recipesService;
    }

    @GetMapping
    public ResponseEntity<List<Recipes_Entity>> getAll()
    {
        return ResponseEntity.ok(recipesService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipes_Entity> getById(@PathVariable int id)
    {
        Recipes_Entity recipe = recipesService.getById(id);
        if (recipe == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(recipe);
    }

    @PostMapping
    public ResponseEntity<Recipes_Entity> create(@RequestBody Recipes_Entity recipe)
    {
        Recipes_Entity created = recipesService.create(recipe);
        if (created == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Recipes_Entity> update(@PathVariable int id, @RequestBody Recipes_Entity data)
    {
        if (recipesService.getById(id) == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Recipes_Entity updated = recipesService.update(id, data);
        if (updated == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id)
    {
        Recipes_Entity deleted = recipesService.delete(id);
        if (deleted == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
}