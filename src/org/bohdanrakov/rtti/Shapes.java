package org.bohdanrakov.rtti;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

abstract class Shape {
    private boolean highlighted;

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    void draw() {
        System.out.println(this + " draw()");
    }

    void rotate() {
        System.out.println(this + " rotate()");
    }

    @Override
    public String toString() {
        return getClass().getName() + ", highlighted: " + highlighted;
    }
}

class Triangle extends Shape {
}

class Square extends Shape {
}

class Circle extends Shape {
    @Override
    void rotate() {
    }
}

class Rhomboid extends Shape {
}

public class Shapes {

    static void highlightByType(Class shapeClass, List<Shape> shapes) {
        for (Shape shape : shapes) {
            if (shapeClass.isInstance(shape)) {
                shape.setHighlighted(true);
            }
        }
    }

    static void removeHighlightByType(Class shapeClass, List<Shape> shapes) {
        for (Shape shape : shapes) {
            if (shapeClass.isInstance(shape)) {
                shape.setHighlighted(false);
            }
        }
    }

    static void forEach(Class type, String methodName, List<Shape> shapes, Object... args) {
        Method method = null;

        try {
            method = type.getMethod(methodName, Boolean.TYPE);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        try {
            for (Shape shape : shapes) {
                if (type.isInstance(shape)) {
                    method.invoke(shape, args);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    static void highlightByType2(Class type, List<Shape> shapes) {
        forEach(type, "setHighlighted", shapes, true);
    }

    static void removeHighlightByType2(Class type, List<Shape> shapes) {
        forEach(type, "setHighlighted", shapes, false);
    }

    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Triangle());
        shapes.add(new Triangle());
        shapes.add(new Triangle());
        shapes.add(new Circle());
        shapes.add(new Circle());
        shapes.add(new Square());
        shapes.add(new Square());
        shapes.add(new Square());
        shapes.add(new Rhomboid());

        printShapes(shapes);

        for (Shape shape : shapes) {
            shape.rotate();
        }

        highlightByType(Triangle.class, shapes);
        highlightByType2(Rhomboid.class, shapes);
        printShapes(shapes);
        removeHighlightByType(Triangle.class, shapes);
        removeHighlightByType2(Square.class, shapes);
        printShapes(shapes);

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

    private static void printShapes(List<Shape> shapes) {
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
        System.out.println("--------------------");
    }
}
