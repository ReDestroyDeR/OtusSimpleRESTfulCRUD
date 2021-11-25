package ru.red.otussimplerestfulcrud.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.red.otussimplerestfulcrud.domain.User;
import ru.red.otussimplerestfulcrud.dto.UserPostDto;
import ru.red.otussimplerestfulcrud.exception.BadRequest;
import ru.red.otussimplerestfulcrud.exception.NotFound;
import ru.red.otussimplerestfulcrud.mapper.UserMapper;
import ru.red.otussimplerestfulcrud.repository.UserRepository;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @PostMapping
    public Mono<User> createUser(@RequestBody UserPostDto userPostDto) {
        return userRepository.save(userMapper.userPostDtoToUser(userPostDto))
                .onErrorMap(error -> new BadRequest(error.getMessage()))
                .doOnSuccess(user -> log.info(
                        "Successfully created user {} with id {}",
                        user.getUsername(),
                        user.getId()))
                .doOnError(error -> log.info("Failed created user - {}", error.getMessage()));
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> findUser(@PathVariable long id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFound("Not found")))
                .doOnSuccess(user -> log.info("Successfully found user with id {}", user.getId()))
                .doOnError(error -> log.info("Failed finding user with id {} - {}", id, error.getMessage()));
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Object>> deleteUser(@PathVariable long id) {
        return userRepository.deleteUserById(id)
                .onErrorMap(error -> new BadRequest(error.getMessage()))
                .flatMap(bool -> bool
                        ? Mono.just(ResponseEntity.noContent().build())
                        : Mono.error(new NotFound("Not found")))
                .doOnSuccess(response -> log.info("Successfully deleted user with id {}", id))
                .doOnError(error -> log.info("Failed deleting user: {}", error.getMessage()));
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<String>> updateUser(@PathVariable long id, @RequestBody UserPostDto userPostDto) {
        User user = userMapper.userPostDtoToUser(userPostDto);
        user.setId(id);
        return userRepository.findById(id)
                .flatMap(src -> userRepository.save(User.merge(src, user)))
                .mapNotNull(updatedUser -> ResponseEntity.ok("Updated"))
                .onErrorMap(error -> new BadRequest(error.getMessage()))
                .doOnSuccess(response -> log.info("Successfully updated user with id {}", id))
                .doOnError(error -> log.info("Failed updated user: {}", error.getMessage()));
    }
}
