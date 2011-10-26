package net.zensoft.games.sudoku;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a Sudoku game
 * 
 * @author Agustín Ramos
 *
 */
public class Sudoku {

  private static final int SUBMATRIX_SIZE = 9;

  Integer[][] board;
  
  /**
   * Initializes the board. 
   * Cells can have any value in the set (-1, 1, 2..9)
   * @param board
   */
  public Sudoku(Integer[][] board) {
    this.board = board.clone();
  }
  
  boolean isValidValueForRow(int n, int row) {
    for (int col = 0; col < board.length; col++) {
      if (board[row][col] == n) {
        return false;
      }
    }
    return true;
  }

  boolean isValidValueForColumn(int n, int col) {
    for (int row = 0; row < board.length; row++) {
      if (board[row][col] == n) {
        return false;
      }
    }
    return true;
  }

  Integer[] submatrixValuesFor(int row, int col) {
    Integer[] values = new Integer[SUBMATRIX_SIZE];
    int pRow = (row/3)*3;
    int pCol = (col/3)*3;
    
    for (int r = pRow, i = 0; r < (pRow+3); r++) {
      for (int c = pCol; c < (pCol+3); c++) {
        values[i++] = board[r][c];
      }
    }
    return values;
  }
  
  public boolean isValidValue(CellValue cell) {
    return isValidValue(cell.value).forPosition(cell.row, cell.col);
  }
  
  public ValueValidationFactory isValidValue(int n) {
    return new ValueValidationFactory(n);
  }
  
  class ValueValidationFactory {
    int n;
    ValueValidationFactory(int n) { this.n = n; }
    
    boolean forPosition(int row, int col) {
      return forRow(row) &&
             forColumn(col) &&
             forSubMatrix(submatrixValuesFor(row, col));  
    }
    
    boolean forRow(int row) {
      return isValidValueForRow(n, row);
    }
    
    boolean forColumn(int col) {
      return isValidValueForColumn(n, col);
    }
    
    boolean forSubMatrix(Integer[] values) {
      return ! Arrays.asList(values).contains(n);
    }
  }

  boolean isEmpty(int row, int col) {
    return board[row][col] == -1;
  }

  public void setValue(CellValue cell) {
    setValue (cell.row, cell.col, cell.value);
  }
  
  void setValue(int row, int col, int value) {
    board[row][col] = value;
  }
  
  public void clear(int row, int col) {
    board[row][col] = -1;
  }

  public int getRows() {
    return board.length;
  }

  public int getColumns() {
    return board[0].length;
  } 

  public Integer[][] asIntArray() {
    return board.clone();
  }
  
  public List<CellValue> asCellValueList() {
    List<CellValue> cells = new ArrayList<CellValue>(board.length^2);
    for (int row = 0; row < getRows(); row++)
      for (int col = 0; col < getColumns(); col++)
          cells.add(new CellValue(row, col, board[row][col]));
    return cells;
  }

  public void print(OutputStream out) {
    PrintStream printer = new PrintStream(out);
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board.length; col++) {
        printer.print(board[row][col] + ",\t");
      }
      printer.println("");
    }
  }

}
