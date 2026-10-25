# LLD Practice – Part 2: MEDIUM (20 problems)

Focus: multiple classes, collections (List/Map), inheritance, abstract classes, interfaces, polymorphism, validation.
No design patterns yet – just clean classes with clear responsibilities.

---

## M1. Shapes (abstract class)
**Statement:** Abstract `Shape` with `Circle`, `Rectangle`, `Triangle`. Compute total area of a list.

**Requirements**
- `abstract double area()`, `String getName()`
- `Circle(r)`, `Rectangle(w,h)`, `Triangle(base,height)`
- Print with `String.format("%.2f", ...)`

**Test**
```java
List<Shape> shapes = List.of(new Circle(2), new Rectangle(3, 4), new Triangle(6, 5));
double total = 0;
for (Shape s : shapes) {
    System.out.println(s.getName() + ": " + String.format("%.2f", s.area()));
    total += s.area();
}
System.out.println("Total: " + String.format("%.2f", total));
```
**Expected**
```text
Circle: 12.57
Rectangle: 12.00
Triangle: 15.00
Total: 39.57
```

---

## M2. Library Shelf
**Statement:** Library keeps titles and who has them issued.

**Requirements**
- `addBook(String title)`
- `boolean issueBook(String title)` → false if missing or already issued
- `void returnBook(String title)`
- `List<String> availableBooks()` → alphabetically sorted

**Test**
```java
Library l = new Library();
l.addBook("Java"); l.addBook("DSA"); l.addBook("OS");
System.out.println(l.issueBook("DSA"));
System.out.println(l.issueBook("DSA"));
System.out.println(l.issueBook("Python"));
System.out.println(l.availableBooks());
l.returnBook("DSA");
System.out.println(l.availableBooks());
```
**Expected**
```text
true
false
false
[Java, OS]
[DSA, Java, OS]
```

---

## M3. Shopping Cart
**Statement:** Cart with items, removal and a percentage discount.

**Requirements**
- `addItem(String name, double price, int qty)` (same name → add to qty)
- `boolean removeItem(String name)`
- `double getTotal()`
- `double getTotalWithDiscount(double percent)`
- `int distinctItems()`

**Test**
```java
Cart c = new Cart();
c.addItem("Pen", 10.0, 5);
c.addItem("Book", 200.0, 2);
System.out.println(c.getTotal());
c.addItem("Pen", 10.0, 0);
System.out.println(c.removeItem("Pen"));
System.out.println(c.removeItem("Bag"));
System.out.println(c.getTotal());
System.out.println(c.getTotalWithDiscount(10));
System.out.println(c.distinctItems());
```
**Expected**
```text
450.0
true
false
400.0
360.0
1
```

---

## M4. Simple Parking Lot
**Statement:** Fixed number of numbered slots; always give the lowest free slot.

**Requirements**
- `ParkingLot(int capacity)`
- `int park(String vehicleNo)` → slot (1-based) or -1 if full / already parked
- `boolean leave(String vehicleNo)`
- `int availableSlots()`

**Test**
```java
ParkingLot p = new ParkingLot(2);
System.out.println(p.park("KA01"));
System.out.println(p.park("KA02"));
System.out.println(p.park("KA03"));
System.out.println(p.leave("KA01"));
System.out.println(p.park("KA03"));
System.out.println(p.availableSlots());
```
**Expected**
```text
1
2
-1
true
1
0
```

---

## M5. Class Result Manager
**Statement:** Store students and marks; find topper, class average, ranking.

**Requirements**
- `addStudent(String name, int marks)`
- `String topper()`
- `double classAverage()`
- `List<String> ranking()` → names sorted by marks descending

**Test**
```java
ResultManager r = new ResultManager();
r.addStudent("Asha", 85); r.addStudent("Ben", 92); r.addStudent("Cy", 70);
System.out.println(r.topper());
System.out.println(String.format("%.2f", r.classAverage()));
System.out.println(r.ranking());
```
**Expected**
```text
Ben
82.33
[Ben, Asha, Cy]
```

---

