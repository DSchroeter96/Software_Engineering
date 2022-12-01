package ch.epfl.sweng;

import java.util.Objects;

public class Story {

    public final int id;
    public final String title;
    public final String url;

    public Story(int id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Story)) return false;
        Story story = (Story) o;
        return id == story.id && Objects.equals(title, story.title) && Objects.equals(url, story.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, url);
    }
}
