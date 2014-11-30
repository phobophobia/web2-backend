package hu.tilos.radio.backend.data.output;

import hu.radio.tilos.model.type.TagType;

public class TagCloudElement {

    private String name;

    private int count;

    private TagType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public TagType getType() {
        return type;
    }

    public void setType(TagType type) {
        this.type = type;
    }
}
