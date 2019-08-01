package com.learning;

import com.learning.entitycomponent.Component;
import com.learning.entitycomponent.ComponentType;
import org.junit.jupiter.api.Test;

public class Wondering {

    @Test
    public void test() {
        System.out.println(getComponentName(TestComponent.class));
    }

    private String getComponentName(Class<? extends Component> tClass) {
        return tClass.getName();
    }


    public class TestComponent implements Component {
        public int value = 27;

        @Override
        public ComponentType getComponentType() {
            return null;
        }

        @Override
        public String toString() {
            return "TestComponent{" +
                    "value=" + value +
                    '}';
        }
    }

}
