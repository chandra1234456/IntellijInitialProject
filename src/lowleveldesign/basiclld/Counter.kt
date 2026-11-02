package lowleveldesign.basiclld


fun main(){
    val c = Counter()
    c.increment()
    c.increment()
    c.increment()
    c.decrement()
    println(c.getValue())
    c.reset()
    println(c.getValue())
}

data class Counter(
    private var countValue: Int
) {
    constructor() : this(0)

    fun reset() {
        countValue = 0
    }

    fun getValue(): Int {
        return countValue
    }

    fun increment(): Int {
        return ++countValue
    }

    fun decrement(): Int {
        return --countValue
    }
}