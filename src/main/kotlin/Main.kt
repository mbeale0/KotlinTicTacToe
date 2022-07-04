val letters = arrayOf('A', 'B', 'C')
val playedMoves = mutableListOf<String>()
val boardArray = arrayOf(charArrayOf(' ', ' ', ' '), charArrayOf(' ', ' ', ' '), charArrayOf(' ', ' ', ' '))

fun main() {
    var isPLaying = true

    // starting game board doesn't need all the extra values
    println("  1   2   3")
    println("A   |   |   ")
    println("  ---------")
    println("B   |   |   ")
    println("  ---------")
    println("C   |   |   ")

    println("\nEnter your move in the form of row and letter")
    println("Ex: B2")

    var turn = 1
    while (isPLaying) {

        askForMove()?.let { userMove -> addMove(userMove, turn) }
        printGameBoard()
        if (checkPlayerWon()) {
            isPLaying = false
        }
        turn++
    }
}

private fun checkPlayerWon(): Boolean {
    var xCount = 0
    var oCount = 0

    for (i in 0..2) {
        for (j in 0..2) {
            if (boardArray[i][j] == 'X') {
                xCount++
            } else if (boardArray[i][j] == 'O') {
                oCount++
            }
        }
        if (xCount == 3) {
            println("X won!")
            return true
        } else if (oCount == 3) {
            println("O won!")
            return true
        }
        xCount = 0
        oCount = 0
    }

    // check columns
    for (i in 0..2) {
        for (j in 0..2) {
            if (boardArray[j][i] == 'X') {
                xCount++
            } else if (boardArray[i][j] == 'O') {
                oCount++
            }
        }
        if (xCount == 3) {
            println("X won!!")
            return true
        } else if (oCount == 3) {
            println("O won!!")
            return true
        }
        xCount = 0
        oCount = 0
    }

    // check down diagonal
    for (i in 0..2) {
        if (boardArray[i][i] == 'X') {
            xCount++
        } else if (boardArray[i][i] == 'O') {
            oCount++
        }
    }
    if (xCount == 3) {
        println("X won!!!")
        return true
    } else if (oCount == 3) {
        println("O won!!!")
        return true
    }
    xCount = 0
    oCount = 0
    var j = 2
    for (i in 0..2) {
        if (boardArray[i][j] == 'X') {
            xCount++
        } else if (boardArray[i][j] == 'O') {
            oCount++
        }
        j--
    }
    if (xCount == 3) {
        println("X won!!!!")
        return true
    } else if (oCount == 3) {
        println("O won!!!!")
        return true
    }
    return false
}

fun addMove(userMove: String, turn: Int) {
    playedMoves.add(userMove)
    val row = userMove[0].code.minus(65)
    val col = userMove[1].digitToInt().minus(1)

    if (turn % 2 != 0) {
        // X's turn
        boardArray[row][col] = 'X'
    } else {
        // O's turn
        boardArray[row][col] = 'O'
    }
}

private fun askForMove(): String? {
    println("Enter your move: ")
    val userMove = readLine()
    val validated = validateUserMove(userMove)
    if (!validated) askForMove()

    return userMove
}

private fun validateUserMove(move: String?): Boolean {
    when {
        move?.length != 2 -> println("\nInvalid move, try again")
        !letters.contains(move[0].uppercaseChar()) -> println("\nInvalid letter")
        !(move[1].isDigit()) || move[1].digitToInt() < 1 || move[1].digitToInt() > 3 -> println("\nInvalid number")
        playedMoves.contains(move) -> println("\nMove already played")
        else -> return true
    }
    return false
}

private fun printGameBoard() {
    println("  1   2   3")
    println("A ${boardArray[0][0]} | ${boardArray[0][1]} | ${boardArray[0][2]} ")
    println("  ---------")
    println("B ${boardArray[1][0]} | ${boardArray[1][1]} | ${boardArray[1][2]} ")
    println("  ---------")
    println("C ${boardArray[2][0]} | ${boardArray[2][1]} | ${boardArray[2][2]} ")
}