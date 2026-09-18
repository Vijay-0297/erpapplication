package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.SettingRequest;
import com.example.Erp.inventorymanagement.dto.SettingResponse;
import com.example.Erp.inventorymanagement.model.Setting;
import com.example.Erp.inventorymanagement.repository.SettingRepository;
import com.example.Erp.inventorymanagement.service.SettingService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SettingServiceImpl implements SettingService {

    private final SettingRepository settingRepository;


    @Override
    public SettingResponse create(SettingRequest request) {

        if (settingRepository.existsBySettingKey(
                request.getSettingKey())) {

            throw new RuntimeException(
                    "Setting key already exists: "
                            + request.getSettingKey()
            );
        }

        Setting setting = Setting.builder()
                .settingKey(request.getSettingKey())
                .settingValue(request.getSettingValue())
                .build();

        Setting saved = settingRepository.save(setting);

        return mapToResponse(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public List<SettingResponse> getAll() {

        return settingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public SettingResponse getById(Integer id) {

        Setting setting = settingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Setting not found with ID: " + id
                        )
                );

        return mapToResponse(setting);
    }


    @Override
    @Transactional(readOnly = true)
    public SettingResponse getByKey(String settingKey) {

        Setting setting = settingRepository
                .findBySettingKey(settingKey)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Setting not found with key: "
                                        + settingKey
                        )
                );

        return mapToResponse(setting);
    }


    @Override
    public SettingResponse update(
            Integer id,
            SettingRequest request) {

        Setting setting = settingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Setting not found with ID: " + id
                        )
                );

        /*
         * If the setting key is changed,
         * make sure the new key is not already used.
         */
        if (!setting.getSettingKey()
                .equals(request.getSettingKey())
                && settingRepository.existsBySettingKey(
                request.getSettingKey())) {

            throw new RuntimeException(
                    "Setting key already exists: "
                            + request.getSettingKey()
            );
        }

        setting.setSettingKey(request.getSettingKey());
        setting.setSettingValue(request.getSettingValue());

        /*
         * @PreUpdate automatically updates updatedAt.
         */
        Setting updated = settingRepository.save(setting);

        return mapToResponse(updated);
    }


    @Override
    public void delete(Integer id) {

        Setting setting = settingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Setting not found with ID: " + id
                        )
                );

        settingRepository.delete(setting);
    }


    private SettingResponse mapToResponse(
            Setting setting) {

        return SettingResponse.builder()
                .settingId(setting.getSettingId())
                .settingKey(setting.getSettingKey())
                .settingValue(setting.getSettingValue())
                .updatedAt(setting.getUpdatedAt())
                .build();
    }
}