package model;

import java.util.Map;

/**
 * @author Schroeter David (301062)
 */
public class AuthenticatedUserAdapter implements AuthenticatedUser {
    private final String userName;
    private final String sciper;

    public AuthenticatedUserAdapter(Map<String, Object> data) {
        if (!data.containsKey("userName") || !data.containsKey("sciper")) {
            throw new IllegalArgumentException("Invalid data");
        }
        this.userName = data.get("userName").toString();
        this.sciper = data.get("sciper").toString();
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getSciper() {
        return sciper;
    }
}
