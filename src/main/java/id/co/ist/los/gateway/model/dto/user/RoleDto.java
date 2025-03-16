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
public class RoleDto implements Serializable {

	private static final long serialVersionUID = -4290101846742282153L;
	
	private Long id;
	private String name;
	private String description;
	
}
