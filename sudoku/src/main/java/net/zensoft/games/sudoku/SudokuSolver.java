package net.zensoft.games.sudoku;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

/**
 * Implements a backtracking algorithm to solve Sudoku games.
 * 
 * @author Agustín Ramos
 */
public class SudokuSolver {

  private Sudoku problem;

  /**
   * Initializes with a problem to solve.
   * 
   * @param problem
   */
  public SudokuSolver(Sudoku problem) {
    this.problem = problem;
  }

  /**
   * Go for it!
   * 
   * @return a new Sudoku object representing the solution
   * @throws IllegalArgumentException when it's impossible to solve this sudoku.
   */
  public Sudoku solve() {
    Sudoku solution = new Sudoku(problem.asIntArray().clone());
    if (! solveCells(  solution, cellsToSolve( problem ) ) ) {
      throw new IllegalArgumentException("Impossible to solve this Sudoku!");
    }
    return solution;
  }

  private List<CellValue> cellsToSolve(Sudoku game) {
    return
      filter(
        having(on(CellValue.class).getValue(), equalTo( -1 )), // Remember, -1 means undefined
        game.asCellValueList() );
  }
  
  /**
   * Magic happens here
   * Given the current state of the board, it tries to find the solution for a list of cells.
   * @param solutions
   * @return
   */
  private boolean solveCells(Sudoku game, List<CellValue> solutions) {
    if (! solutions.isEmpty() ) {
      
      resetSolutions(solutions); // clean slate for this cells
      CellValue pivot = head(solutions);
      boolean isSublistSolved;
      
      do {
        if (!solveCell(game, pivot))
          return false;
        isSublistSolved = solveCells(game, tail(solutions));
      } while (! isSublistSolved && pivot.hasSolutionCandidates());
      
      if (!isSublistSolved) {
        clearCell(game, pivot);
        return false;
      }
    }
    return true;
  }

  /**
   * Returns the head of a list
   * @param <T>
   * @param list
   * @return
   */
  private <T> T head(List<T> list) {
    return list.get(0);
  }
  
  /**
   * Returns the tail of a list
   * @param candidates
   * @return
   */
  private List<CellValue> tail(List<CellValue> candidates) {
    return candidates.subList(1, candidates.size());
  }

  /**
   * Resets each cell solution as if you haven't tried any value in it.
   * @param solutions
   */
  private void resetSolutions(List<CellValue> solutions) {
    forEach(solutions).reset();
  }
  
  /**
   * Finds a solution for a cell, given the current state of the board.
   * CellValue.value contains the current guess for the cell, if that
   * value proves to be wrong, it tryies the nex solution until a valid one
   * it's found.
   * @param cell
   * @return
   */
  private boolean solveCell(Sudoku game, CellValue cell) {
    while (cell.hasSolutionCandidates()) {
      cell.nextSolutionCandidate();
      if (game.isValidValue( cell )) {
        game.setValue( cell );
        return true;
      }
    }
    clearCell(game, cell);
    return false;
  }
  
  private void clearCell(Sudoku game, CellValue cell) {
    game.clear(cell.row, cell.col);
  }

}
