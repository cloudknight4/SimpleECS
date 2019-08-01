package com.learning.entitycomponent;

public class DummyTestComponent implements Component {
    private static final ComponentType COMPONENT_TYPE = new ComponentType(DummyTestComponent.class.getName());

    private int value;

    public DummyTestComponent(int value) {
        this.value = value;
    }

    @Override
    public ComponentType getComponentType() {
        return COMPONENT_TYPE;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
