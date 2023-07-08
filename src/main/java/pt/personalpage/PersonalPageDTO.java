package pt.personalpage;

import pt.personalpage.menu.MenuDTO;
import pt.personalpage.post.Post;
import pt.personalpage.profile.Profile;

import java.util.List;

public record PersonalPageDTO(Profile profile, MenuDTO menu, List<Post> posts) {
}
