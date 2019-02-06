package org.bohdanrakov.rtti;

public class Ex10 {

    public static void main(String[] args) {
        char[] charArray = {'a', 'b', 'c'};
        System.out.println(charArray.getClass().getName());
        System.out.println(charArray.getClass().getSuperclass().equals(Object.class));
        System.out.println(Object.class.isAssignableFrom(charArray.getClass()));
        System.out.println(Object.class.isInstance(charArray));
        System.out.println(charArray instanceof Object);
    }
}
