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

  final private Sudoku problem;

  public SudokuSolver(Sudoku problem) {
    this.problem = problem;
  }

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

  private <T> T head(List<T> list) {
    return list.get(0);
  }
  
  private List<CellValue> tail(List<CellValue> candidates) {
    return candidates.subList(1, candidates.size());
  }

  private void resetSolutions(List<CellValue> solutions) {
    forEach(solutions).reset();
  }

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
