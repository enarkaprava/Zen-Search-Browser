import java.util.ArrayList;
import java.util.List;

public class BookmarkManager {
    private List<String> bookmarks;

    public BookmarkManager() {
        bookmarks = new ArrayList<>();
    }

    public void addBookmark(String url) {
        if (!bookmarks.contains(url)) {
            bookmarks.add(url);
        }
    }

    public List<String> getBookmarks() {
        return bookmarks;
    }
}