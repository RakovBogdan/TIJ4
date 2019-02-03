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
        new Candy();

        try {
            Class.forName("org.bohdanrakov.rtti.Gum");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("after Class.forName(\"Gum\")");
        new Cookie();

    }
}
