package id.co.ist.los.gateway.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponseDto implements Serializable {
	
	private static final long serialVersionUID = -3569483726050769562L;
	
	private UserDto user;
	private UserAuthDto userAuth;
	private List<RoleDto> listRole;
	private List<UserGroupDto> listUserGroup;
	private List<FunctionDto> listFunction;

}