## M6. Inventory
**Statement:** Track products and stock levels.

**Requirements**
- `addProduct(String id, String name, int qty)`
- `boolean removeStock(String id, int qty)` → false if not enough
- `int getQty(String id)`
- `List<String> lowStock(int threshold)` → product names with qty < threshold, insertion order

**Test**
```java
Inventory inv = new Inventory();
inv.addProduct("P1", "Pen", 50);
inv.addProduct("P2", "Book", 5);
inv.addProduct("P3", "Bag", 8);
System.out.println(inv.removeStock("P1", 20));
System.out.println(inv.removeStock("P2", 10));
System.out.println(inv.getQty("P1"));
System.out.println(inv.lowStock(10));
```
**Expected**
```text
true
false
30
[Book, Bag]
```

---

## M7. Payroll (inheritance + polymorphism)
**Statement:** Abstract `Employee` with `Manager` (base+bonus), `Developer` (base), `Intern` (fixed stipend).

**Requirements**
- `abstract int salary()`, `getName()`, `getRole()`
- `Payroll` class: `add(Employee)`, `int total()`

**Test**
```java
Payroll p = new Payroll();
p.add(new Manager("Asha", 80000, 20000));
p.add(new Developer("Ben", 60000));
p.add(new Intern("Cy", 15000));
p.printAll();   // "<name> (<role>): <salary>"
System.out.println("Total payroll: " + p.total());
```
**Expected**
```text
Asha (Manager): 100000
Ben (Developer): 60000
Cy (Intern): 15000
Total payroll: 175000
```

---

## M8. Vehicle Rental
**Statement:** Different vehicle types with different rent rules.

**Requirements**
- `abstract int rent(int days)`
- Car = 100/day, Bike = 30/day, Truck = 200/day + flat 50 loading fee
- `RentalShop.totalRent(List<Rental>)` where `Rental(Vehicle, days)`

**Test**
```java
System.out.println(new Car().rent(3));
System.out.println(new Bike().rent(5));
System.out.println(new Truck().rent(2));
RentalShop shop = new RentalShop();
shop.add(new Rental(new Car(), 3));
shop.add(new Rental(new Bike(), 5));
shop.add(new Rental(new Truck(), 2));
System.out.println(shop.totalRent());
```
**Expected**
```text
300
150
450
900
```

---

## M9. Savings vs Current Account
**Statement:** Two account types with different withdrawal rules.

**Requirements**
- `SavingsAccount(double bal)`: balance must stay >= 500; `addInterest(double percent)`
- `CurrentAccount(double bal)`: can overdraw up to 1000 (balance may go to -1000)
- Both: `boolean withdraw(double)`, `double getBalance()`

**Test**
```java
SavingsAccount s = new SavingsAccount(2000);
System.out.println(s.withdraw(1600));
System.out.println(s.withdraw(1500));
System.out.println(s.getBalance());
CurrentAccount c = new CurrentAccount(100);
System.out.println(c.withdraw(900));
System.out.println(c.getBalance());
System.out.println(c.withdraw(300));
s.addInterest(4);
System.out.println(s.getBalance());
```
**Expected**
```text
false
true
500.0
true
-800.0
false
520.0
```

---

## M10. Tic-Tac-Toe
**Statement:** 3x3 game, X starts, turns alternate. Invalid move does not change turn.

**Requirements**
- `String makeMove(int row, int col)` returns one of
  `CONTINUE`, `INVALID` (occupied/out of range), `X WINS`, `O WINS`, `DRAW`, `GAME OVER`
- Detect rows, columns, both diagonals

**Test**
```java
Game g = new Game();
System.out.println(g.makeMove(0, 0));  // X
System.out.println(g.makeMove(0, 0));  // O tries same cell
System.out.println(g.makeMove(1, 0));  // O
System.out.println(g.makeMove(0, 1));  // X
System.out.println(g.makeMove(1, 1));  // O
System.out.println(g.makeMove(0, 2));  // X
System.out.println(g.makeMove(2, 2));  // after end
```
**Expected**
```text
CONTINUE
INVALID
CONTINUE
CONTINUE
CONTINUE
X WINS
GAME OVER
```

