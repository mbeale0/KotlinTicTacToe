class Board(initialPositions: Map<Char, List<String>> = mapOf()) {
    private var turn: Int = 0
    private val boardSpots = arrayOf(charArrayOf(' ', ' ', ' '), charArrayOf(' ', ' ', ' '), charArrayOf(' ', ' ', ' '))

    init {
        initialPositions.forEach { (xOr0, placements) ->
            placements.forEach { move ->
                val (row, col) = parseMove(move)
                boardSpots[row][col] = xOr0
            }
        }
    }

    fun addMove(move: String) {
        val (row, col) = parseMove(move)

        if (isZerosTurn(turn)) {
            boardSpots[row][col] = 'O'
        } else {
            boardSpots[row][col] = 'X'
        }
        turn++
    }

    private fun isZerosTurn(turn: Int) = turn % 2 == 0

    fun isOccupied(move: String): Boolean {
        val (row, col) = parseMove(move)
        val square = boardSpots[row][col]
        return square == 'X' || square == '0'
    }

    fun parseMove(move: String): Pair<Int, Int> {
        val row = move[0].uppercaseChar().code.minus(65)
        val col = move[1].digitToInt().minus(1)
        return Pair(row, col)
    }

    fun checkPlayerWon(): Boolean {
        return checkColumns()
                || checkHorizontal()
                || checkDiagonalUp()
                || checkDiagonalDown()
    }

    private fun checkHorizontal(): Boolean {
        boardSpots.forEach { row ->
            if (row.all { it == 'X' }) {
                println("X won!")
                return true
            } else if (row.all { it == '0' }) {
                println("0 won!")
                return true
            }
        }
        return false
    }

    private fun checkColumns(): Boolean {
        boardSpots.indices.forEach { col ->
            if (boardSpots.all { row -> row[col] == 'X' }) {
                println("X won!")
                return true
            } else if (boardSpots.all { row -> row[col] == '0' }) {
                println("0 won!")
                return true
            }
        }
        return false
    }

    private fun checkDiagonalUp(): Boolean {
        val size = boardSpots.size - 1
        return when {
            boardSpots.indices.all { boardSpots[it][size - it] == 'X' } -> {
                println("X won!!!")
                return true
            }
            boardSpots.indices.all { boardSpots[it][size - it] == '0' } -> {
                println("0 won!!!")
                return true
            }
            else -> false
        }
    }

    private fun checkDiagonalDown(): Boolean {
        return when {
            boardSpots.indices.all { boardSpots[it][it] == 'X' } -> {
                println("X won!!!")
                return true
            }
            boardSpots.indices.all { boardSpots[it][it] == '0' } -> {
                println("0 won!!!")
                return true
            }
            else -> false
        }
    }

    fun print() {
        println("  1   2   3")
        letters.forEachIndexed { i, letter ->
            println("$letter " + boardSpots[i].joinToString(" | ") { "$it" })
        }
    }

}