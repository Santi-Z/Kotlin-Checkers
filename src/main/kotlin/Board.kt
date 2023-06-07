import Piece

class Board {

    private val emptyPiece: Piece = Piece(' ')
    private var board = Array(8) { _ -> Array(8) { _ -> emptyPiece } }
    private var selectedPiece = IntArray(2) {_ -> -1}
    private var firstPlayerTurn: Boolean = true

    fun isFirstPlayerTurn(): Boolean {
        return firstPlayerTurn
    }

    private fun addPiece(isFirstPlayer: Boolean, row: Int, column: Int) {
        val playerChar = if (isFirstPlayer) 'R' else 'W'
        val piece = Piece(playerChar)
        piece.setFirstPlayer(isFirstPlayer)
        board[row][column] = piece
    }

    fun initializeBoard() {
        fillSection(true, 5, 0)
        fillSection(false, 0, 1)
    }

    private fun fillSection(isFirstPlayer: Boolean, startIndex: Int, offset: Int) {
        for ((rowIndex: Int, row: Array<Piece>) in board.withIndex()) {
            if (rowIndex > 2) {break}
            for ((columnIndex: Int) in row.withIndex()) {
                if ((columnIndex + rowIndex) % 2 == offset) {
                    addPiece(isFirstPlayer, rowIndex + startIndex, columnIndex)
                }
            }
        }
    }

    fun flipBoard() {
        val temporaryBoard = Array(8) { _ -> Array(8) { _ -> emptyPiece } }

        for ((rowIndex: Int, row: Array<Piece>) in board.withIndex()) {
            for ((columnIndex: Int) in row.withIndex()) {
                temporaryBoard[rowIndex][columnIndex] = board[7 - rowIndex][7 - columnIndex]
            }
        }

        for ((rowIndex: Int, row: Array<Piece>) in board.withIndex()) {
            for ((columnIndex: Int) in row.withIndex()) {
                board[rowIndex][columnIndex] = temporaryBoard[rowIndex][columnIndex]
            }
        }
    }

    fun printBoard() {
        var result: String = ""

        for ((rowIndex: Int, row: Array<Piece>) in board.withIndex()) {
            for ((columnIndex: Int) in row.withIndex()) {
                if (columnIndex < 8) {
                    result = result.plus("|")
                }
                result = result.plus(board[rowIndex][columnIndex].display)
            }
            result = result.plus("|")
            result = result.plus("\n")
        }

        println(result)
    }

    fun choosePiece() {
        var improperInput = false
        println("What is the row of the piece you'd like to move? 1-8")
        val row = readln().toInt()
        println("What is the column of the piece you'd like to move? 1-8")
        val column = readln().toInt()

        val piece: Piece = board[row][column]
        if ((firstPlayerTurn && !piece.isFirstPlayer()) || (!firstPlayerTurn && piece.isFirstPlayer())) {
            println("This is not your piece")
            improperInput = true
        }

        if (!isMovable(arrayOf(row, column))) {
            println("Piece is not movable")
            improperInput = true
        }

        if (improperInput) choosePiece()
        else selectedPiece = intArrayOf(row, column)
    }

    fun specifyMotion() {
        var isMovingLeft: Boolean = false
        var isMovingBackwards: Boolean = false

        println("Which direction would you like to move the piece? L/R")
        var input = readln()
        if (input == "L") isMovingLeft = true
        else if (input == "R") isMovingLeft = false
        else println("Invalid Input. Go agane!")
        specifyMotion()

        if (board[selectedPiece[0]][selectedPiece[1]].getKing()) {
            println("Which direction would you like to move the piece? F/B")
        }


    }

    fun movePiece(isMovingLeft: Boolean, isMovingBackwards: Boolean) {
        val piece = board[selectedPiece[0]][selectedPiece[1]]
        val newColumn: Int = if (isMovingLeft) selectedPiece[1] - 1 else selectedPiece[1] + 1
        val newRow: Int = if (isMovingBackwards) selectedPiece[0] - 1 else selectedPiece[0] + 1

        board[selectedPiece[0]][selectedPiece[1]] = emptyPiece
        board[newRow][newColumn] = piece
    }

    private fun isMovable(piece: Array<Int>): Boolean {
        var row = piece[0]
        var column = piece[1]


        return true
    }
}