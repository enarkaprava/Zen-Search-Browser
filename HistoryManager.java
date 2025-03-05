import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private List<String> history;

    public HistoryManager() {
        history = new ArrayList<>();
    }

    public void addHistoryEntry(String url) {
        if (!history.isEmpty() && history.get(history.size() - 1).equals(url)) {
            return; // Avoid adding duplicate consecutive entries
        }
        history.add(url);
    }

    public List<String> getHistory() {
        return history;
    }
}