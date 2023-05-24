package pt.personalpage.profile;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfileService {
    public void createOrUpdate(Profile profile) {
        Profile.
                findAll()
                .stream().
                findAny().ifPresentOrElse(entity -> {
                    update((Profile)entity,profile);
                }, () -> {
                    profile.persist();
                });
    }

    private void update(Profile legacy, Profile updated){
        legacy.name = updated.name;
        legacy.introduction = updated.introduction;
        legacy.aboutMe = updated.aboutMe;
        legacy.contacts = updated.contacts;
        legacy.image = updated.image;
        legacy.update();
    }
}
