package mk.ukim.finki.sharearide.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.sharearide.model.enumerations.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;

    private String password;

    private String repeatPassword;

    private Role role;
}
