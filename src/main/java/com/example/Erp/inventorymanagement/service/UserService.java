package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.UserRequest;
import com.example.Erp.inventorymanagement.dto.UserResponse;
import com.example.Erp.inventorymanagement.dto.UserUpdate;

import java.util.List;

public interface UserService {

    UserResponse create(UserRequest dto);

    UserResponse get(Integer id);

    List<UserResponse> getAll();

    UserResponse update(Integer id, UserUpdate dto);

    void delete(Integer id);
}
