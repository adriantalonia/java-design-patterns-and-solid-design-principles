package _01_solid._04_interface_segregation_principle;

// ✅ Interface Segregation Principle (ISP) - All Examples in One File

// Split Interfaces
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

// Implementing small interfaces only as needed
class HumanWorker implements Workable, Eatable {
    public void work() {
        System.out.println("Human working...");
    }

    public void eat() {
        System.out.println("Human eating lunch...");
    }
}

class RobotWorker implements Workable {
    public void work() {
        System.out.println("Robot working...");
    }
}

// More granular interfaces for device functionality
interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

interface Fax {
    void fax();
}

class MultiFunctionPrinter implements Printer, Scanner, Fax {
    public void print() {
        System.out.println("Printing document...");
    }

    public void scan() {
        System.out.println("Scanning document...");
    }

    public void fax() {
        System.out.println("Faxing document...");
    }
}

class BasicPrinter implements Printer {
    public void print() {
        System.out.println("Basic print only");
    }
}

// Main class to demonstrate behavior
public class InterfaceSegregationPrincipleDemo {
    public static void main(String[] args) {
        // Worker examples
        Workable human = new HumanWorker();
        Workable robot = new RobotWorker();

        human.work();
        robot.work();

        ((Eatable) human).eat();

        // Printer examples
        Printer multiPrinter = new MultiFunctionPrinter();
        Scanner multiScanner = new MultiFunctionPrinter();
        Fax multiFax = new MultiFunctionPrinter();

        multiPrinter.print();
        multiScanner.scan();
        multiFax.fax();

        Printer basicPrinter = new BasicPrinter();
        basicPrinter.print();
    }
}
