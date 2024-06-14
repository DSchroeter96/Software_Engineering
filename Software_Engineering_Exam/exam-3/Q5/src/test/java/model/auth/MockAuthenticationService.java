package model.auth;

import model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class MockAuthenticationService implements AuthenticationService {
    private final Map<String, User> users;
    private User currentUser;

    /**
     * Creates a new remote authentication service.
     */
    public MockAuthenticationService() {
        users = new HashMap<>();
        users.put("Sweng", new User("Sweng", "1"));
        currentUser = null;
    }

    @Override
    public CompletableFuture<Void> login(String username, String password) {
        if (users.containsKey(username) && users.get(username).password().equals(password)) {
            currentUser = users.get(username);
            return CompletableFuture.completedFuture(null);
        }
        return CompletableFuture.failedFuture(new IllegalArgumentException("Failed to authenticate user: " + username));
    }

    @Override
    public CompletableFuture<Void> logout() {
        currentUser = null;
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public User getAuthenticatedUser() {
        return currentUser;
    }
}
