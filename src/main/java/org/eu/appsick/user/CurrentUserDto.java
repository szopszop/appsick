package org.eu.appsick.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

import org.eu.appsick.user.User.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrentUserDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private List<HashMap<String, Role>> authorities;
    private boolean enabled;
    private String username;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
}
