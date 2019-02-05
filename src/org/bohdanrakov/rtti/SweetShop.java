package org.bohdanrakov.rtti;

class Candy {
    static {
        System.out.println("Loading Candy");
    }
}

class Gum {
    static {
        System.out.println("Loading Gum");
    }
}

class Cookie {
    static {
        System.out.println("Loading Cookie");
    }
}

public class SweetShop {

    public static void main(String[] args) {
        String className = args[0];

        Class clazz = null;
        try {
            String packageName = SweetShop.class.getPackage().getName();
            clazz = Class.forName(packageName + "." + className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Object shape = null;
        try {
            shape = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
