package com.learning.entitycomponent;

import java.util.Map;
import java.util.Set;

public interface EntityComponentManager {

    EntityBuilder createEntity();

    void addComponent(int entityId, Component component);

    boolean hasComponent(int entityId, Class<? extends Component> tClass);

    <T extends Component> T getComponent(int entityId, Class<T> tClass);

    Set<Integer> getComponentToEntitySet(Class<? extends Component> tClass);

    void removeEntity(int entityId);

    void removeComponentFromEntity(int entityId, ComponentType componentType);

    interface EntityBuilder {
        EntityBuilder addComponent(Component component);
        void build();
    }

}
