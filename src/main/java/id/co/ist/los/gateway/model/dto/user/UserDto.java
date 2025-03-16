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
public class UserDto implements Serializable {
	
	private static final long serialVersionUID = -8110062732060480559L;
	
	private Long id;
	private String nickName;
	private String fullName;
	private String phoneNumber;
	private String email;
	private Integer status;
	private Long superiorId;
	private String segmentFinancing;

	private String jobPosition;
	private String jobPositionLabel;
	private String division;
	private String divisionLabel;
	private String directorate;
	private String directorateLabel;
	
}
