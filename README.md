# 8-Queens-Game

The Eight Queens puzzle game is a puzzle in which one must place 8 queen pieces from the game of chess onto a chess board, under the condition that no queen is ever in a position to take another queen. If you're unfamiliar with chess, this means each piece cannot be in the same row, column, or diagonal of any other piece. There are 92 distinct solutions - if you're not careful with your placement early on, it can later become impossible to solve.

This puzzle was created in Java for a mobile android system for UNC-Chapel Hill's Mobile Computing Systems class. The main part of the puzzle is figuring out how to arrange the 8 pieces in such a way such that they don't intersect. In coding, this translated to a lot of looping with arrays to check diagonals of the game board.

Users can tap on the screen on a square to place a queen, or again to remove the queen there.

<img width="315" alt="Placing" src="https://user-images.githubusercontent.com/25047954/123498851-4cb1d900-d600-11eb-9124-c3164043b45b.png">

If users try to place a queen where they cannot, an error message will appear letting the user know.

<img width="315" alt="UnableToPlace" src="https://user-images.githubusercontent.com/25047954/123498854-52a7ba00-d600-11eb-86ba-5b87026cab24.png">
