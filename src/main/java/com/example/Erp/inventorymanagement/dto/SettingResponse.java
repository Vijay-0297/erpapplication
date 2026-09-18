package com.example.Erp.inventorymanagement.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingResponse {

    private Integer settingId;

    private String settingKey;

    private String settingValue;

    private OffsetDateTime updatedAt;
}