import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private Map<String, String> accounts;

    public AccountManager() {
        accounts = new HashMap<>();
    }

    public void createAccount(String username, String password) {
        accounts.put(username, password);
    }

    public boolean login(String username, String password) {
        return accounts.containsKey(username) && accounts.get(username).equals(password);
    }
}