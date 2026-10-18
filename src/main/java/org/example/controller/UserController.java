package org.example.controller;
import java.util.Map;

import org.example.entity.UserEntity;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping //get - чтение всего
    public Map<Integer,UserEntity> getAllUser()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}") //get по id
    public ResponseEntity<UserEntity> getUserById(@PathVariable int id)
    {
        UserEntity user = userService.getUserById(id);
        if (user == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity user)
    {
        UserEntity created = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable int id,@RequestBody UserEntity user)
    {
        UserEntity updated = userService.updateUser(id,user);
        if (updated == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<UserEntity> deleteUser(@PathVariable int id)
    {
        UserEntity deleted = userService.deleteUser(id);

        if(deleted == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}