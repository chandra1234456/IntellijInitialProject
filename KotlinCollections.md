# Kotlin Collections: Functions with Simple Examples

A quick reference for the most-used Kotlin collection functions. Each entry has a one-line meaning and a small example with its output.

## Contents

1. [Collection types](#1-collection-types)
2. [Read and check basics](#2-read-and-check-basics)
3. [Filter](#3-filter)
4. [Take and Drop](#4-take-and-drop)
5. [Transform](#5-transform)
6. [Find and access elements](#6-find-and-access-elements)
7. [Check conditions](#7-check-conditions)
8. [Aggregate](#8-aggregate)
9. [Group, chunk and window](#9-group-chunk-and-window)
10. [Sort](#10-sort)
11. [Set operations and duplicates](#11-set-operations-and-duplicates)
12. [Plus and minus](#12-plus-and-minus)
13. [Mutable collection operations](#13-mutable-collection-operations)
14. [Map functions](#14-map-functions)
15. [Sequences (lazy)](#15-sequences-lazy)
16. [Iterate and print](#16-iterate-and-print)
17. [Conversions](#17-conversions)
18. [Cheat sheet](#18-cheat-sheet)
19. [Practice problems](#19-practice-problems)
20. [Arrays](#20-arrays)
21. [String functions](#21-string-functions)
22. [Scope functions (let, run, with, apply, also)](#22-scope-functions-let-run-with-apply-also)

---

## 1. Collection types

| Type | Read-only | Mutable | Notes |
|---|---|---|---|
| List | `listOf(1, 2, 3)` | `mutableListOf(1, 2, 3)` | Ordered, allows duplicates, accessed by index |
| Set | `setOf(1, 2, 3)` | `mutableSetOf(1, 2, 3)` | No duplicates |
| Map | `mapOf("a" to 1)` | `mutableMapOf("a" to 1)` | Key-value pairs, unique keys |
| Array | `arrayOf(1, 2, 3)` | (fixed size, elements changeable) | Fixed size |
| Sequence | `sequenceOf(1, 2, 3)` | n/a | Lazy evaluation |

```kotlin
val list = listOf(1, 2, 3, 3)        // [1, 2, 3, 3]
val set = setOf(1, 2, 3, 3)          // [1, 2, 3]
val map = mapOf("a" to 1, "b" to 2)  // {a=1, b=2}
val empty = emptyList<Int>()         // []
val range = (1..5).toList()          // [1, 2, 3, 4, 5]
val repeated = List(3) { it * 2 }    // [0, 2, 4]
```

Read-only collections (`listOf`) cannot be changed through their interface. Use the `mutable...` versions when you need `add` or `remove`.

---

## 2. Read and check basics

| Function | Meaning |
|---|---|
| `size` | Number of elements |
| `isEmpty()` / `isNotEmpty()` | Check if empty |
| `contains(x)` or `x in list` | Check if an element exists |
| `indices` | Valid index range |
| `lastIndex` | Index of the last element |

```kotlin
val list = listOf(10, 20, 30)
list.size            // 3
list.isEmpty()       // false
list.isNotEmpty()    // true
20 in list           // true
list.indices         // 0..2
list.lastIndex       // 2
list[0]              // 10
list.get(1)          // 20
```

---

## 3. Filter

```kotlin
val nums = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
```

| Function | Meaning |
|---|---|
| `filter` | Keep matching elements everywhere. |
| `filterNot` | Keep non-matching elements everywhere. |
| `filterNotNull` | Remove nulls. |
| `filterIsInstance<T>` | Keep only elements of type T. |
| `filterIndexed` | Filter using index and value. |
| `partition` | Split into (matching, non-matching) in one pass. |

```kotlin
nums.filter { it % 2 == 0 }      // [2, 4, 6, 8, 10]
nums.filterNot { it % 2 == 0 }   // [1, 3, 5, 7, 9]

listOf(1, null, 2, null).filterNotNull()          // [1, 2]
listOf(1, "a", 2.5, "b").filterIsInstance<String>() // [a, b]
nums.filterIndexed { index, _ -> index % 3 == 0 } // [1, 4, 7, 10]

val (even, odd) = nums.partition { it % 2 == 0 }
// even = [2, 4, 6, 8, 10], odd = [1, 3, 5, 7, 9]
```

---

## 4. Take and Drop

| Function | Meaning |
|---|---|
| `take(n)` | Take the first n elements. |
| `takeLast(n)` | Take the last n elements. |
| `takeWhile` | Take until condition fails. |
| `takeLastWhile` | Take from the end until condition fails. |
| `drop(n)` | Drop the first n elements. |
| `dropLast(n)` | Drop the last n elements. |
| `dropWhile` | Drop until condition fails. |
| `dropLastWhile` | Drop from the end until condition fails. |

```kotlin
val nums = listOf(1, 2, 3, 4, 1, 2)

nums.take(3)                       // [1, 2, 3]
nums.takeLast(3)                   // [4, 1, 2]
nums.takeWhile { it < 3 }          // [1, 2]            stops at the first 3
nums.takeLastWhile { it < 3 }      // [1, 2]            from the end: 2, 1, then 4 fails

nums.drop(3)                       // [4, 1, 2]
nums.dropLast(3)                   // [1, 2, 3]
nums.dropWhile { it < 3 }          // [3, 4, 1, 2]      drops 1, 2 then stops at 3
nums.dropLastWhile { it < 3 }      // [1, 2, 3, 4]
```

### takeWhile vs filter (common confusion)

```kotlin
val nums = listOf(1, 2, 3, 4, 1, 2)

nums.takeWhile { it < 3 }   // [1, 2]            stops at the first failure
nums.filter { it < 3 }      // [1, 2, 1, 2]      checks EVERY element
```

| | `takeWhile` / `dropWhile` | `filter` / `filterNot` |
|---|---|---|
| Checks | From the start, stops at the first failure | Every element |
| Order sensitive | Yes | No |

---

## 5. Transform

| Function | Meaning |
|---|---|
| `map` | Transform each element. |
| `mapIndexed` | Transform using index and value. |
| `mapNotNull` | Transform and drop nulls. |
| `flatMap` | Transform each element to a list, then join them. |
| `flatten` | Join a list of lists into one. |
| `zip` | Pair elements of two lists. |
| `unzip` | Split a list of pairs into two lists. |
| `associate` | Build a map from elements, with key and value. |
| `associateBy` | Build a map using a key selector. |
| `associateWith` | Build a map using the element as key and a value selector. |
| `withIndex` | Pair each element with its index. |

```kotlin
val nums = listOf(1, 2, 3)

nums.map { it * 2 }                         // [2, 4, 6]
nums.mapIndexed { i, v -> i * v }           // [0, 2, 6]
listOf("1", "x", "3").mapNotNull { it.toIntOrNull() } // [1, 3]

listOf("ab", "cd").flatMap { it.toList() }  // [a, b, c, d]
listOf(listOf(1, 2), listOf(3)).flatten()   // [1, 2, 3]

listOf(1, 2, 3) zip listOf("a", "b", "c")   // [(1, a), (2, b), (3, c)]
listOf(1 to "a", 2 to "b").unzip()          // ([1, 2], [a, b])

listOf("a", "bb").associate { it to it.length }        // {a=1, bb=2}
listOf("a", "bb", "cc").associateBy { it.length }      // {1=a, 2=cc}  (last one wins)
listOf("a", "bb").associateWith { it.length }          // {a=1, bb=2}

for ((index, value) in listOf("x", "y").withIndex()) {
    println("$index -> $value")             // 0 -> x, 1 -> y
}
```

---

## 6. Find and access elements

| Function | Meaning | If not found |
|---|---|---|
| `first()` | First element | Throws `NoSuchElementException` |
| `firstOrNull()` | First element, or null | `null` |
| `first { }` | First matching | Throws |
| `firstOrNull { }` / `find { }` | First matching | `null` |
| `last()` / `lastOrNull()` | Last element | Throws / `null` |
| `findLast { }` | Last matching | `null` |
| `single()` / `singleOrNull()` | The only element | Throws / `null` |
| `elementAt(i)` / `elementAtOrNull(i)` | Element at index | Throws / `null` |
| `getOrNull(i)` / `getOrElse(i) { }` | Safe access by index | `null` / default |
| `indexOf(x)` / `lastIndexOf(x)` | Position of an element | `-1` |
| `indexOfFirst { }` / `indexOfLast { }` | Position of first or last match | `-1` |

```kotlin
val nums = listOf(1, 2, 3, 4, 5)

nums.first()                  // 1
nums.last()                   // 5
nums.first { it > 3 }         // 4
nums.find { it > 3 }          // 4
nums.find { it > 10 }         // null
nums.findLast { it < 4 }      // 3
nums.firstOrNull { it > 10 }  // null
nums.singleOrNull { it == 3 } // 3
nums.elementAtOrNull(10)      // null
nums.getOrNull(10)            // null
nums.getOrElse(10) { -1 }     // -1
nums.indexOf(3)               // 2
nums.indexOfFirst { it > 3 }  // 3
```

---

## 7. Check conditions

| Function | Meaning |
|---|---|
| `any { }` | True if at least one element matches. |
| `all { }` | True if every element matches. |
| `none { }` | True if no element matches. |
| `count { }` | How many elements match. |
| `contains(x)` | Is the element present. |
| `containsAll(list)` | Are all of those elements present. |

```kotlin
val nums = listOf(1, 2, 3, 4, 5)

nums.any { it > 4 }        // true
nums.all { it > 0 }        // true
nums.none { it > 10 }      // true
nums.count { it % 2 == 0 } // 2
nums.count()               // 5
nums.containsAll(listOf(1, 2)) // true
```

`all` returns `true` and `any` returns `false` on an empty list.

---

## 8. Aggregate

| Function | Meaning |
|---|---|
| `sum()` | Add all numbers. |
| `sumOf { }` | Add a value computed per element. |
| `average()` | Mean of the numbers (Double). |
| `min()` / `max()` | Smallest / largest (throws if empty). |
| `minOrNull()` / `maxOrNull()` | Smallest / largest, or null if empty. |
| `minByOrNull { }` / `maxByOrNull { }` | Element with the smallest / largest selector value. |
| `minOf { }` / `maxOf { }` | Smallest / largest selector value. |
| `reduce` | Combine all elements into one, starting from the first. |
| `fold` | Same as reduce, but with an initial value. |
| `runningFold` / `scan` | Like fold, but keeps every step. |
| `runningReduce` | Like reduce, but keeps every step. |

```kotlin
val nums = listOf(1, 2, 3, 4)

nums.sum()                         // 10
nums.sumOf { it * 2 }              // 20
nums.average()                     // 2.5
nums.min()                         // 1
nums.maxOrNull()                   // 4

val words = listOf("kiwi", "banana", "fig")
words.maxByOrNull { it.length }    // banana
words.minByOrNull { it.length }    // fig
words.maxOf { it.length }          // 6

nums.reduce { acc, n -> acc + n }          // 10
nums.fold(100) { acc, n -> acc + n }       // 110
nums.runningFold(0) { acc, n -> acc + n }  // [0, 1, 3, 6, 10]
nums.runningReduce { acc, n -> acc + n }   // [1, 3, 6, 10]
```

`reduce` throws on an empty list. `fold` does not, because it has an initial value.

---

## 9. Group, chunk and window

| Function | Meaning |
|---|---|
| `groupBy { }` | Group elements into a map by a key. |
| `groupingBy { }.eachCount()` | Count how many elements per key. |
| `chunked(n)` | Split into lists of size n. |
| `windowed(n)` | Sliding windows of size n. |

```kotlin
val nums = listOf(1, 2, 3, 4, 5, 6)

nums.groupBy { it % 2 == 0 }      // {false=[1, 3, 5], true=[2, 4, 6]}
listOf("a", "b", "a").groupingBy { it }.eachCount() // {a=2, b=1}

listOf("apple", "avocado", "banana").groupBy { it.first() }
// {a=[apple, avocado], b=[banana]}

(1..5).toList().chunked(2)        // [[1, 2], [3, 4], [5]]
(1..5).toList().windowed(3)       // [[1, 2, 3], [2, 3, 4], [3, 4, 5]]
(1..5).toList().windowed(2)       // [[1, 2], [2, 3], [3, 4], [4, 5]]
```

---

## 10. Sort

| Function | Meaning |
|---|---|
| `sorted()` | Ascending order (new list). |
| `sortedDescending()` | Descending order (new list). |
| `sortedBy { }` | Ascending by a selector. |
| `sortedByDescending { }` | Descending by a selector. |
| `sortedWith(comparator)` | Custom comparator. |
| `reversed()` | Reverse the order. |
| `shuffled()` | Random order. |

```kotlin
val nums = listOf(3, 1, 2)
nums.sorted()                    // [1, 2, 3]
nums.sortedDescending()          // [3, 2, 1]
nums.reversed()                  // [2, 1, 3]

val words = listOf("pear", "fig", "banana")
words.sortedBy { it.length }                 // [fig, pear, banana]
words.sortedByDescending { it.length }       // [banana, pear, fig]
words.sortedWith(compareBy({ it.length }, { it })) // [fig, pear, banana]
```

On a `MutableList`, `sort()`, `sortBy { }`, `reverse()` and `shuffle()` change the list in place (they return `Unit`).

---

## 11. Set operations and duplicates

| Function | Meaning |
|---|---|
| `distinct()` | Remove duplicates, keep the first of each. |
| `distinctBy { }` | Remove duplicates by a key. |
| `toSet()` | Convert to a Set (removes duplicates). |
| `union` | All elements from both. |
| `intersect` | Elements in both. |
| `subtract` | Elements in the first but not the second. |

```kotlin
val a = listOf(1, 2, 3, 3)
val b = listOf(3, 4)

a.distinct()                       // [1, 2, 3]
listOf("a", "bb", "cc").distinctBy { it.length } // [a, bb]
a union b                          // [1, 2, 3, 4]
a intersect b                      // [3]
a subtract b                       // [1, 2]
```

### Find duplicates

```kotlin
fun findDuplicates(list: List<Int>): Set<Int> {
    val seen = mutableSetOf<Int>()
    val duplicates = mutableSetOf<Int>()
    for (n in list) {
        if (!seen.add(n)) duplicates.add(n)   // add() returns false if already present
    }
    return duplicates
}
// findDuplicates(listOf(1, 2, 3, 2, 1, 4)) -> [2, 1]

// One-liner
list.groupingBy { it }.eachCount().filterValues { it > 1 }.keys
```

---

## 12. Plus and minus

```kotlin
listOf(1, 2, 3) + 4              // [1, 2, 3, 4]
listOf(1, 2, 3) + listOf(4, 5)   // [1, 2, 3, 4, 5]
listOf(1, 2, 3) - 2              // [1, 3]
listOf(1, 2, 3, 2) - 2           // [1, 3, 2]   (removes only the first match)
```

They return a new collection and leave the original unchanged.

---

## 13. Mutable collection operations

| Function | Meaning |
|---|---|
| `add(x)` | Add to the end. |
| `add(i, x)` | Insert at an index. |
| `addAll(list)` | Add many. |
| `remove(x)` | Remove the first matching element. |
| `removeAt(i)` | Remove by index. |
| `removeIf { }` / `removeAll { }` | Remove all matching. |
| `retainAll { }` | Keep only matching. |
| `set(i, x)` or `list[i] = x` | Replace an element. |
| `clear()` | Remove everything. |

```kotlin
val list = mutableListOf(1, 2, 3, 4)
list.add(5)               // [1, 2, 3, 4, 5]
list.add(0, 99)           // [99, 1, 2, 3, 4, 5]
list.remove(99)           // [1, 2, 3, 4, 5]
list.removeAt(0)          // [2, 3, 4, 5]
list.removeAll { it > 4 } // [2, 3, 4]
list[0] = 20              // [20, 3, 4]
list.clear()              // []

val set = mutableSetOf(1, 2)
set.add(2)                // false (already there)
set.add(3)                // true
```

---

## 14. Map functions

```kotlin
val map = mapOf("a" to 1, "b" to 2, "c" to 3)
```

| Function | Meaning |
|---|---|
| `map["a"]` / `get` | Value or null. |
| `getValue("a")` | Value, throws if missing. |
| `getOrDefault("z", 0)` / `getOrElse` | Value or a default. |
| `getOrPut(key) { }` | Get, or compute and store (mutable map). |
| `containsKey` / `containsValue` | Check key or value. |
| `keys` / `values` / `entries` | The parts of the map. |
| `mapValues { }` / `mapKeys { }` | Transform values or keys. |
| `filter { }` / `filterKeys { }` / `filterValues { }` | Filter entries. |
| `toList()` | List of pairs. |

```kotlin
map["a"]                          // 1
map["z"]                          // null
map.getOrDefault("z", 0)          // 0
map.keys                          // [a, b, c]
map.values                        // [1, 2, 3]
map.mapValues { it.value * 10 }   // {a=10, b=20, c=30}
map.filterValues { it > 1 }       // {b=2, c=3}
map.filterKeys { it != "a" }      // {b=2, c=3}
map.maxByOrNull { it.value }?.key // c

for ((key, value) in map) {
    println("$key = $value")
}

val counts = mutableMapOf<String, Int>()
for (w in listOf("a", "b", "a")) {
    counts[w] = (counts[w] ?: 0) + 1   // {a=2, b=1}
}
counts.getOrPut("c") { 0 }             // adds c=0
```

---

## 15. Sequences (lazy)

A `Sequence` processes elements one at a time and only when a result is needed. It is better for large data or many chained steps.

```kotlin
val result = (1..1_000_000)
    .asSequence()
    .filter { it % 2 == 0 }
    .map { it * 3 }
    .take(3)
    .toList()
// [6, 12, 18]  -- stops after 3 results

generateSequence(1) { it + 1 }.take(5).toList()  // [1, 2, 3, 4, 5]
```

| | Collection | Sequence |
|---|---|---|
| Evaluation | Eager (each step builds a new list) | Lazy (element by element) |
| Best for | Small data | Large data, long chains |

---

## 16. Iterate and print

```kotlin
val list = listOf("a", "b", "c")

list.forEach { println(it) }
list.forEachIndexed { i, v -> println("$i: $v") }
list.onEach { println(it) }   // like forEach but returns the list (for chaining)

list.joinToString()                     // a, b, c
list.joinToString(" | ")                // a | b | c
list.joinToString(", ", "[", "]")       // [a, b, c]
list.joinToString { it.uppercase() }    // A, B, C
```

---

## 17. Conversions

```kotlin
val list = listOf(1, 2, 2, 3)
list.toSet()                        // [1, 2, 3]
list.toMutableList()                // editable copy
list.toTypedArray()                 // Array<Int>
list.toIntArray()                   // IntArray
listOf("a" to 1, "b" to 2).toMap()  // {a=1, b=2}
mapOf("a" to 1).toList()            // [(a, 1)]
arrayOf(1, 2, 3).toList()           // [1, 2, 3]
(1..5).toList()                     // [1, 2, 3, 4, 5]
```

---

## 18. Cheat sheet

| I want to... | Use |
|---|---|
| Keep matching elements everywhere | `filter` |
| Keep non-matching elements everywhere | `filterNot` |
| Take until condition fails | `takeWhile` |
| Drop until condition fails | `dropWhile` |
| First n / last n elements | `take(n)` / `takeLast(n)` |
| Skip first n / last n | `drop(n)` / `dropLast(n)` |
| Change every element | `map` |
| Flatten nested lists | `flatten` / `flatMap` |
| Split into two lists | `partition` |
| First match or null | `find` / `firstOrNull` |
| Does any / all / none match | `any` / `all` / `none` |
| Count matches | `count { }` |
| Total / average | `sum` / `average` |
| Biggest / smallest | `max` / `min`, `maxByOrNull` |
| Combine into one value | `fold` / `reduce` |
| Group by a key | `groupBy` |
| Count per item | `groupingBy { }.eachCount()` |
| Fixed-size pieces | `chunked(n)` |
| Sliding pieces | `windowed(n)` |
| Remove duplicates | `distinct` / `toSet` |
| Sort | `sorted` / `sortedBy` / `sortedDescending` |
| Pair two lists | `zip` |
| List to map | `associate` / `associateBy` / `associateWith` |
| Lazy processing | `asSequence()` |

---

## 19. Practice problems

```kotlin
val nums = listOf(5, 3, 8, 3, 5)

// 1. First repeated number
fun findFirstRepeatedNumber(list: List<Int>): Int? {
    val seen = mutableSetOf<Int>()
    for (n in list) if (!seen.add(n)) return n
    return null
}
// findFirstRepeatedNumber(nums) -> 3

// 2. Sum of squares of even numbers
(1..10).filter { it % 2 == 0 }.map { it * it }.sum()   // 220

// 3. Word frequency
"to be or not to be".split(" ").groupingBy { it }.eachCount()
// {to=2, be=2, or=1, not=1}

// 4. Longest word
listOf("kiwi", "banana", "fig").maxByOrNull { it.length }  // banana

// 5. Second largest distinct number
nums.distinct().sortedDescending().getOrNull(1)   // 5

// 6. Remove leading numbers smaller than 4, then double the rest
nums.dropWhile { it < 4 }.map { it * 2 }          // [10, 6, 16, 6, 10]

// 7. Split into names starting with A and others
listOf("Asha", "Ravi", "Anil").partition { it.startsWith("A") }
// ([Asha, Anil], [Ravi])
```

Tip: `println` any result while learning. For scratch testing, use the Kotlin Playground (play.kotlinlang.org) or a `main()` in a plain Kotlin or JVM project.

---

## 20. Arrays

An array has a **fixed size**. Elements can change, but you can't add or remove them. Most collection functions from the sections above (`map`, `filter`, `sum`, `any`, `take`, ...) also work on arrays.

### Create

| Function | Meaning |
|---|---|
| `arrayOf(1, 2, 3)` | Array of objects (`Array<Int>`). |
| `intArrayOf(1, 2, 3)` | Primitive array (`IntArray`), faster and uses less memory. |
| `IntArray(3)` | Size 3, filled with 0. |
| `Array(3) { it * 2 }` | Size 3, each element built from its index. |
| `arrayOfNulls<String>(3)` | Size 3, filled with null. |
| `emptyArray<Int>()` | Empty array. |

Others exist for each type: `DoubleArray`, `BooleanArray`, `CharArray`, `LongArray`.

```kotlin
val a = intArrayOf(3, 1, 2)
val b = Array(3) { it * 2 }      // [0, 2, 4]
val c = IntArray(3)              // [0, 0, 0]

a[0]            // 3
a[1] = 10       // a is now [3, 10, 2]
a.size          // 3
a.indices       // 0..2
a.lastIndex     // 2
```

### Print and compare

`println(array)` prints a memory address, not the contents. Use these instead:

```kotlin
println(a.contentToString())          // [3, 10, 2]
intArrayOf(1, 2) == intArrayOf(1, 2)              // false (compares references)
intArrayOf(1, 2).contentEquals(intArrayOf(1, 2))  // true
```

### Common array functions

| Function | Meaning |
|---|---|
| `sort()` | Sort the array **in place**. |
| `sortedArray()` | Return a **new** sorted array. |
| `sortDescending()` | Sort in place, descending. |
| `reverse()` | Reverse in place. |
| `reversedArray()` | Return a new reversed array. |
| `copyOf()` / `copyOf(n)` | Copy, optionally resized (new slots get 0 or null). |
| `copyOfRange(from, to)` | Copy a slice (`to` is exclusive). |
| `fill(x)` | Set every element to x. |
| `plus(x)` or `+` | New array with the element added at the end. |
| `toList()` / `toMutableList()` | Convert to a list. |
| `contains(x)` / `x in a` | Check if present. |

```kotlin
val a = intArrayOf(3, 1, 2)

a.sortedArray().contentToString()      // [1, 2, 3]  (a unchanged)
a.sort()                               // a is now [1, 2, 3]
a.reversedArray().contentToString()    // [3, 2, 1]
a.copyOf(5).contentToString()          // [1, 2, 3, 0, 0]
a.copyOfRange(1, 3).contentToString()  // [2, 3]
(a + 9).contentToString()              // [1, 2, 3, 9]
a.fill(0)                              // [0, 0, 0]

a.toList()                             // [0, 0, 0]
arrayOf(1, 2, 3).map { it * 2 }        // [2, 4, 6]  (a List)
arrayOf(1, 2, 3).sum()                 // 6
```

### Loop

```kotlin
val arr = arrayOf("a", "b", "c")

for (item in arr) println(item)
for (i in arr.indices) println("$i -> ${arr[i]}")
for ((i, item) in arr.withIndex()) println("$i -> $item")
arr.forEachIndexed { i, item -> println("$i -> $item") }
```

### 2D arrays

```kotlin
val grid = Array(2) { IntArray(3) }   // [[0, 0, 0], [0, 0, 0]]
grid[1][2] = 5
println(grid.contentDeepToString())   // [[0, 0, 0], [0, 0, 5]]

val m = arrayOf(intArrayOf(1, 2), intArrayOf(3, 4))
m[0][1]                               // 2
```

### Array vs List

| | Array | List |
|---|---|---|
| Size | Fixed | Fixed (`listOf`) or growable (`mutableListOf`) |
| Print | `contentToString()` needed | Prints contents |
| `==` | Compares references | Compares contents |
| Primitive support | `IntArray` etc. | Boxed objects |
| Use when | Fixed size, performance, interop with Java | Most everyday code |

---

## 21. String functions

A `String` is **immutable**: functions return a new string and never change the original. A string is also a sequence of characters, so collection functions like `filter`, `map`, `count`, `take` and `reversed` work on it.

### Basics

| Function | Meaning |
|---|---|
| `length` | Number of characters. |
| `isEmpty()` / `isNotEmpty()` | Check for zero length. |
| `isBlank()` / `isNotBlank()` | Check for empty or only whitespace. |
| `s[i]` / `get(i)` | Character at an index. |
| `first()` / `last()` | First or last character. |
| `lastIndex` | Index of the last character. |

```kotlin
val s = "Hello World"
s.length        // 11
s[0]            // H
s.first()       // H
s.last()        // d
"   ".isBlank() // true
"".isEmpty()    // true
```

### Case and whitespace

| Function | Meaning |
|---|---|
| `uppercase()` / `lowercase()` | Change case. |
| `replaceFirstChar { it.uppercase() }` | Capitalize the first letter. |
| `trim()` / `trimStart()` / `trimEnd()` | Remove surrounding whitespace. |
| `padStart(n, c)` / `padEnd(n, c)` | Pad to length n with character c. |

```kotlin
"kotlin".uppercase()                      // KOTLIN
"KOTLIN".lowercase()                      // kotlin
"hello".replaceFirstChar { it.uppercase() } // Hello
"  hi  ".trim()                           // hi
"Hello".padStart(8, '*')                  // ***Hello
"7".padStart(3, '0')                      // 007
```

`capitalize()` and `toUpperCase()` are deprecated. Use `replaceFirstChar` and `uppercase()`.

### Search and compare

| Function | Meaning |
|---|---|
| `contains("x")` | Does it contain the text. |
| `startsWith("x")` / `endsWith("x")` | Check the start or end. |
| `indexOf("x")` / `lastIndexOf("x")` | Position, or -1 if missing. |
| `equals(other, ignoreCase = true)` | Compare ignoring case. |
| `compareTo(other)` | Alphabetical comparison. |
| `count { }` | Count matching characters. |

```kotlin
val s = "Hello World"
s.contains("World")                 // true
s.contains("world", ignoreCase = true) // true
s.startsWith("Hell")                // true
s.endsWith("d")                     // true
s.indexOf("o")                      // 4
s.lastIndexOf("o")                  // 7
"Kotlin".equals("KOTLIN", ignoreCase = true) // true
"Hello".count { it in "aeiou" }     // 2
```

### Cut and slice

| Function | Meaning |
|---|---|
| `substring(from, to)` | Part of the string (`to` is exclusive). |
| `take(n)` / `takeLast(n)` | First or last n characters. |
| `drop(n)` / `dropLast(n)` | Skip the first or last n characters. |
| `substringBefore("x")` / `substringAfter("x")` | Text before or after the first match. |
| `removePrefix("x")` / `removeSuffix("x")` | Remove from the start or end if present. |

```kotlin
val s = "Hello World"
s.substring(0, 5)           // Hello
s.substring(6)              // World
s.take(3)                   // Hel
s.takeLast(3)               // rld
s.drop(6)                   // World
s.substringBefore(" ")      // Hello
s.substringAfter(" ")       // World
"file.txt".removeSuffix(".txt") // file
```

### Split, join and replace

| Function | Meaning |
|---|---|
| `split(",")` | Split into a list. |
| `lines()` | Split on line breaks. |
| `replace("a", "b")` | Replace every match. |
| `replaceFirst("a", "b")` | Replace the first match. |
| `repeat(n)` | Repeat n times. |
| `joinToString` | Join a list back into a string. |

```kotlin
"a,b,c".split(",")                       // [a, b, c]
"a b  c".split(" ")                      // [a, b, , c]
"a b  c".split(Regex("\\s+"))            // [a, b, c]
"Hello".replace("l", "L")                // HeLLo
"Hello".replaceFirst("l", "L")           // HeLlo
"ab".repeat(3)                           // ababab
listOf("a", "b").joinToString("-")       // a-b
```

### Convert

| Function | Meaning |
|---|---|
| `toInt()` / `toDouble()` / `toLong()` | Parse a number (throws `NumberFormatException` if invalid). |
| `toIntOrNull()` / `toDoubleOrNull()` | Parse a number, or null if invalid. |
| `toCharArray()` | String to `CharArray`. |
| `toList()` | String to a list of characters. |
| `reversed()` | Reverse the characters. |
| `format(...)` | Build text from a pattern. |

```kotlin
"42".toInt()            // 42
"abc".toIntOrNull()     // null
"3.5".toDouble()        // 3.5
"abc".reversed()        // cba
"abc".toList()          // [a, b, c]
"%.2f".format(3.14159)  // 3.14
"%05d".format(42)       // 00042
```

### Character checks

```kotlin
'a'.isLetter()          // true
'5'.isDigit()           // true
'A'.isUpperCase()       // true
' '.isWhitespace()      // true
'a'.uppercaseChar()     // A
'5'.digitToInt()        // 5
```

### Templates, raw strings and StringBuilder

```kotlin
val name = "Ravi"
val age = 25
"Name: $name, next year: ${age + 1}"   // Name: Ravi, next year: 26

val text = """
    Line one
    Line two
""".trimIndent()                        // multi-line, no escaping needed

val sb = StringBuilder()
sb.append("Hello").append(" ").append("World")
sb.insert(0, ">> ")
sb.toString()                           // >> Hello World

val built = buildString {               // cleaner way to use StringBuilder
    append("a")
    append("b")
}                                       // ab
```

Use `StringBuilder` (or `buildString`) when you join strings in a loop. Repeated `+` creates a new string each time.

### Examples

```kotlin
// Palindrome check
fun isPalindrome(s: String): Boolean {
    val clean = s.filter { it.isLetterOrDigit() }.lowercase()
    return clean == clean.reversed()
}
// isPalindrome("A man, a plan, a canal: Panama") -> true

// Count vowels
"Kotlin Collections".count { it.lowercaseChar() in "aeiou" }   // 6

// Reverse each word
"hello world".split(" ").joinToString(" ") { it.reversed() }   // olleh dlrow

// Character frequency
"banana".groupingBy { it }.eachCount()                         // {b=1, a=3, n=2}

// Capitalize each word
"hello kotlin world".split(" ")
    .joinToString(" ") { w -> w.replaceFirstChar { it.uppercase() } }  // Hello Kotlin World
```

---

## 22. Scope functions (let, run, with, apply, also)

Scope functions run a block of code on an object, inside a temporary scope. They differ in two things:

1. How you refer to the object: `it` (lambda argument) or `this` (lambda receiver).
2. What they return: the **lambda result** or the **object itself**.

| Function | Object is | Returns | Typical use |
|---|---|---|---|
| `let` | `it` | Lambda result | Null checks, transform a value |
| `run` | `this` | Lambda result | Configure an object and compute a result |
| `with(obj)` | `this` | Lambda result | Call many functions on an object |
| `apply` | `this` | The object | Configure an object (builder style) |
| `also` | `it` | The object | Side effects (logging, debugging) |

Quick memory trick:
- **it** (`let`, `also`) and **this** (`run`, `with`, `apply`).
- **let / run / with** return the **result**. **apply / also** return the **object**.

### let: run code if not null, or transform a value

```kotlin
val name: String? = "Kotlin"

name?.let { println(it.length) }        // prints 6; does nothing if name is null

val length = name?.let { it.length }    // 6 (Int?)
val upper = "abc".let { it.uppercase() } // ABC

// Use it to avoid a temporary variable
listOf(1, 2, 3).let { list -> list.sum() / list.size }  // 2
```

### run: use `this` and return the result

```kotlin
val result = "Hello".run {
    length + 5          // `this` is the string
}                       // 10

// run without an object: just a block that returns a value
val value = run {
    val a = 2
    val b = 3
    a * b
}                       // 6
```

### with: same idea as run, but the object is passed in

```kotlin
val sb = StringBuilder()
val text = with(sb) {
    append("Hello")
    append(" World")
    toString()          // last line is the result
}                       // Hello World
```

### apply: configure an object and get the object back

```kotlin
class Person { var name = ""; var age = 0 }

val person = Person().apply {
    name = "Ravi"       // `this` is the Person
    age = 25
}                       // returns the Person

// Android example
val intent = Intent(this, DetailActivity::class.java).apply {
    putExtra("id", 5)
    putExtra("name", "Ravi")
}
```

### also: do a side effect and pass the object along

```kotlin
val numbers = mutableListOf(1, 2, 3)
    .also { println("Before add: $it") }     // logs, returns the same list
    .also { it.add(4) }

val doubled = listOf(1, 2, 3)
    .also { println("Input: $it") }
    .map { it * 2 }
    .also { println("Output: $it") }
```

### takeIf and takeUnless

| Function | Meaning |
|---|---|
| `takeIf { }` | Return the object if the condition is true, otherwise null. |
| `takeUnless { }` | Return the object if the condition is false, otherwise null. |

```kotlin
val age = 20
age.takeIf { it >= 18 }        // 20
age.takeIf { it >= 21 }        // null
age.takeUnless { it >= 21 }    // 20

// Common with let
"kotlin".takeIf { it.isNotBlank() }?.let { println(it.uppercase()) } // KOTLIN
```

### Which one should I use?

| Situation | Use |
|---|---|
| Run code only when a value is not null | `value?.let { }` |
| Initialize or configure an object | `apply` |
| Log or debug in the middle of a chain | `also` |
| Compute something from an object | `run` or `let` |
| Call many members of one object | `with` |
| Pick a value only if a condition holds | `takeIf` / `takeUnless` |

### Tips

- Don't nest scope functions deeply. Two levels is the practical limit before code becomes hard to read.
- In nested scopes, name the lambda parameter (`let { person -> ... }`) so `it` doesn't get confusing.
- `this` can usually be omitted inside `apply`, `run` and `with`.
