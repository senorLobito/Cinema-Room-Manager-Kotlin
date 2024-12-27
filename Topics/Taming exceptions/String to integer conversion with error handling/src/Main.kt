// Necessary import statements
import java.lang.Exception
import java.lang.NumberFormatException

// Function to convert String to Integer and calculate its length
fun stringToIntLength(input: String): Int {
    // Placeholder where the exception handling logic shall go
    // This is where your responsibility as a programmer begins
    try {
        // Convert the string to an Integer and get its length
        return input.toInt().toString().length
    } catch (e: NumberFormatException) {
        // If it fails to convert, return -1
        return -1
    }
    // Remember all exceptions derive from the Exception superclass, but we are dealing with a NumberFormatException

    // After handling the exceptions, convert the string to an Integer and get its length
    // If it fails to convert, return -1
}

// Entry point of the program
fun main(args: Array<String>) {
    val strInput = readLine().toString()

    // Call the previously declared function with `strInput` as an argument
    println(stringToIntLength(strInput))
}