package org.superdata.transform.models;

public class XmlData implements Data{
    @Override
    public Object reader() {
        return null;
    }

    @Override
    public Object process() {
        return null;
    }

    @Override
    public Object writer() {
        return null;
    }

    @Override
    public TypeData getType() {
        return TypeData.XML;
    }
}
