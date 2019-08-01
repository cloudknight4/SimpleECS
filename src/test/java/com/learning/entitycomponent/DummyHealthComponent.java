package com.learning.entitycomponent;

public class DummyHealthComponent implements Component {
    private static final ComponentType COMPONENT_TYPE = new ComponentType(DummyHealthComponent.class.getName());

    private int health;

    public DummyHealthComponent(int health) {
        this.health = health;
    }

    @Override
    public ComponentType getComponentType() {
        return COMPONENT_TYPE;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
