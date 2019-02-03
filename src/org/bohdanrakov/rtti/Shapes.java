package org.bohdanrakov.rtti;

import java.util.ArrayList;
import java.util.List;

abstract class Shape {
    private boolean highlighted;

    public abstract String toString();

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    void draw() {
        System.out.println(this + ".draw()");
    }

    void rotate() {
        System.out.println(this + ".rotate()");
    }


}

class Triangle extends Shape {

    @Override
    public String toString() {
        return "Triangle" + ", isHighlighted: " + isHighlighted();
    }
}

class Square extends Shape {
    @Override
    public String toString() {
        return "Square" + ", isHighlighted: " + isHighlighted();
    }
}

class Circle extends Shape {
    @Override
    public String toString() {
        return "Circle" + ", isHighlighted: " + isHighlighted();
    }

    @Override
    void rotate() { }
}

class Rhomboid extends Shape {
    @Override
    public String toString() {
        return "Rhomboid" + ", isHighlighted: " + isHighlighted();
    }
}

public class Shapes {

    static void highlightByType(Class shapeClass, List<Shape> shapes) {
        for (Shape shape : shapes) {
            if (shapeClass.isInstance(shape)) {
                shape.setHighlighted(true);
            }
        }
    }

    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Triangle());
        shapes.add(new Circle());
        shapes.add(new Square());
        shapes.add(new Rhomboid());

        for (Shape shape : shapes) {
            System.out.println(shape);
        }

        for (Shape shape : shapes) {
            shape.rotate();
        }

        highlightByType(Triangle.class, shapes);

        for (Shape shape : shapes) {
            System.out.println(shape);
        }

        Class clazz = null;

        Shape shape = new Rhomboid();

        if (shape instanceof Rhomboid) {
            ((Rhomboid) shape).draw();
        }

        if (shape instanceof Circle) {
            ((Circle) shape).draw();
        } else {
            System.out.println("Shape is Not a circle");
        }
    }
}
