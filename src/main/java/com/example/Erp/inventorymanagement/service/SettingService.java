package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.SettingRequest;
import com.example.Erp.inventorymanagement.dto.SettingResponse;

import java.util.List;

public interface SettingService {

    SettingResponse create(SettingRequest request);

    List<SettingResponse> getAll();

    SettingResponse getById(Integer id);

    SettingResponse getByKey(String settingKey);

    SettingResponse update(Integer id, SettingRequest request);

    void delete(Integer id);
}