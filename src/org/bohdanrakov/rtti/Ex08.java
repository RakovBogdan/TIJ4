package org.bohdanrakov.rtti;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;

public class Ex08 {

    private static HashSet<Class> displayedClasses = new HashSet<>();


    public static void main(String[] args) throws Exception {
        HashMap<String, String> test = new HashMap<>();
        FancyToy fancyToy = new FancyToy();
        printClassHierarchy(test);
    }

    private static void printClassHierarchy(Object object) {
        Class clazz = object.getClass();
        printClassesRecursively(clazz, 0);
    }

    private static void printClassesRecursively(Class clazz, int hierarchyPlace) {
        displayedClasses.add(clazz);

        if (clazz == null) {
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hierarchyPlace; i++) {
            stringBuilder.append("-");
        }

        System.out.println(stringBuilder.toString() + clazz.getName());

        Field[] declaredFields = clazz.getDeclaredFields();
        if (declaredFields.length != 0) {
            System.out.println(clazz.getName() + " fields:");
            for (Field field : declaredFields) {
                if (!displayedClasses.contains(field.getDeclaringClass())) {
                    printClassesRecursively(field.getDeclaringClass(), hierarchyPlace + 1);
                    displayedClasses.add(field.getDeclaringClass());
                }
            }
        }

        Class superClass = clazz.getSuperclass();
        for (Class anInterface : clazz.getInterfaces()) {
            printClassesRecursively(anInterface, hierarchyPlace + 1);
        }
        printClassesRecursively(superClass, hierarchyPlace + 1);
    }
}
