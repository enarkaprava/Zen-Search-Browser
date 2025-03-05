import javafx.scene.Scene;

public class ThemeManager {
    private Scene scene;

    public ThemeManager(Scene scene) {
        this.scene = scene;
    }

    public void setTheme(String theme) {
        if ("bw".equalsIgnoreCase(theme)) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource("/bw.css").toExternalForm());
        } else {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource("/default.css").toExternalForm());
        }
    }
}