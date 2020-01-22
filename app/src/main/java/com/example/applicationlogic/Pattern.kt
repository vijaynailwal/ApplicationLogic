package com.example.applicationlogic

//swap varaible without using third varaible
fun main(args: Array<String>) {
//    swapWithoutThirdVariable()
//    pattern()
//    factorial()
    val numbers = listOf<Int>(2, 4, 7, 3, 6, 9, 5, 1, 0)
    println(numbers)
    val ordered = quicksort(numbers)
    println(ordered)
}

fun swapWithoutThirdVariable() {
    var first = 1
    var second = 2
    first = first - second
    second = first + second
    first = second - first
    println("First number = $first")
    println("Second number = $second")
}

fun pattern() {
    val rows = 5
    for (i in 1..rows) {
        for (j in i..rows) {
            print("* ")
        }
        println()
    }

}

fun factorial() {
    var number = 5
    var factorial = 1
    for (i in 1..number) {
        factorial = factorial * i
    }
    println(factorial)
}

fun quicksort(items: List<Int>): List<Int> {
    if (items.count() < 2) {
        return items
    }
    val pivot = items[items.count() / 2]
    val equal = items.filter { it == pivot }
    println("pivot value is : " + equal)
    val less = items.filter { it < pivot }
    println("Lesser values than pivot : " + less)
    val greater = items.filter { it > pivot }
    println("Greater values than pivot : " + greater)

    return quicksort(less) + equal + quicksort(greater)
}
