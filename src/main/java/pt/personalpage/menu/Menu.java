package pt.personalpage.menu;

import io.quarkus.cache.CacheResult;
import pt.personalpage.post.Post;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public record Menu(Map<String, Object> items) {
    @CacheResult(cacheName = "menu-cache")
    public static Menu get() {
        var menu = new Menu(new HashMap<>());
        Post.listAll()
                .stream()
                .map(entity -> ((Post) entity).path)
                .collect(Collectors.toSet()).forEach(path -> menu.addItem(path));
        return menu;
    }

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
