package org.superdata.transform.models;

public interface Data {
    public abstract Object reader();
    public abstract Object process();
    public abstract Object writer();
    public abstract TypeData getType();
}
