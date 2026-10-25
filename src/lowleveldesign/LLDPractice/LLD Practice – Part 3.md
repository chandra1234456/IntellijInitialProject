**LLD Practice – Part 3: LITTLE-MEDIUM (10 problems, real LLD style, NO design patterns)Focus: identify entities, split responsibilities (model / service / main), use enums, custom exceptions or result values, keep classes small.
Suggested process for each: (1) list nouns = classes, (2) list verbs = methods, (3) decide who owns which data, (4) code, (5) match the expected output exactly.**

---

## L1. Parking Lot (typed spots, tickets, fee)

**Statement:** Lot has spots of type BIKE and CAR. A vehicle gets the first free spot of its type and a ticket. Fee = hours × rate (BIKE 10/hr, CAR 20/hr).

**Entities:** `VehicleType` (enum), `Spot`, `Ticket`, `ParkingLot`

**Requirements**

- `ParkingLot(int bikeSpots, int carSpots)` → spot ids `B1..`, `C1..`
- `Ticket park(String vehicleNo, VehicleType type)` → `null` if no spot / already parked
- `int unpark(String ticketId, int hours)` → fee, frees the spot; `-1` if invalid ticket
- Ticket ids: `T1, T2, ...`

**Test**

```java
ParkingLot lot = new ParkingLot(1, 2);
Ticket t1 = lot.park("KA01", VehicleType.BIKE);
Ticket t2 = lot.park("KA02", VehicleType.CAR);
Ticket t3 = lot.park("KA03", VehicleType.CAR);
Ticket t4 = lot.park("KA04", VehicleType.CAR);
System.out.println(t1.getId() + " " + t1.getSpotId());
System.out.println(t2.getId() + " " + t2.getSpotId());
System.out.println(t3.getId() + " " + t3.getSpotId());
System.out.println(t4);
System.out.println(lot.unpark("T2", 3));
Ticket t5 = lot.park("KA04", VehicleType.CAR);
System.out.println(t5.getId() + " " + t5.getSpotId());
System.out.println(lot.unpark("T99", 1));
```

**Expected**

```text
T1 B1
T2 C1
T3 C2
null
60
T4 C1
-1
```

---

## L2. LRU Cache (HashMap + your own doubly linked list)

**Statement:** Fixed-capacity cache; evicts the least recently used key. Both `get` and `put` count as "use". O(1) operations. Do NOT use `LinkedHashMap`.

**Requirements**

- `LRUCache(int capacity)`
- `int get(int key)` → -1 if absent
- `void put(int key, int value)`

**Test**

```java
LRUCache c = new LRUCache(2);
c.put(1, 10);
c.put(2, 20);
System.out.println(c.get(1));
c.put(3, 30);
System.out.println(c.get(2));
System.out.println(c.get(3));
c.put(4, 40);
System.out.println(c.get(1));
System.out.println(c.get(3));
System.out.println(c.get(4));
```

**Expected**

```text
10
-1
30
-1
30
40
```

---

## L3. Rate Limiter (fixed window, per user)

**Statement:** Each user may make at most `limit` requests per window of `windowSec` seconds. Window index = `timestamp / windowSec`. Timestamps are passed in (so output is deterministic).

**Requirements**

- `RateLimiter(int limit, int windowSec)`
- `boolean allow(String userId, int timestampSec)`
- A new window resets that user's count; users are independent

**Test**

```java
RateLimiter r = new RateLimiter(3, 10);
System.out.println(r.allow("u1", 1));
System.out.println(r.allow("u1", 2));
System.out.println(r.allow("u1", 3));
System.out.println(r.allow("u1", 4));
System.out.println(r.allow("u1", 9));
System.out.println(r.allow("u1", 10));
System.out.println(r.allow("u2", 5));
System.out.println(r.allow("u1", 11));
```

**Expected**

```text
true
true
true
false
false
true
true
true
```

---

## L4. Library Management System

**Statement:** Books, members, loans. Loan period 7 days, fine Rs 2 per extra day. Search is case-insensitive "contains" on title or author.

**Entities:** `Book`, `Member`, `Loan`, `LibraryService`

**Requirements**

