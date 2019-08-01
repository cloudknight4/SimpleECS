package com.learning.entitycomponent;

import java.util.Objects;

public class ComponentType {

    private String typeName;

    public ComponentType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return "ComponentType{" +
                "typeName='" + typeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentType that = (ComponentType) o;
        return typeName.equals(that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName);
    }

}
