import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class BrowserUI {
    private BorderPane root;
    private MenuBarUI menuBarUI;
    private ToolBarUI toolBarUI;
    private TabManager tabManager;
    private SettingsUI settingsUI;
    private ThemeManager themeManager;
    private SearchEngineManager searchEngineManager;
    private AccountManager accountManager;
    private VBox onionBox;
    private Text onionText;
    private Button yesButton;
    private Button noButton;

    public BrowserUI(Stage primaryStage) {
        root = new BorderPane();
        tabManager = new TabManager(primaryStage, this);
        menuBarUI = new MenuBarUI(this);
        toolBarUI = new ToolBarUI(this);
        settingsUI = new SettingsUI(this);
        themeManager = new ThemeManager(root.getScene());
        searchEngineManager = new SearchEngineManager();
        accountManager = new AccountManager();

        // Welcome Message
        VBox welcomeBox = new VBox();
        Text welcomeText = new Text("                                                                                        Search Securely with ZENSearch ");
        welcomeText.setFont(Font.font("Arial", 24));
        welcomeText.setFill(Color.RED);
        welcomeBox.getChildren().add(welcomeText);
        root.setTop(welcomeBox);

        // Set up the center layout with ToolBarUI and TabManager
        VBox centerLayout = new VBox();
        centerLayout.getChildren().addAll(toolBarUI.getToolBar(), tabManager.getTabPane());
        centerLayout.setStyle("-fx-alignment: center;");
        root.setCenter(centerLayout);

        // Onionization Question
        setupOnionizationQuestion();
    }

    private void setupOnionizationQuestion() {
        onionBox = new VBox();
        onionText = new Text("Do you want to Isolate the entire system by Onionizing it?");
        onionText.setFont(Font.font("Arial", 16));
        onionText.setFill(Color.RED);

        yesButton = new Button("Yes");
        noButton = new Button("No");

        yesButton.setOnAction(event -> {
            showAlert("Onionized", "Congratulations! Your IPv4, IPv6, and MAC Address has been blocked and when the Search engine opens kindly select the region whose temporary IP addresses you want to use.");
            onionBox.setVisible(false);
            onionBox.setManaged(false);
        });

        noButton.setOnAction(event -> {
            showAlert("Not Onionized", "Your system IPv4, IPv6, and MAC Addresses are visible to the Server.");
            onionBox.setVisible(false);
            onionBox.setManaged(false);
        });

        onionBox.getChildren().addAll(onionText, yesButton, noButton);
        onionBox.setStyle("-fx-alignment: center;");
        onionBox.setSpacing(10);
        root.setBottom(onionBox);
    }

    public BorderPane getRoot() {
        return root;
    }

    public ToolBarUI getToolBarUI() {
        return toolBarUI;
    }

    public TabManager getTabManager() {
        return tabManager;
    }

    public SettingsUI getSettingsUI() {
        return settingsUI;
    }

    public ThemeManager getThemeManager() {
        return themeManager;
    }

    public SearchEngineManager getSearchEngineManager() {
        return searchEngineManager;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}