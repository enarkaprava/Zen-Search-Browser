import javafx.scene.control.ToolBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;

public class ToolBarUI {
    private VBox toolBar;
    private TextField addressBar;
    private Button goButton;
    private Button bookmarkButton;
    private BrowserUI browserUI;

    public ToolBarUI(BrowserUI browserUI) {
        this.browserUI = browserUI;
        toolBar = new VBox();
        addressBar = new TextField();
        goButton = new Button("Go");
        bookmarkButton = new Button("Bookmark");

        addressBar.setPrefWidth(400); // Limit the width of the address bar

        goButton.setOnAction(event -> {
            String query = addressBar.getText();
            String url = browserUI.getSearchEngineManager().getActiveSearchEngine() + query;
            WebView webView = browserUI.getTabManager().getActiveTab().getWebView();
            webView.getEngine().load(url);
        });

        addressBar.setOnAction(event -> {
            String query = addressBar.getText();
            String url = browserUI.getSearchEngineManager().getActiveSearchEngine() + query;
            WebView webView = browserUI.getTabManager().getActiveTab().getWebView();
            webView.getEngine().load(url);
        });

        bookmarkButton.setOnAction(event -> {
            WebView webView = browserUI.getTabManager().getActiveTab().getWebView();
            String url = webView.getEngine().getLocation();
            browserUI.getTabManager().getBookmarkManager().addBookmark(url);
            showAlert("Bookmark Added", "The page has been bookmarked.");
        });

        HBox searchBarBox = new HBox();
        searchBarBox.getChildren().addAll(addressBar, goButton);
        searchBarBox.setSpacing(10);
        searchBarBox.setStyle("-fx-alignment: center;");

        toolBar.getChildren().addAll(searchBarBox, bookmarkButton);
        toolBar.setSpacing(10);
        toolBar.setStyle("-fx-alignment: center;");
    }

    public VBox getToolBar() {
        return toolBar;
    }

    public TextField getAddressBar() {
        return addressBar;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}