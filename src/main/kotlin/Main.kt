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
    while(isPLaying){

        val userMove = askForMove()
        addMove(userMove, turn)
        printGameBoard()
        if(checkPlayerWon()){
            isPLaying = false
        }
        turn++
    }
}

fun checkPlayerWon(): Boolean {
    var xCount = 0
    var oCount = 0

    for (i in 0..2) {
        for (j in 0..2) {
            if (boardArray[i][j] == 'X') {
                xCount++
            } else if (boardArray[i][j] == 'O'){
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
            } else if (boardArray[i][j] == 'O'){
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
        val j = i
        if (boardArray[i][j] == 'X') {
            xCount++
        } else if (boardArray[i][j] == 'O'){
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
    for(i in 0..2){
        if(boardArray[i][j] == 'X'){
            xCount++
        }
        else if (boardArray[i][j] == 'O'){
            oCount++
        }
        j--
    }
    if(xCount == 3){
        println("X won!!!!")
        return true
    }
    else if(oCount == 3){
        println("O won!!!!")
        return  true
    }
    return false
}
private fun addMove(userMove: String?, turn: Int) {
    if (userMove != null) {
        playedMoves.add(userMove)
    }
    val row = userMove?.get(0)?.code?.minus(65)
    val col = userMove?.get(1)?.digitToInt()?.minus(1)

    if (turn % 2 != 0) {
        // X's turn
        boardArray[row!!][col!!] = 'X'
    } else {
        // O's turn
        boardArray[row!!][col!!] = 'O'
    }
}

private fun askForMove(): String? {
    println("Enter your move: ")
    val userMove = readLine()
    val validated: Boolean = validateUserMove(userMove)
    if(!validated){
        askForMove()
    }
    return userMove
}

fun validateUserMove(move: String?): Boolean {
    if(move?.length != 2){
        println("\nInvalid move, try again")
        return false
    }
    else if(!letters.contains(move[0].uppercaseChar())){
        println("\nInvalid letter")
        return false
    }
    else if(!(move[1].isDigit()) || move[1].digitToInt() < 1 || move[1].digitToInt() > 3){
        println("\nInvalid number")
        return false
    }
    else if(playedMoves.contains(move)){
        println("\nMove already played")
        return false
    }
    return true
}

fun printGameBoard(){
    println("  1   2   3")
    println("A ${boardArray[0][0]} | ${boardArray[0][1]} | ${boardArray[0][2]} ")
    println("  ---------")
    println("B ${boardArray[1][0]} | ${boardArray[1][1]} | ${boardArray[1][2]} ")
    println("  ---------")
    println("C ${boardArray[2][0]} | ${boardArray[2][1]} | ${boardArray[2][2]} ")
}