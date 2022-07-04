class Board(initialPositions: Map<Char, List<String>> = mapOf()) {
    private var turn: Int = 0
    private val boardArray = arrayOf(charArrayOf(' ', ' ', ' '), charArrayOf(' ', ' ', ' '), charArrayOf(' ', ' ', ' '))

    init {
        initialPositions.forEach { (xOr0, placements) ->
            placements.forEach { move ->
                val (row, col) = parseMove(move)
                boardArray[row][col] = xOr0
            }
        }
    }

    fun addMove(move: String) {
        val (row, col) = parseMove(move)

        if (isZerosTurn(turn)) {
            boardArray[row][col] = 'O'
        } else {
            boardArray[row][col] = 'X'
        }
        turn++
    }

    private fun isZerosTurn(turn: Int) = turn % 2 == 0

    fun isOccupied(move: String): Boolean {
        val (row, col) = parseMove(move)
        val square = boardArray[row][col]
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
        var xCount = 0
        var oCount = 0

        for (row in 0..2) {
            for (col in 0..2) {
                if (boardArray[row][col] == 'X') {
                    xCount++
                } else if (boardArray[row][col] == 'O') {
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
        return false
    }

    private fun checkColumns(): Boolean {
        var xCount = 0
        var oCount = 0
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
        return false
    }

    private fun checkDiagonalUp(): Boolean {
        var xCount = 0
        var oCount = 0
        var col = 2
        for (row in 0..2) {
            if (boardArray[row][col] == 'X') {
                xCount++
            } else if (boardArray[row][col] == 'O') {
                oCount++
            }
            col--
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

    private fun checkDiagonalDown(): Boolean {
        var xCount = 0
        var oCount = 0
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
        return false
    }

    fun print() {
        println("  1   2   3")
        letters.forEachIndexed { i, letter ->
            println("$letter " + boardArray[i].joinToString(" | ") { "$it" })
        }
    }

}