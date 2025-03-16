package id.co.ist.los.gateway.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailSubData implements Serializable {

    @Serial
    private static final long serialVersionUID = -5362545747603917667L;

    private String fullName;
    private String email;
    private String nik;
    private String userId;
}
