package com.konoha.konoha_server.services;
import com.github.slugify.Slugify;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class Slug {

    private Slugify slg;
    public Slug() {
        this.slg=new Slugify();
    }

    public String setSlugify(String item){
        return slg.slugify(item);
    }
}
