package net.zensoft.games.sudoku;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

public class SudokuSolver {

  private Sudoku problem;
  private Sudoku solution;

  public SudokuSolver(Sudoku problem) {
    this.problem = problem;
    this.solution = new Sudoku(problem.asIntArray());
  }

  public Sudoku solve() {
    if (! solveCells( cellsToSolve() ) ) {
      throw new IllegalStateException("Impossible to solve this Sudoku!");
    }
    return solution;
  }

  List<CellValue> cellsToSolve() {
    return
      filter(
        having(on(CellValue.class).getValue(), equalTo( -1 )), // Remember, -1 means undefined
        solution.asCellValueList() );
  }
  
  private boolean solveCells(List<CellValue> solutions) {
    if (! solutions.isEmpty() ) {
      
      resetSolutions(solutions);
      CellValue first = solutions.get(0);
      boolean isSublistSolved;
      
      do {
        if (!solveCell(first))
          return false;
        isSublistSolved = solveCells(subList(solutions));
      } while (! isSublistSolved && first.hasSolutionCandidates());
      
      if (!isSublistSolved) {
        clearCell(first);
        return false;
      }
    }
    return true;
  }

  private List<CellValue> subList(List<CellValue> candidates) {
    return candidates.subList(1, candidates.size());
  }

  private void resetSolutions(List<CellValue> solutions) {
    forEach(solutions).reset();
  }
  
  private boolean solveCell(CellValue cell) {
    while (cell.hasSolutionCandidates()) {
      cell.nextSolutionCandidate();
      if (solution.isValidValue( cell )) {
        solution.setValue( cell );
        return true;
      }
    }
    clearCell(cell);
    return false;
  }
  
  private void clearCell(CellValue cell) {
    solution.clear(cell.row, cell.col);
  }

}
