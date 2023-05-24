package pt.personalpage;

import pt.personalpage.menu.Menu;
import pt.personalpage.post.Post;
import pt.personalpage.profile.Profile;

import java.util.List;

public record PersonalPageDTO(Profile profile, Menu menu, List<Post> posts) {
}
