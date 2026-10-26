package collections

//https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/
//https://kotlinlang.org/docs/collection-operations.html#common-operations

/**
 * Beginner level (1–7)
 * 1. Filter Even Numbers
 * Write a function that accepts a list of integers and returns only the even numbers using Kotlin collection functions.
 *
 * 2. Find Odd Numbers
 * Write a function that filters all odd numbers from a list of integers.
 *
 * 3. Square Every Number
 * Write a function that transforms each number in a list into its square.
 *
 * 4. Find Numbers Greater Than a Given Value
 * Write a function that accepts a list of integers and a target value, then returns all numbers greater than that value.
 *
 * 5. Remove Duplicate Elements
 * Write a function that removes duplicate elements from a list while preserving their original order.
 *
 * 6. Find the Sum of All Numbers
 * Write a function that calculates the sum of all integers in a list using Kotlin collection operations.
 *
 * 7. Find the Maximum and Minimum
 * Write a function that finds the maximum and minimum numbers in a list without manually iterating through it.
 *
 * Intermediate level (8–14)
 * 8. Group Employees by Department
 * Create an Employee data class with name, age, and department properties. Write a function that groups employees by department.
 *
 * 9. Count Employees by Department
 * Using a list of employees, write a function that counts how many employees belong to each department.
 *
 * 10. Find the Highest-Paid Employee
 * Create an Employee data class with name, department, and salary. Write a function that finds the employee with the highest salary.
 *
 * 11. Sort Employees by Salary
 * Write a function that sorts a list of employees by salary in ascending order.
 *
 * 12. Find Employees Older Than a Given Age
 * Write a function that filters employees whose age is greater than a specified age.
 *
 * 13. Partition Numbers into Even and Odd
 * Write a function that separates a list of integers into two groups: even numbers and odd numbers.
 *
 * 14. Find Duplicate Elements
 * Write a function that identifies all elements that appear more than once in a list.
 *
 * Advanced level (15–20)
 * 15. Group Words by Their Length
 * Write a function that groups a list of strings according to the length of each word.
 *
 * 16. Find the Frequency of Each Word
 * Write a function that accepts a list of words and counts how many times each word occurs.
 *
 * 17. Find the Second-Highest Number
 * Write a function that finds the second-highest distinct number in a list of integers.
 *
 * 18. Group Products by Category
 * Create a Product data class with name, category, and price. Write a function that groups products by category.
 *
 * 19. Calculate Total Salary by Department
 * Using a list of employees, write a function that calculates the total salary paid to employees in each department.
 *
 * 20. Find the Most Expensive Product in Each Category
 * Create a Product data class with name, category, and price. Write a function that finds the most expensive product within each category.
 */
fun main() {
    val stringList = listOf("one", "two", "one")
    printAll(stringList)

    val stringSet = setOf("one", "two", "three")
    printAll(stringSet)
    groupEmployeesByTheirDepartment(list)
    countEmployeesByDepartment(list)
    findTheHighestPaidEmployee(list)
    filterEvenNumbers()
    filterOddNumbers()
    groupWordsByTheirLength()
    squareEveryNumber()
}

fun printAll(strings: Collection<String>) {
    for(s in strings) print(s)
    println()
}
data class Employee(val name :String,val department :String,val salary :Double)
 val list = arrayListOf(
     Employee("chandra0","DEV",100.00),
     Employee("chandra1","HR",40.00),
     Employee("chandra2","ADMIN",30.00),
     Employee("chandra3","HR",30.00),
     Employee("chandra4","DEV",20.00),
     Employee("chandra5","ADMIN",70.00),
     Employee("chandra6","HR", 120.00),
     Employee("chandra7","CEO", 1000.00)
 )
fun groupEmployeesByTheirDepartment(list : ArrayList<Employee>){
    val group = list.groupBy { it.department }
    println(group)
}
fun countEmployeesByDepartment(list : ArrayList<Employee>){
    val group = list.groupingBy { it.department }.eachCount()
    println(group)
}
fun findTheHighestPaidEmployee(list : ArrayList<Employee>){
    val group = list.groupBy { it.department }.mapValues { (_,deptEmployees) ->
        deptEmployees.minOf{it.salary}
    }
    println(group)
}
val strings = listOf("one","two","three","four","o")
fun groupWordsByTheirLength(){
    val words = strings.groupBy { it.length }
    println(words)
}
val numbers = listOf(1,2,3,4,5,6,7,8,9,10)
fun filterEvenNumbers(){
   val evenNumbers = numbers.filter { it%2 ==0 }
    println(evenNumbers.joinToString())
}
fun filterOddNumbers(){
    val evenNumbers = numbers.filter { it%2 !=0 }
    println(evenNumbers.joinToString())
}
fun squareEveryNumber(){
    val values = numbers.map { it*2 }
    println(values)
}