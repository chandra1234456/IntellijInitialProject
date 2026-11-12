package geeksforgeeks


fun main() {
    firstOne(arrayListOf(5, 2, 8, 1, 9, 4, 6))
    secondOne(arrayListOf(10, 20, 10, 30, 20, 40, 30, 50))
    thirdOne(arrayListOf(2, 4, 6, 7, 8, 10))
    fourOne(arrayListOf(2, 4, 6, 7, 8, 10))
    fiveOne(arrayListOf(1, 2, 3))
    sixOne(arrayListOf("apple", "ant", "banana", "ball", "cat", "car"))
    sevenOne(arrayListOf("Ram", "Ravi", "Raj", "Sam"))
    eightOne(arrayListOf(1, 2, 3, 4, 5, 6, 7, 8))
}
fun eightOne(list : ArrayList<Int>){
    val newList = list.partition {it%2==0 }//.map { it }
    println(newList)
}
fun sevenOne(list : ArrayList<String>){
    val newList = list.mapIndexed {index,value -> list[index] }//.map { it }
    println(newList)
}
fun sixOne(list : ArrayList<String>){
    val newList = list.groupBy { it.get(0) }
    println(newList)
}
fun fiveOne(list : ArrayList<Int>){
    val newList = list.flatMap{ num -> listOf(num, num * 2)}
    println(newList)
}
fun firstOne(list : ArrayList<Int>){
    val newList = list.filter { it > 4 }.map { it*2 }.sorted()
    println(newList)
}
fun secondOne(list : ArrayList<Int>){
   val newList = list.distinct().sortedDescending()
   println(newList)
}
fun thirdOne(list : ArrayList<Int>){
    val newList = list.takeWhile { it%2==0 }
    println(newList)
    val newList1 = list.filter { it%2==0 }
    println(newList1)
}
fun fourOne(list : ArrayList<Int>){
    val newList = list.dropWhile { it<7 }
    println(newList)
    val newList1 = list.filterNot  { it<7 }
    println(newList1)
}