- `addBook(id, title, author)`, `addMember(id, name)`
- `List<String> searchByAuthor(String q)` → titles, insertion order
- `String borrow(String memberId, String bookId)` → `"Borrowed <title>"` / `"Book not available"` / `"Invalid member or book"`
- `String giveBack(String memberId, String bookId, int daysTaken)` → `"Returned. Fine: <n>"` / `"No such loan"`

**Test**

```java
LibraryService lib = new LibraryService();
lib.addBook("B1", "Clean Code", "Robert Martin");
lib.addBook("B2", "Effective Java", "Joshua Bloch");
lib.addBook("B3", "Refactoring", "Martin Fowler");
lib.addMember("M1", "Asha");
System.out.println(lib.searchByAuthor("martin"));
System.out.println(lib.borrow("M1", "B1"));
System.out.println(lib.borrow("M1", "B1"));
System.out.println(lib.borrow("M9", "B2"));
System.out.println(lib.giveBack("M1", "B1", 10));
System.out.println(lib.giveBack("M1", "B1", 1));
System.out.println(lib.borrow("M1", "B1"));
```

**Expected**

```text
[Clean Code, Refactoring]
Borrowed Clean Code
Book not available
Invalid member or book
Returned. Fine: 6
No such loan
Borrowed Clean Code
```

---

## L5. Expense Sharing (Splitwise-lite, equal split)

**Statement:** Users add expenses; the amount is split equally among the listed participants (payer may or may not be one). Track who owes whom, netting opposite debts between the same pair.

**Requirements**

- `addExpense(String paidBy, double amount, List<String> participants)`
- `List<String> balances()` → lines `"<debtor> owes <creditor>: <amt>"`, sorted by debtor then creditor, only non-zero

**Test**

```java
ExpenseManager m = new ExpenseManager();
m.addExpense("A", 300, List.of("A", "B", "C"));
m.addExpense("B", 150, List.of("B", "C"));
m.balances().forEach(System.out::println);
m.addExpense("C", 100, List.of("A", "C"));
System.out.println("--");
m.balances().forEach(System.out::println);
```

**Expected**

```text
B owes A: 100.0
C owes A: 100.0
C owes B: 75.0
--
B owes A: 100.0
C owes A: 50.0
C owes B: 75.0
```

(Second expense of C: A owes C 50 → nets against "C owes A 100" → "C owes A: 50.0".)

---

## L6. Multi-Elevator Dispatch

**Statement:** Building has several elevators. A request for floor `f` goes to the nearest elevator (by floor distance); ties → lowest elevator id. The chosen elevator moves to `f` immediately (no concurrency, no passengers).

**Requirements**

- `ElevatorSystem(int[] startFloors)` → ids `E1, E2, ...`
- `String request(int floor)` → `"<id> assigned (moved <n> floors)"`
- `int floorOf(String id)`

**Test**

```java
ElevatorSystem es = new ElevatorSystem(new int[]{0, 10});
System.out.println(es.request(2));
System.out.println(es.request(9));
System.out.println(es.request(5));
System.out.println(es.floorOf("E1"));
System.out.println(es.floorOf("E2"));
```

**Expected**

```text
E1 assigned (moved 2 floors)
E2 assigned (moved 1 floors)
E1 assigned (moved 3 floors)
5
9
```

---

## L7. Hotel Reservation with Date Ranges

**Statement:** Rooms are booked for `[checkIn, checkOut)` using day numbers. Back-to-back bookings (checkOut == next checkIn) are allowed; overlaps are not.

**Requirements**

- `addRoom(int roomNo)`
- `int book(int roomNo, int checkIn, int checkOut)` → booking id (1,2,3…) or -1
- `boolean cancel(int bookingId)`
- `List<Integer> availableRooms(int from, int to)` → sorted room numbers

**Test**

```java
Hotel h = new Hotel();
h.addRoom(101); h.addRoom(102);
System.out.println(h.book(101, 1, 5));
System.out.println(h.book(101, 5, 8));
System.out.println(h.book(101, 4, 6));
System.out.println(h.availableRooms(2, 3));
System.out.println(h.availableRooms(8, 9));
System.out.println(h.cancel(1));
System.out.println(h.availableRooms(2, 3));
System.out.println(h.cancel(1));
```

**Expected**

```text
1
2
-1
[102]
[101, 102]
true
[101, 102]
false
```

