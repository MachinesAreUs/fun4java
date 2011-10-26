package net.zensoft.games.sudoku;

/**
 * Represents a cell solution in a Sudoku board.
 * 
 * @author Agustín Ramos
 */
public class CellValue {
  
  final int row;
  final int col;
  int value = 0;

  CellValue(int row, int col) {  this.row = row; this.col = col; }
  CellValue(int row, int col, int value) { this.row = row; this.col = col; this.value = value; }
  int nextSolutionCandidate() { return ++value; }
  boolean hasSolutionCandidates() { return value < 9 ? true : false; }
  void reset() { value = 0; }
  
  int getValue() { return value; } // This is just 'cause lambdaj can't handle "on(CellValue.class).value" 
}