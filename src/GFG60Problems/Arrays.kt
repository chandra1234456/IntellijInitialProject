package GFG60Problems

fun main(){
    secondLargest(arrayListOf(12, 35, 1, 10, 34, 1))
    secondLargest(arrayListOf(10, 5, 20, 8, 15))
    secondLargest(arrayListOf(5, 10, 15))
    secondLargest(arrayListOf(10, 10, 5))
    secondLargest(arrayListOf(5, 5, 5))
    secondLargest(arrayListOf(-5, -10, -2))
}


fun secondLargest(list: ArrayList<Int>) {
    var firstLargest = Int.MIN_VALUE
    var secondLargest = Int.MIN_VALUE

    for (num in list) {
        if (num > firstLargest) {
            secondLargest = firstLargest
            firstLargest = num
        } else if (num < firstLargest && num > secondLargest) {
            secondLargest = num
        }
    }

    if (secondLargest == Int.MIN_VALUE) {
        println("No second distinct largest element")
    } else {
        println("Largest $firstLargest SecondLargest $secondLargest")
    }
}
