package com.learning.speed;

import com.learning.entitycomponent.Component;
import com.learning.entitycomponent.ComponentType;

public class Components {


    public static class AString implements Component {
        private static final ComponentType COMPONENT_TYPE = new ComponentType(AString.class.getName());

        public String string;

        @Override
        public ComponentType getComponentType() {
            return COMPONENT_TYPE;
        }
    }

    public static class BString implements Component {
        private static final ComponentType COMPONENT_TYPE = new ComponentType(BString.class.getName());

        public String string;

        @Override
        public ComponentType getComponentType() {
            return COMPONENT_TYPE;
        }
    }
    public static class CString implements Component {
        private static final ComponentType COMPONENT_TYPE = new ComponentType(CString.class.getName());

        public String string;

        @Override
        public ComponentType getComponentType() {
            return COMPONENT_TYPE;
        }
    }

    public static class DString implements Component {
        private static final ComponentType COMPONENT_TYPE = new ComponentType(DString.class.getName());

        public String string;

        @Override
        public ComponentType getComponentType() {
            return COMPONENT_TYPE;
        }
    }
    public static class EString implements Component {
        private static final ComponentType COMPONENT_TYPE = new ComponentType(EString.class.getName());

        public String string;

        @Override
        public ComponentType getComponentType() {
            return COMPONENT_TYPE;
        }
    }

    public static class AInteger implements Component {
        private static final ComponentType COMPONENT_TYPE = new ComponentType(AInteger.class.getName());

        public int integer;

        @Override
        public ComponentType getComponentType() {
            return COMPONENT_TYPE;
        }
    }

    public static class BInteger implements Component {
        private static final ComponentType COMPONENT_TYPE = new ComponentType(BInteger.class.getName());

        public int integer;

        @Override
        public ComponentType getComponentType() {
            return COMPONENT_TYPE;
        }
    }

    public static class CInteger implements Component {
        private static final ComponentType COMPONENT_TYPE = new ComponentType(CInteger.class.getName());

        public int integer;

        @Override
        public ComponentType getComponentType() {
            return COMPONENT_TYPE;
        }
    }

    public static class DInteger implements Component {
        private static final ComponentType COMPONENT_TYPE = new ComponentType(DInteger.class.getName());

        public int integer;

        @Override
        public ComponentType getComponentType() {
            return COMPONENT_TYPE;
        }
    }

    public static class EInteger implements Component {
        private static final ComponentType COMPONENT_TYPE = new ComponentType(EInteger.class.getName());

        public int integer;

        @Override
        public ComponentType getComponentType() {
            return COMPONENT_TYPE;
        }
    }

    public static class GInteger implements Component {
        private static final ComponentType COMPONENT_TYPE = new ComponentType(GInteger.class.getName());

        public int integer;

        @Override
        public ComponentType getComponentType() {
            return COMPONENT_TYPE;
        }
    }
}
