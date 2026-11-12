# Kotlin Collections: Interview-Level Output Questions

Each question shows a snippet. Write down the output before checking the answer.

---

## Level 1: Basics

**Q1.**
```kotlin
println(listOf(1, 2, 3, 4, 5).takeWhile { it < 4 })
```
**Output:** `[1, 2, 3]`

**Q2.**
```kotlin
println(listOf(1, 2, 3, 4, 1, 2).takeWhile { it < 3 })
```
**Output:** `[1, 2]`

**Q3.**
```kotlin
println(listOf(1, 2, 3, 4, 1, 2).filter { it < 3 })
```
**Output:** `[1, 2, 1, 2]`

**Q4.**
```kotlin
println(listOf(1, 2, 3, 4, 1, 2).dropWhile { it < 3 })
```
**Output:** `[3, 4, 1, 2]`

**Q5.**
```kotlin
println(listOf(1, 2, 3, 4, 1, 2).takeLastWhile { it < 3 })
```
**Output:** `[1, 2]`

**Q6.**
```kotlin
println(listOf(1, 2, 3, 4, 1, 2).dropLastWhile { it < 3 })
```
**Output:** `[1, 2, 3, 4]`

**Q7.**
```kotlin
println((1..10).filterNot { it % 3 == 0 })
```
**Output:** `[1, 2, 4, 5, 7, 8, 10]`

**Q8.**
```kotlin
println(listOf(1, 2, 3, 4, 5, 6).partition { it % 2 == 0 })
```
**Output:** `([2, 4, 6], [1, 3, 5])`

**Q9.**
```kotlin
println(listOf(1, 2, 3).map { it * it }.sum())
```
**Output:** `14`

**Q10.**
```kotlin
println(listOf("a", "bb", "ccc").map { it.length })
```
**Output:** `[1, 2, 3]`

**Q11.**
```kotlin
println(listOf(1, null, 3, null).filterNotNull())
```
**Output:** `[1, 3]`

**Q12.**
```kotlin
println(listOf("1", "x", "3").mapNotNull { it.toIntOrNull() })
```
**Output:** `[1, 3]`

**Q13.**
```kotlin
println(listOf(1, 2, 3, 4).count { it > 2 })
```
**Output:** `2`

**Q14.**
```kotlin
println(listOf(1, 2, 3, 4).average())
```
**Output:** `2.5`

**Q15.**
```kotlin
println(listOf(1, 2, 3).indexOf(9))
```
**Output:** `-1`

**Q16.**
```kotlin
println(listOf(1, 2, 3).getOrNull(5))
```
**Output:** `null`

**Q17.**
```kotlin
println(listOf(1, 2, 3).firstOrNull { it > 5 })
```
**Output:** `null`

**Q18.**
```kotlin
println(listOf(3, 1, 2).sortedDescending())
```
**Output:** `[3, 2, 1]`

**Q19.**
```kotlin
println(listOf("pear", "fig", "banana").sortedBy { it.length })
```
**Output:** `[fig, pear, banana]`

**Q20.**
```kotlin
println(listOf(1, 2, 2, 3, 3, 3).distinct())
```
**Output:** `[1, 2, 3]`

---

## Level 2: Intermediate

**Q21.**
```kotlin
println(listOf(1, 2, 3, 4).reduce { a, b -> a - b })
```
**Output:** `-8`

**Q22.**
```kotlin
println(listOf(1, 2, 3, 4).fold(10) { a, b -> a - b })
```
**Output:** `0`

**Q23.**
```kotlin
println(listOf(1, 2, 3, 4).runningFold(0) { a, b -> a + b })
```
**Output:** `[0, 1, 3, 6, 10]`

**Q24.**
```kotlin
println(listOf(1, 2, 3, 4).runningReduce { a, b -> a + b })
```
**Output:** `[1, 3, 6, 10]`

**Q25.**
```kotlin
println((1..6).toList().groupBy { it % 3 })
```
**Output:** `{1=[1, 4], 2=[2, 5], 0=[3, 6]}`

**Q26.**
```kotlin
println(listOf("a", "b", "a", "c", "a").groupingBy { it }.eachCount())
```
**Output:** `{a=3, b=1, c=1}`

**Q27.**
```kotlin
println((1..5).toList().chunked(2))
```
**Output:** `[[1, 2], [3, 4], [5]]`

**Q28.**
```kotlin
println((1..5).toList().windowed(3))
```
**Output:** `[[1, 2, 3], [2, 3, 4], [3, 4, 5]]`

**Q29.**
```kotlin
println(listOf(1, 2, 3, 4).windowed(2) { it.sum() })
```
**Output:** `[3, 5, 7]`

**Q30.**
```kotlin
println(listOf("ab", "cd").flatMap { it.toList() })
```
**Output:** `[a, b, c, d]`

**Q31.**
```kotlin
println(listOf(listOf(1, 2), listOf(3), emptyList<Int>()).flatten())
```
**Output:** `[1, 2, 3]`

**Q32.**
```kotlin
println(listOf(1, 2, 3) zip listOf("a", "b"))
```
**Output:** `[(1, a), (2, b)]`

**Q33.**
```kotlin
println(listOf("a", "b").zip(listOf(1, 2)).toMap())
```
**Output:** `{a=1, b=2}`

**Q34.**
```kotlin
println(listOf("a", "bb", "cc").associateBy { it.length })
```
**Output:** `{1=a, 2=cc}`

