package geeksforgeeks

import java.util.Collections

//https://www.geeksforgeeks.org/dsa/logic-building-problems/

fun main(){
    multipleOfTable(6,10)
    println("${swapToNumbers(1,2)}")
    println(closestNumber(-15,6))
}

fun multipleOfTable(value: Int, tillValue: Int) {
    for (i in 1 ..tillValue){
     //   println("$value x $i = ${value*i}")
    }
}

fun swapToNumbers(a:Int,b:Int):ArrayList<Int>{
    val list = ArrayList<Int>()
    list.add(a)
    list.add(b)
    Collections.swap(list,0,1)
    return list
}

fun closestNumber(n: Int, m: Int): Int {
    if (m == 0) return n   // avoid division by zero

    val lower = (n / m) * m
    val upper = if (n % m == 0) lower else lower + m

    return if (n - lower < upper - n) lower else upper
}

