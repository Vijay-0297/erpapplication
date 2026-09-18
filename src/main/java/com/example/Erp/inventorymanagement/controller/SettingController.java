package com.example.Erp.inventorymanagement.controller;

import com.example.Erp.inventorymanagement.dto.SettingRequest;
import com.example.Erp.inventorymanagement.dto.SettingResponse;
import com.example.Erp.inventorymanagement.service.SettingService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/settings")
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService;


    // CREATE
    @PostMapping
    public ResponseEntity<SettingResponse> create(
            @Valid @RequestBody SettingRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(settingService.create(request));
    }


    // GET ALL
    @GetMapping
    public ResponseEntity<List<SettingResponse>> getAll() {

        return ResponseEntity.ok(
                settingService.getAll()
        );
    }


    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<SettingResponse> getById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                settingService.getById(id)
        );
    }


    // GET BY KEY
    @GetMapping("/key/{settingKey}")
    public ResponseEntity<SettingResponse> getByKey(
            @PathVariable String settingKey) {

        return ResponseEntity.ok(
                settingService.getByKey(settingKey)
        );
    }


    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<SettingResponse> update(
            @PathVariable Integer id,
            @Valid @RequestBody SettingRequest request) {

        return ResponseEntity.ok(
                settingService.update(id, request)
        );
    }


    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {

        settingService.delete(id);

        return ResponseEntity.noContent().build();
    }
}