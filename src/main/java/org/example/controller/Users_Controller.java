package org.example.controller;
import java.util.List;
import java.util.Map;

import org.example.entity.Users_Entity;
import org.example.service.Users_Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class Users_Controller
{
    private final Users_Service usersService;

    public Users_Controller(Users_Service usersService)
    {
        this.usersService = usersService;
    }

    @GetMapping
    public List<Users_Entity> getAll()
    {
        return usersService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users_Entity> getById(@PathVariable int id)
    {
        Users_Entity user = usersService.getById(id);
        if (user == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Users_Entity> create(@RequestBody Users_Entity user)
    {
        Users_Entity created = usersService.create(user);
        if (created == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Users_Entity> update(@PathVariable int id, @RequestBody Users_Entity data)
    {
        if (usersService.getById(id) == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Users_Entity updated = usersService.update(id, data);
        if (updated == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id)
    {
        Users_Entity deleted = usersService.delete(id);
        if (deleted == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
}