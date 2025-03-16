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
public class UserAuthDto implements Serializable {

	private static final long serialVersionUID = -8371490669387809380L;
	
	private Long id;
	private String userName;
	private String password;
	private Boolean isDeleted;
	private Long userId;
	
}
