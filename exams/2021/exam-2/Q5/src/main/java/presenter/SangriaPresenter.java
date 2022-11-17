package presenter;

import model.AuthenticatedUser;
import model.CredentialDatabase;
import model.DatabaseException;
import view.View;

/**
 * @author Schroeter David (301062)
 */
public class SangriaPresenter implements Presenter {

    private final View view;
    private final CredentialDatabase model;


    public SangriaPresenter(View view, CredentialDatabase model) {
        // TODO not forget to check for null
        if (view == null || model == null) {
            throw new IllegalArgumentException();
        }
        this.view = view;
        this.model = model;
        this.view.setPresenter(this);
    }

    @Override
    public void authenticateUser(String userName, String password) {
        try {
            AuthenticatedUser authenticatedUser = model.authenticate(userName, password);
            this.view.displayUser(authenticatedUser);
        } catch (DatabaseException e) {
            this.view.displayError(e.getMessage());
        }
    }
}
