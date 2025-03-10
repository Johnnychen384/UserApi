package com.example.user.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Queue registerQueue() {
        return new Queue("user.register.queue", true);
    }

    @Bean
    public Queue loginQueue() {
        return new Queue("user.login.queue", true);
    }

    @Bean
    public DirectExchange responseExchange() {
        return new DirectExchange("user.response.exchange");
    }

    @Bean
    public Binding registerResponseBinding() {
        return BindingBuilder.bind(new Queue("user.register.response", true))
                .to(responseExchange())
                .with("user.register.response");
    }

    @Bean
    public Binding loginResponseBinding() {
        return BindingBuilder.bind(new Queue("user.login.response", true))
                .to(responseExchange())
                .with("user.login.response");
    }

    @Bean
    public Binding errorBinding() {
        return BindingBuilder.bind(new Queue("user.errors", true))
                .to(responseExchange())
                .with("user.errors");
    }
}
