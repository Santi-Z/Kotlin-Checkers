class Piece constructor(displayChar: Char) {

    var display: Char = displayChar
    private var isKing: Boolean = false
    private var isFirstPlayer: Boolean = false

    fun setFirstPlayer(playerSet: Boolean) {
        isFirstPlayer = playerSet
    }

    fun isFirstPlayer(): Boolean {
        return isFirstPlayer
    }

    fun setKing() { isKing = true }
    fun getKing(): Boolean {return isKing}
}