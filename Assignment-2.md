# Java Core Concepts — Explanation + Features + Code

---

## 1. `throw`

**Explanation:**
`throw` is a keyword used to **explicitly throw an exception** from a method or block of code. You create an exception object and throw it manually.

**Features:**
- Used inside a method body (not the method signature).
- Throws only **one** exception object at a time.
- Can be used for both checked and unchecked exceptions.
- Must be followed by an instance of `Throwable` or its subclass.
- Immediately stops normal execution and transfers control to the nearest matching `catch`.

```java
public class ThrowExample {
    static void checkAge(int age) {
        if (age < 18) {
            throw new ArithmeticException("Not eligible to vote");
        } else {
            System.out.println("Eligible to vote");
        }
    }

    public static void main(String[] args) {
        checkAge(15); // This will throw an exception
    }
}
```

**Output:**
```
Exception in thread "main" java.lang.ArithmeticException: Not eligible to vote
```

---

## 2. `throws`

**Explanation:**
`throws` is used in a **method signature** to declare that a method might throw certain exceptions. It shifts the responsibility of handling the exception to the caller of the method (checked exceptions).

**Features:**
- Used in the method declaration, not the body.
- Can declare **multiple** exceptions, separated by commas.
- Mainly used for checked exceptions (compiler enforces handling).
- Does not itself throw an exception — just warns callers.
- Helps with clean separation of exception declaration and handling.

```java
import java.io.IOException;

public class ThrowsExample {
    static void readFile() throws IOException {
        throw new IOException("File not found");
    }

    public static void main(String[] args) {
        try {
            readFile();
        } catch (IOException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
```

**Key Difference:** `throw` actually throws the exception; `throws` just declares that a method *can* throw one.

---

## 3. Exception Handling

**Explanation:**
Exception handling is a mechanism to handle **runtime errors** so that normal program flow can continue. It uses `try`, `catch`, `finally`, `throw`, and `throws`.

**Features:**
- Separates error-handling code from regular business logic.
- Supports multiple `catch` blocks for different exception types.
- `finally` block always executes, whether an exception occurs or not.
- Supports custom (user-defined) exceptions.
- Improves program stability by preventing abrupt termination.

```java
public class ExceptionHandlingExample {
    public static void main(String[] args) {
        try {
            int a = 10, b = 0;
            int result = a / b; // ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("This block always executes");
        }
    }
}
```

**Output:**
```
Error: / by zero
This block always executes
```

---

## 4. File Handling (`FileWriter`)

**Explanation:**
`FileWriter` is used to **write character data to a file**. It's part of `java.io` package. If the file doesn't exist, it creates one.

**Features:**
- Writes character streams (text), not raw bytes.
- Automatically creates the file if it doesn't already exist.
- Overwrites file content by default; supports append mode (`true` flag).
- Should be closed after use (or used with try-with-resources) to release resources.
- Throws `IOException` if the write operation fails.

```java
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write("Hello, this is written using FileWriter!");
            System.out.println("Data written successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
```

**Note:** Passing `true` as second argument (`new FileWriter("output.txt", true)`) appends data instead of overwriting.

---

## 5. Serialization

**Explanation:**
Serialization is the process of **converting an object into a byte stream** so it can be saved to a file or sent over a network. The class must implement `Serializable`.

**Features:**
- Requires the class to implement the `Serializable` marker interface.
- Preserves the object's state so it can be reconstructed later (deserialization).
- Fields marked `transient` are excluded from serialization.
- Uses `ObjectOutputStream` (write) and `ObjectInputStream` (read).
- Useful for saving objects to disk, caching, or sending over a network.

```java
import java.io.*;

class Student implements Serializable {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class SerializationExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student s1 = new Student(101, "Aarav");

        // Serialize
        FileOutputStream fos = new FileOutputStream("student.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s1);
        oos.close();

        // Deserialize
        FileInputStream fis = new FileInputStream("student.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Student s2 = (Student) ois.readObject();
        ois.close();

        System.out.println("ID: " + s2.id + ", Name: " + s2.name);
    }
}
```

---

## 6. `ArrayList`

**Explanation:**
`ArrayList` is a **resizable array** implementation of the `List` interface. It allows duplicate elements and maintains insertion order. Backed internally by a dynamic array — fast for random access, slower for insert/delete in the middle.

**Features:**
- Resizes itself automatically as elements are added.
- Maintains insertion order and allows duplicate elements.
- Provides fast random access via index (`get(i)` is O(1)).
- Insertion/removal in the middle is slow (elements need shifting).
- Not synchronized (not thread-safe by default).

```java
import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        list.remove("Banana");

        for (String fruit : list) {
            System.out.println(fruit);
        }

        System.out.println("Size: " + list.size());
    }
}
```

---

## 7. `LinkedList`

**Explanation:**
`LinkedList` is a **doubly linked list** implementation of `List` and `Deque` interfaces. It's efficient for frequent insertions/deletions since it doesn't require shifting elements, but slower for random access compared to `ArrayList`.