---

## M11. Elevator (single)
**Statement:** One elevator between floor 0 and 10.

**Requirements**
- `String goTo(int floor)`:
    - `"Moved UP n floors to f"`, `"Moved DOWN n floors to f"`, `"Already on floor f"`, `"Invalid floor"`
- `int getCurrentFloor()`

**Test**
```java
Elevator e = new Elevator(0, 10);
System.out.println(e.goTo(5));
System.out.println(e.goTo(2));
System.out.println(e.goTo(2));
System.out.println(e.goTo(15));
System.out.println(e.getCurrentFloor());
```
**Expected**
```text
Moved UP 5 floors to 5
Moved DOWN 3 floors to 2
Already on floor 2
Invalid floor
2
```

---

## M12. ATM PIN Login
**Statement:** Cards with PIN; 3 wrong attempts block the card.

**Requirements**
- `addCard(int cardNo, int pin)`
- `String login(int cardNo, int pin)`:
  `"Welcome"`, `"Wrong PIN (n attempts left)"`, `"Card blocked"`, `"Unknown card"`
- Correct PIN resets attempts; a blocked card stays blocked

**Test**
```java
Atm atm = new Atm();
atm.addCard(1001, 1234); atm.addCard(1002, 9999);
System.out.println(atm.login(1001, 1111));
System.out.println(atm.login(1001, 2222));
System.out.println(atm.login(1001, 3333));
System.out.println(atm.login(1001, 1234));
System.out.println(atm.login(1002, 9999));
System.out.println(atm.login(5555, 1));
```
**Expected**
```text
Wrong PIN (2 attempts left)
Wrong PIN (1 attempts left)
Card blocked
Card blocked
Welcome
Unknown card
```

---

## M13. Library Member Limits & Fines
**Statement:** Member can hold max 2 books; loan period 7 days; fine Rs 5 per extra day.

**Requirements**
- `Member(String name)`
- `boolean borrow(String bookId)` → false if limit reached or already holding it
- `int returnBook(String bookId, int daysKept)` → fine (0 if on time)

**Test**
```java
Member m = new Member("Ravi");
System.out.println(m.borrow("B1"));
System.out.println(m.borrow("B2"));
System.out.println(m.borrow("B3"));
System.out.println(m.returnBook("B1", 10));
System.out.println(m.borrow("B3"));
```
**Expected**
```text
true
true
false
15
true
```

---

## M14. Hotel Room Booking
**Statement:** Room types with count and nightly price.

**Requirements**
- `addRoomType(String type, int count, int pricePerNight)`
- `int book(String type, int nights)` → bill, or -1 if none free
- `void checkout(String type)`
- `int available(String type)`

**Test**
```java
Hotel h = new Hotel();
h.addRoomType("SINGLE", 2, 1000);
h.addRoomType("DOUBLE", 1, 1800);
System.out.println(h.book("SINGLE", 2));
System.out.println(h.book("SINGLE", 1));
System.out.println(h.book("SINGLE", 1));
System.out.println(h.book("DOUBLE", 3));
System.out.println(h.available("SINGLE"));
h.checkout("SINGLE");
System.out.println(h.available("SINGLE"));
```
**Expected**
```text
2000
1000
-1
5400
0
1
```

---

## M15. Movie Seat Booking
**Statement:** Show with rows A, B and 3 seats per row (A1..A3, B1..B3).

**Requirements**
- `boolean bookSeat(String seat)` → false if invalid or taken
- `boolean cancelSeat(String seat)` → false if not booked
- `int availableSeats()`

**Test**
```java
Show s = new Show(2, 3);
System.out.println(s.bookSeat("A1"));
System.out.println(s.bookSeat("A1"));
System.out.println(s.bookSeat("C1"));
System.out.println(s.bookSeat("B2"));
System.out.println(s.availableSeats());
System.out.println(s.cancelSeat("A1"));
System.out.println(s.cancelSeat("A1"));
System.out.println(s.availableSeats());
```
**Expected**
```text
true
false
false
true
4
true
false
5
```

