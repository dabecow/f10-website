package team.f10.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String occupation;
}
