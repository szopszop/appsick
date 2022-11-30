package org.eu.appsick.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.eu.appsick.user.User;


@AllArgsConstructor
@Data
public class UserInfoResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private User.Role role;

}
