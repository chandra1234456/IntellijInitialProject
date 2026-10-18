package NewJourney

fun main(){
    /*println(findLargestNumber(arrayListOf(10, 25, 3, 50, 18)))

    println(findSmallestNumber(arrayListOf(25, 10, 45, 3, 18, 7)))

    println(countEvenNumbers(arrayListOf(10, 3, 8, 7, 12, 5)))

    println(calculateSum(arrayListOf(10, 20, 30, 40)))*/

   // println(countNegativeNumbers(arrayListOf(-5, 10, -2, 8, -7, 15)))

    //println(sumEvenNumbers(arrayListOf(10, 3, 8, 7, 12, 5)))

    println(countGreaterThanTenInList(
        arrayListOf(5, 15, 20, 3, 10, 25, 7,20,30)
    ))

    //println(findLargestEvenNumber(arrayListOf(10, 25, 8, 17, 30, 12, 7)))
    println(calculateAverage(arrayListOf(10, 20, 30, 40, 50)))
    println(calculateAverage(arrayListOf(10, 20)))
    println(countOddNumbers(arrayListOf(2, 5, 7, 8, 11, 14)))
    println(findLargestPositiveNumber(arrayListOf(-10, 5, 20,40, -3, 15, 0)))
    println(getEvenNumbers(arrayListOf(3, 8, 11, 14, 5, 20)))
    println("Smallest ${findTheSmallestNumber(listOf(12, 5, 18, 2, 9))}")
    println("Smallest ${findTheSmallestNumber(listOf(-5, 3, -10, 2))}")
    println("Smallest ${findTheSmallestNumber(listOf(-10, -3, -7))}")
    println("Smallest ${findTheSmallestNumber(listOf(0, -2, 3))}")
    println("Smallest ${findTheSmallestNumber(listOf(5, 0, 3))}")
}
fun findTheSmallestNumber(list: List<Int>): Int {
    require(list.isNotEmpty()) { "The list cannot be empty" }
    var smallestNumber = list[0]
    for (i in 1 until list.size) {
        val current = list[i]
        if (current < smallestNumber) {
            smallestNumber = current
        }
    }

    println("Final smallestNumber = $smallestNumber")
    return smallestNumber
}
/*
fun findTheSmallestNumber(list: List<Int>): Int {
    var smallestNumber = list[0]
    for (i in 1 until list.size) {
        if (list[i]<0){
            if (smallestNumber>list[i]) {
                smallestNumber = list[i]
            }
        }else{
          if (list[i]>=0){
              if (smallestNumber>list[i]){
                  smallestNumber = list[i]
              }
          }
        }
    }

    println("Final smallestNumber = $smallestNumber")
    return smallestNumber
}
*/
fun addAnElement(list : MutableList<Int>): MutableList<Int>{
    list.add(40)
    return list
}
fun countEvenNumbers(list : MutableList<Int>):Int{
    var countEven = 0
    for(i in list.indices){
        if(list[i]%2==0){
            countEven++
        }
    }
    return countEven
}
fun getEvenNumbers(list: ArrayList<Int>): ArrayList<Int>{
    val evenNumbers = ArrayList<Int>()
      for(i in list.indices){
          if (list[i]%2==0){
              evenNumbers.add(list[i])
          }
      }
    return evenNumbers
}
fun findLargestPositiveNumber(list: ArrayList<Int>): Int{
    var largest = 0
        for (i in list.indices) {
            if (list[i] > 0 && list[i] > largest) {
                largest = list[i]
            }
        }

    return largest
}
fun countOddNumbers(list: ArrayList<Int>): Int{
     var oddNumCount = 0
     for (i in list.indices){
         if(list[i]%2!=0){
             oddNumCount++
         }
     }
    return oddNumCount
}
fun countGreaterThanTenInList(list: ArrayList<Int>):Int{
    var count = 0
      for(i in list.indices){
          if (list[i]>10){
            count++
          }
      }
    return count
}
fun calculateAverage(list : ArrayList<Int>): Double{
    var totalSum = 0
    for (i in list.indices){
        totalSum+=list[i]
    }
    return totalSum.toDouble() / list.size
}
fun findLargestEvenNumber(list: ArrayList<Int>): Int {
    var largestEven = 0

    for (num in list) {
        if (num % 2 == 0 && num > largestEven) {
            largestEven = num
        }
    }

    return largestEven
}

fun sumEvenNumbers(list: ArrayList<Int>):Int{
    var sumOfEvenNumbers = 0
    for (i in list.indices){
        if (list[i]%2==0){
            sumOfEvenNumbers+=list[i]
        }
    }
    return sumOfEvenNumbers
}

fun countNegativeNumbers(list: ArrayList<Int>):Int{
    var negativeCount = 0
    for (i in list.indices){
        if (0>list[i]){
            negativeCount++
        }
    }
    return negativeCount
}

fun calculateSum(list : ArrayList<Int>): Int{
    var totalSum = 0
    for(i in list.indices){
        totalSum += list[i]
    }
    return totalSum
}
fun countEvenNumbers(list: ArrayList<Int>):Int{
    var countEven = 0
    for (i in list.indices){
        if (list[i]%2==0){
            countEven++
        }
    }
    return countEven
}

fun findSmallestNumber(list: ArrayList<Int>): Int{
    var assumeSmallestValue = list[0]
    for(i in list.indices){
            if (assumeSmallestValue>list[i]){
                assumeSmallestValue = list[i]

        }
    }
    return assumeSmallestValue
}

fun findLargestNumber(list : ArrayList<Int>):Int{
    var assumeLargestValue = list[0]
    for (i in list.indices){
            if (assumeLargestValue<list[i]){
                assumeLargestValue = list[i]
            }

    }
    return assumeLargestValue
}
