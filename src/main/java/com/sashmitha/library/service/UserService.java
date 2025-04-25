package com.sashmitha.library.service;

import com.sashmitha.library.dto.UserResponse;
import com.sashmitha.library.dto.UserSignupRequest;
import com.sashmitha.library.entity.User;
import com.sashmitha.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse registerUser(UserSignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered.");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saved = userRepository.save(user);

        return new UserResponse(
                saved.getId(), saved.getName(), saved.getEmail(), saved.getCreatedAt()
        );
    }
}
