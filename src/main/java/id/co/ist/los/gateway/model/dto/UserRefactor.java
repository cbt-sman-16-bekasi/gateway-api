package id.co.ist.los.gateway.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.co.ist.los.gateway.config.UserFrom;
import id.co.ist.los.gateway.config.UserStatus;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRefactor implements Serializable {
	@Serial
	private static final long serialVersionUID = 1111785836710661712L;

	protected Long id;
	private String userId;
	private String fullName;
	private String email;
	private String nik;
	private UserStatus status;
	private String privyId;
	private String skill;

	@JsonIgnore
	private String password;
	private Date lastLogin;
	private Boolean isDeleted;
	private UserFrom userFrom;
	private List<Division> userDivision;
	private RoleRefactor userRoleRefactor;
	private List<Position> userPosition;
}
