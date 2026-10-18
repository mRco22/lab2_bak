package org.example.controller;
import java.util.Map;

import org.example.entity.RecipeEntity;
import org.example.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")

public class RecipeController
{
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService)
    {
        this.recipeService = recipeService;
    }

    @GetMapping
    public Map<Integer, RecipeEntity> getAllRecipes()
    {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeEntity> getRecipeById(@PathVariable int id)
    {
        RecipeEntity recipe = recipeService.getRecipeById(id);

        if (recipe == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(recipe);
    }

    @PostMapping
    public ResponseEntity<RecipeEntity> addRecipe(@RequestBody RecipeEntity recipe)
    {
        RecipeEntity created = recipeService.addRecipe(recipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RecipeEntity> updateRecipe(@PathVariable int id, @RequestBody RecipeEntity recipe)
    {
        RecipeEntity updated = recipeService.updateRecipe(id, recipe);

        if (updated == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RecipeEntity> deleteRecipe(@PathVariable int id)
    {
        RecipeEntity deleted = recipeService.deleteRecipe(id);

        if (deleted == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

}