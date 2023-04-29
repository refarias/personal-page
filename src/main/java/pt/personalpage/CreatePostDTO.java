package pt.personalpage;


import javax.validation.constraints.NotBlank;

public class CreatePostDTO {
        @NotBlank public String path;
        public String coverImage;
        @NotBlank public String title;
        @NotBlank public String content;
        public boolean visible;
        @NotBlank public String language;
}