**Features:**
- Implemented as a doubly linked list (each node has previous/next pointers).
- Fast insertions/deletions, especially at the beginning or end.
- Implements both `List` and `Deque`, so it can act as a queue or stack too.
- Slower random access compared to `ArrayList` (must traverse nodes).
- Uses more memory per element due to storing node pointers.

```java
import java.util.LinkedList;

public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("Dog");
        list.add("Cat");
        list.addFirst("Elephant"); // add at beginning
        list.addLast("Fish");      // add at end

        list.remove("Cat");

        for (String animal : list) {
            System.out.println(animal);
        }
    }
}
```

**ArrayList vs LinkedList:** ArrayList → fast access, slow insert/delete. LinkedList → slow access, fast insert/delete.

---

## 8. Annotation

**Explanation:**
Annotations are **metadata** added to code (classes, methods, fields) that provide information to the compiler or JVM, but don't change program logic directly. Common built-in ones: `@Override`, `@Deprecated`, `@SuppressWarnings`. You can also create custom annotations.

**Features:**
- Doesn't directly affect program logic; provides metadata only.
- Can target classes, methods, fields, parameters, etc. (via `@Target`).
- Retention policy (`@Retention`) controls whether it's available at compile-time, class-time, or runtime.
- Can be read at runtime using **Reflection**.
- Widely used in frameworks (Spring, JPA, JUnit) for configuration without XML.

```java
import java.lang.annotation.*;

// Custom annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Info {
    String author();
    String date();
}

class Demo {
    @Info(author = "Aarav", date = "2026-07-16")
    public void show() {
        System.out.println("Method executed");
    }

    @Override
    public String toString() {
        return "Demo class object";
    }
}

public class AnnotationExample {
    public static void main(String[] args) throws Exception {
        Demo d = new Demo();
        d.show();

        // Reading annotation using reflection
        Info info = Demo.class.getMethod("show").getAnnotation(Info.class);
        System.out.println("Author: " + info.author());
        System.out.println("Date: " + info.date());
    }
}
```

---

## 9. Design Pattern — Singleton

**Explanation:**
Singleton ensures a class has **only one instance** throughout the application and provides a global access point to it. Commonly used for logging, configuration, database connections, etc.

**Features:**
- Ensures only one instance of the class exists in the JVM.
- Private constructor prevents direct instantiation from outside.
- Provides a static method (`getInstance()`) for global access.
- Can be made thread-safe using synchronization or holder pattern.
- Commonly used for shared resources like config managers, loggers, connection pools.

```java
class Singleton {
    // Single instance, created only once
    private static Singleton instance;

    // Private constructor prevents outside instantiation
    private Singleton() {
        System.out.println("Singleton instance created");
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}

public class SingletonExample {
    public static void main(String[] args) {
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();

        obj1.showMessage();

        System.out.println("Same instance? " + (obj1 == obj2)); // true
    }
}
```

**Note:** For thread-safe singleton, synchronize `getInstance()` or use the "Bill Pugh" holder pattern.

---

## 10. `finalize()`

**Explanation:**
`finalize()` is a method (from `Object` class) called by the **Garbage Collector** just before an object is destroyed, allowing cleanup of resources. It's **deprecated since Java 9** and discouraged in modern Java (use `try-with-resources` or `AutoCloseable` instead), but still important conceptually.

**Features:**
- Defined in the `Object` class; can be overridden by any class.
- Called by the Garbage Collector, not directly by the programmer.
- No guarantee on *when* (or even *if*) it will be called.
- Deprecated since Java 9 due to unpredictability and performance issues.
- Modern alternative: `AutoCloseable` with try-with-resources.

```java
public class FinalizeExample {
    public static void main(String[] args) {
        FinalizeExample obj = new FinalizeExample();
        obj = null;
        System.gc(); // requests garbage collection
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize() called - object being garbage collected");
    }
}
```

---

## 11. `System.gc()`

**Explanation:**
`System.gc()` is a **request** (not a guarantee) to the JVM to run the Garbage Collector and reclaim memory from unreferenced objects. The JVM decides whether/when to actually perform collection.

**Features:**
- Only a *suggestion/request* to the JVM — not a forced action.
- JVM may ignore the request depending on memory state.
- Helps reclaim memory occupied by unreferenced objects.
- Overuse can hurt performance (GC is normally auto-managed by JVM).
- Equivalent to calling `Runtime.getRuntime().gc()`.

```java
public class GCExample {
    public static void main(String[] args) {
        GCExample obj1 = new GCExample();
        GCExample obj2 = new GCExample();

        obj1 = null; // eligible for garbage collection
        obj2 = null; // eligible for garbage collection

        System.gc(); // suggests JVM to run garbage collector
        System.out.println("Garbage collection requested");
    }

    @Override
    protected void finalize() {
        System.out.println("Object garbage collected");
    }
}
```
