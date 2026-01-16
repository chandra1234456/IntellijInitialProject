package opps

fun main(){
   Person("Rahul",12).introduce()
    val bankAccount = BankAccount()
   println("Please Select")
   println("1.Withdraw")
   println("2.Deposit")
   val choice = readlnOrNull()?.toIntOrNull()
    when (choice) {
        1 -> {
            println("Withdraw selected")
            println("Please Enter Amount")
            val withDrawAmount = readlnOrNull()?.toIntOrNull()
            bankAccount.withdraw(withDrawAmount?:0)
        }
        2 -> {
            println("Deposit selected")
            println("Please Enter Amount")
            val amount = readlnOrNull()?.toInt()
            bankAccount.deposit(amount?:0)
        }
        else -> {
            println("Invalid selection")
        }
    }

}


class Person(val name : String,val age : Int){
    fun introduce(){
        println("Person name is $name and age $age")
    }
}

class BankAccount {
    private var balanceAmount = 0

    fun deposit(amount: Int) {
        if (amount > 0) {
            balanceAmount += amount
        } else {
            println("Invalid deposit amount")
        }
        showBalanceAmount()
    }

    fun withdraw(amount: Int) {
        if (amount in 1..balanceAmount) {
            balanceAmount -= amount
        } else {
            println("Insufficient Funds or Invalid Amount")
        }
        showBalanceAmount()
    }

    fun showBalanceAmount() {
        println("Balance: $balanceAmount")
    }
}

open class Animal{
   open fun sound(){
        println("Animal Sound")
    }
}

class Dog: Animal() {
    override fun sound() {
        super.sound()
    }
}