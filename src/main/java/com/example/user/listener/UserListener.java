package com.example.user.listener;

import com.example.user.domain.UserRegistration;
import com.example.user.domain.UserResponse;
import com.example.user.domain.UserSignIn;
import com.example.user.dto.UserRegistrationJson;
import com.example.user.dto.UserResponseJson;
import com.example.user.dto.UserSignInJson;
import com.example.user.mappers.ToDomainMapper;
import com.example.user.mappers.ToJsonMapper;
import com.example.user.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class UserListener {
    private final UserService userService;
    private final ToDomainMapper<UserRegistrationJson, UserRegistration> userRegisterMapper;
    private final ToDomainMapper<UserSignInJson, UserSignIn> userSignInMapper;
    private final ToJsonMapper<UserResponse, UserResponseJson> userToJsonMapper;
    private final RabbitTemplate rabbitTemplate;


    public UserListener(
            UserService userService,
            ToDomainMapper<UserRegistrationJson, UserRegistration> userRegisterMapper,
            ToDomainMapper<UserSignInJson, UserSignIn> userSignInMapper,
            ToJsonMapper<UserResponse, UserResponseJson> userToJsonMapper,
            RabbitTemplate rabbitTemplate
    ) {
        this.userService = userService;
        this.userRegisterMapper = userRegisterMapper;
        this.userSignInMapper = userSignInMapper;
        this.userToJsonMapper = userToJsonMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "user.register.queue")
    public void handleRegistration(UserRegistrationJson req) {
        UserRegistration userRegistration = userRegisterMapper.toDomain(req);
        UserResponseJson response = userToJsonMapper.toJson(userService.register(userRegistration));

        rabbitTemplate.convertAndSend(
                "user.response.exchange",
                "user.register.response",
                response
        );
    }

    @RabbitListener(queues = "user.login.queue")
    public void handleLogin(UserSignInJson req) {
        UserSignIn userSignIn = userSignInMapper.toDomain(req);
        UserResponseJson response = userToJsonMapper.toJson(userService.login(userSignIn));

        rabbitTemplate.convertAndSend(
                "user.response.exchange",
                "user.login.response",
                response
        );
    }
}
