package io.github.isguliyev.random.tictactoe;

public class Main {
   public static void main(String[] args) {
      TicTacToeGrid ticTacToeGrid = new TicTacToeGrid(10, 10);

      while (ticTacToeGrid.getEmptySymbolCount() != 0) {
         RandomTicTacToePlayer.play(ticTacToeGrid);
         TicTacToeGridPrinter.printWithASCIICharacters(ticTacToeGrid);

         System.out.println();
      }
   }
}