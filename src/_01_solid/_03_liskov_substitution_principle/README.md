# 🔄 Liskov Substitution Principle (LSP)

<!-- TOC -->
* [🔄 Liskov Substitution Principle (LSP)](#-liskov-substitution-principle-lsp)
  * [📖 What Is It?](#-what-is-it)
  * [💡 Key Concepts](#-key-concepts)
  * [🚨 How to Detect LSP Violations](#-how-to-detect-lsp-violations)
  * [🧪 Java Example (Bad Design – Violates LSP)](#-java-example-bad-design--violates-lsp)
  * [✅ Java Example (Good Design – LSP Applied)](#-java-example-good-design--lsp-applied)
  * [📐 Advanced Java Example](#-advanced-java-example)
  * [🌍 Real-World Analogy](#-real-world-analogy)
  * [🛠 Real Application Example](#-real-application-example)
  * [💼 Interview Preparation](#-interview-preparation)
  * [📌 Summary](#-summary)
  * [📚 Further Reading](#-further-reading)
<!-- TOC -->

> "Subtypes must be substitutable for their base types without altering the correctness of the program." – Barbara Liskov

The **Liskov Substitution Principle (LSP)** is the third SOLID principle. It ensures that a subclass can stand in for its parent class **without changing the expected behavior** of the program.

---

## 📖 What Is It?

If `S` is a subtype of `T`, then objects of type `T` **may be replaced** with objects of type `S` **without breaking the application**.

LSP is all about **contract adherence** — subtypes must honor the behavior promised by the base type.

---

## 💡 Key Concepts

| Concept                 | Description                                                                |
| ----------------------- | -------------------------------------------------------------------------- |
| **Behavioral contract** | Subtypes must behave as expected by the client code                        |
| **Polymorphism safety** | Overridden methods should not weaken guarantees or introduce side effects  |
| **Replaceability**      | You should be able to use a subclass anywhere the parent class is expected |
| **Design by contract**  | Input/output expectations must remain consistent across hierarchy          |

---

## 🚨 How to Detect LSP Violations

* Subclass throws `UnsupportedOperationException` for base method
* Subclass changes method behavior in an incompatible way (e.g., removing side effects, changing return meaning)
* Tests fail when replacing superclass with subclass
* Violations show up as bugs when new types are introduced

---

## 🧪 Java Example (Bad Design – Violates LSP)

```java
public class Bird {
    public void fly() {
        System.out.println("Flying");
    }
}

public class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostriches can't fly!");
    }
}
```

**❌ What's Wrong Here?**

* Code expecting a `Bird` to fly will **break** when passed an `Ostrich`
* Violates LSP: Ostrich is **not truly substitutable** for Bird

---

## ✅ Java Example (Good Design – LSP Applied)

```java
public interface Bird {
    void eat();
}

public interface FlyingBird extends Bird {
    void fly();
}

public class Sparrow implements FlyingBird {
    public void eat() { System.out.println("Eating seeds"); }
    public void fly() { System.out.println("Flying"); }
}

public class Ostrich implements Bird {
    public void eat() { System.out.println("Pecking at food"); }
}
```

**✅ Benefits:**

* Now `Ostrich` doesn’t promise to fly — no broken expectations
* Clients using `FlyingBird` can assume safe flying behavior

---

## 📐 Advanced Java Example

```java
public abstract class Shape {
    abstract double area();
}

public class Rectangle extends Shape {
    protected double width, height;
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    public double area() { return width * height; }
}

public class Square extends Shape {
    private double side;
    public Square(double side) {
        this.side = side;
    }
    public double area() { return side * side; }
}
```

✅ This is better than forcing `Square` to inherit from `Rectangle`, which often breaks behavior (e.g., `setWidth()` in Rectangle being misused).

---

## 🌍 Real-World Analogy

Imagine a **credit card** system:

* You can swipe a **Visa** or a **Mastercard** anywhere a credit card is accepted
* If a card failed because it required a different swipe method, it would violate LSP

Software should behave the same way: **new implementations shouldn’t break existing usage**

---

## 🛠 Real Application Example

Suppose you have a `NotificationSender` interface:

```java
public interface NotificationSender {
    void send(String message);
}
```

You create `EmailSender`, `SmsSender`, `PushNotificationSender`, etc.

**LSP ensures** each of them respects the `send` contract:

* Sends the message
* Throws expected errors
* Doesn’t change input/output behavior

If `PushNotificationSender.send()` opened a GUI dialog instead, **it would break expectations** of the interface.

---

## 💼 Interview Preparation

**Sample Questions**

1. **How do you test LSP in real systems?**

    * "I replace base types with subclasses in tests. If they fail, LSP may be violated."

2. **Why is throwing `UnsupportedOperationException` a red flag?**

    * "It suggests the subclass cannot support the full contract of the superclass."

3. **Have you encountered a real LSP violation?**

    * Share examples like `Square extends Rectangle`, or an overridden method breaking flow

4. **How do you design class hierarchies to avoid LSP violations?**

    * Favor interfaces
    * Use composition over inheritance
    * Apply interface segregation

---

## 📌 Summary

| Rule                            | Explanation                                                      |
| ------------------------------- | ---------------------------------------------------------------- |
| Subtypes must be replaceable    | Without affecting behavior or correctness                        |
| Follow behavioral contracts     | Keep method inputs/outputs and side effects consistent           |
| Avoid implementation mismatches | Don’t inherit just for code reuse; honor true is-a relationships |
| Use interfaces and abstractions | Narrow interfaces can prevent unsupported method exposure        |

✅ Applying LSP leads to **robust, reliable**, and **safe polymorphic code**.

> “If it looks like a duck and quacks like a duck, it should act like a duck.”

---

## 📚 Further Reading

* "Design Principles and Design Patterns" – Robert C. Martin
* [https://martinfowler.com](https://martinfowler.com)
* [https://en.wikipedia.org/wiki/Liskov\_substitution\_principle](https://en.wikipedia.org/wiki/Liskov_substitution_principle)
* [https://softwareengineering.stackexchange.com/questions/94928/liskov-substitution-principle-real-world-example](https://softwareengineering.stackexchange.com/questions/94928/liskov-substitution-principle-real-world-example)
* [https://dev.to](https://dev.to)
