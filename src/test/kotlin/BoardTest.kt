import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun notVictory(){
        val board = Board(mapOf('X' to listOf("B2")))
        assertFalse(board.checkPlayerWon())
    }

    @Test
    fun horizontalVictory(){
        val board = Board(mapOf('X' to listOf("A1", "A2", "A3")))
        assertTrue(board.checkPlayerWon())
    }

    @Test
    fun horizontalVictory2(){
        val board = Board(mapOf('X' to listOf("B1", "B2", "B3")))
        assertTrue(board.checkPlayerWon())
    }

    @Test
    fun columnVictory(){
        val board = Board(mapOf('X' to listOf("A2", "B2", "C2")))
        assertTrue(board.checkPlayerWon())
    }

    @Test
    fun columnVictory2(){
        val board = Board(mapOf('X' to listOf("A3", "B3", "C3")))
        assertTrue(board.checkPlayerWon())
    }

    @Test
    fun diagonalVictory(){
        val board = Board(mapOf('X' to listOf("A1", "B2", "C3")))
        assertTrue(board.checkPlayerWon())
    }

    @Test
    fun diagonalDownVictory(){
        val board = Board(mapOf('X' to listOf("A3", "B2", "C1")))
        assertTrue(board.checkPlayerWon())
    }
}