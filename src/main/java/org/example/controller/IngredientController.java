package org.example.controller;
import java.util.Map;

import org.example.entity.IngredientEntity;
import org.example.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController
{
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService)
    {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public Map<Integer,IngredientEntity> getAllIngredients()
    {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientEntity> getIngredientById(@PathVariable int id)
    {
        IngredientEntity ingredient = ingredientService.getIngredientById(id);

        if(ingredient == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ingredient);
    }

    @PostMapping
    public ResponseEntity<IngredientEntity> addingredient (@RequestBody IngredientEntity ingredient )
    {
        IngredientEntity created = ingredientService.addIngredient(ingredient);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public  ResponseEntity<IngredientEntity> updateIngredient(@PathVariable int id,@RequestBody IngredientEntity ingredient)
    {
        IngredientEntity updated = ingredientService.updateIngredient(id,ingredient);

        if(updated == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IngredientEntity> deleteIngredient(@PathVariable int id)
    {
        IngredientEntity deleted = ingredientService.deleteIngredient(id);
        if(deleted == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}