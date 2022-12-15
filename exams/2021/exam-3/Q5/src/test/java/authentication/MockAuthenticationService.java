package authentication;

import session.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class MockAuthenticationService implements AuthenticationService {
    private Map<String, String> users;
    private static String validToken = "sweng2021";

    public MockAuthenticationService() {
        users = new HashMap<>();
        users.put("SwengFan", "123456");
    }

    @Override
    public CompletableFuture<Session> login(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            var session = new Session(username, validToken);
            return CompletableFuture.completedFuture(session);
        }
        return CompletableFuture.failedFuture(new AuthenticationException("Failed to authenticate user: " + username));
    }
}
