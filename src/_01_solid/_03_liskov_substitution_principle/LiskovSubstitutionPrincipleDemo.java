package _01_solid._03_liskov_substitution_principle;

/**
 * Liskov Substitution Principle (LSP) Examples
 *
 * Demonstrates proper and improper inheritance relationships where:
 * - Subtypes should be substitutable for their base types
 * - Without altering the correctness of the program
 */
public class LiskovSubstitutionPrincipleDemo {

    // ======================
    // 1. GOOD LSP EXAMPLES
    // ======================

    interface Bird {
        void makeSound();
    }

    // Proper LSP implementation - Sparrow IS-A Bird
    static class Sparrow implements Bird {
        @Override
        public void makeSound() {
            System.out.println("Chirp chirp!");
        }
    }

    // Another proper implementation
    static class Duck implements Bird {
        @Override
        public void makeSound() {
            System.out.println("Quack quack!");
        }

        public void swim() {
            System.out.println("Duck swimming");
        }
    }

    // ======================
    // 2. LSP VIOLATIONS
    // ======================

    // Classic LSP violation example
    static class Ostrich implements Bird {
        @Override
        public void makeSound() {
            System.out.println("Boom boom!");
        }

        // This breaks LSP because not all birds can fly
        public void fly() {
            throw new UnsupportedOperationException("Ostriches can't fly!");
        }
    }

    // ======================
    // 3. RECTANGLE-SQUARE PROBLEM
    // ======================

    static class Rectangle {
        protected int width;
        protected int height;

        public void setWidth(int width) {
            this.width = width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getArea() {
            return width * height;
        }
    }

    // LSP-violating Square subclass
    static class Square extends Rectangle {
        @Override
        public void setWidth(int width) {
            super.setWidth(width);
            super.setHeight(width); // Violation - changes height too
        }

        @Override
        public void setHeight(int height) {
            super.setHeight(height);
            super.setWidth(height); // Violation - changes width too
        }
    }

    // ======================
    // 4. PROPER LSP SOLUTION
    // ======================

    interface Shape {
        int getArea();
    }

    static class ProperRectangle implements Shape {
        private int width;
        private int height;

        public ProperRectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public int getArea() {
            return width * height;
        }
    }

    static class ProperSquare implements Shape {
        private int side;

        public ProperSquare(int side) {
            this.side = side;
        }

        @Override
        public int getArea() {
            return side * side;
        }
    }

    // ======================
    // 5. COLLECTION EXAMPLE
    // ======================

    static class CustomList<T> {
        protected int size = 0;

        public void add(T item) {
            // Implementation
            size++;
        }

        public int getSize() {
            return size;
        }
    }

    // LSP-compliant custom list
    static class BoundedList<T> extends CustomList<T> {
        private final int maxSize;

        public BoundedList(int maxSize) {
            this.maxSize = maxSize;
        }

        @Override
        public void add(T item) {
            if (size >= maxSize) {
                throw new IllegalStateException("List is full");
            }
            super.add(item);
        }
    }

    // ======================
    // 6. DEMO METHOD
    // ======================

    public static void main(String[] args) {
        System.out.println("=== GOOD LSP EXAMPLES ===");
        Bird sparrow = new Sparrow();
        sparrow.makeSound();

        Bird duck = new Duck();
        duck.makeSound();
        ((Duck) duck).swim(); // Duck-specific behavior

        System.out.println("\n=== LSP VIOLATION (Ostrich) ===");
        Bird ostrich = new Ostrich();
        ostrich.makeSound();
        try {
            ((Ostrich) ostrich).fly(); // Throws exception - violates LSP
        } catch (Exception e) {
            System.out.println("LSP Violation: " + e.getMessage());
        }

        System.out.println("\n=== RECTANGLE-SQUARE PROBLEM ===");
        Rectangle rect = new Rectangle();
        rect.setWidth(5);
        rect.setHeight(4);
        System.out.println("Rectangle area: " + rect.getArea());

        Rectangle squareAsRect = new Square();
        squareAsRect.setWidth(5);
        squareAsRect.setHeight(4); // Unexpected behavior - both become 4
        System.out.println("Square as Rectangle area: " + squareAsRect.getArea() + " (LSP violation)");

        System.out.println("\n=== PROPER LSP SOLUTION ===");
        Shape properRect = new ProperRectangle(5, 4);
        System.out.println("Proper Rectangle area: " + properRect.getArea());

        Shape properSquare = new ProperSquare(5);
        System.out.println("Proper Square area: " + properSquare.getArea());

        System.out.println("\n=== COLLECTION EXAMPLE ===");
        CustomList<String> list = new CustomList<>();
        list.add("Item 1");
        System.out.println("CustomList size: " + list.getSize());

        CustomList<String> boundedList = new BoundedList<>(2);
        boundedList.add("Item A");
        boundedList.add("Item B");
        try {
            boundedList.add("Item C"); // Throws exception - but doesn't violate LSP
            System.out.println("This won't print");
        } catch (IllegalStateException e) {
            System.out.println("BoundedList enforced capacity: " + e.getMessage());
        }
    }
}
