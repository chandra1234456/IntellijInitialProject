# LLD Practice – Part 4: PATTERN-COMBINED PROBLEMS (10)

Each problem names the patterns to use. Learn the patterns first (one line each):
- **Singleton** – one instance, global access
- **Factory** – create objects by a key, hide `new`
- **Strategy** – swap an algorithm behind an interface
- **Observer** – subject notifies subscribed listeners
- **Decorator** – wrap an object to add behaviour
- **Builder** – step-by-step object construction
- **State** – behaviour changes with an internal state object
- **Command** – requests as objects (execute/undo)
- **Adapter** – make an incompatible API fit your interface
- **Chain of Responsibility** – pass a request along handlers
- **Template Method** – fixed skeleton, subclasses fill steps
- **Composite / Visitor** – tree of nodes / operations outside the nodes
- **Facade** – one simple entry point over subsystems

---

## P1. Parking Fee Engine — Singleton + Factory + Strategy
**Statement:** One global `ParkingLot`. Vehicles created from a type string. Fee calculation is swappable at runtime.

**Requirements**
- `ParkingLot.getInstance()` – always same object
- `VehicleFactory.create(String type, String number)` → `Car` / `Bike`; unknown type → `IllegalArgumentException("Unknown vehicle type: X")`
- `FeeStrategy.calculate(Vehicle v, int hours)`; `HourlyFee` (CAR 20/hr, BIKE 10/hr), `FlatFee` (CAR 100, BIKE 50)
- `lot.setFeeStrategy(...)`, `lot.calculateFee(Vehicle, hours)`

**Test**
```java
ParkingLot a = ParkingLot.getInstance();
ParkingLot b = ParkingLot.getInstance();
System.out.println(a == b);
Vehicle car  = VehicleFactory.create("CAR", "KA01");
Vehicle bike = VehicleFactory.create("BIKE", "KA02");
a.setFeeStrategy(new HourlyFee());
System.out.println(a.calculateFee(car, 3));
System.out.println(a.calculateFee(bike, 3));
a.setFeeStrategy(new FlatFee());
System.out.println(a.calculateFee(car, 3));
System.out.println(a.calculateFee(bike, 3));
try { VehicleFactory.create("TRUCK", "X"); }
catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
```
**Expected**
```text
true
60
30
100
50
Unknown vehicle type: TRUCK
```

---

## P2. Order Notifications — Observer + Factory
**Statement:** `OrderService` publishes "order placed" to subscribers (Email, SMS, Push). Subscribers can join/leave at runtime; created via a factory.

**Requirements**
- `interface Notifier { void onOrderPlaced(String orderId, String user); }`
- `NotifierFactory.create("EMAIL" | "SMS" | "PUSH")`
- `OrderService.subscribe(Notifier)`, `unsubscribe(Notifier)`, `placeOrder(orderId, user)`
- Message formats: `Email to <user>: Order <id> placed`, `SMS to ...`, `Push to ...`
- Notify in subscription order

**Test**
```java
OrderService svc = new OrderService();
Notifier email = NotifierFactory.create("EMAIL");
Notifier sms   = NotifierFactory.create("SMS");
Notifier push  = NotifierFactory.create("PUSH");
svc.subscribe(email); svc.subscribe(sms);
svc.placeOrder("O1", "Asha");
svc.subscribe(push);
svc.unsubscribe(email);
svc.placeOrder("O2", "Ben");
```
**Expected**
```text
Email to Asha: Order O1 placed
SMS to Asha: Order O1 placed
SMS to Ben: Order O2 placed
Push to Ben: Order O2 placed
```

---

## P3. Coffee Shop — Decorator + Builder + Factory
**Statement:** Beverages with add-ons. Cost and description accumulate through wrappers. A builder offers a fluent API.

