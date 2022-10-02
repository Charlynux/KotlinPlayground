package io.charlynux.playground;

import java.util.Objects;

public class Info {
    private String title;

    public Info() {}

    public Info(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return Objects.equals(title, info.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
