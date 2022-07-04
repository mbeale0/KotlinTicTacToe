val letters = arrayOf('A', 'B', 'C')

fun main() {
    var isPLaying = true
    val board = Board()
    board.print()

    println("\nEnter your move in the form of row and letter")
    println("Ex: B2")

    while (isPLaying) {
        askForMove(board)?.let { userMove -> board.addMove(userMove) }
        board.print()
        if (board.checkPlayerWon()) {
            isPLaying = false
        }
    }
}

private fun askForMove(board: Board): String? {
    println("Enter your move: ")
    val userMove = readLine()
    val validated = validateUserMove(userMove, board)
    if (!validated) askForMove(board)

    return userMove
}

private fun validateUserMove(move: String?, board: Board): Boolean {
    when {
        move?.length != 2 -> println("\nInvalid move, try again")
        !(move[1].isDigit()) || move[1].digitToInt() < 1 || move[1].digitToInt() > 3 -> println("\nInvalid number")
        board.isOccupied(move) -> println("\nMove already played")
        else -> return true
    }
    return false
}

