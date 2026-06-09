package opps

import javax.swing.text.Position

open class Car {
    open fun driving() {
        println("Car is driving")
    }
}

class Men : Car() {
    override fun driving() {
        super.driving()
        println("Men Driving")
    }

}

fun main() {
    val men = Men()
   // men.driving()
    /*println(bubbleSortDescending(arrayListOf(1,2,10,202,100,100)))
    println(findMaxElement(arrayListOf(1,2,10,202,100,100)))
    println(findMaxKElement(arrayListOf(1,2,99,10,202,100,150),2))
   */ //reverseArray(arrayOf(1,2,3,4,6,7,8,9,10,5))
    twoSumArray(arrayListOf(1,2,3,4,6,7,8,9,10,5),10)
}

fun bubbleSortDescending(array: MutableList<Int>): MutableList<Int> {
    for (i in array.indices) {
        for (j in 0 until array.size - i - 1) {
            if (array[j] < array[j + 1]) {
                val temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp
            }
        }
    }
    return array
}
fun findMaxElement(array: MutableList<Int>): Int {
    for (i in array.indices) {
        for (j in 0 until array.size - i - 1) {
            if (array[j] < array[j + 1]) {
                val temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp
            }
        }
    }
    println("Sorted List $array")
    return array[0]
}
fun findMaxKElement(array: MutableList<Int>,position: Int): Int {
    for (i in array.indices) {
        for (j in 0 until array.size - i - 1) {
            if (array[j] < array[j + 1]) {
                val temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp
            }
        }
    }
    println("findMaxKElement Sorted List $array")
    return array[position]
}

fun reverseArray(array : Array<Int>){
    val reversedArray = mutableListOf<Int>()
    for(i in array.size-1 downTo 0){
        reversedArray.add(array[i])
    }
    println(reversedArray)
}

fun twoSumArray(list :List<Int>,targetValue :Int){
    for(i in list.indices){
        for (j in i+1 until list.size){
            if (list[i]+list[j] == targetValue){
               println("positions $i $j values ${list[i]} ${list[j]}" )
                return
            }
        }
    }
}
