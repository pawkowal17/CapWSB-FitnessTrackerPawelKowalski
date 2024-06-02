package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        User user = userService.getUser(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return userMapper.toDto(user);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {

        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");

        User newUser = userMapper.toEntity(userDto);
        User savedUser = userService.createUser(newUser);
        return userMapper.toDto(savedUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/older-than/{age}")
    public List<UserDto> getUsersOlderThan(@PathVariable int age) {
        return userService.findUsersOlderThan(age)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        User userToUpdate = userMapper.toEntity(userDto);
        User updatedUser = userService.updateUser(userId, userToUpdate);
        return userMapper.toDto(updatedUser);
    }
}