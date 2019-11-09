package edu.postech.csed332.homework5;

import java.util.*;
import org.jetbrains.annotations.NotNull;

import edu.postech.csed332.homework5.Cell.Type;

/**
 * An even-odd Sudoku board
 */
public class Board {
    //TODO: add private member variables for Board
    ArrayList<ArrayList<Cell>> cells;
    ArrayList<ArrayList<Group>> groups;

    /**
     * Creates an even-odd Sudoku board
     *
     * @param game an initial configuration
     */
    Board(@NotNull GameInstance game) {
        cells = new ArrayList<ArrayList<Cell>>();
        groups = new ArrayList<ArrayList<Group>>();
        // groups = new Group[3][9]; // 0 for 세로. 1 for 가로. 2 for 덩어리

        for(int i = 0; i < 3; i++){
            ArrayList<Group> arr = new ArrayList<Group>();
            for (int j = 0; j < 9; j++){
                Group temp = new Group();
                arr.add(temp);
            }
            groups.add(arr);
        }


        for(int i = 0; i < 9; i++){
            ArrayList<Cell> arr = new ArrayList<>();
            
            for (int j = 0; j < 9; j++){
                Type t = game.isEven(i, j) ? Type.EVEN : Type.ODD;
                Cell c = new Cell(t);
                arr.add(c);
            }

            cells.add(arr);
        }

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                groups.get(0).get(j).addCell(cells.get(i).get(j));
                groups.get(1).get(i).addCell(cells.get(i).get(j));
                groups.get(2).get(3*(i/3) + (j/3)).addCell(cells.get(i).get(j));
            }
        }

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if (game.getNumbers(i, j).isPresent()){
                    cells.get(i).get(j).setNumber(game.getNumbers(i, j).get());
                }

            }
        }


    }

    /**
     * Returns a cell in the (i+1)-th row of (j+1)-th column, where 0 <= i, j < 9.
     *
     * @param i a row index
     * @param j a column index
     * @return a cell in the (i+1)-th row of (j+1)-th column
     */
    @NotNull
    Cell getCell(int i, int j) {
        return cells.get(i).get(j);
    }

    /**
     * Returns a group for the (i+1)-th row, where 0 <= i < 9.
     *
     * @param i a row index
     * @return a group for the (i+1)-th row
     */
    @NotNull
    Group getRowGroup(int i) {
        return groups.get(1).get(i);
    }

    /**
     * Returns a group for the (j+1)-th column, where 0 <= j < 9.
     *
     * @param j a column index
     * @return a group for the (j+1)-th column
     */
    @NotNull
    Group getColGroup(int j) {
        return groups.get(0).get(j);
    }

    /**
     * Returns a square group for the (n+1)-th row of (m+1)-th column, where 1 <= n, m <= 3
     *
     * @param n a square row index
     * @param m a square column index
     * @return a square group for the (n+1)-th row of (m+1)-th column
     */
    @NotNull
    Group getSquareGroup(int n, int m) {
        return groups.get(2).get(3*n + m);
    }
}
