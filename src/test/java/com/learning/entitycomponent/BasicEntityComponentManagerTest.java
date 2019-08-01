package com.learning.entitycomponent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class BasicEntityComponentManagerTest {

    private static final int NEXT_ID = 1;

    private Map<Integer, Map<ComponentType, Component>> componentContainers;
    private Map<ComponentType, Set<Integer>> entitySets;

    private BasicEntityComponentManager basicEcManager;

    @BeforeEach
    void setUp() {
        componentContainers = new HashMap<>();
        entitySets = new LinkedHashMap<>();
        basicEcManager = new BasicEntityComponentManager(NEXT_ID, componentContainers, entitySets);
    }

    @Test
    void createEntity_AllWorks() {
        basicEcManager.createEntity()
                .addComponent(new DummyHealthComponent(11))
                .build();
        basicEcManager.createEntity()
                .addComponent(new DummyHealthComponent(22))
                .build();
        Set<Integer> expectedEntityIdSet = new LinkedHashSet<>();
        expectedEntityIdSet.add(1);
        expectedEntityIdSet.add(2);

        Set<Integer> actualEntityIdSet = basicEcManager.getComponentToEntitySet(DummyHealthComponent.class);
        Assertions.assertEquals(expectedEntityIdSet, actualEntityIdSet);

        int expectedHealthValue = 11;
        DummyHealthComponent dummyHealthComponent = basicEcManager.getComponent(1, DummyHealthComponent.class);
        int actualHealthValue = dummyHealthComponent.getHealth();

        Assertions.assertEquals(expectedHealthValue, actualHealthValue);
    }

    @Test
    void createEntity_DoesNotAllowDuplicateComponents() {
        Assertions.assertThrows(RuntimeException.class, () ->
            basicEcManager.createEntity()
                    .addComponent(new DummyHealthComponent(12))
                    .addComponent(new DummyHealthComponent(12))
                    .build()
        );

    }
    @Test
    void addComponent_EntityAlreadyExists() {
        int entityId = basicEcManager.getNextId();

        basicEcManager.createEntity()
                .addComponent(new DummyHealthComponent(11))
                .build();

        basicEcManager.addComponent(entityId, new DummyTestComponent(900));

        Set<Integer> expectedEntityIdSet = new LinkedHashSet<>();
        expectedEntityIdSet.add(1);

        Set<Integer> actualEntityIdSet = basicEcManager.getComponentToEntitySet(DummyTestComponent.class);
        Assertions.assertEquals(expectedEntityIdSet, actualEntityIdSet);

        int expectedTestValue = 900;
        DummyTestComponent dummyTestComponent = basicEcManager.getComponent(1, DummyTestComponent.class);
        int actualTestValue = dummyTestComponent.getValue();

        Assertions.assertEquals(expectedTestValue, actualTestValue);
    }

    @Test
    public void removeEntity() {
        int entityId = basicEcManager.getNextId();

        basicEcManager.createEntity()
                .addComponent(new DummyHealthComponent(11))
                .addComponent(new DummyTestComponent(900))
                .build();

        basicEcManager.removeEntity(entityId);

        Assertions.assertThrows(RuntimeException.class, () ->
                basicEcManager.getComponent(entityId, DummyHealthComponent.class));

        Assertions.assertThrows(RuntimeException.class, () ->
                basicEcManager.getComponent(entityId, DummyTestComponent.class));

        Set<Integer> entityIdSetForDummyHealthComponent = basicEcManager.getComponentToEntitySet(DummyHealthComponent.class);
        Assertions.assertFalse(entityIdSetForDummyHealthComponent .contains(entityId));
        Set<Integer> entityIdSetDummyTestComponent = basicEcManager.getComponentToEntitySet(DummyTestComponent.class);
        Assertions.assertFalse(entityIdSetDummyTestComponent.contains(entityId));
    }

    @Test
    public void removeComponentFromEntity() {
        int entityId = basicEcManager.getNextId();
        Component testComponent = new DummyTestComponent(900);

        basicEcManager.createEntity()
                .addComponent(new DummyHealthComponent(11))
                .addComponent(testComponent)
                .build();

        Assertions.assertNotNull(basicEcManager.getComponent(entityId, DummyTestComponent.class));

        basicEcManager.removeComponentFromEntity(entityId, testComponent.getComponentType());

        Assertions.assertNull(basicEcManager.getComponent(entityId, DummyTestComponent.class));

        Set<Integer> actualEntityIdSet = basicEcManager.getComponentToEntitySet(DummyTestComponent.class);
        Assertions.assertFalse(actualEntityIdSet.contains(entityId));
    }
}