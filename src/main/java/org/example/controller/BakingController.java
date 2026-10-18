package org.example.controller;
import java.util.Map;


import org.example.entity.BakingEntity;
import org.example.service.BakingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/baking-requests")
public class BakingController {
    private final BakingService bakingService;

    public BakingController(BakingService bakingService)
    {
        this.bakingService = bakingService;
    }

    @GetMapping
    public Map<Integer, BakingEntity> getAllBaking()
    {
        return bakingService.getAllBaking();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BakingEntity> getBakingById(@PathVariable int id)
    {
        BakingEntity request = bakingService.getBakingById(id);

        if (request == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(request);
    }

    @PostMapping
    public ResponseEntity<BakingEntity> addBaking(@RequestBody BakingEntity request)
    {
        BakingEntity created = bakingService.addBaking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BakingEntity> updateBaking(@PathVariable int id, @RequestBody BakingEntity request)
    {
        BakingEntity updated = bakingService.updateBaking(id, request);

        if (updated == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BakingEntity> deleteBaking(@PathVariable int id)
    {
        BakingEntity deleted = bakingService.deleteBaking(id);

        if (deleted == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }


}