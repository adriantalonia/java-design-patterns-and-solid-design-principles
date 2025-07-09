# 🧩 Interface Segregation Principle (ISP)

<!-- TOC -->
* [🧩 Interface Segregation Principle (ISP)](#-interface-segregation-principle-isp)
  * [📖 What Is It?](#-what-is-it)
  * [💡 Key Concepts](#-key-concepts)
  * [🚨 How to Detect ISP Violations](#-how-to-detect-isp-violations)
  * [🧪 Java Example (Bad Design – Violates ISP)](#-java-example-bad-design--violates-isp)
  * [✅ Java Example (Good Design – ISP Applied)](#-java-example-good-design--isp-applied)
  * [📐 Advanced Java Example](#-advanced-java-example)
  * [🌍 Real-World Analogy](#-real-world-analogy)
  * [🛠 Real Application Example](#-real-application-example)
  * [💼 Interview Preparation](#-interview-preparation)
  * [📌 Summary](#-summary)
  * [📚 Further Reading](#-further-reading)
<!-- TOC -->

> "Clients should not be forced to depend on methods they do not use." – Robert C. Martin

The **Interface Segregation Principle (ISP)** is the fourth of the SOLID principles. It encourages splitting large
interfaces into smaller, more specific ones so that **clients only implement what they actually need**.

---

## 📖 What Is It?

Large, fat interfaces create unnecessary dependencies for classes that only need a subset of methods. ISP suggests you *
*divide interfaces into role-specific contracts**.

This makes your software **flexible, decoupled, and easier to maintain**.

---

## 💡 Key Concepts

| Concept                          | Description                                                          |
|----------------------------------|----------------------------------------------------------------------|
| **Fat Interface**                | An interface with many unrelated methods                             |
| **Interface Pollution**          | Forcing a class to implement methods it doesn't use                  |
| **Client-specific interfaces**   | Small, focused interfaces tailored to the consuming class            |
| **Composition over inheritance** | Compose behaviors with small interfaces instead of giant hierarchies |

---

## 🚨 How to Detect ISP Violations

* A class has to implement several methods it doesn’t need
* Method implementations throw `UnsupportedOperationException`
* Interfaces grow over time to serve many unrelated clients
* Unused interface methods clutter the code

---

## 🧪 Java Example (Bad Design – Violates ISP)

```java
public interface Worker {
    void work();

    void eat();
}

public class HumanWorker implements Worker {
    public void work() {
        System.out.println("Working...");
    }

    public void eat() {
        System.out.println("Eating lunch...");
    }
}

public class RobotWorker implements Worker {
    public void work() {
        System.out.println("Robot working...");
    }

    public void eat() {
        // ❌ Robots don't eat
        throw new UnsupportedOperationException("Robots don't eat!");
    }
}
```

**❌ What's Wrong Here?**

* `RobotWorker` is forced to implement `eat()`, even though it doesn't need to
* This violates ISP

---

## ✅ Java Example (Good Design – ISP Applied)

```java
public interface Workable {
    void work();
}

public interface Eatable {
    void eat();
}

public class HumanWorker implements Workable, Eatable {
    public void work() {
        System.out.println("Human working...");
    }

    public void eat() {
        System.out.println("Human eating...");
    }
}

public class RobotWorker implements Workable {
    public void work() {
        System.out.println("Robot working...");
    }
}
```

**✅ Benefits:**

* Each class implements **only** the interfaces it needs
* Reduces unnecessary code and tight coupling
* Encourages flexible, composable design

---

## 📐 Advanced Java Example

```java
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
        System.out.println("Printing...");
    }

    public void scan() {
        System.out.println("Scanning...");
    }

    public void fax() {
        System.out.println("Faxing...");
    }
}

class BasicPrinter implements Printer {
    public void print() {
        System.out.println("Basic print");
    }
}
```

✅ Now a class can implement only the functionality it supports — without any unused methods.

---

## 🌍 Real-World Analogy

Consider a **restaurant menu**:

* A **Chef** only needs to see the cooking instructions
* A **Waiter** needs table assignments and customer preferences
* A **Cashier** only sees billing and payment options

Don’t give them a giant menu with everything. Give each role their **specific interface**.

---

## 🛠 Real Application Example

In a **modular microservices system**:

* A service exposes different endpoints for different clients
* Instead of one giant API interface, split into:

    * `AdminApi`
    * `UserApi`
    * `AnalyticsApi`

This allows clients to **implement or consume only what they need**, reducing risk and complexity.

---

## 💼 Interview Preparation

**Sample Questions**

1. **Why is ISP important in large-scale systems?**

    * "It ensures decoupling and flexibility between modules and teams."

2. **How do you handle evolving interfaces?**

    * "Favor interface composition. Create new interfaces when needs diverge."

3. **What tools or practices help maintain ISP?**

    * Linting tools, interface segregation tests, contract-based testing

4. **Can you give a real example where ISP saved your project?**

    * Use an example of breaking up a monolithic service or overgrown interface

---

## 📌 Summary

| Rule                              | Explanation                                        |
|-----------------------------------|----------------------------------------------------|
| Keep interfaces small and focused | Avoid forcing classes to implement unused behavior |
| Use role-specific interfaces      | Tailor interfaces to client needs                  |
| Compose over inherit              | Build behavior using small interfaces              |
| Avoid fat interfaces              | Prevent method bloat and implementation overhead   |

✅ Applying ISP results in **clearer contracts**, **less brittle systems**, and **better modularity**.

> “Segregate wisely, code responsibly.”

---

## 📚 Further Reading

* "Clean Architecture" – Robert C. Martin
* [https://blog.cleancoder.com](https://blog.cleancoder.com)
* [https://martinfowler.com](https://martinfowler.com)
* [https://refactoring.guru/design-principles/interface-segregation-principle](https://refactoring.guru/design-principles/interface-segregation-principle)
* [https://dev.to](https://dev.to)