**Requirements**
- `interface Beverage { String getDescription(); int getCost(); }`
- Bases via `BeverageFactory.create("ESPRESSO"|"LATTE")` → cost 100 / 150
- Decorators: `Milk` +20, `Sugar` +5, `ExtraShot` +40; description appends `" + <Name>"`
- `OrderBuilder.base("ESPRESSO").addMilk().addSugar().addSugar().build()`

**Test**
```java
Beverage b1 = new OrderBuilder().base("ESPRESSO").addMilk().addSugar().addSugar().build();
Beverage b2 = new OrderBuilder().base("LATTE").addExtraShot().build();
Beverage b3 = BeverageFactory.create("ESPRESSO");
for (Beverage b : List.of(b1, b2, b3))
    System.out.println(b.getDescription() + " = " + b.getCost());
```
**Expected**
```text
Espresso + Milk + Sugar + Sugar = 130
Latte + ExtraShot = 190
Espresso = 100
```

---

## P4. Vending Machine — State (+ counter)
**Statement:** Classic gumball machine. Behaviour of `insertCoin / ejectCoin / turnCrank` depends on the current state: `NoCoin`, `HasCoin`, `SoldOut`. (No `if(state==...)` chains inside the machine.)

**Requirements**
- `VendingMachine(int items)`; `int getCount()`
- Each action returns a message:
    - NoCoin: insert → `Coin inserted`; eject → `No coin to eject`; crank → `Insert coin first`
    - HasCoin: insert → `Coin already inserted`; eject → `Coin returned`; crank → `Dispensing item` (count−1; go to SoldOut if 0, else NoCoin)
    - SoldOut: insert → `Machine sold out`; eject → `No coin to eject`; crank → `Machine sold out`

**Test**
```java
VendingMachine m = new VendingMachine(2);
System.out.println(m.insertCoin());
System.out.println(m.turnCrank());
System.out.println(m.turnCrank());
System.out.println(m.insertCoin());
System.out.println(m.insertCoin());
System.out.println(m.ejectCoin());
System.out.println(m.insertCoin());
System.out.println(m.turnCrank());
System.out.println(m.insertCoin());
System.out.println(m.ejectCoin());
System.out.println(m.getCount());
```
**Expected**
```text
Coin inserted
Dispensing item
Insert coin first
Coin inserted
Coin already inserted
Coin returned
Coin inserted
Dispensing item
Machine sold out
No coin to eject
0
```

---

## P5. Text Editor with Undo/Redo — Command
**Statement:** Editor operations are command objects with `execute()` and `undo()`. A manager keeps undo and redo stacks. A new command clears the redo stack.

**Requirements**
- `TextEditor` holds a `StringBuilder`; `getText()`
- `AppendCommand(editor, text)`, `DeleteCommand(editor, n)` (delete last n chars; remember what was removed)
- `CommandManager.run(Command)`, `boolean undo()`, `boolean redo()`

**Test**
```java
TextEditor ed = new TextEditor();
CommandManager cm = new CommandManager();
cm.run(new AppendCommand(ed, "Hello"));        System.out.println(ed.getText());
cm.run(new AppendCommand(ed, " World"));       System.out.println(ed.getText());
cm.run(new DeleteCommand(ed, 6));              System.out.println(ed.getText());
cm.undo();                                     System.out.println(ed.getText());
cm.undo();                                     System.out.println(ed.getText());
cm.redo();                                     System.out.println(ed.getText());
cm.run(new AppendCommand(ed, "!"));            System.out.println(ed.getText());
System.out.println(cm.redo() ? ed.getText() : "Nothing to redo");
```
**Expected**
```text
Hello
Hello World
Hello
Hello World
Hello
Hello World
Hello World!
Nothing to redo
```

---

## P6. Payment Gateway — Strategy + Factory + Adapter + Facade
**Statement:** Checkout supports several payment methods. PayPal comes from a legacy SDK that expects dollars, so it needs an adapter (rate 1 USD = Rs 80). Callers only use `PaymentService.checkout`.

