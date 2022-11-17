import model.CredentialDatabase;
import model.SangriaDatabase;
import model.SecureDatabase;
import presenter.Presenter;
import presenter.SangriaPresenter;
import view.SangriaCliView;

/**
 * Main application entry point
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * You CAN edit everything in this file.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
public final class App {

    public static void main(String[] args) {
        SangriaCliView view = new SangriaCliView();
        CredentialDatabase model = new SecureDatabase(new SangriaDatabase());
        new SangriaPresenter(view, model);
        view.startApplication();
    }
}
