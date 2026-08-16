package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.RoleRequest;
import com.example.Erp.inventorymanagement.dto.RoleResponse;

import java.util.List;

public interface RoleService {

    RoleResponse createRole(RoleRequest request);

    RoleResponse getRoleById(Long roleId);

    List<RoleResponse> getAllRoles();

    RoleResponse updateRole(Long roleId, RoleRequest request);

    void deleteRole(Long roleId);
}