**Requirements**
- `interface PaymentMethod { void pay(int amountRs); }`
- `CreditCardPayment` prints `Paid Rs.<n> using Credit Card`; `UpiPayment` prints `Paid Rs.<n> using UPI`
- `LegacyPayPal.sendPayment(double usd)` prints `PayPal charged $<usd>` (already given, do not change)
- `PayPalAdapter implements PaymentMethod` converts Rs → USD
- `PaymentFactory.get(String)`; unknown → `IllegalArgumentException("Unsupported payment method: X")`
- `PaymentService.checkout(String method, int amountRs)` catches the exception and prints its message

**Test**
```java
PaymentService svc = new PaymentService();
svc.checkout("CARD", 500);
svc.checkout("UPI", 200);
svc.checkout("PAYPAL", 800);
svc.checkout("BITCOIN", 100);
```
**Expected**
```text
Paid Rs.500 using Credit Card
Paid Rs.200 using UPI
PayPal charged $10.0
Unsupported payment method: BITCOIN
```

---

## P7. Logger Framework — Singleton + Chain of Responsibility + Observer (appenders)
**Statement:** One global `Logger`. A message goes through a chain `InfoHandler → DebugHandler → ErrorHandler`; the handler matching the level formats it as `[LEVEL] message` and sends it to appenders. Appenders are subscribed observers: `ConsoleAppender` gets everything; `FileAppender` (stores lines in an in-memory list) subscribes only to ERROR.

**Requirements**
- `Logger.getInstance()`, `info(msg)`, `debug(msg)`, `error(msg)`
- `Logger.addAppender(Appender, LogLevel... levels)`
- `FileAppender.getLines()`

**Test**
```java
Logger log1 = Logger.getInstance();
Logger log2 = Logger.getInstance();
System.out.println(log1 == log2);
FileAppender file = new FileAppender();
log1.addAppender(new ConsoleAppender(), LogLevel.INFO, LogLevel.DEBUG, LogLevel.ERROR);
log1.addAppender(file, LogLevel.ERROR);
log1.info("Server started");
log1.debug("x=5");
log1.error("DB down");
System.out.println("File log: " + file.getLines());
```
**Expected**
```text
true
[INFO] Server started
[DEBUG] x=5
[ERROR] DB down
File log: [[ERROR] DB down]
```

---

## P8. File System — Composite + Visitor
**Statement:** Files and folders form a tree. Folder size = sum of children. Operations (tree printing, search by extension, file count) are visitors, not methods on the nodes.

**Requirements**
- `Node { accept(Visitor v); getName(); getSize(); }`; `FileNode(name, size)`, `FolderNode(name)` with `add(Node)`
- `PrintVisitor` – 2 spaces per depth; folders `name/ (size)`, files `name (size)`
- `ExtensionSearchVisitor(".java")` → `getResults()` names in traversal order
- `CountVisitor` → number of files

**Test**
```java
FolderNode root = new FolderNode("root");
FolderNode docs = new FolderNode("docs");
FolderNode src  = new FolderNode("src");
FolderNode test = new FolderNode("test");
docs.add(new FileNode("resume.pdf", 200)); docs.add(new FileNode("notes.txt", 50));
test.add(new FileNode("MainTest.java", 60));
src.add(new FileNode("Main.java", 120)); src.add(new FileNode("Util.java", 80)); src.add(test);
root.add(docs); root.add(src); root.add(new FileNode("readme.txt", 10));

root.accept(new PrintVisitor());
ExtensionSearchVisitor sv = new ExtensionSearchVisitor(".java");
root.accept(sv);
System.out.println(sv.getResults());
CountVisitor cv = new CountVisitor();
root.accept(cv);
System.out.println(cv.getCount());
```
**Expected**
```text
root/ (520)
  docs/ (250)
    resume.pdf (200)
    notes.txt (50)
  src/ (260)
    Main.java (120)
    Util.java (80)
    test/ (60)
      MainTest.java (60)
  readme.txt (10)
[Main.java, Util.java, MainTest.java]
6
```

---

