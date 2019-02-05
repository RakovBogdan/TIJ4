package org.bohdanrakov.rtti;

import java.util.HashMap;

public class Ex08 {

    public static void main(String[] args) throws Exception {
        HashMap<String, String> test = new HashMap<>();
        FancyToy fancyToy = new FancyToy();
        printClassHierarchy(fancyToy);
    }

    private static void printClassHierarchy(Object object) {
        Class clazz = object.getClass();
        printClassesRecursively(clazz, 0);
    }

    private static void printClassesRecursively(Class clazz, int hierarchyPlace) {
        if (clazz == null) {
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hierarchyPlace; i++) {
            stringBuilder.append("-");
        }

        System.out.println(stringBuilder.toString() + clazz.getName());

        Class superClass = clazz.getSuperclass();
        for (Class anInterface : clazz.getInterfaces()) {
            printClassesRecursively(anInterface, hierarchyPlace + 1);
        }
        printClassesRecursively(superClass, hierarchyPlace + 1);
    }
}
