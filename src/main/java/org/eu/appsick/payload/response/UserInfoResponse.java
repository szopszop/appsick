package org.eu.appsick.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.eu.appsick.user.User;



@Data
public class UserInfoResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private User.Role role;


    public UserInfoResponse(Long id, String firstName, String lastName, User.Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}
