package pt.personalpage.profile;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.List;
import java.util.Map;

@MongoEntity(collection = "profile")
public class Profile extends PanacheMongoEntity {
    public String image;
    public String name;
    public String introduction;
    public String aboutMe;
    public Map<String,String> contacts;

    public Profile() {}

    public Profile(String image, String name, String introduction, String aboutMe, Map<String, String> contacts) {
        this.image = image;
        this.name = name;
        this.introduction = introduction;
        this.aboutMe = aboutMe;
        this.contacts = contacts;
    }
}
