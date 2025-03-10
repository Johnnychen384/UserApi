package com.example.user.service;

import com.example.user.domain.UserRegistration;
import com.example.user.domain.UserResponse;
import com.example.user.domain.UserSignIn;
import com.example.user.entity.UserEntity;
import com.example.user.exceptions.BadRequestException;
import com.example.user.mappers.userregistration.UserRegistrationToEntity;
import com.example.user.mappers.userresponse.UserResponseFromEntity;
import com.example.user.mappers.usersignin.UserSignInToEntity;
import com.example.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRegistrationToEntity userRegistrationToEntity;
    private final UserResponseFromEntity fromEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserSignInToEntity userSignInToEntity;

    public UserServiceImpl(
            UserRepository userRepository,
            UserRegistrationToEntity userRegistrationToEntity,
            UserResponseFromEntity fromEntityMapper,
            PasswordEncoder passwordEncoder,
            UserSignInToEntity userSignInToEntity
    ) {
        this.userRepository = userRepository;
        this.userRegistrationToEntity = userRegistrationToEntity;
        this.fromEntityMapper = fromEntityMapper;
        this.passwordEncoder = passwordEncoder;
        this.userSignInToEntity = userSignInToEntity;
    }

    @Override
    public UserResponse register(UserRegistration userRegistration) {
        UserEntity entity = userRegistrationToEntity.toEntity(userRegistration);

        if (userRepository.existsByUsername(entity.getUsername())) {
            throw new BadRequestException("Username already exists, please select a different username.");
        }
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        userRepository.save(entity);

        return fromEntityMapper.fromEntity(userRepository.findByUsername(entity.getUsername()));
    }

    @Override
    public UserResponse login(UserSignIn userSignIn) {
        UserEntity entity = userSignInToEntity.toEntity(userSignIn);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        UserResponse user;

        try {
            user = fromEntityMapper.fromEntity(
                    userRepository.findByUsernameAndPassword(entity.getUsername(), entity.getPassword()));
        } catch (Exception e) {
            throw new BadRequestException("Could not find account");
        }

        return user;
    }

    @Override
    public UserResponse find(String username) {
        if (username == null) {
            throw new BadRequestException("must have username");
        }

        if (userRepository.existsByUsername(username)) {
            throw new BadRequestException("user could not be found");
        }

        return fromEntityMapper.fromEntity(userRepository.findByUsername(username));
    }

    @Override
    public List<UserResponse> findAll() {
        return fromEntityMapper.fromEntity(userRepository.findAll());
    }

    @Override
    public void update(UserResponse user) {
        if(user == null) {
            throw new BadRequestException("nothing to update");
        }

        UserEntity currentUser = userRepository.findByUsername(user.getUsername());
        currentUser.setName(user.getName() != null ? user.getName() : currentUser.getName());
        currentUser.setUsername(user.getUsername() != null ? user.getUsername() : currentUser.getUsername());
        currentUser.setPassword(user.getPassword() != null ? user.getPassword() : currentUser.getPassword());
        currentUser.setRole(user.getRole() != null ? user.getRole() : currentUser.getRole());
    }
}
