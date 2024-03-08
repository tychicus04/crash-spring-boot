package com.tychicus.WestLakeHotel.Response;

import com.tychicus.WestLakeHotel.Model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class UserResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private RoleResponse roles;

    public UserResponse(Long id, String firstName, String lastName, String email, String password, RoleResponse roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
