package pt.personalpage.menu;

import java.util.List;

public record ItemDTO(String name, String path, List<ItemDTO> children ) {}
