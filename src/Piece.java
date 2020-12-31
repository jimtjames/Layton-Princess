import java.util.Arrays;

public class Piece {
    PiecePart[] pieces;
    int identity;

    public Piece(int identity) {
        this.identity = identity;
    }

    /**
     * Moves this piece up
     */
    public boolean moveUp(PiecePart[][] board) {
        if (identity == 1 || identity == 3) {
            PiecePart part = pieces[0];
            //check if move is possible
            if (part.i - 1 >= 0) {
                //check for empty space
                if (board[part.i - 1][part.j] != null) {
                    return false;
                }
                for (int i = 0; i < pieces.length; i++) {
                    PiecePart curPiece = pieces[i];
                    curPiece.i--;
                    board[curPiece.i][curPiece.j] = pieces[i];
                    board[curPiece.i + 1][curPiece.j] = null;
                    pieces[i] = board[curPiece.i][curPiece.j];
                }
                return true;
            }
            return false;
        } else {
            PiecePart partL = pieces[0];
            PiecePart partR = pieces[1];

            //check if move is possible
            if (partL.i - 1 >= 0) {
                //check for empty space
                if (board[partL.i - 1][partL.j] != null || board[partR.i - 1][partR.j] != null) {
                    return false;
                }
                for (int i = 0; i < pieces.length; i++) {
                    PiecePart curPiece = pieces[i];
                    curPiece.i--;
                    board[curPiece.i][curPiece.j] = pieces[i];
                    board[curPiece.i + 1][curPiece.j] = null;
                    pieces[i] = board[curPiece.i][curPiece.j];
                }
                return true;
            }
            return false;
        }
    }
    /**
     * Moves this piece down
     */
    public boolean moveDown(PiecePart[][] board) {
        if (identity == 1 || identity == 3) {
            PiecePart part;
            if (identity == 3) {
                part = pieces[1];
            } else {
                part = pieces[0];
            }
            //check if move is possible
            if (part.i + 1 < board.length) {
                //check for empty space
                if (board[part.i + 1][part.j] != null) {
                    return false;
                }
                for (int i = pieces.length - 1; i >= 0; i--) {
                    PiecePart curPiece = pieces[i];
                    curPiece.i++;
                    board[curPiece.i][curPiece.j] = pieces[i];
                    board[curPiece.i - 1][curPiece.j] = null;
                    pieces[i] = board[curPiece.i][curPiece.j];
                }
                return true;
            }
            return false;
        } else {
            PiecePart partL;
            PiecePart partR;
            if (identity == 4) {
                partL = pieces[2];
                partR = pieces[3];
            } else {
                partL = pieces[0];
                partR = pieces[1];
            }

            //check if move is possible
            if (partL.i + 1 < board.length) {
                //check for empty space
                if (board[partL.i + 1][partL.j] != null || board[partR.i + 1][partR.j] != null) {
                    return false;
                }
                for (int i = pieces.length - 1; i >= 0; i--) {
                    PiecePart curPiece = pieces[i];
                    curPiece.i++;
                    board[curPiece.i][curPiece.j] = pieces[i];
                    board[curPiece.i - 1][curPiece.j] = null;
                    pieces[i] = board[curPiece.i][curPiece.j];
                }
                return true;
            }
            return false;
        }
    }
    /**
     * Moves this piece right
     */
    public boolean moveRight(PiecePart[][] board) {
        if (identity == 1 || identity == 2) {
            PiecePart part;
            if (identity == 1) {
                part = pieces[0];
            } else {
                part = pieces[1];
            }
            //check if move is possible
            if (part.j + 1 < board[0].length) {
                //check for empty space
                if (board[part.i][part.j + 1] != null) {
                    return false;
                }
                for (int i = pieces.length - 1; i >= 0; i--) {
                    PiecePart curPiece = pieces[i];
                    curPiece.j++;
                    board[curPiece.i][curPiece.j] = pieces[i];
                    board[curPiece.i][curPiece.j - 1] = null;
                    pieces[i] = board[curPiece.i][curPiece.j];
                }
                return true;
            }
            return false;
        } else {
            PiecePart partT;
            PiecePart partB;
            if (identity == 4) {
                partT = pieces[1];
                partB = pieces[3];
            } else {
                partT = pieces[0];
                partB = pieces[1];
            }
            //check if move is possible
            if (partT.j + 1 < board[0].length) {
                //check for empty space
                if (board[partT.i][partT.j + 1] != null || board[partB.i][partB.j + 1] != null) {
                    return false;
                }
                for (int i = pieces.length - 1; i >= 0; i--) {
                    PiecePart curPiece = pieces[i];
                    curPiece.j++;
                    board[curPiece.i][curPiece.j] = pieces[i];
                    board[curPiece.i][curPiece.j - 1] = null;
                    pieces[i] = board[curPiece.i][curPiece.j];
                }
                return true;
            }
            return false;
        }
    }
    /**
     * Moves this piece left
     */
    public boolean moveLeft(PiecePart[][] board) {
        if (identity == 1 || identity == 2) {
            PiecePart part = pieces[0];
            //check if move is possible
            if (part.j - 1 >= 0) {
                //check for empty space
                if (board[part.i][part.j - 1] != null) {
                    return false;
                }
                for (int i = 0; i < pieces.length; i++) {
                    PiecePart curPiece = pieces[i];
                    curPiece.j--;
                    board[curPiece.i][curPiece.j] = pieces[i];
                    board[curPiece.i][curPiece.j + 1] = null;
                    pieces[i] = board[curPiece.i][curPiece.j];
                }
                return true;
            }
            return false;
        } else {
            PiecePart partT;
            PiecePart partB;
            if (identity == 4) {
                partT = pieces[0];
                partB = pieces[2];
            } else {
                partT = pieces[0];
                partB = pieces[1];
            }
            //check if move is possible
            if (partT.j - 1 >= 0) {
                //check for empty space
                if (board[partT.i][partT.j - 1] != null || board[partB.i][partB.j - 1] != null) {
                    return false;
                }
                for (int i = 0; i < pieces.length; i++) {
                    PiecePart curPiece = pieces[i];
                    curPiece.j--;
                    board[curPiece.i][curPiece.j] = pieces[i];
                    board[curPiece.i][curPiece.j + 1] = null;
                    pieces[i] = board[curPiece.i][curPiece.j];
                }
                return true;
            }
            return false;
        }
    }

    public String toString() {
        return Arrays.toString(pieces);
    }
}
