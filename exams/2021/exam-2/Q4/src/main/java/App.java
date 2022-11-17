import model.*;
import view.AuthenticatedUserView;
import view.ErrorView;

import java.util.Scanner;

/**
 * Main application entry point
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * You CAN edit everything in this file.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
public final class App {

    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CredentialDatabase model = new SangriaDatabase();
        // We want to pass an AuthenticatedUser to the view but the database interface
        // prevents us from doing so...
        AuthResult result = model.authenticate(args[0], args[1]);
        if (result.isSuccessful()) {
            AuthenticatedUser user = new AuthenticatedUserAdapter(result.getData());
            System.out.println(new AuthenticatedUserView(user));
        } else {
            System.out.println(new ErrorView(result.getException().getMessage()));
        }
    }

}
