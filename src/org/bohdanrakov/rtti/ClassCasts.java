package org.bohdanrakov.rtti;

class Building {

}

class House extends Building {

}

public class ClassCasts {

    public static void main(String[] args) {
        Building building = new House();
        Class<? extends  House> houseType = House.class;
        House house = houseType.cast(building);
    }
}
