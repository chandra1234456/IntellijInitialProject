import kotlin.random.Random

fun main() {
    println("Please Enter a number")
   // val input = readln().toInt()
    // is -Type Check Operator
    /*if (input is String){
        println("please Enter Number not String Value example 1,2,3")
    }*/
  //  primeNumber(input)
    //val itemSum = arrayListOf(1,2,3,4,5,6).productOfList()
   // println("$itemSum")
    //val itemSum = swapTheItems(arrayListOf(1,2,3,4,5,6))
    //println("$itemSum")
    println("foobar".sarcastic())
}

fun String.sarcastic():String{
  return this.asIterable().joinToString(""){
      if (Random.nextBoolean())it.uppercase() else it.lowercase()
  }
}
fun swapTheItems(list: ArrayList<Int>): ArrayList<Int> {
    val newList = ArrayList<Int>()
    for (i in list){
      println()
    }
    return newList
}

fun List<Int>.productOfList(): Int {
    var product = 1
    for (i in this) {
        product *= i
    }
    return product
}


fun primeNumber(input:Int){
    val isPrimeNumber = input.isPrime()
    if (input != null) {
        if (isPrimeNumber) {
            println("$input is Prime Number")
        } else {
            println("$input is Not Prime Number")
        }
    } else {
        println("Please Enter a Number")
    }
}

fun Int.isPrime(): Boolean {
    for (i in 2 until this - 1) {
        if (this % 2 == 0) {
            return false
        }
    }
    return true
}
