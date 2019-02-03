package org.bohdanrakov.rtti;

interface HasBatteries {
}

interface Waterproof {
}

interface Shoots {
}

interface Bulletproof {
}

class Toy {
    Toy() {
    }

    Toy(int i) {
    }
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots, Bulletproof {
    FancyToy() {
        super(1);
    }
}

public class ToyTest {
    static void printClassInfo(Class clazz) {
        System.out.println("Name: " + clazz.getName() + ", is interface? " + clazz.isInterface());
        System.out.println("Simple Name: " + clazz.getSimpleName());
        System.out.println("Cannonical Name: " + clazz.getCanonicalName());
    }

    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("org.bohdanrakov.rtti.FancyToy");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        printClassInfo(c);

        for (Class anInterface : c.getInterfaces()) {
            printClassInfo(anInterface);
        }

        Class up = c.getSuperclass();
        Object upObj = null;
        try {
            upObj = up.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        printClassInfo(upObj.getClass());
    }
}
