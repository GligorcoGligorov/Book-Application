package com.example.emt_lab.model.dto;
import com.example.emt_lab.enumerations.Role;
import com.example.emt_lab.model.User;
import lombok.Data;


@Data
public class UserDetailsDto {
    private String username;
    private Role role;

    public static UserDetailsDto of(User user) {
        UserDetailsDto details = new UserDetailsDto();
        details.username = user.getUsername();
        details.role = user.getRole();
        return details;
    }
}