---

## M16. Leaderboard
**Statement:** Scores accumulate per player.

**Requirements**
- `addScore(String player, int score)` (adds to existing)
- `List<String> top(int n)` → `"name=score"` sorted by score desc
- `int rank(String player)` → 1-based, -1 if unknown

**Test**
```java
Leaderboard lb = new Leaderboard();
lb.addScore("Asha", 50); lb.addScore("Ben", 80);
lb.addScore("Cy", 65);   lb.addScore("Asha", 40);
System.out.println(lb.top(2));
System.out.println(lb.rank("Cy"));
System.out.println(lb.rank("Ben"));
System.out.println(lb.rank("Zed"));
```
**Expected**
```text
[Asha=90, Ben=80]
3
2
-1
```

---

## M17. To-Do Manager
**Statement:** Tasks with auto-increment ids.

**Requirements**
- `int add(String title)` → id (starts at 1)
- `boolean complete(int id)`, `boolean delete(int id)`
- `List<String> pending()` → titles in insertion order
- `int completedCount()`

**Test**
```java
TodoManager t = new TodoManager();
t.add("Buy milk"); t.add("Study LLD"); t.add("Gym");
t.complete(2);
System.out.println(t.pending());
System.out.println(t.delete(1));
System.out.println(t.delete(9));
System.out.println(t.pending());
System.out.println(t.completedCount());
```
**Expected**
```text
[Buy milk, Gym]
true
false
[Gym]
1
```

---

## M18. Playlist
**Statement:** Circular playlist.

**Requirements**
- `addSong(String name)`
- `String current()`, `String next()`, `String previous()` (wrap around)
- Empty playlist → `"No songs"`

**Test**
```java
Playlist p = new Playlist();
System.out.println(p.current());
p.addSong("A"); p.addSong("B"); p.addSong("C");
System.out.println(p.current());
System.out.println(p.next());
System.out.println(p.next());
System.out.println(p.next());
System.out.println(p.previous());
```
**Expected**
```text
No songs
A
B
C
A
C
```

---

## M19. Meeting Room Booking
**Statement:** One room, bookings as `[start, end)` in hours; no overlaps.

**Requirements**
- `boolean book(int start, int end)` → false if overlaps or start >= end
- `int bookedCount()`

**Test**
```java
MeetingRoom r = new MeetingRoom();
System.out.println(r.book(9, 11));
System.out.println(r.book(10, 12));
System.out.println(r.book(11, 13));
System.out.println(r.book(8, 9));
System.out.println(r.book(8, 10));
System.out.println(r.bookedCount());
```
**Expected**
```text
true
false
true
true
false
3
```

---

## M20. Vending Machine (no pattern, plain if/else)
**Statement:** Products by code with price and stock; user inserts money, selects a product. Change is returned immediately on a successful sale (balance becomes 0).

**Requirements**
- `addProduct(String code, String name, int price, int qty)`
- `void insertMoney(int amt)`
- `String select(String code)`:
  `"Dispensed <name>, change <n>"`, `"Insufficient balance, need <n> more"`, `"Out of stock"`, `"Invalid code"`
- `int refund()` → returns balance and sets it to 0

**Test**
```java
VendingMachine v = new VendingMachine();
v.addProduct("A1", "Chips", 30, 2);
v.addProduct("B1", "Coke", 40, 1);
v.insertMoney(50);  System.out.println(v.select("A1"));
v.insertMoney(20);  System.out.println(v.select("B1"));
v.insertMoney(20);  System.out.println(v.select("B1"));
v.insertMoney(100); System.out.println(v.select("B1"));
System.out.println(v.select("Z9"));
System.out.println(v.refund());
```
**Expected**
```text
Dispensed Chips, change 20
Insufficient balance, need 20 more
Dispensed Coke, change 0
Out of stock
Invalid code
100
```
(Hint for line 2/3: after the first sale balance is 0; insert 20 → balance 20 → needs 20 more; insert 20 more → balance 40 → sale.)