---

## L8. Food Ordering (order lifecycle with enum, no State pattern)

**Statement:** Menu items, cart, order with delivery fee 30. Status flow: `PLACED → PREPARING → DELIVERED`. Cancel allowed only in `PLACED`.

**Entities:** `MenuItem`, `OrderItem`, `Order`, `OrderStatus` (enum), `FoodService`

**Requirements**

- `addMenuItem(name, price)`
- `Order placeOrder(Map<String,Integer> items)` (LinkedHashMap, name → qty); order ids `O1, O2...`
- `boolean advance(String orderId)` → false if already DELIVERED/CANCELLED
- `boolean cancel(String orderId)`
- `Order.getTotal()`, `Order.getStatus()`

**Test**

```java
FoodService f = new FoodService();
f.addMenuItem("Burger", 120); f.addMenuItem("Fries", 60); f.addMenuItem("Coke", 40);
Map<String,Integer> cart = new LinkedHashMap<>();
cart.put("Burger", 2); cart.put("Coke", 1);
Order o1 = f.placeOrder(cart);
System.out.println(o1.getId() + " " + o1.getTotal() + " " + o1.getStatus());
System.out.println(f.advance("O1") + " " + o1.getStatus());
System.out.println(f.cancel("O1"));
System.out.println(f.advance("O1") + " " + o1.getStatus());
System.out.println(f.advance("O1"));
Order o2 = f.placeOrder(Map.of("Fries", 1));
System.out.println(o2.getId() + " " + o2.getTotal());
System.out.println(f.cancel("O2") + " " + o2.getStatus());
```

**Expected**

```text
O1 310 PLACED
true PREPARING
false
true DELIVERED
false
O2 90
true CANCELLED
```

---

## L9. Snake & Ladder (deterministic dice)

**Statement:** Board of 20 cells. Snakes: 17→7, 14→4. Ladders: 3→12, 9→18. Players start at 0 and move in turn. A roll that would exceed 20 is ignored (stay). Landing exactly on 20 wins. Dice values come from a supplied array (cycled in order).

**Requirements**

- `Game(List<String> players, int[] diceValues)`
- `void play()` prints each turn as `"<name> rolled <d>: <from> -> <to>"` (to = final position after snake/ladder) and finally `"<name> wins!"`

**Test**

```java
Game g = new Game(List.of("A", "B"), new int[]{3, 4, 2, 5, 6, 3, 4, 2});
g.play();
```

**Expected**

```text
A rolled 3: 0 -> 12
B rolled 4: 0 -> 4
A rolled 2: 12 -> 4
B rolled 5: 4 -> 18
A rolled 6: 4 -> 10
B rolled 3: 18 -> 18
A rolled 4: 10 -> 4
B rolled 2: 18 -> 20
B wins!
```

---

## L10. Cab Booking (nearest driver)

**Statement:** Drivers have (x,y) location and availability. Rider requests pickup→drop. Nearest available driver by Manhattan distance to pickup (tie → lowest driver id) is assigned. Fare = Manhattan(pickup, drop) × 10. When a ride completes, the driver is free again and sits at the drop location.

**Requirements**

- `addDriver(String id, int x, int y)`
- `String book(String riderId, int px, int py, int dx, int dy)` → `"<rider> -> <driver> fare <n>"` or `"<rider> -> No drivers available"`
- `void complete(String riderId)`

**Test**

```java
CabService s = new CabService();
s.addDriver("D1", 0, 0); s.addDriver("D2", 5, 5); s.addDriver("D3", 10, 0);
System.out.println(s.book("R1", 1, 1, 4, 5));
System.out.println(s.book("R2", 1, 0, 2, 2));
s.complete("R1");
System.out.println(s.book("R3", 4, 4, 0, 0));
System.out.println(s.book("R4", 9, 0, 9, 3));
System.out.println(s.book("R5", 0, 0, 1, 1));
```

**Expected**

```text
R1 -> D1 fare 70
R2 -> D2 fare 30
R3 -> D1 fare 80
R4 -> D3 fare 30
R5 -> No drivers available
```

(R2: D2 at (5,5) and D3 at (10,0) are both distance 9 from (1,0) → D2 wins the tie. R3: D1 is at (4,5), distance 1.)
