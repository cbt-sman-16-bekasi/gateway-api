package id.co.ist.los.gateway.model.dto;

import id.co.ist.los.gateway.config.UserFrom;
import id.co.ist.los.gateway.config.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = -7498840945430648322L;

    private Long id;
    private String fullName;
    private String email;
    private String nik;
    private UserStatus status;
    private String statusLabel;
    private String privyId;
    private String skill;
    private Date lastLoginDate;
    private RoleRefactor roleRefactor;
    private List<Division> division;
    private List<Position> position;
    private UserDetailSubData superior;
    private List<GroupMenuApps> groupMenuApps;
    private String userId;
    private UserFrom userFrom;
    private String bucketProcessId;
}
