package NewJourney

/*30 CODING PRACTICE QUESTIONS WITH OUTPUT (NO SOLUTIONS / NO CODE)

============================== BASIC PROGRAMMING
Print Hello World Output: Hello World
Add Two Numbers Input: 10 20 Output: Sum = 30
Check Even or Odd Input: 7 Output: Odd
Find Largest of Two Numbers Input: 15 25 Output: 25 is largest
Find Largest of Three Numbers Input: 10 50 30 Output: 50 is largest
Check Positive, Negative or Zero Input: -5 Output: Negative
Swap Two Numbers Input: A = 10 B = 20 Output: A = 20 B = 10
Find Square of a Number Input: 5 Output: Square = 25
Find Area of a Circle Input: Radius = 7 Output: Area = 153.94
Check Leap Year Input: 2024 Output: Leap Year
============================== LOOP-BASED PROGRAMS
Print Numbers from 1 to 10 Output: 1 2 3 4 5 6 7 8 9 10
Print Multiplication Table Input: 5 Output: 5 x 1 = 5 5 x 2 = 10 5 x 3 = 15 5 x 4 = 20 5 x 5 = 25 5 x 6 = 30 5 x 7 = 35 5 x 8 = 40 5 x 9 = 45 5 x 10 = 50
Find Sum of First N Natural Numbers Input: 5 Output: Sum = 15
Find Factorial of a Number Input: 5 Output: Factorial = 120
Reverse a Number Input: 1234 Output: 4321
Check Palindrome Number Input: 121 Output: Palindrome
Check Prime Number Input: 7 Output: Prime Number
Print Fibonacci Series Input: 7 Output: 0 1 1 2 3 5 8
Find Sum of Digits Input: 1234 Output: Sum = 10
Count Number of Digits Input: 12345 Output: Count = 5
============================== STRING PROGRAMS
Reverse a String Input: HELLO Output: OLLEH
Check Palindrome String Input: MADAM Output: Palindrome
Count Vowels in a String Input: PROGRAMMING Output: Vowels = 3
Count Characters in a String Input: COMPUTER Output: Length = 8
Convert Lowercase to Uppercase Input: hello Output: HELLO
============================== ARRAY PROGRAMS
Find Largest Element in an Array Input: 5 10 20 50 30 40 Output: Largest = 50
Find Smallest Element in an Array Input: 5 10 20 5 30 40 Output: Smallest = 5
Find Sum of Array Elements Input: 5 1 2 3 4 5 Output: Sum = 15
Sort an Array in Ascending Order Input: 5 5 2 8 1 3 Output: 1 2 3 5 8
Find Second Largest Element in an Array Input: 5 10 50 30 20 40 Output: Second Largest = 40
============================== END OF QUESTIONS*/


fun main(){
   println("Hello World")
    addTwoNumbers(10, 20)
    findLargestOfTwoNumbers(10, 20)
    findLargestOfThreeNumbers(70, 60,50)
    checkEvenOrOdd(7)
    checkPositiveNegativeZero(0)
    swapTwoNumber()
    findSquareOfaNumber(10)
    findAreaOfaCircleInput(7)
    findSumOfDigits(1234)
    findSumOfDigitsValue(1234)
}
fun findSumOfDigitsValue(a: Int) {
    var num = a
    var sum = 0

    while (num > 0) {
        val digit = num % 10
        sum += digit
        num /= 10
    }
    println(sum)
}

fun findSumOfDigits(a: Int){
    val numbers = a.toString().toCharArray()
    var sum = 0
    for (i in numbers.indices){
        sum+=numbers[i].digitToInt()
        println(sum)
    }
    //println(sum)
}
fun findAreaOfaCircleInput(a:Int){
    println(3.14*a*a)
}
fun findSquareOfaNumber(a:Int){
    println(a*a)
}
fun swapTwoNumber(){
    var a= 20
    var b=30
    val temp = b
    b = a
    a = temp
    println(a)
    println(b)
}
fun checkPositiveNegativeZero(a:Int){
    if (0>a){
        println("Negative")
    }else if(0<a){
        println("Positive")
    }else{
        println("Zero")
    }
}
fun findLargestOfThreeNumbers(a:Int,b:Int,c:Int){
    if (a<b) {
        if (c<b){
            println(b)
        }else{
            println(c)
        }
    }else if(a<c) {
        println(c)
    }else{
        println(a)
    }

}
fun findLargestOfTwoNumbers(a:Int,b:Int){
     if (a<b) println(b) else println(a)
}
fun checkEvenOrOdd(a:Int){
    if (a%2==0) println("Positive") else println("Odd")
}
fun addTwoNumbers(a:Int,b :Int){
    println(a+b)
}