**Q35.**
```kotlin
println(listOf("a", "bb").associateWith { it.length })
```
**Output:** `{a=1, bb=2}`

**Q36.**
```kotlin
println(listOf(1, 2, 3, 2) - 2)
```
**Output:** `[1, 3, 2]`

**Q37.**
```kotlin
println(listOf(1, 2, 3) + listOf(4))
```
**Output:** `[1, 2, 3, 4]`

**Q38.**
```kotlin
println(listOf(1, 2, 3) union listOf(3, 4))
```
**Output:** `[1, 2, 3, 4]`

**Q39.**
```kotlin
println(listOf(1, 2, 3, 3) intersect listOf(3, 4))
```
**Output:** `[3]`

**Q40.**
```kotlin
println(listOf(1, 2, 3, 4, 5, 6).filter { it % 2 == 0 }.map { it * it }.take(2))
```
**Output:** `[4, 16]`

**Q41.**
```kotlin
println((1..3).flatMap { i -> (1..i).map { i * it } })
```
**Output:** `[1, 2, 4, 3, 6, 9]`

**Q42.**
```kotlin
println(listOf("kiwi", "banana", "fig", "mango").maxByOrNull { it.length })
```
**Output:** `banana`

**Q43.**
```kotlin
println(listOf("ab", "cd").maxByOrNull { it.length })
```
**Output:** `ab`

**Q44.**
```kotlin
println(mapOf("a" to 1, "b" to 2).mapValues { it.value * 10 })
```
**Output:** `{a=10, b=20}`

**Q45.**
```kotlin
println(mapOf("a" to 1, "b" to 2, "c" to 3).filterValues { it > 1 }.keys)
```
**Output:** `[b, c]`

---

## Level 3: Tricky

**Q46.**
```kotlin
println(listOf(1, 2, 3).first { it > 5 })
```
**Output:** `Exception: NoSuchElementException`

**Q47.**
```kotlin
println(emptyList<Int>().sum())
```
**Output:** `0`

**Q48.**
```kotlin
println(emptyList<Int>().average())
```
**Output:** `NaN`

**Q49.**
```kotlin
println(emptyList<Int>().max())
```
**Output:** `Exception: NoSuchElementException`

**Q50.**
```kotlin
println(emptyList<Int>().reduce { a, b -> a + b })
```
**Output:** `Exception: UnsupportedOperationException`

**Q51.**
```kotlin
println(emptyList<Int>().all { it > 0 })
println(emptyList<Int>().any { it > 0 })
```
**Output:**
```
true
false
```

**Q52.**
```kotlin
val set = mutableSetOf(1, 2)
println(set.add(2))
println(set.add(3))
```
**Output:**
```
false
true
```

**Q53.**
```kotlin
println(mapOf("a" to 1, "a" to 2))
```
**Output:** `{a=2}`

**Q54.**
```kotlin
val m = mapOf("a" to 1)
println(m["b"])
println(m.getOrDefault("b", 0))
```
**Output:**
```
null
0
```

**Q55.**
```kotlin
val l = listOf(3, 1, 2)
l.sorted()
println(l)
```
**Output:** `[3, 1, 2]`

**Q56.**
```kotlin
val l = mutableListOf(3, 1, 2)
l.sort()
println(l)
```
**Output:** `[1, 2, 3]`

**Q57.**
```kotlin
println(mutableListOf(3, 1, 2).sort())
```
**Output:** `kotlin.Unit`

**Q58.**
```kotlin
val l = mutableListOf(1, 2, 3, 4)
l.removeAll { it % 2 == 0 }
println(l)
```
**Output:** `[1, 3]`

**Q59.**
```kotlin
val l = mutableListOf(1, 2, 3)
l.remove(2)
println(l)
```
**Output:** `[1, 3]`

**Q60.**
```kotlin
val l = mutableListOf(1, 2, 3)
l.removeAt(2)
println(l)
```
**Output:** `[1, 2]`

**Q61.**
```kotlin
println(listOf(1, 2) == mutableListOf(1, 2))
```
**Output:** `true`

**Q62.**
```kotlin
println(intArrayOf(1, 2) == intArrayOf(1, 2))
println(intArrayOf(1, 2).contentEquals(intArrayOf(1, 2)))
```
**Output:**
```
false
true
```

**Q63.**
```kotlin
println("banana".groupingBy { it }.eachCount())
```
**Output:** `{b=1, a=3, n=2}`

**Q64.**
```kotlin
println("hello".toList().distinct().size)
```
**Output:** `4`

**Q65.**
```kotlin
(1..5).asSequence()
    .map { print("m$it "); it * 2 }
    .filter { print("f$it "); it > 4 }
    .first()
    .let { println(it) }
```
**Output:** `m1 f2 m2 f4 m3 f6 6`

**Q66.**
```kotlin
listOf(1, 2, 3)
    .map { print("m$it "); it }
    .filter { print("f$it "); true }
```
**Output:** `m1 m2 m3 f1 f2 f3 `

**Q67.**
```kotlin
println((1..5).reduce { a, b -> a * b })
```
**Output:** `120`

**Q68.**
```kotlin
println(listOf(1, 2, 3, 4, 5).sumOf { if (it % 2 == 0) it else 0 })
```
**Output:** `6`

**Q69.**
```kotlin
println(listOf("b", "a", "c").sorted().reversed())
```
**Output:** `[c, b, a]`

**Q70.**
```kotlin
println(listOf(1, 2, 3).any { it > 2 } && emptyList<Int>().none())
```
**Output:** `true`
