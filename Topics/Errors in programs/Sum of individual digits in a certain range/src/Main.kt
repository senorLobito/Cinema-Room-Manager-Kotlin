import java.util.*

//Start of Kotlin Program
fun main(args: Array<String>) {

    //Create a scan instance for readling line from standard Input
    val scan = Scanner(System.`in`)

    // Reading an integer as input
    var userInput = scan.nextLine().trim()

    //TODO: 
    //You need to Check:
    //If the user input is an integer
    //If the user input is between 1 and 9999 (inclusive) 
    //If not, print 'Invalid Input'.
    //Otherwise, calculate the sum of the individual digits of this integer.
    if (userInput.toIntOrNull() != null && userInput.toInt() in 1..9999) {
        var sum = 0
        for (i in userInput) {
            sum += i.toString().toInt()
        }
        println(sum)
    } else {
        println("Invalid Input")
    }
}
// End of Kotlin Program