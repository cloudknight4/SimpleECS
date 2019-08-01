package com.learning.entitycomponent;

import java.util.*;

public class BasicEntityComponentManager implements EntityComponentManager {

    private int nextId;
    private Map<Integer, Map<ComponentType, Component>> componentContainers;
    private Map<ComponentType, Set<Integer>> entitySets;

    public BasicEntityComponentManager(int nextId) {
        this(nextId, new HashMap<>(), new LinkedHashMap<>());
    }

    public BasicEntityComponentManager(int nextId,
                                       Map<Integer, Map<ComponentType, Component>> componentContainers,
                                       Map<ComponentType, Set<Integer>> entitySets) {
        this.nextId = nextId;
        this.componentContainers = componentContainers;
        this.entitySets = entitySets;
    }

    public int getNextId() {
        return nextId;
    }

    public Map<Integer, Map<ComponentType, Component>> getComponentContainers() {
        return componentContainers;
    }

    public Map<ComponentType, Set<Integer>> getEntitySets() {
        return entitySets;
    }

    @Override
    public EntityBuilder createEntity() {
        return new EntityBuilder(nextId++);
    }

    @Override
    public void addComponent(int entityId, Component component) {
        addComponentToEntity(entityId, component);
        insertMappingFromComponentToEntities(entityId, component.getComponentType());
    }

    @Override
    public boolean hasComponent(int entityId, Class<? extends Component> tClass) {
        return componentContainers.get(entityId).containsKey(new ComponentType(tClass.getName()));
    }

    @Override
    public <T extends Component> T getComponent(int entityId, Class<T> tClass) {
        try {
            return (T) componentContainers.get(entityId)
                    .get(new ComponentType(tClass.getName()));
        } catch (Exception e) {
            throw new RuntimeException("Entity probably no longer exists if null pointer exception", e);
        }
    }

    @Override
    public Set<Integer> getComponentToEntitySet(Class<? extends Component> tClass) {
        return entitySets.get(new ComponentType(tClass.getName()));
    }

    @Override
    public void removeEntity(int entityId) {
        removeEntityFromComponentContainers(entityId);
        removeEntityFromEntitySets(entityId);
    }

    @Override
    public void removeComponentFromEntity(int entityId, ComponentType componentType) {
        componentContainers.get(entityId).remove(componentType);
        entitySets.get(componentType).remove(entityId);
    }

    private void addComponentToEntity(int entityId, Component component) {
        Map<ComponentType, Component> componentMap = componentContainers.get(entityId);
        if (componentMap == null) {
            componentMap = new HashMap<>();
            componentMap.put(component.getComponentType(), component);
            componentContainers.put(entityId, componentMap);
        } else {
            if (componentMap.containsKey(component.getComponentType())) {
                throw new RuntimeException("EntityId is already mapped to the entitycomponent. " +
                        "The adding of a duplicate Component to an Entity is not allowed.");
            }
            componentMap.put(component.getComponentType(), component);
            componentContainers.put(entityId, componentMap);
        }
    }

    private void insertMappingFromComponentToEntities(int entityId, ComponentType componentType) {
        Set<Integer> entityIdSet = entitySets.get(componentType);
        if (entityIdSet == null) {
            Set<Integer> newSet = new LinkedHashSet<>();
            newSet.add(entityId);
            entitySets.put(componentType, newSet);
        } else {
            Set<Integer> set = entitySets.get(componentType);
            if (set.contains(entityId)) {
                throw new RuntimeException("EntityId is already mapped to this ComponentType. " +
                        "The adding of a duplicate Component to an Entity is not allowed.");
            }
            set.add(entityId);
        }
    }

    private void removeEntityFromComponentContainers(int entityId) {
        componentContainers.remove(entityId);
    }

    private void removeEntityFromEntitySets(int entityId) {
        Iterator<Map.Entry<ComponentType, Set<Integer>>> iterator = entitySets.entrySet().iterator();
        while (iterator.hasNext()) {
            iterator.next().getValue().remove(entityId);
        }
    }

    public class EntityBuilder implements EntityComponentManager.EntityBuilder {
        private int entityId;
        private List<Component> componentList;

        EntityBuilder(int entityId) {
            this.entityId = entityId;
            this.componentList = new ArrayList<>();
        }

        @Override
        public EntityBuilder addComponent(Component component) {
            componentList.add(component);
            return this;
        }

        @Override
        public void build() {
            if (componentList.isEmpty()) {
                throw new RuntimeException("Entities must have at least one Component attached to it");
            }
            for (Component component : componentList) {
                addComponentToEntity(entityId, component);
                insertMappingFromComponentToEntities(entityId, component.getComponentType());
            }
        }
    }
}
