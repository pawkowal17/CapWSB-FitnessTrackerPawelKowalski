package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between User entities and User DTOs.
 */
@Component
class UserMapper {

    /**
     * Converts a User entity to a User DTO.
     *
     * @param user the User entity
     * @return the User DTO
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    /**
     * Converts a User DTO to a User entity.
     *
     * @param userDto the User DTO
     * @return the User entity
     */
    User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }
}