package id.co.ist.los.gateway.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Division implements Serializable {
	@Serial
	private static final long serialVersionUID = -6322620527762339334L;

	protected Long id;
	private String divisionCode;
	private Category category;
	private Directorate directorate;
	private String name;
	private String description;
	private String segmentFinance;

}
