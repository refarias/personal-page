package pt.personalpage.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record MenuDTO(List<ItemDTO> items) {
    public MenuDTO( Menu menu) {
        this(mapItemToItem(menu.items()));
    }

    private static List<ItemDTO> mapItemToItem(Map<String,Object> mapItem) {
        var items = new ArrayList<ItemDTO>();
        for ( var key : mapItem.keySet()) {
            var objValue = mapItem.get(key);
            if ( objValue instanceof String path ) {
                items.add(new ItemDTO(key, path, null));
            }else if(objValue instanceof Map children ){
                var item = new ItemDTO(key,null, mapItemToItem(children));
                items.add(item);
            }
        }
        return items;
    }
}
