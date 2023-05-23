package pt.personalpage.menu;

import java.util.HashMap;
import java.util.Map;

public record Menu(Map<String, Object> items) {

    public void addItem(String path) {
        var tokens = path.split("/");
        int index = 0;
        var token = tokens[index];
        Map<String, Object> item = items;

        while (index < tokens.length - 1) {
            if (!item.containsKey(token)) {
                item.put(token, new HashMap<String, Object>());
            }
            item = (Map<String, Object>) item.get(token);
            token = tokens[++index];

        }
        item.put(token, path);
    }

}
