package geeksforgeeks


fun main() {
    println("Hello Kotlin")
    //printLoop()
    //printAllEven()
    //sumOf()
    //  reverseNum(1234)
    //val numbers = listOf(10, 45, 2, 78, 23)
    //findLargest(numbers)
   // println(numbers.maxOrNull())
    countFrequency(listOf(1, 2, 2, 3, 3, 3))
    countFrequencyMap(listOf(1, 2, 2, 3, 3, 3))
    val result = listOf(1, 1,2, 2, 3, 3, 3).groupingBy { it }.eachCount()
    println(result)
    firstNonRepeatingElement(listOf(4, 5, 1, 2, 1, 2, 4))
}
fun firstNonRepeatingElement(list: List<Int>) {
      for(i in list){
          for (j in i+1..list.lastIndex){
              if (list[i]==list[j]){

              }
          }
      }
}
fun countFrequency(list: List<Int>) {
    for (i in list.indices) {
        var count = 1
        var alreadyCounted = false
        // Check if already processed
        for (k in 0 until i) {
            if (list[k] == list[i]) {
                alreadyCounted = true
                break
            }
        }
        if (alreadyCounted) continue
        for (j in i + 1..list.lastIndex) {
            if (list[i] == list[j]) {
                count++
            }
        }

        println("${list[i]} -> $count")
    }
}
fun countFrequencyMap(list: List<Int>) {
    val map = mutableMapOf<Int, Int>()

    for (num in list) {
        map[num] = (map[num] ?: 0) + 1
    }

    println(map)
}

fun findLargest(numbers: List<Int>) {
    if (numbers.isEmpty()) {
        println("List is empty")
        return
    }
    var largest = numbers[0]

    for (num in numbers) {
        if (num > largest) {
            largest = num
        }
    }
    println(largest)
}
fun findLargestLoops(numbers: List<Int>) {
    if (numbers.isEmpty()) {
        println("List is empty")
        return
    }
    var largest = numbers[0]

    for (i in numbers.indices) {
        for (j in i + 1..numbers.lastIndex) {
            if (numbers[j] > largest) {
                largest = numbers[j]
            }
        }
    }

    println(largest)
}
fun printLoop() {
    for (i in 1..11) {
        print("$i ")
    }
}

/*fun printAllEven() {
    for (i in 2..20 step 2) {
        print("$i ")
    }
}*/
fun printAllEven() {
    for (i in 1..20) {
        if (i % 2 == 0) {
            print("$i ")
        }
    }
}

//“Can you do this without a loop?”
//val sum = 100 * 101 / 2
fun sumOf() {
    var totalSum = 0
    for (i in 1..200) {
        totalSum += i
    }
    println(totalSum)
}

//Leading zeros are not stored in integers, so:
//1000 → reversed becomes 0001
//But 0001 is just 1 as an Int
fun reverseNum(i: Int) {
    var number = 1000
    var reverseValue = 0
    while (number != 0) {
        val digit = number % 10
        reverseValue = reverseValue * 10 + digit
        number /= 10
    }
    println(reverseValue)
}
fun reverseNumber(x: Int): Int {
    var num = x
    var reversed = 0

    while (num != 0) {
        val digit = num % 10
        num /= 10

        // Check for overflow before multiplying
        if (reversed > Int.MAX_VALUE / 10 || reversed < Int.MIN_VALUE / 10) {
            return 0
        }

        reversed = reversed * 10 + digit
    }

    return reversed
}

