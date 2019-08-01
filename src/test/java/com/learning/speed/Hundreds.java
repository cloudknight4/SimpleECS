package com.learning.speed;

import com.learning.entitycomponent.BasicEntityComponentManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Hundreds {
;

    BasicEntityComponentManager basicEntityComponentManager;

    @BeforeEach
    void setUp() {
        basicEntityComponentManager = new BasicEntityComponentManager(1);


    }

    @Test
    public void components100Entities() {
        basicEntityComponentManager.createEntity()
                .addComponent(new Components.AString())
                .addComponent(new Components.BString())
                .build();
    }



}
