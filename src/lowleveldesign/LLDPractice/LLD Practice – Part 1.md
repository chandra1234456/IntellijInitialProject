# LLD Practice – Part 1: SIMPLE (10 problems)

Focus: one class, fields, constructor, getters, simple logic, encapsulation.
Rules: keep fields `private`, put logic inside the class, `main` only calls methods and prints.

---

## S1. Counter
**Statement:** Build a counter that can go up, down and reset.

**Requirements**
- `Counter()` starts at 0
- `int increment()` → +1, returns new value
- `int decrement()` → -1, returns new value
- `void reset()` → sets to 0
- `int getValue()`

**Test**
```java
Counter c = new Counter();
c.increment(); c.increment(); c.increment(); c.decrement();
System.out.println(c.getValue());
c.reset();
System.out.println(c.getValue());
```
**Expected**
```text
2
0
```

---

## S2. BankAccount
**Statement:** Simple account with deposit and withdraw (no overdraft).

**Requirements**
- `BankAccount(String owner, double balance)`
- `double deposit(double amt)` → returns new balance
- `boolean withdraw(double amt)` → false if amt > balance (balance unchanged)
- `double getBalance()`

**Test**
```java
BankAccount a = new BankAccount("Ravi", 1000);
System.out.println(a.deposit(500));
System.out.println(a.withdraw(2000));
System.out.println(a.withdraw(300));
System.out.println(a.getBalance());
```
**Expected**
```text
1500.0
false
true
1200.0
```

---

## S3. Rectangle
**Statement:** Rectangle with area, perimeter and square check.

**Requirements**
- `Rectangle(int w, int h)`
- `int area()`, `int perimeter()`, `boolean isSquare()`

**Test**
```java
Rectangle r = new Rectangle(4, 5);
System.out.println(r.area());
System.out.println(r.perimeter());
System.out.println(r.isSquare());
System.out.println(new Rectangle(3, 3).isSquare());
```
**Expected**
```text
20
18
false
true
```

---

## S4. TemperatureConverter
**Statement:** Utility class with static conversion methods.

**Requirements**
- `static double toFahrenheit(double c)` = c*9/5+32
- `static double toCelsius(double f)` = (f-32)*5/9

**Test**
```java
System.out.println(TemperatureConverter.toFahrenheit(100));
System.out.println(TemperatureConverter.toFahrenheit(0));
System.out.println(TemperatureConverter.toCelsius(212));
System.out.println(TemperatureConverter.toCelsius(32));
```
**Expected**
```text
212.0
32.0
100.0
0.0
```

---

## S5. Student
**Statement:** Student with marks array, average, highest and grade.

**Requirements**
- `Student(String name, int[] marks)`
- `double average()`, `int highest()`
- `char grade()` based on average: >=90 'A', >=75 'B', >=50 'C', else 'F'

**Test**
```java
Student s1 = new Student("Asha", new int[]{80, 90, 100});
Student s2 = new Student("Ben",  new int[]{40, 50, 60});
Student s3 = new Student("Cy",   new int[]{10, 20, 30});
System.out.println(s1.average() + " " + s1.highest() + " " + s1.grade());
System.out.println(s2.average() + " " + s2.highest() + " " + s2.grade());
System.out.println(s3.average() + " " + s3.highest() + " " + s3.grade());
```
**Expected**
```text
90.0 100 A
50.0 60 C
20.0 30 F
```

---

## S6. ArrayStack
**Statement:** Fixed-capacity stack of ints using an array (no `java.util.Stack`).

**Requirements**
- `ArrayStack(int capacity)`
- `boolean push(int x)` → false if full
- `int pop()` → -1 if empty
- `int peek()` → -1 if empty
- `boolean isEmpty()`, `boolean isFull()`

**Test**
```java
ArrayStack s = new ArrayStack(3);
System.out.println(s.push(10));
System.out.println(s.push(20));
System.out.println(s.push(30));
System.out.println(s.push(40));
System.out.println(s.pop());
System.out.println(s.peek());
System.out.println(s.isEmpty());
System.out.println(s.isFull());
```
**Expected**
```text
true
true
true
false
30
20
false
false
```

---

## S7. LightSwitch
**Statement:** Switch that remembers its state and how many times it was toggled.

**Requirements**
- Starts OFF
- `boolean toggle()` → flips, returns new state (true = ON)
- `boolean isOn()`
- `int getToggleCount()`

**Test**
```java
LightSwitch sw = new LightSwitch();
System.out.println(sw.isOn());
System.out.println(sw.toggle());
System.out.println(sw.toggle());
System.out.println(sw.toggle());
System.out.println(sw.getToggleCount());
```
**Expected**
```text
false
true
false
true
3
```

---

## S8. Book
**Statement:** Book that can be issued and returned.

**Requirements**
- `Book(String title, String author)`
- `boolean issue()` → false if already issued
- `void returnBook()`
- `toString()` → `"<title> by <author> [Issued]"` or `[Available]`

**Test**
```java
Book b = new Book("Clean Code", "Robert Martin");
System.out.println(b.issue());
System.out.println(b.issue());
b.returnBook();
System.out.println(b.issue());
System.out.println(b);
```
**Expected**
```text
true
false
true
Clean Code by Robert Martin [Issued]
```

---

## S9. Employee
**Statement:** Employee salary with percentage raise.

**Requirements**
- `Employee(String name, double baseSalary)`
- `void giveRaise(double percent)`
- `double getSalary()`
- `double annualSalary()` = salary * 12

**Test**
```java
Employee e = new Employee("Meera", 50000);
System.out.println(e.getSalary());
e.giveRaise(10);
System.out.println(e.getSalary());
System.out.println(e.annualSalary());
```
**Expected**
```text
50000.0
55000.0
660000.0
```

---

## S10. Point
**Statement:** 2D point with distance and translation.

**Requirements**
- `Point(int x, int y)`
- `double distanceTo(Point o)` (Euclidean)
- `void translate(int dx, int dy)`
- `toString()` → `"(x, y)"`

**Test**
```java
Point p1 = new Point(0, 0);
Point p2 = new Point(3, 4);
System.out.println(p1.distanceTo(p2));
p2.translate(1, 1);
System.out.println(p1);
System.out.println(p2);
```
**Expected**
```text
5.0
(0, 0)
(4, 5)
```
