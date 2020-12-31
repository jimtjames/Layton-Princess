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
        LinkedList<PiecePart[][]> queue = new LinkedList<>();
        LinkedList<Piece[]> piecesQueue = new LinkedList<>();
        queue.addLast(board);
        paths.addLast(new StringBuilder("  "));
        piecesQueue.addLast(pieceArr);
        long count = 0;
        System.out.print("Searching");
        while (!queue.isEmpty()) {
            PiecePart[][] state = queue.removeFirst();
            StringBuilder path = paths.removeFirst();
            Piece[] pieces = piecesQueue.removeFirst();
            /*for (int i = 0; i < 4; i++) {
                System.out.println(Arrays.toString(state[i]));
            }
            System.out.println();*/
            if (count++ % 10000 == 0) {
                //System.out.print(".");
                System.out.println(path.length() / 2);
            }
            if (path.length() > 200) {
                continue;
            }
            for (int i = 0; i < pieces.length; i++) {
                for (int m = 0; m < 4; m ++) {
                    //deep copy the board
                    PiecePart[][] boardCopy = new PiecePart[4][5];
                    for (int j = 0; j < 4; j++) {
                        for (int k = 0; k < 5; k++) {
                            if (state[j][k] == null) {
                                continue;
                            }
                            boardCopy[j][k] = (PiecePart) state[j][k].clone();
                        }
                    }
                    Piece[] pieceCopy = new Piece[pieces.length];
                    //deep copy the pieces
                    for (int j = 0; j < pieceCopy.length; j++) {
                        pieceCopy[j] = new Piece(pieces[j].identity);
                        pieceCopy[j].pieces = new PiecePart[pieces[j].pieces.length];
                        for (int k = 0; k < pieces[j].pieces.length; k++) {
                            pieceCopy[j].pieces[k] = boardCopy[pieces[j].pieces[k].i][pieces[j].pieces[k].j];
                        }
                    }
                    if (m == 0 && !path.toString().substring(path.toString().length() - 2).equals(i + "D") && pieceCopy[i].moveUp(boardCopy)) {
                        //check if we moved red piece
                        if (pieceCopy[i].identity == 4) {
                            if (pieceCopy[i].pieces[0].i == 1 && pieceCopy[i].pieces[0].j == 3) {
                                StringBuilder pathCopy = new StringBuilder(path.toString());
                                pathCopy.append(i + "U");
                                System.out.println(pathCopy);
                                for (int r = 0; r < 4; r++) {
                                    System.out.println(Arrays.toString(boardCopy[r]));
                                }
                                return;
                            }
                        }
                        //add to queue
                        queue.addLast(boardCopy);
                        piecesQueue.addLast(pieceCopy);
                        //copy the path
                        StringBuilder pathCopy = new StringBuilder(path.toString());
                        pathCopy.append(i + "U");
                        paths.addLast(pathCopy);
                    } else if (m == 1 && !path.toString().substring(path.toString().length() - 2).equals(i + "U") && pieceCopy[i].moveDown(boardCopy)) {
                        //check if we moved red piece
                        if (pieceCopy[i].identity == 4) {
                            if (pieceCopy[i].pieces[0].i == 1 && pieceCopy[i].pieces[0].j == 3) {
                                StringBuilder pathCopy = new StringBuilder(path.toString());
                                pathCopy.append(i + "D");
                                System.out.println(pathCopy);
                                for (int r = 0; r < 4; r++) {
                                    System.out.println(Arrays.toString(boardCopy[r]));
                                }
                                return;
                            }
                        }
                        //add to queue
                        queue.addLast(boardCopy);
                        piecesQueue.addLast(pieceCopy);
                        //copy the path
                        StringBuilder pathCopy = new StringBuilder(path.toString());
                        pathCopy.append(i + "D");
                        paths.addLast(pathCopy);
                    } else if (m == 2 && !path.toString().substring(path.toString().length() - 2).equals(i + "L") && pieceCopy[i].moveRight(boardCopy)) {
                        //check if we moved red piece
                        if (pieceCopy[i].identity == 4) {
                            if (pieceCopy[i].pieces[0].i == 1 && pieceCopy[i].pieces[0].j == 3) {
                                StringBuilder pathCopy = new StringBuilder(path.toString());
                                pathCopy.append(i + "R");
                                System.out.println(pathCopy);
                                for (int r = 0; r < 4; r++) {
                                    System.out.println(Arrays.toString(boardCopy[r]));
                                }
                                return;
                            }
                        }
                        //add to queue
                        queue.addLast(boardCopy);
                        piecesQueue.addLast(pieceCopy);
                        //copy the path
                        StringBuilder pathCopy = new StringBuilder(path.toString());
                        pathCopy.append(i + "R");
                        paths.addLast(pathCopy);
                    } else if (m == 3 && !path.toString().substring(path.toString().length() - 2).equals(i + "R") && pieceCopy[i].moveLeft(boardCopy)) {
                        //check if we moved red piece
                        if (pieceCopy[i].identity == 4) {
                            if (pieceCopy[i].pieces[0].i == 1 && pieceCopy[i].pieces[0].j == 3) {
                                StringBuilder pathCopy = new StringBuilder(path.toString());
                                pathCopy.append(i + "L");
                                System.out.println(pathCopy);
                                for (int r = 0; r < 4; r++) {
                                    System.out.println(Arrays.toString(boardCopy[r]));
                                }
                                return;
                            }
                        }
                        //add to queue
                        queue.addLast(boardCopy);
                        piecesQueue.addLast(pieceCopy);
                        //copy the path
                        StringBuilder pathCopy = new StringBuilder(path.toString());
                        pathCopy.append(i + "L");
                        paths.addLast(pathCopy);
                    }
                }
            }
        }
        System.out.println("Finished, found no solution...");
    }
}
