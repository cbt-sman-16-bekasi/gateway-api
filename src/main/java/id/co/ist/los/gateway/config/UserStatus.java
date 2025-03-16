package id.co.ist.los.gateway.config;

import java.util.HashMap;

public enum UserStatus {
    ACTIVE,
    IN_ACTIVE,
    SUSPEND;

    public String getStatusLabel(UserStatus status) {
        HashMap<UserStatus, String> userStatus = new HashMap<>();
        userStatus.put(ACTIVE, "Active");
        userStatus.put(IN_ACTIVE, "In Active");
        userStatus.put(SUSPEND, "Suspend");
        return userStatus.getOrDefault(status, "In Active");
    }
}
