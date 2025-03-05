import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.scene.web.WebView;

public class TabManager {
    private TabPane tabPane;
    private Stage primaryStage;
    private BrowserUI browserUI;
    private HistoryManager historyManager;
    private BookmarkManager bookmarkManager;

    public TabManager(Stage primaryStage, BrowserUI browserUI) {
        this.primaryStage = primaryStage;
        this.browserUI = browserUI;
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        historyManager = new HistoryManager();
        bookmarkManager = new BookmarkManager();
        addNewTab();
    }

    public void addNewTab() {
        CustomTab tab = new CustomTab("New Tab");
        WebView webView = tab.getWebView();

        webView.getEngine().locationProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.isEmpty()) {
                historyManager.addHistoryEntry(newVal);
                browserUI.getToolBarUI().getAddressBar().setText(newVal);
            }
        });

        tab.setOnSelectionChanged(event -> {
            if (tab.isSelected()) {
                String url = webView.getEngine().getLocation();
                browserUI.getToolBarUI().getAddressBar().setText(url);
            }
        });

        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
    }

    public CustomTab getActiveTab() {
        return (CustomTab) tabPane.getSelectionModel().getSelectedItem();
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public HistoryManager getHistoryManager() {
        return historyManager;
    }

    public BookmarkManager getBookmarkManager() {
        return bookmarkManager;
    }
}