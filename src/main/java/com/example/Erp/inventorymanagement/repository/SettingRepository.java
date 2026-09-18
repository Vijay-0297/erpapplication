package com.example.Erp.inventorymanagement.repository;

import com.example.Erp.inventorymanagement.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettingRepository
        extends JpaRepository<Setting, Integer> {

    Optional<Setting> findBySettingKey(String settingKey);

    boolean existsBySettingKey(String settingKey);
}