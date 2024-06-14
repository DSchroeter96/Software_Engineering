import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Client for the JaaS API.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * You CAN add new constructors, methods, and nested classes to this class.
 * You CAN edit the parameters, return types, and checked exceptions of the existing method and constructor.
 * You MUST NOT edit the names of the existing method and constructor.
 * You MUST NOT rename this file.
 * You MUST NOT delete this file.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */

interface HttpClient {
    String getUsers(String url) throws IOException;
}

interface RoleClient {
    TreeSet<String> getRoles();
}
public class JaasClient {

    private final HttpClient httpClient;
    private final RoleClient roleClient;
    private final String path;

    /**
     * Creates a new client for the JaaS API.
     */
    public JaasClient(String path, HttpClient httpClient, RoleClient roleClient) {
        if (path == null) {
            throw new IllegalArgumentException("JaaS backend URL may not be null.");
        }
        if (httpClient == null) {
            throw new IllegalArgumentException("JaaS http client may not be null");
        }
        if (roleClient == null) {
            throw new IllegalArgumentException("JaaS role client may not be null");
        }

        this.path = path;
        this.httpClient = httpClient;
        this.roleClient = roleClient;
    }

    /**
     * Runs the client, querying for users with specific roles and printing them.
     */
    public void run() {

        // Retrieve the list of roles.
        var roles = roleClient.getRoles();

        var url = path + "/users/" + String.join(",", roles);

        String users;
        try {
            // Using \\A as the delimiter is a Java trick to get the Scanner to read the entire input
            users = httpClient.getUsers(url);
        } catch (IOException e) {
            System.out.println("Users could not be fetched from the server.");
            return;
        }

        for (var user : users.split("\n")) {
            System.out.println(user);
        }
    }
}