public class SearchEngineManager {
    private String activeSearchEngine;

    public SearchEngineManager() {
        activeSearchEngine = "https://duckduckgo.com/?q=";
    }

    public void setActiveSearchEngine(String engine) {
        if ("google".equalsIgnoreCase(engine)) {
            activeSearchEngine = "https://www.google.com/search?q=";
        } else {
            activeSearchEngine = "https://duckduckgo.com/?q=";
        }
    }

    public String getActiveSearchEngine() {
        return activeSearchEngine;
    }
}