package geeksforgeeks

import java.util.*
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.pow

//https://www.geeksforgeeks.org/dsa/logic-building-problems/

fun main() {
    multipleOfTable(6, 10)
    println("${swapToNumbers(1, 2)}")
    println(closestNumber(-15, 6))
    reverseInteger(10)
    println(isArmStrongNumber(193))
    bubbleSort()
}

fun multipleOfTable(value: Int, tillValue: Int) {
    for (i in 1..tillValue) {
        //   println("$value x $i = ${value*i}")
    }
}

fun swapToNumbers(a: Int, b: Int): ArrayList<Int> {
    val list = ArrayList<Int>()
    list.add(a)
    list.add(b)
    Collections.swap(list, 0, 1)
    return list
}

fun closestNumber(n: Int, m: Int): Int {
    if (m == 0) return n   // avoid division by zero

    val lower = (n / m) * m
    val upper = if (n % m == 0) lower else lower + m

    return if (n - lower < upper - n) lower else upper
}

fun reverseInteger(number: Int) {
    println(log10(abs(number).toDouble()).toInt() + 1)
    val stringBuilder = StringBuilder(number.toString())
    val reverseNumber = stringBuilder.reverse()
    println(reverseNumber)
}

fun isArmStrongNumber(number: Int): Boolean {
    val numberLength = log10(abs(number).toDouble()).toInt() + 1
    val number = number.toString()
    val length = number.length
    var output = 0
    for (c in number.toCharArray()) {
        output += (c.code - '0'.code).toDouble().pow(length).toInt()
    }
    return output == number.toInt()
}

fun bubbleSort() {
    val array = arrayOf(1, 4, 5, 10, 6, 2, 8, 7, 3, 9)

    println("Before Sorting: ${array.joinToString()}")

    for (i in 0 until array.lastIndex) {
        for (j in 0 until array.lastIndex - i) {

            if (array[j] > array[j + 1]) {

                println("Swapping ${array[j]} and ${array[j + 1]}")

                val temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp

                println("Current Array: ${array.joinToString()}")
                println("----------------------")
            }
        }
    }

    println("After Sorting: ${array.joinToString()}")
}

