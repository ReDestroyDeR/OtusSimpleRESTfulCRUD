package ru.red.otussimplerestfulcrud.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.red.otussimplerestfulcrud.domain.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<Boolean> deleteUserById(Long id);
}
