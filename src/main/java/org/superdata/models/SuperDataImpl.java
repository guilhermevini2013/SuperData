package org.superdata.models;

public abstract class SuperDataImpl<T> {
    private T content;
    private TypeData type;

    public SuperDataImpl(T content, TypeData type) {
        this.content = content;
        this.type = type;
    }

    public T getContent() {
        return content;
    }

    public TypeData getType() {
        return type;
    }
}
