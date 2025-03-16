package id.co.ist.los.gateway.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupDto implements Serializable {

	private static final long serialVersionUID = 2142579578903806432L;
	
	private Long id;
	private String name;
	private String description;
	
}
