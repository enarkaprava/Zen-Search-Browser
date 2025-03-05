import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ListView;
import javafx.scene.control.Dialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.web.WebView;

public class MenuBarUI {
    private MenuBar menuBar;
    private BrowserUI browserUI;

    public MenuBarUI(BrowserUI browserUI) {
        this.browserUI = browserUI;
        menuBar = new MenuBar();
        setupFileMenu();
        setupEditMenu();
        setupViewMenu();
        setupHelpMenu();
        setupAccountsMenu();
        setupHistoryMenu();
        setupBookmarksMenu();
    }

    private void setupFileMenu() {
        Menu fileMenu = new Menu("File");
        MenuItem newTabItem = new MenuItem("New Tab");
        newTabItem.setOnAction(event -> browserUI.getTabManager().addNewTab());
        fileMenu.getItems().add(newTabItem);
        menuBar.getMenus().add(fileMenu);
    }

    private void setupEditMenu() {
        Menu editMenu = new Menu("Edit");
        // Add Edit menu items here
        menuBar.getMenus().add(editMenu);
    }

    private void setupViewMenu() {
        Menu viewMenu = new Menu("View");
        MenuItem themeBWItem = new MenuItem("Black & White");
        themeBWItem.setOnAction(event -> browserUI.getThemeManager().setTheme("bw"));
        MenuItem themeDefaultItem = new MenuItem("Default");
        themeDefaultItem.setOnAction(event -> browserUI.getThemeManager().setTheme("default"));
        viewMenu.getItems().addAll(themeBWItem, themeDefaultItem);
        menuBar.getMenus().add(viewMenu);
    }

    private void setupHelpMenu() {
        Menu helpMenu = new Menu("Help");
        // Add Help menu items here
        menuBar.getMenus().add(helpMenu);
    }

    private void setupAccountsMenu() {
        Menu accountsMenu = new Menu("Accounts");
        MenuItem createAccountItem = new MenuItem("Create Account");
        createAccountItem.setOnAction(event -> browserUI.getSettingsUI().showCreateAccountDialog());
        MenuItem loginItem = new MenuItem("Login");
        loginItem.setOnAction(event -> browserUI.getSettingsUI().showLoginDialog());
        accountsMenu.getItems().addAll(createAccountItem, loginItem);
        menuBar.getMenus().add(accountsMenu);
    }

    private void setupHistoryMenu() {
        Menu historyMenu = new Menu("History");
        MenuItem viewHistoryItem = new MenuItem("View History");
        viewHistoryItem.setOnAction(event -> showHistoryDialog());
        historyMenu.getItems().add(viewHistoryItem);
        menuBar.getMenus().add(historyMenu);
    }

    private void setupBookmarksMenu() {
        Menu bookmarksMenu = new Menu("Bookmarks");
        MenuItem viewBookmarksItem = new MenuItem("View Bookmarks");
        viewBookmarksItem.setOnAction(event -> showBookmarksDialog());
        bookmarksMenu.getItems().addAll(viewBookmarksItem);
        menuBar.getMenus().add(bookmarksMenu);
    }

    private void showHistoryDialog() {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("History");
        dialog.setHeaderText("Browsing History");

        ListView<String> listView = new ListView<>();
        ObservableList<String> historyItems = FXCollections.observableArrayList(browserUI.getTabManager().getHistoryManager().getHistory());
        listView.setItems(historyItems);

        dialog.getDialogPane().setContent(listView);
        dialog.getDialogPane().getButtonTypes().add(javafx.scene.control.ButtonType.CLOSE);

        dialog.showAndWait();
    }

    private void showBookmarksDialog() {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Bookmarks");
        dialog.setHeaderText("Bookmarks");

        ListView<String> listView = new ListView<>();
        ObservableList<String> bookmarkItems = FXCollections.observableArrayList(browserUI.getTabManager().getBookmarkManager().getBookmarks());
        listView.setItems(bookmarkItems);

        listView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String url = listView.getSelectionModel().getSelectedItem();
                if (url != null) {
                    WebView webView = browserUI.getTabManager().getActiveTab().getWebView();
                    webView.getEngine().load(url);
                    dialog.close();
                }
            }
        });

        dialog.getDialogPane().setContent(listView);
        dialog.getDialogPane().getButtonTypes().add(javafx.scene.control.ButtonType.CLOSE);

        dialog.showAndWait();
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}