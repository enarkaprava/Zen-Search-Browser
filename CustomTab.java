import javafx.scene.control.Tab;
import javafx.scene.web.WebView;

public class CustomTab extends Tab {
    private WebView webView;

    public CustomTab(String title) {
        super(title);
        webView = new WebView();
        setContent(webView);
    }

    public WebView getWebView() {
        return webView;
    }
}