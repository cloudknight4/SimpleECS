package com.learning.entitycomponent.arrayBased;

import java.util.HashMap;
import java.util.Map;

public class ComponentManager {

    private int nextIndex = 0;

    private Map<String, Integer> classToIndexMap = new HashMap<>();

    public void register(AbstractComponent abstractComponent) {
        String className = abstractComponent.getClass().getCanonicalName();
        if (classToIndexMap.containsKey(className)) {
            abstractComponent.setIndex(classToIndexMap.get(className));
        } else {
            int index = getNextIndex();
            classToIndexMap.put(className, index);
            abstractComponent.setIndex(index);
        }
    }

    public int getNextIndex() {
        return nextIndex++;
    }
}
