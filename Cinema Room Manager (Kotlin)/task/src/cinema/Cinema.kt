package cinema

const val SMALL_CINEMA = 60
const val STANDARD_PRICE = 10
const val DISCOUNT_PRICE = 8
const val SEAT = "S"
const val PURCHASED_SEAT = "B"

fun main() {
    println("Enter the number of rows:")
    val numOfRows = readln().toInt()
    println("Enter the number of seats in each row:")
    val numOfSeatsPerRow = readln().toInt()

    var auditorium = composeAuditorium(numOfRows, numOfSeatsPerRow)
    val totalSeats = numOfRows * numOfSeatsPerRow

    var command: Int
    do  {
        println(
            """
            1. Show the seats
            2. Buy a ticket
            3. Statistics
            0. Exit
        """.trimIndent()
        )
        command = readln().toInt()
        when (command) {
            1 -> printAuditorium(auditorium)
            2 -> {
                var rowNumber: Int
                var seatNumber: Int
                do {
                    println("Enter a row number:")
                    rowNumber = readln().toInt()
                    println("Enter a seat number in that row:")
                    seatNumber = readln().toInt()

                    val validInput = rowNumber in 1..numOfRows && seatNumber in 1..numOfSeatsPerRow
                    val seatAvailable = validInput && checkSeatAvailability(auditorium, rowNumber, seatNumber)

                    when {
                        !validInput -> println("Wrong input!")
                        !seatAvailable -> println("That ticket has already been purchased!")
                    }
                } while (!validInput || !seatAvailable)

                println("Ticket price: $${calculateTicketPrice(numOfRows, numOfSeatsPerRow, rowNumber)}")
                auditorium = updateAuditorium(auditorium, rowNumber, seatNumber)
            }
            3 -> {
                val purchasedTickets = auditorium.sumOf { row -> row.count { it == PURCHASED_SEAT } }
                val percentage = purchasedTickets.toDouble() / totalSeats * 100
                val currentIncome = calculateCurrentIncome(auditorium)
                val totalIncome = calculateTotalIncome(numOfRows, numOfSeatsPerRow)
                println(
                    """
                    Number of purchased tickets: $purchasedTickets
                    Percentage: ${"%.2f".format(percentage)}%
                    Current income: $$currentIncome
                    Total income: $$totalIncome
                """.trimIndent()
                )
            }
            0 -> break
        }
    } while (true)
}

fun composeAuditorium(numOfRows: Int, numOfSeatsPerRow: Int): Array<Array<String>> {
    val auditorium = Array(numOfRows) { Array(numOfSeatsPerRow) { SEAT } }
    return auditorium
}

fun updateAuditorium(auditorium: Array<Array<String>>, rowNumber: Int, seatNumber: Int): Array<Array<String>> {
    auditorium[rowNumber - 1][seatNumber - 1] = PURCHASED_SEAT
    return auditorium
}

fun printAuditorium(auditorium: Array<Array<String>>) {
    println("Cinema:")
    print("  ")
    auditorium[0].indices.forEach { print("${it + 1} ") }
    println()
    auditorium.forEachIndexed { rowIndex, row ->
        print("${rowIndex + 1} ")
        row.forEachIndexed { _, seat ->
            print("$seat ")
        }
        println()
    }
}

fun checkSeatAvailability(auditorium: Array<Array<String>>, rowNumber: Int, seatNumber: Int): Boolean {
    return auditorium[rowNumber - 1][seatNumber - 1] == SEAT
}

fun calculateTicketPrice(numOfRows: Int, numOfSeatsPerRow: Int, rowNumber: Int): Int {
    return when {
        numOfRows * numOfSeatsPerRow <= SMALL_CINEMA -> STANDARD_PRICE
        rowNumber <= numOfRows / 2 -> STANDARD_PRICE
        else -> DISCOUNT_PRICE
    }
}

fun calculateCurrentIncome(auditorium: Array<Array<String>>): Int {
    var currentIncome = 0
    auditorium.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { _, seat ->
            if (seat == PURCHASED_SEAT) {
                currentIncome += calculateTicketPrice(auditorium.size, row.size, rowIndex + 1)
            }
        }
    }
    return currentIncome
}

fun calculateTotalIncome(numOfRows: Int, numOfSeatsPerRow: Int): Int {
    val totalSeats = numOfRows * numOfSeatsPerRow
    return if (totalSeats <= SMALL_CINEMA) totalSeats * STANDARD_PRICE else numOfRows / 2 * numOfSeatsPerRow * STANDARD_PRICE + (numOfRows - numOfRows / 2) * numOfSeatsPerRow * DISCOUNT_PRICE
}