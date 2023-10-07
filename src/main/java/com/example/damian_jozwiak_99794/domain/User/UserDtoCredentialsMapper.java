package com.example.damian_jozwiak_99794.domain.User;

import com.example.damian_jozwiak_99794.domain.User.dto.UserCredentialsDto;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDtoCredentialsMapper {
        static UserCredentialsDto map(User user) {
            String email = user.getEmail();
            String password = user.getPassword();
            Set<String> roles = user.getRoles()
                    .stream()
                    .map(UserRole::getName)
                    .collect(Collectors.toSet());
            return new UserCredentialsDto(email, password, roles);
        }
}
