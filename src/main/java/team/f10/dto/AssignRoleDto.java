package team.f10.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.f10.model.Role;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignRoleDto {
    private Long userId;
    private Role role;
}
