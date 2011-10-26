package net.zensoft.games.sudoku;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestSudoku {

  // sub-matrices
  // | A1 | A2 | A3 |
  // | B1 | B2 | B3 |    
  // | C1 | C2 | C3 |
  
  Integer board[][] = {
      { 9,  8,  5,  6,  3, -1,  7,  1,  4},
      { 1,  3,  2,  4,  8,  7,  6,  9,  5},
      {-1, -1,  7,  9,  5,  1,  3,  8,  2},
      { 3,  7,  6, -1,  4,  5,  1,  2,  9},
      { 8,  4,  9,  2,  1, -1,  5,  7,  3},
      { 2,  5,  1,  3,  7,  9,  8,  4,  6},
      { 7,  9,  8,  5,  6,  4,  2,  3,  1},
      { 5,  2,  3,  1,  9, -1,  4,  6,  7},
      { 6,  1,  4,  7,  2,  3,  9,  5,  8}};
  
  @Test
  public void isValidValueForRow()
  { 
    Sudoku sudoku = new Sudoku(board);
    
    int[][] tuples = new int[][] {
        // {value, col}
        // Valid values for 3rd row
        {4, 2},
        {6, 2},
    };
    
    for (int[] tuple : tuples) {
      assertTrue(
          "Failed value " + tuple[0] + " for row " + tuple[1] + ")",
          sudoku.isValidValueForRow(tuple[0], tuple[1]));      
    } 
  }
  
  @Test
  public void isNotValidValueForRow() {
    
    Sudoku sudoku = new Sudoku(board);
    
    int[][] tuples = new int[][] {
        // {value, col}
        // Invalid values for 3rd row
        {1, 2},
        {2, 2},
        {3, 2},
        {5, 2},
        {7, 2},
        {8, 2},
        {9, 2},
    };
    
    for (int[] tuple : tuples) {
      assertFalse(
          "Passed value " + tuple[0] + " for row " + tuple[1] + ")",
          sudoku.isValidValueForRow(tuple[0], tuple[1]));      
    } 
  }
  
  @Test
  public void isValidValueForColumn()
  {
    Sudoku sudoku = new Sudoku(board);
    
    int[][] tuples = new int[][] {
        // {value, col}
        // Valid values for 1st column
        {4, 0},
        // Valid values for 6th column
        {2, 5},
        {6, 5},
        {8, 5}
    };
    
    for (int[] tuple : tuples) {
      assertTrue(
          "Failed value " + tuple[0] + " for column " + tuple[1] + ")",
          sudoku.isValidValueForColumn(tuple[0], tuple[1]));      
    }
  }
  
  @Test
  public void isNotValidValueForColumn()
  {
    Sudoku sudoku = new Sudoku(board);
    
    int[][] tuples = new int[][] {
        // {value, col}
        // Invalid values for 1st column
        {1, 0},
        {2, 0},
        {3, 0},
        {5, 0},
        {6, 0},
        {7, 0},
        {8, 0},
        {9, 0},
        // Invalid values for 6th column
        {1, 5},
        {3, 5},
        {4, 5},
        {5, 5},
        {7, 5},
        {9, 5},
    };
    
    for (int[] tuple : tuples) {
      assertFalse(
          "Passed value " + tuple[0] + " for column " + tuple[1] + ")",    
          sudoku.isValidValueForColumn(tuple[0], tuple[1]));
    }
  }
  
  @Test
  public void isValidValueForSubmatrix()
  {
    Sudoku sudoku = new Sudoku(board);
    
    int[][] tuples = new int[][] {
        // {value, row, col}
        // Valid values for A1 (2,0)
        {4, 2, 0},
        {6, 2, 0},
        // Valid values for A1 (2,1) 
        {4, 2, 1},
        {6, 2, 1}
    };
    
    for (int[] tuple : tuples) {
      assertTrue(
          "Failed value " + tuple[0] + " for cell (" + tuple[1] + "," + tuple[2] + ")",
          sudoku.isValidValue(tuple[0]).forSubMatrix(sudoku.submatrixValuesFor(tuple[1], tuple[2])));    
    } 
  }
  
  @Test
  public void isNotValidValueForSubMatrix() {

    Sudoku sudoku = new Sudoku(board);

    int[][] tuples = {
        // {value, row, col}
        // Invalid values for A1 (2,0)
        {1, 2, 0},
        {2, 2, 0},
        {3, 2, 0},
        {5, 2, 0},
        {7, 2, 0},
        {8, 2, 0},
        {9, 2, 0},
        // Invalid values for A1 (2,1)
        {1, 2, 1},
        {2, 2, 1},
        {3, 2, 1},
        {5, 2, 1},
        {7, 2, 1},
        {8, 2, 1},
        {9, 2, 1},
    };
    
    for (int[] tuple : tuples) {
      assertFalse(
          "Passed value " + tuple[0] + " for cell (" + tuple[1] + "," + tuple[2] + ")",
          sudoku.isValidValue(tuple[0]).forSubMatrix( sudoku.submatrixValuesFor(tuple[1], tuple[2])));    
    }
  }
  
  @Test
  public void submatrixValues()
  {
    Sudoku sudoku = new Sudoku(board);
    Integer[] val = sudoku.submatrixValuesFor(2, 0);
    assertEquals(9, val.length);
    
    List<Integer> values = Arrays.asList(val);
    assertEquals(9, values.size());
    
    assertTrue(values.contains(9));
    assertTrue(values.contains(8));
    assertTrue(values.contains(5));
    assertTrue(values.contains(1));
    assertTrue(values.contains(3));
    assertTrue(values.contains(2));
    assertTrue(values.contains(7));
  }
  
}
