# 🔓 Open/Closed Principle (OCP)

<!-- TOC -->
* [🔓 Open/Closed Principle (OCP)](#-openclosed-principle-ocp)
  * [📖 What Is It?](#-what-is-it)
  * [💡 Key Concepts](#-key-concepts)
  * [🧪 Java Example (Bad Design – Violates OCP)](#-java-example-bad-design--violates-ocp)
  * [✅ Java Example (Good Design – OCP Applied)](#-java-example-good-design--ocp-applied)
  * [🌍 Real-World Analogy](#-real-world-analogy)
  * [🛠 Real Application Example](#-real-application-example)
  * [📌 Summary](#-summary)
<!-- TOC -->

> "Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification." –
> Bertrand Meyer

The **Open/Closed Principle (OCP)** is the second of the SOLID principles. It promotes a design where new behavior can
be added to a system **without modifying existing code**.

---

## 📖 What Is It?

**Open for Extension**: You can add new functionality.

**Closed for Modification**: You don’t have to touch existing tested code.

This principle enables **scalable, maintainable** systems that are easy to enhance over time while minimizing regression
risks.

---

## 💡 Key Concepts

| Concept           | Description                                                |
|-------------------|------------------------------------------------------------|
| **Abstraction**   | Use interfaces or abstract classes to define behavior      |
| **Polymorphism**  | Extend functionality through subclasses or strategies      |
| **Extensibility** | Add new behavior without altering working code             |
| **Stability**     | Reduce risk of breaking functionality by isolating changes |

---

## 🧪 Java Example (Bad Design – Violates OCP)

```java
public class AreaCalculator {
    public double calculateArea(Object shape) {
        if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            return Math.PI * c.radius * c.radius;
        } else if (shape instanceof Rectangle) {
            Rectangle r = (Rectangle) shape;
            return r.length * r.width;
        }
        return 0;
    }
}

class Circle {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }
}

class Rectangle {
    double length, width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
}
```

**❌ What's Wrong Here?**

* Every time a new shape is added (e.g., `Triangle`), you must modify `AreaCalculator`
* This violates **Closed for Modification**

---

## ✅ Java Example (Good Design – OCP Applied)

```java
interface Shape {
    double calculateArea();
}

class Circle implements Shape {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Shape {
    double length, width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double calculateArea() {
        return length * width;
    }
}

class AreaCalculator {
    public double calculateArea(Shape shape) {
        return shape.calculateArea();
    }
}
```

**✅ Benefits:**

* You can now extend the system by adding new shapes **without modifying** `AreaCalculator`
* OCP is fulfilled through **interfaces and polymorphism**

---

## 🌍 Real-World Analogy

Think of a **USB port**:

* You can plug in new devices (keyboard, mouse, camera)
* You don’t change the **USB port** itself

Similarly, you should design software components so that new features plug into existing interfaces.

---

## 🛠 Real Application Example

In a **payment processing system**:

* You define a `PaymentMethod` interface with a `pay()` method
* Implementations like `CreditCardPayment`, `PaypalPayment`, or `CryptoPayment` extend functionality
* The payment processor doesn't change; you just add new `PaymentMethod` classes

---

## 📌 Summary

| Rule                        | Explanation                                                  |
|-----------------------------|--------------------------------------------------------------|
| **Open for extension**      | Add new functionality by creating new classes or behaviors   |
| **Closed for modification** | Never change existing logic for new requirements             |
| **Use abstraction**         | Define interfaces or base classes to allow future extensions |
| **Apply polymorphism**      | Achieve flexibility through overridden methods               |

---


✅ Applying the Open/Closed Principle leads to **scalable**, **maintainable**, and **future-proof** software.

> “Design for change, and you won’t have to redesign for change.”
