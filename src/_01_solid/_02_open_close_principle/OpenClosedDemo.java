package _01_solid._02_open_close_principle;

public class OpenClosedDemo {

    // === BAD EXAMPLE: Violates the Open/Closed Principle ===
    static class AreaCalculatorBad {
        public double calculateArea(Object shape) {
            if (shape instanceof Circle) {
                Circle c = (Circle) shape;
                return Math.PI * c.radius * c.radius;
            } else if (shape instanceof Rectangle) {
                Rectangle r = (Rectangle) shape;
                return r.length * r.width;
            } else if (shape instanceof Triangle) {
                Triangle t = (Triangle) shape;
                return 0.5 * t.base * t.height;
            }
            return 0;
        }
    }

    static class Circle {
        double radius;

        public Circle(double radius) {
            this.radius = radius;
        }
    }

    static class Rectangle {
        double length, width;

        public Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
        }
    }

    static class Triangle {
        double base, height;

        public Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }
    }

    // === GOOD EXAMPLE: Applies the Open/Closed Principle ===

    interface Shape {
        double calculateArea();
    }

    static class CircleOCP implements Shape {
        double radius;

        public CircleOCP(double radius) {
            this.radius = radius;
        }

        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }
    }

    static class RectangleOCP implements Shape {
        double length, width;

        public RectangleOCP(double length, double width) {
            this.length = length;
            this.width = width;
        }

        @Override
        public double calculateArea() {
            return length * width;
        }
    }

    static class TriangleOCP implements Shape {
        double base, height;

        public TriangleOCP(double base, double height) {
            this.base = base;
            this.height = height;
        }

        @Override
        public double calculateArea() {
            return 0.5 * base * height;
        }
    }

    static class AreaCalculatorOCP {
        public double calculateArea(Shape shape) {
            return shape.calculateArea(); // No need to change when new shapes are added
        }
    }

    public static void main(String[] args) {

        System.out.println("=== BAD EXAMPLE (Violates OCP) ===");
        AreaCalculatorBad calcBad = new AreaCalculatorBad();
        System.out.println("Circle area: " + calcBad.calculateArea(new Circle(5)));
        System.out.println("Rectangle area: " + calcBad.calculateArea(new Rectangle(4, 6)));
        System.out.println("Triangle area: " + calcBad.calculateArea(new Triangle(4, 3)));
        // Adding a new shape would require modifying AreaCalculatorBad => Violates OCP

        System.out.println("\n=== GOOD EXAMPLE (Applies OCP) ===");
        AreaCalculatorOCP calcOCP = new AreaCalculatorOCP();
        System.out.println("Circle area: " + calcOCP.calculateArea(new CircleOCP(5)));
        System.out.println("Rectangle area: " + calcOCP.calculateArea(new RectangleOCP(4, 6)));
        System.out.println("Triangle area: " + calcOCP.calculateArea(new TriangleOCP(4, 3)));
        // New shapes can be added without modifying AreaCalculatorOCP
    }
}
