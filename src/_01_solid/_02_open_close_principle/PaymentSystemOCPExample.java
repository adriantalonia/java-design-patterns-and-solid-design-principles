package _01_solid._02_open_close_principle;

/**
 * Payment Processing System Example - Open/Closed Principle (OCP)
 * <p>
 * Demonstrates how to extend payment methods without modifying existing code.
 */
public class PaymentSystemOCPExample {

    // 1. Define the abstraction (open for extension)
    public interface PaymentMethod {
        void pay(double amount);

        String getPaymentMethodName();
    }

    // 2. Implementations (can be extended without modifying existing code)
    public static class CreditCardPayment implements PaymentMethod {
        private final String cardNumber;
        private final String cardHolder;

        public CreditCardPayment(String cardNumber, String cardHolder) {
            this.cardNumber = cardNumber;
            this.cardHolder = cardHolder;
        }

        @Override
        public void pay(double amount) {
            System.out.printf("Processing credit card payment of $%.2f for %s (Card: ****%s)%n",
                    amount, cardHolder, cardNumber.substring(cardNumber.length() - 4));
            // Actual credit card processing logic would go here
        }

        @Override
        public String getPaymentMethodName() {
            return "Credit Card";
        }
    }

    public static class PayPalPayment implements PaymentMethod {
        private final String email;

        public PayPalPayment(String email) {
            this.email = email;
        }

        @Override
        public void pay(double amount) {
            System.out.printf("Processing PayPal payment of $%.2f to %s%n", amount, email);
            // Actual PayPal API call would go here
        }

        @Override
        public String getPaymentMethodName() {
            return "PayPal";
        }
    }

    public static class CryptoPayment implements PaymentMethod {
        private final String walletAddress;
        private final String cryptoType;

        public CryptoPayment(String walletAddress, String cryptoType) {
            this.walletAddress = walletAddress;
            this.cryptoType = cryptoType;
        }

        @Override
        public void pay(double amount) {
            System.out.printf("Processing %s payment of %.4f to wallet %s%n",
                    cryptoType, amount, walletAddress);
            // Actual blockchain transaction would go here
        }

        @Override
        public String getPaymentMethodName() {
            return cryptoType + " Crypto";
        }
    }

    // 3. Payment Processor (closed for modification)
    public static class PaymentProcessor {
        public void processPayment(PaymentMethod method, double amount) {
            System.out.println("Initiating " + method.getPaymentMethodName() + " payment...");
            method.pay(amount);
            System.out.println("Payment completed successfully!");
            System.out.println("----------------------------------");
        }
    }

    // 4. Usage Example
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        // Existing payment methods
        PaymentMethod creditCard = new CreditCardPayment("4111111111111111", "John Doe");
        PaymentMethod paypal = new PayPalPayment("john.doe@example.com");

        processor.processPayment(creditCard, 100.00);
        processor.processPayment(paypal, 50.50);

        // NEW: Adding cryptocurrency payment without modifying existing code
        PaymentMethod bitcoin = new CryptoPayment("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", "Bitcoin");
        processor.processPayment(bitcoin, 0.005);

        // NEW: Adding another crypto payment (further extension)
        PaymentMethod ethereum = new CryptoPayment("0x71C7656EC7ab88b098defB751B7401B5f6d8976F", "Ethereum");
        processor.processPayment(ethereum, 0.1);
    }
}
