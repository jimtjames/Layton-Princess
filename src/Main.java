import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        // 0 = empty, 1 = green, 2 = blue, 3 = purple, 4 = princess
        PiecePart[][] board = new PiecePart[4][5];

        Piece blue1 = new Piece(2);
        board[0][0] = new PiecePart(0, 0, 2);
        board[0][1] = new PiecePart(0, 1, 2);
        blue1.pieces = new PiecePart[]{board[0][0], board[0][1]};

        Piece blue2 = new Piece(2);
        board[0][2] = new PiecePart(0, 2, 2);
        board[0][3] = new PiecePart(0, 3, 2);
        blue2.pieces = new PiecePart[]{board[0][2], board[0][3]};

        Piece blue3 = new Piece(2);
        board[3][0] = new PiecePart(3, 0, 2);
        board[3][1] = new PiecePart(3, 1, 2);
        blue3.pieces = new PiecePart[]{board[3][0], board[3][1]};

        Piece blue4 = new Piece(2);
        board[3][2] = new PiecePart(3, 2, 2);
        board[3][3] = new PiecePart(3, 3, 2);
        blue4.pieces = new PiecePart[]{board[3][2], board[3][3]};

        Piece red = new Piece(4);
        board[1][0] = new PiecePart(1, 0, 4);
        board[1][1] = new PiecePart(1, 1, 4);
        board[2][0] = new PiecePart(2, 0, 4);
        board[2][1] = new PiecePart(2, 1, 4);
        red.pieces = new PiecePart[]{board[1][0], board[1][1], board[2][0], board[2][1]};

        Piece purple = new Piece(3);
        board[1][2] = new PiecePart(1, 2, 3);
        board[2][2] = new PiecePart(2, 2, 3);
        purple.pieces = new PiecePart[]{board[1][2], board[2][2]};

        Piece green1 = new Piece(1);
        board[0][4] = new PiecePart(0, 4, 1);
        green1.pieces = new PiecePart[]{board[0][4]};

        Piece green2 = new Piece(1);
        board[1][3] = new PiecePart(1, 3, 1);
        green2.pieces = new PiecePart[]{board[1][3]};

        Piece green3 = new Piece(1);
        board[2][3] = new PiecePart(2, 3, 1);
        green3.pieces = new PiecePart[]{board[2][3]};

        Piece green4 = new Piece(1);
        board[3][4] = new PiecePart(3, 4, 1);
        green4.pieces = new PiecePart[]{board[3][4]};

        Piece[] pieceArr = new Piece[]{green1, green2, green3, green4, blue1, blue2, blue3, blue4, purple, red};
        LinkedList<StringBuilder> paths = new LinkedList<>();
        //LinkedList<PiecePart[][]> queue = new LinkedList<>();
        //LinkedList<Piece[]> piecesQueue = new LinkedList<>();
        //queue.addLast(board);
        paths.addLast(new StringBuilder(""));
        //piecesQueue.addLast(pieceArr);
        long count = 0;
        System.out.println("Searching");

        while (!paths.isEmpty()) {
            //PiecePart[][] state = queue.removeFirst();
            StringBuilder path = paths.removeFirst();
            //Piece[] pieces = piecesQueue.removeFirst();
            //printBoard(state);
            if (count++ % 10000 == 0) {
                //System.out.print(".");
                System.out.println(path.length() / 2);
            }
            if (path.length() > 10) {
                continue;
            }
            for (int i = 0; i < pieceArr.length; i++) {
                for (int m = 0; m < 4; m ++) {
                    StringBuilder new_path;
                    if (m == 0 ) {
                        new_path = new StringBuilder(path.toString());
                        new_path.append(i+"U");
                    } else if (m == 1) {
                        new_path = new StringBuilder(path.toString());
                        new_path.append(i+"D");
                    } else if (m == 2) {
                        new_path = new StringBuilder(path.toString());
                        new_path.append(i+"R");
                    } else{
                        new_path = new StringBuilder(path.toString());
                        new_path.append(i+"L");
                    }
                    //if the new directions don't contain any cycles or invalid moves, we can continue working
                    if (check_cycle_or_invalid(new_path, board, pieceArr)) {
                        if (pieceArr[i].identity == 4) {
                            if (pieceArr[i].pieces[0].i == 1 && pieceArr[i].pieces[0].j == 3) {
                                System.out.println(new_path);
                                printBoard(execute_moves(new_path,board,pieceArr));
                                return;
                            }
                        }
                        //printBoard(execute_moves(new_path,board,pieceArr));
                        paths.addLast(new_path);
                    }
                }
            }
        }
        System.out.println("Finished, found no solution...");
    }

    public static void printBoard(PiecePart[][] board) {
        for (int i = 0; i < 4; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println();
    }


    public static PiecePart[][] boardCopy(PiecePart[][] input) throws CloneNotSupportedException {
        PiecePart[][] boardCopy = new PiecePart[input.length][input[0].length];
        for (int j = 0; j < input.length; j++) {
            for (int k = 0; k < input[0].length; k++) {
                if (input[j][k] == null) {
                    continue;
                }
                boardCopy[j][k] = (PiecePart) input[j][k].clone();
            }
        }
        return boardCopy;
    }

    /**
     * Creates a deep copy of the pieces array. Requires a board to alreay be copied
     * @param pieces Pieces to copy
     * @param boardCopy board that will back the piece part array in each piece
     * @return a new piece array
     */
    public static Piece[] piecesCopy(Piece[] pieces, PiecePart[][] boardCopy) {
        Piece[] pieceCopy = new Piece[pieces.length];
        //deep copy the pieces
        for (int j = 0; j < pieceCopy.length; j++) {
            pieceCopy[j] = new Piece(pieces[j].identity);
            pieceCopy[j].pieces = new PiecePart[pieces[j].pieces.length];
            for (int k = 0; k < pieces[j].pieces.length; k++) {
                pieceCopy[j].pieces[k] = boardCopy[pieces[j].pieces[k].i][pieces[j].pieces[k].j];
            }
        }
        return pieceCopy;
    }

    public static PiecePart[][] execute_moves(StringBuilder path, PiecePart[][] board, Piece[] pieces) throws CloneNotSupportedException {
        PiecePart[][] boardCopy = boardCopy(board);
        Piece[] piecesCopy = piecesCopy(pieces, boardCopy);
        if (path == null || path.toString().equals("")) {
            return boardCopy;
        }

        for (int i = 0; i < path.length(); i+=2 ) {
            String next_move = path.substring(i, i+2);
            int piece_index = Integer.parseInt(next_move.substring(0, 1));
            char direction = next_move.charAt(1);
            boolean move_success;
            if (direction == 'U') {
                move_success = piecesCopy[piece_index].moveUp(boardCopy);
            } else if (direction == 'D') {
                move_success = piecesCopy[piece_index].moveDown(boardCopy);
            } else if (direction == 'R') {
                move_success = piecesCopy[piece_index].moveRight(boardCopy);
            } else {
                move_success = piecesCopy[piece_index].moveLeft(boardCopy);
            }
        }
        return boardCopy;
    }

    /**
     * Determines if the string representation of moves contains a cycle or is invalid
     * @param path - represents moves from starting position
     * @return false if cycle or invalid, true if no cycle and valid path
     */
    public static boolean check_cycle_or_invalid(StringBuilder path, PiecePart[][] board, Piece[] pieces) throws CloneNotSupportedException {
        if (path == null || path.toString().equals("")) {
            return true;
        }
        ArrayList<PiecePart[][]> states = new ArrayList<>();
        states.add(board);
        PiecePart[][] boardCopy = boardCopy(board);
        Piece[] piecesCopy = piecesCopy(pieces, boardCopy);
        assert(path.length() % 2 == 0);

        for (int i = 0; i < path.length(); i+=2 ) {
            String next_move = path.substring(i, i+2);
            int piece_index = Integer.parseInt(next_move.substring(0, 1));
            char direction = next_move.charAt(1);
            boolean move_success;
            if (direction == 'U') {
                move_success = piecesCopy[piece_index].moveUp(boardCopy);
            } else if (direction == 'D') {
                move_success = piecesCopy[piece_index].moveDown(boardCopy);
            } else if (direction == 'R') {
                move_success = piecesCopy[piece_index].moveRight(boardCopy);
            } else {
                move_success = piecesCopy[piece_index].moveLeft(boardCopy);
            }
            if (!move_success) {
                return false;
            }
            //check if our current board state is the same as any previous state
            for (PiecePart[][] oldstate : states) {


                if (Arrays.deepEquals(oldstate, boardCopy)) {
                    /*
                    System.out.println("old: " + j);
                    printBoard(oldstate);
                    System.out.println("new: " + i/2);
                    printBoard(boardCopy);*/
                    return false;
                }
            }
            //otherwise, no cycle detected so far, so let's continue working
            states.add(boardCopy(boardCopy));
        }

        return true;
    }

    /**
     * Just some basic functionality tests for the cycle detection
     * @param boards default board state
     * @param pieces default piece array
     */
    public static void cycle_tests(PiecePart[][] boards, Piece[] pieces) {
        try{
            StringBuilder test_path = null;
            if (check_cycle_or_invalid(test_path, boards, pieces)) {
                System.out.println("Null test passed (true)");
            } else {
                System.out.println("Null test failed (false)");
            }

        } catch (Exception e) {
            System.out.println("Null test exception:" + e);
        }

        try{
            StringBuilder test_path = new StringBuilder("");
            if (check_cycle_or_invalid(test_path, boards, pieces)) {
                System.out.println("Empty test passed (true)");
            } else {
                System.out.println("Empty test failed (false)");
            }

        } catch (Exception e) {
            System.out.println("Empty test exception:" + e);
        }

        try{
            StringBuilder test_path = new StringBuilder("0D");
            if (check_cycle_or_invalid(test_path, boards, pieces)) {
                System.out.println("Basic test passed (true)");
            } else {
                System.out.println("Basic test failed (false)");
            }

        } catch (Exception e) {
            System.out.println("Basic test exception:" + e);
        }

        try{
            StringBuilder test_path = new StringBuilder("0U");
            if (check_cycle_or_invalid(test_path, boards, pieces)) {
                System.out.println("Invalid test failed (true)");
            } else {
                System.out.println("Invalid test passed (false)");
            }

        } catch (Exception e) {
            System.out.println("Invalid test exception:" + e);
        }

        try{
            StringBuilder test_path = new StringBuilder("0D0U");
            if (check_cycle_or_invalid(test_path, boards, pieces)) {
                System.out.println("Basic cycle test failed (true)");
            } else {
                System.out.println("Basic cycle test passed (false)");
            }

        } catch (Exception e) {
            System.out.println("Basic cycle test exception:" + e);
        }

        try{
            StringBuilder test_path = new StringBuilder("0D3U1R2R0U2L1L3D0D");
            if (check_cycle_or_invalid(test_path, boards, pieces)) {
                System.out.println("Advanced cycle test failed (true)");
            } else {
                System.out.println("Advanced cycle test passed (false)");
            }

        } catch (Exception e) {
            System.out.println("Advanced cycle test exception:" + e);
        }

    }

}



