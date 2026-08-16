package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.UserRequest;
import com.example.Erp.inventorymanagement.dto.UserResponse;
import com.example.Erp.inventorymanagement.dto.UserUpdate;
import com.example.Erp.inventorymanagement.model.Role;
import com.example.Erp.inventorymanagement.model.User;
import com.example.Erp.inventorymanagement.repository.RoleRepository;
import com.example.Erp.inventorymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse create(UserRequest dto) {

        if (userRepository.existsByUsername(dto.getUsername()))
            throw new RuntimeException("Username already exists");

        if (userRepository.existsByEmail(dto.getEmail()))
            throw new RuntimeException("Email already exists");

        Role role = roleRepository.findById(Long.valueOf(dto.getRoleId()))
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setRole(role);
        user.setStatus("active");

        userRepository.save(user);

        return map(user);
    }

    @Override
    public UserResponse get(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return map(user);
    }

    private UserResponse map(User user) {

        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole().getRoleName())
                .status(user.getStatus())
                .build();
    }

    @Override
    public List<UserResponse> getAll() {

        return userRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public UserResponse update(Integer id, UserUpdate dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getFullName() != null) {
            user.setFullName(dto.getFullName());
        }

        if (dto.getStatus() != null) {
            user.setStatus(dto.getStatus());
        }

        if (dto.getRoleId() != null) {
            Role role = roleRepository.findById(Long.valueOf(dto.getRoleId()))
                    .orElseThrow(() -> new RuntimeException("Role not found"));

            user.setRole(role);
        }

        User updatedUser = userRepository.save(user);

        return map(updatedUser);
    }


    @Override
    public void delete(Integer id) {

    }

}
