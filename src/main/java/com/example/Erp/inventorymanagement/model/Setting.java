package com.example.Erp.inventorymanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(
        name = "settings",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_settings_setting_key",
                        columnNames = "setting_key"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setting_id")
    private Integer settingId;

    @Column(
            name = "setting_key",
            nullable = false,
            unique = true,
            length = 100
    )
    private String settingKey;

    @Column(
            name = "setting_value",
            columnDefinition = "TEXT"
    )
    private String settingValue;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }
}