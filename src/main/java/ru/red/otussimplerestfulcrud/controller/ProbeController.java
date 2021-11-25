package ru.red.otussimplerestfulcrud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/health")
public class ProbeController {
    @GetMapping
    public Mono<Void> health() {
        return Mono.empty();
    }
}
