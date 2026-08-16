package com.example.Erp.inventorymanagement.controller;

import com.example.Erp.inventorymanagement.dto.UserUpdate;
import com.example.Erp.inventorymanagement.dto.UserRequest;
import com.example.Erp.inventorymanagement.dto.UserResponse;
import com.example.Erp.inventorymanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> get(
            @PathVariable Integer id){

        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> all(){

        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Integer id,
            @RequestBody UserUpdate dto){

        return ResponseEntity.ok(service.update(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Integer id){

        service.delete(id);

        return ResponseEntity.ok("User Deleted");
    }

}
