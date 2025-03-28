package id.co.ist.los.gateway.model.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleRefactor implements Serializable {
	@Serial
	private static final long serialVersionUID = -1081244659654309941L;


	protected Long id;
	private String roleCode;
	private String name;
	private String description;

}
