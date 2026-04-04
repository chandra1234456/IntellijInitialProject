package geeksforgeeks

import kotlin.time.Duration.Companion.seconds

fun main() {
    println("Hello Kotlin")
    //printLoop()
    //printAllEven()
    //sumOf()
     reverseNum(1234)
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
fun sumOf(){
    var totalSum = 0
    for(i in 1..200){
      totalSum+=i
    }
    println(totalSum)
}

//Leading zeros are not stored in integers, so:
//1000 → reversed becomes 0001
//But 0001 is just 1 as an Int
fun reverseNum(i: Int) {
    var number = 1000
    var reverseValue = 0
    while (number!=0){
        val digit = number %10
         reverseValue = reverseValue*10+digit
        number/=10
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

