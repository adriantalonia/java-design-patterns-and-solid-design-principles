package _01_solid._01_single_responsability_principle;

public class SingleResponsibilityDemo {

    // BAD EXAMPLE: Violates SRP
    static class InvoiceBad {
        String customer;
        double amount;

        public InvoiceBad(String customer, double amount) {
            this.customer = customer;
            this.amount = amount;
        }

        public double calculateTotal() {
            // Business logic
            return amount * 1.16; // Example: adding 16% tax
        }

        public void printInvoice() {
            // UI responsibility
            System.out.println("Invoice for: " + customer);
            System.out.println("Total: $" + calculateTotal());
        }

        public void saveToDatabase() {
            // Persistence responsibility
            System.out.println("Saving invoice to database for customer: " + customer);
        }
    }

    // GOOD EXAMPLE: Follows SRP - each class has one responsibility

    // Class responsible only for invoice data and calculation
    static class Invoice {
        String customer;
        double amount;

        public Invoice(String customer, double amount) {
            this.customer = customer;
            this.amount = amount;
        }

        public double calculateTotal() {
            return amount * 1.16; // Example: adding tax
        }

        public String getCustomer() {
            return customer;
        }

        public double getAmount() {
            return amount;
        }
    }

    // Class responsible for printing invoices
    static class InvoicePrinter {
        public void print(Invoice invoice) {
            System.out.println("=== Invoice ===");
            System.out.println("Customer: " + invoice.getCustomer());
            System.out.println("Amount: $" + invoice.getAmount());
            System.out.println("Total (with tax): $" + invoice.calculateTotal());
        }
    }

    // Class responsible for persisting invoices
    static class InvoiceRepository {
        public void save(Invoice invoice) {
            // Simulate saving to database
            System.out.println("Saving invoice for " + invoice.getCustomer() + " with total $" + invoice.calculateTotal());
        }
    }

    // MAIN method to run the examples
    public static void main(String[] args) {
        System.out.println("=== Violating SRP ===");
        InvoiceBad badInvoice = new InvoiceBad("John Doe", 100.0);
        badInvoice.printInvoice();
        badInvoice.saveToDatabase();

        System.out.println("\n=== Applying SRP ===");
        Invoice invoice = new Invoice("Jane Smith", 200.0);
        InvoicePrinter printer = new InvoicePrinter();
        InvoiceRepository repository = new InvoiceRepository();

        printer.print(invoice);        // Responsible only for printing
        repository.save(invoice);      // Responsible only for saving
    }
}

