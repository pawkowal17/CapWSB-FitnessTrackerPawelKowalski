package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller class for handling user-related HTTP requests.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve
     * @return the DTO representation of the user
     * @throws UserNotFoundException if the user with the specified ID does not exist
     */
    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        User user = userService.getUser(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return userMapper.toDto(user);
    }

    /**
     * Retrieves a list of all users.
     *
     * @return a list of DTO representations of users
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    /**
     * Adds a new user.
     *
     * @param userDto the DTO representation of the user to add
     * @return the DTO representation of the added user
     * @throws InterruptedException if the thread is interrupted while processing the request
     */
    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {

        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");

        User newUser = userMapper.toEntity(userDto);
        User savedUser = userService.createUser(newUser);
        return userMapper.toDto(savedUser);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to delete
     */
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    /**
     * Retrieves a list of users older than the specified age.
     *
     * @param age the minimum age for users to be included in the list
     * @return a list of DTO representations of users
     */
    @GetMapping("/older-than/{age}")
    public List<UserDto> getUsersOlderThan(@PathVariable int age) {
        return userService.findUsersOlderThan(age)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Updates a user's details.
     *
     * @param userId  the ID of the user to update
     * @param userDto the DTO representation of the updated user details
     * @return the DTO representation of the updated user
     */
    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        User userToUpdate = userMapper.toEntity(userDto);
        User updatedUser = userService.updateUser(userId, userToUpdate);
        return userMapper.toDto(updatedUser);
    }
}