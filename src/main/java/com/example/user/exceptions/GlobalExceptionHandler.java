package com.example.user.exceptions;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final RabbitTemplate rabbitTemplate;

    public GlobalExceptionHandler(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @ExceptionHandler(BadRequestException.class)
    public void handleBadRequestException(BadRequestException ex) {
        rabbitTemplate.convertAndSend(
                "user.response.exchange",
                "user.errors",
                ex.getMessage()
        );
    }
}
