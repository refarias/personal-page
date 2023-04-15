package pt.personalpage;


import org.jboss.resteasy.reactive.RestForm;

import javax.validation.constraints.NotBlank;

public class CreatePostDTO {
        @NotBlank public String path;
        @NotBlank public String title;
        @NotBlank public String content;
        public boolean visible;
        @NotBlank public String language;
}
