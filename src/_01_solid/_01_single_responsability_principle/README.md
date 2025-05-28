# 🧠 Single Responsibility Principle (SRP)

<!-- TOC -->
* [🧠 Single Responsibility Principle (SRP)](#-single-responsibility-principle-srp)
  * [📖 What Is It?](#-what-is-it)
  * [💡 Key Concepts](#-key-concepts)
  * [🧪 Java Example (Bad Design – Violates SRP)](#-java-example-bad-design--violates-srp)
  * [✅ Java Example (Good Design – SRP Applied)](#-java-example-good-design--srp-applied)
  * [🌍 Real-World Analogy](#-real-world-analogy)
  * [🛠 Real Application Example](#-real-application-example)
  * [📌 Summary](#-summary)
  * [📚 Further Reading](#-further-reading)
<!-- TOC -->

> "A class should have only one reason to change." – Robert C. Martin

The **Single Responsibility Principle (SRP)** is the first of the five SOLID principles of object-oriented design. It
states that a class should **only have one responsibility**, meaning it should **only have one reason to change**.

---

## 📖 What Is It?

In simple terms, the **SRP** means a class should **do one thing and do it well**.

A class with multiple responsibilities is harder to:

* Understand
* Maintain
* Extend
* Test

By separating responsibilities, you reduce the chances of bugs and make your code more modular and reusable.

---

## 💡 Key Concepts

| Concept             | Description                                                                |
|---------------------|----------------------------------------------------------------------------|
| **Responsibility**  | A reason for a class to change.                                            |
| **High Cohesion**   | When a class focuses on a single task, its internal logic becomes clearer. |
| **Low Coupling**    | Smaller, focused classes are easier to reuse and test independently.       |
| **Maintainability** | Is improved by minimizing unrelated changes to the same class.             |

---

## 🧪 Java Example (Bad Design – Violates SRP)

```java
public class Invoice {
    private String customer;
    private double amount;

    public Invoice(String customer, double amount) {
        this.customer = customer;
        this.amount = amount;
    }

    public void calculateTotal() {
        // logic for total calculation
    }

    public void printInvoice() {
        // logic for printing
    }

    public void saveToDatabase() {
        // logic for saving invoice to DB
    }
}
```

**❌ What's Wrong Here?**

* `Invoice` handles:

    * Business logic (total calculation)
    * UI/printing logic
    * Data persistence

So it has **multiple reasons to change**, violating SRP.

---

## ✅ Java Example (Good Design – SRP Applied)

```java
// Class 1: Handles invoice data and business logic
public class Invoice {
    private String customer;
    private double amount;

    public Invoice(String customer, double amount) {
        this.customer = customer;
        this.amount = amount;
    }

    public double calculateTotal() {
        // logic for total calculation
        return amount * 1.16; // e.g., 16% tax
    }
}

// Class 2: Handles printing
public class InvoicePrinter {
    public void print(Invoice invoice) {
        System.out.println("Invoice for: " + invoice.customer);
        System.out.println("Total: " + invoice.calculateTotal());
    }
}

// Class 3: Handles data persistence
public class InvoiceRepository {
    public void save(Invoice invoice) {
        // logic to save invoice to DB
    }
}
```

**✅ Benefits:**

* Each class has one responsibility
* Easier to test and extend (e.g., use different printers or repositories)
* Follows **SRP** perfectly

---

## 🌍 Real-World Analogy

Think of a **restaurant**:

* A **Chef** cooks (single responsibility)
* A **Waiter** serves food (single responsibility)
* A **Cashier** handles billing (single responsibility)

If one person did all of that, they'd be overwhelmed and inefficient.

Similarly, in software, dividing responsibilities leads to more efficient, cleaner systems.

---

## 🛠 Real Application Example

In an e-commerce system:

* The `Order` class handles order data and price calculations
* The `OrderPrinter` class handles exporting the invoice to PDF
* The `OrderRepository` class saves or retrieves orders from a database

📦 This structure:

* Makes the codebase easier to maintain
* Allows for easy swapping of printers (PDF, HTML, console)
* Facilitates better unit testing and mocking

---

## 📌 Summary

| Rule                                         | Explanation                                                                          |
|----------------------------------------------|--------------------------------------------------------------------------------------|
| A class should have **one reason to change** | If a class handles multiple concerns, changes in one concern could affect the others |
| Keep classes **small and focused**           | This enhances reusability and testability                                            |
| **Delegate responsibilities**                | Separate classes should handle UI, persistence, and business logic independently     |

---

✅ Applying the Single Responsibility Principle leads to **modular**, **testable**, and **robust** software designs.

> “SRP is simple, but its simplicity is powerful.”

---
