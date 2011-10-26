package net.zensoft.games.sudoku;

import static org.junit.Assert.assertArrayEquals;

import net.zensoft.games.sudoku.Sudoku;
import net.zensoft.games.sudoku.SudokuSolver;

import org.junit.Test;

public class TestSudokuSolver 
{
  
  @Test
  public void simpleComplexity()
  {
    int solved[][] = {
        { 9,  8,  5,  6,  3,  2,  7,  1,  4},
        { 1,  3,  2,  4,  8,  7,  6,  9,  5},
        { 4,  6,  7,  9,  5,  1,  3,  8,  2},
        { 3,  7,  6,  8,  4,  5,  1,  2,  9},
        { 8,  4,  9,  2,  1,  6,  5,  7,  3},
        { 2,  5,  1,  3,  7,  9,  8,  4,  6},
        { 7,  9,  8,  5,  6,  4,  2,  3,  1},
        { 5,  2,  3,  1,  9,  8,  4,  6,  7},
        { 6,  1,  4,  7,  2,  3,  9,  5,  8}};

    Integer problem[][] = {
        { 9,  8,  5,  6,  3, -1,  7,  1,  4},
        { 1,  3,  2,  4,  8,  7,  6,  9,  5},
        {-1, -1,  7,  9,  5,  1,  3,  8,  2},
        { 3,  7,  6, -1,  4,  5,  1,  2,  9},
        { 8,  4,  9,  2,  1, -1,  5,  7,  3},
        { 2,  5,  1,  3,  7,  9,  8,  4,  6},
        { 7,  9,  8,  5,  6,  4,  2,  3,  1},
        { 5,  2,  3,  1,  9, -1,  4,  6,  7},
        { 6,  1,  4,  7,  2,  3,  9,  5,  8}};
    
    Sudoku sudoku = new Sudoku(problem);
    SudokuSolver solver = new SudokuSolver(sudoku);

    assertArrayEquals(solved, solver.solve().asIntArray());
  }

  @Test
  public void mediumComplexity()
  {
    Integer solved[][] = {
        { 9,  8,  5,  6,  3,  2,  7,  1,  4},
        { 1,  3,  2,  4,  8,  7,  6,  9,  5},
        { 4,  6,  7,  9,  5,  1,  3,  8,  2},
        { 3,  7,  6,  8,  4,  5,  1,  2,  9},
        { 8,  4,  9,  2,  1,  6,  5,  7,  3},
        { 2,  5,  1,  3,  7,  9,  8,  4,  6},
        { 7,  9,  8,  5,  6,  4,  2,  3,  1},
        { 5,  2,  3,  1,  9,  8,  4,  6,  7},
        { 6,  1,  4,  7,  2,  3,  9,  5,  8}};

    Integer problem[][] = {
        { 9,  8,  5,  6,  3, -1,  7,  1,  4},
        { 1,  3,  2,  4,  8,  7,  6,  9,  5},
        {-1, -1,  7,  9,  5,  1,  3,  8,  2},
        { 3,  7,  6, -1,  4,  5,  1,  2,  9},
        { 8,  4,  9,  2,  1, -1,  5,  7,  3},
        { 2,  5,  1,  3,  7,  9, -1, -1,  6},
        { 7,  9,  8,  5,  6,  4,  2,  3,  1},
        { 5,  2,  3,  1,  9, -1, -1,  6,  7},
        { 6,  1,  4,  7,  2,  3,  9,  5,  8}};
    
    Sudoku sudoku = new Sudoku(problem);
    SudokuSolver solver = new SudokuSolver(sudoku);
    assertArrayEquals(solved, solver.solve().asIntArray());
  }
  
  @Test
  public void highComplexity()
  {
    Integer solved[][] = {
        {  5,  4,  7,  6,  1,  8,  9,  3,  2 },
        {  1,  9,  8,  2,  3,  7,  4,  6,  5 },
        {  2,  3,  6,  9,  4,  5,  8,  1,  7 },
        {  4,  1,  9,  5,  2,  6,  3,  7,  8 },
        {  7,  5,  3,  4,  8,  1,  6,  2,  9 },
        {  8,  6,  2,  3,  7,  9,  5,  4,  1 },
        {  3,  8,  4,  1,  5,  2,  7,  9,  6 },
        {  9,  7,  1,  8,  6,  4,  2,  5,  3 },
        {  6,  2,  5,  7,  9,  3,  1,  8,  4 }};

    Integer problem[][] = {
        {  5, -1, -1, -1, -1, -1, -1, -1, -1 },
        {  1, -1, -1,  2, -1,  7,  4, -1, -1 },
        {  2,  3, -1, -1, -1,  5,  8, -1, -1 },
        { -1, -1, -1,  5, -1,  6, -1, -1,  8 },
        { -1,  5,  3, -1, -1, -1,  6,  2, -1 },
        {  8, -1, -1,  3, -1,  9, -1, -1, -1 },
        { -1, -1,  4,  1, -1, -1, -1,  9,  6 },
        { -1, -1,  1,  8, -1,  4, -1, -1, -1 },
        { -1, -1, -1, -1,  9, -1, -1, -1, -1 }};
    
    Sudoku sudoku = new Sudoku(problem);
    SudokuSolver solver = new SudokuSolver(sudoku);    
    assertArrayEquals(solved, solver.solve().asIntArray());
  }
  
  @Test
  public void evilComplexity()
  {
    Integer solved[][] = {
        { 5,  3,  7,  1,  4,  6,  2,  9,  8 },   
        { 2,  6,  9,  8,  7,  3,  4,  5,  1 },  
        { 4,  8,  1,  9,  2,  5,  3,  6,  7 },  
        { 6,  2,  4,  3,  1,  9,  8,  7,  5 },  
        { 3,  9,  5,  6,  8,  7,  1,  4,  2 },  
        { 1,  7,  8,  2,  5,  4,  9,  3,  6 },  
        { 9,  1,  3,  7,  6,  2,  5,  8,  4 },  
        { 8,  5,  6,  4,  3,  1,  7,  2,  9 },  
        { 7,  4,  2,  5,  9,  8,  6,  1,  3}};

    Integer problem[][] = {
        { -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        {  2,  6, -1,  8, -1,  3,  4,  5, -1 },
        { -1, -1, -1, -1,  2,  5, -1,  6, -1 },
        {  6, -1,  4, -1, -1, -1,  8,  7, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  7,  8, -1, -1, -1,  9, -1,  6 },
        { -1,  1, -1,  7,  6, -1, -1, -1, -1 },
        { -1,  5,  6,  4, -1,  1, -1,  2,  9 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1 }};
    
    Sudoku sudoku = new Sudoku(problem);
    SudokuSolver solver = new SudokuSolver(sudoku);   
    assertArrayEquals(solved, solver.solve().asIntArray());
  }

}