## P9. Order Processing Pipeline — Chain of Responsibility + Template Method + Facade
**Statement:** Orders pass validators in a chain (Stock → Payment → Fraud). Accepted orders are processed by a processor with a fixed skeleton `calculateTotal → ship`; Online and Store differ in the steps. `OrderFacade` hides everything.

**Requirements**
- `Order(id, qty, stock, amount, cardValid, type)` where `type` is `"ONLINE"` or `"STORE"`
- Validators (reject message): Stock `qty > stock` → `out of stock`; Payment `!cardValid` → `invalid payment`; Fraud `amount > 50000` → `fraud suspected`
- `abstract class OrderProcessor { final String process(Order o) }` – template; `OnlineProcessor` total = amount + 50, result `<id> shipped, total <t>`; `StoreProcessor` total = amount, result `<id> ready for pickup, total <t>`
- Rejection output: `<id> rejected: <reason>`
- `OrderFacade.placeOrder(Order)` returns the string

**Test**
```java
OrderFacade f = new OrderFacade();
System.out.println(f.placeOrder(new Order("O1", 2, 5, 1000,  true,  "ONLINE")));
System.out.println(f.placeOrder(new Order("O2", 1, 5, 1000,  true,  "STORE")));
System.out.println(f.placeOrder(new Order("O3", 10, 5, 1000, true,  "ONLINE")));
System.out.println(f.placeOrder(new Order("O4", 1, 5, 1000,  false, "ONLINE")));
System.out.println(f.placeOrder(new Order("O5", 1, 5, 60000, true,  "STORE")));
```
**Expected**
```text
O1 shipped, total 1050
O2 ready for pickup, total 1000
O3 rejected: out of stock
O4 rejected: invalid payment
O5 rejected: fraud suspected
```

---

## P10. ATM — State + Chain of Responsibility (+ Singleton bank data optional)
**Statement:** ATM states: `Idle`, `CardInserted`, `Authenticated`. Cash is dispensed using a chain of denomination handlers `2000 → 500 → 100`.

**Requirements**
- Card PIN is `1234`, account balance `10000`
- Methods return messages:
    - `insertCard()` in Idle → `Card inserted. Enter PIN`; otherwise `Card already inserted`
    - `enterPin(int)` in CardInserted → `Authenticated` / `Wrong PIN`; in Idle → `Insert card first`
    - `withdraw(int amt)`:
        - not authenticated: Idle → `Insert card first`, CardInserted → `Please authenticate first`
        - authenticated: not multiple of 100 → `Amount must be multiple of 100`; amt > balance → `Insufficient balance`; else `Dispensed: 2000 x1, 500 x1, 100 x1` (list only denominations used)
    - `ejectCard()` → `Card ejected` (back to Idle)
    - `getBalance()`

**Test**
```java
Atm atm = new Atm();
System.out.println(atm.withdraw(500));
System.out.println(atm.insertCard());
System.out.println(atm.withdraw(500));
System.out.println(atm.enterPin(1111));
System.out.println(atm.enterPin(1234));
System.out.println(atm.withdraw(2600));
System.out.println(atm.withdraw(150));
System.out.println(atm.withdraw(20000));
System.out.println(atm.getBalance());
System.out.println(atm.ejectCard());
System.out.println(atm.withdraw(100));
```
**Expected**
```text
Insert card first
Card inserted. Enter PIN
Please authenticate first
Wrong PIN
Authenticated
Dispensed: 2000 x1, 500 x1, 100 x1
Amount must be multiple of 100
Insufficient balance
7400
Card ejected
Insert card first
```

---

## Suggested learning order
1. Part 1 (all 10) → Part 2 (all 20) → Part 3 (one per day, draw classes on paper first).
2. For Part 4, learn one pattern from the list above, then do the problem(s) using it: P1, P2, P3 → P4, P5 → P6 → P7, P8 → P9, P10.
3. After each solution, rewrite it once without looking, then try adding one new requirement (e.g. P1: a `PeakHourFee` strategy) to see whether your design absorbs change.
