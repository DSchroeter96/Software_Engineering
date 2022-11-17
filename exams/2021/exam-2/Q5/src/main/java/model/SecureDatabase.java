package model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Schroeter David (301062)
 */
public class SecureDatabase implements CredentialDatabase {
    private final CredentialDatabase database;
    private final Map<String, Integer> userNbTrial;

    public SecureDatabase(CredentialDatabase database) {
        // TODO not forget to test null check
        if (database == null)
            throw new IllegalArgumentException();

        this.database = database;
        this.userNbTrial = new HashMap<>();
    }

    @Override
    public AuthenticatedUser authenticate(String userName, String userPassword) throws DatabaseException {
        int nbTrial = this.userNbTrial.getOrDefault(userName, 0);
        if (nbTrial >= 3)
            throw new BlockedUserException(userName);

        this.userNbTrial.put(userName, nbTrial + 1);
        AuthenticatedUser user = this.database.authenticate(userName, userPassword);
        userNbTrial.put(userName, 0);
        return user;
    }
}
