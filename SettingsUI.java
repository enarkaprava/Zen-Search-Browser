import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SettingsUI {
    private BrowserUI browserUI;

    public SettingsUI(BrowserUI browserUI) {
        this.browserUI = browserUI;
    }

    public void showCreateAccountDialog() {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Create Account");
        dialog.setHeaderText("Create a new account");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        gridPane.add(new javafx.scene.control.Label("Username:"), 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(new javafx.scene.control.Label("Password:"), 0, 1);
        gridPane.add(passwordField, 1, 1);

        dialog.getDialogPane().setContent(gridPane);
        dialog.getDialogPane().getButtonTypes().addAll(javafx.scene.control.ButtonType.OK, javafx.scene.control.ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == javafx.scene.control.ButtonType.OK) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                browserUI.getAccountManager().createAccount(username, password);
                showAlert("Account Created", "Your account has been successfully created.");
            }
            return null;
        });

        dialog.showAndWait();
    }

    public void showLoginDialog() {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Login");
        dialog.setHeaderText("Log in to your account");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        gridPane.add(new javafx.scene.control.Label("Username:"), 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(new javafx.scene.control.Label("Password:"), 0, 1);
        gridPane.add(passwordField, 1, 1);

        dialog.getDialogPane().setContent(gridPane);
        dialog.getDialogPane().getButtonTypes().addAll(javafx.scene.control.ButtonType.OK, javafx.scene.control.ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == javafx.scene.control.ButtonType.OK) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                if (browserUI.getAccountManager().login(username, password)) {
                    showAlert("Login Successful", "You have successfully logged in.");
                } else {
                    showAlert("Login Failed", "Invalid username or password.");
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}