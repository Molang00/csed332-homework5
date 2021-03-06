package edu.postech.csed332.homework5;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import edu.postech.csed332.homework5.Cell.Type;

public class GameUI {
    private static final int unitSize = 10;
    private static final int width = 45;
    private static final int height = 45;

    final JFrame top;

    public GameUI(Board board) {
        top = new JFrame("Even/Odd Sudoku");
        top.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        top.setLayout(new GridLayout(3, 3, 1, 1));

        Dimension dim = new Dimension(unitSize * width, unitSize * height);
        top.setMinimumSize(dim);
        top.setPreferredSize(dim);

        createCellUI(board);

        top.pack();
        top.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Board board = new Board(GameInstanceFactory.createGameInstance());
            new GameUI(board);
        });
    }

    /**
     * Create UI for cells
     * @param board
     */
    private void createCellUI(Board board) {
        ArrayList<ArrayList<JPanel>> squares = new ArrayList<ArrayList<JPanel>>();

        for(int i = 0; i < 3; i++){
            ArrayList<JPanel> arr = new ArrayList<>();
            for (int j = 0; j < 3; j++){
                JPanel temp = new JPanel();
                arr.add(temp);
            }
            squares.add(arr);
        }

        ArrayList<ArrayList<CellUI>> cellUIs = new ArrayList<ArrayList<CellUI>>();
        
        for(int i = 0; i < 9; i++){
            ArrayList<CellUI> arr = new ArrayList<>();

            for (int j = 0; j < 9; j++){
                Cell c = board.getCell(i,j);
                CellUI temp = new CellUI(c);
                arr.add(temp);
            }

            cellUIs.add(arr);
        }

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){

                squares.get(i).get(j).setLayout(new GridLayout(3,3));

                for(int k = 0; k < 3; k++){
                    for(int l = 0; l < 3; l++){
                        squares.get(i).get(j).add(cellUIs.get((3*i)+k).get((3*j)+l));
                    }
                }

                top.add(squares.get(i).get(j));
            }
        }


        //TODO: implement this. Create cells and squares, to add them to top, and to define layouts for them.

    }

}