public class TowersOfHanoiSolver{
   public static int numMoves(int nDiscs){
      /*Calculate the number of moves required to solve the
      Towers of Hanoi Puzzle for nDiscs
      Input:
         int nDiscs: the number of discs being moved
      Output:
         return: the number of moves required to move them
                 from one tower to another
      Ex.
      numMoves(1) -> 1
      numMoves(2) -> 3
      numMoves(3) -> 7
      */
      if (nDiscs == 0) return 0;
      else if (nDiscs == 1) return 1;
      else{
         return numMoves(nDiscs-1) * 2 + 1;
      }
   }
   public static void solve(int nDisks, int from, int to){
      /*Solve how to move nDisks in the Towers of Hanoi puzzle
      from pin from to pin to.
      Input:
         int nDisks: the number of disks to move
         int from: the pin to move the disks from
            0: pin A
            1: pin B
            2: pin C
         int to: the pin to move the disks to
            0: pin A
            1: pin B
            2: pin C
      Output:
         print: print out the moves sequentially line-by-line
      Ex.
      solve(1, 0, 1)
      A->B
      solve(2, 0, 1)
      A->C
      A->B
      C->B
      */
      char fromLetter = (char)(from + 65);
      char toLetter = (char)(to + 65);
      char otherLetter = 'A';
      char otherNumber = 0;;
      if (fromLetter == 'A' && toLetter == 'B'){
         otherLetter = 'C';
         otherNumber = 2;
      }
      else if (fromLetter == 'A' && toLetter == 'C'){
         otherLetter = 'B';
         otherNumber = 1;
      }
      else if (fromLetter == 'B' && toLetter == 'A'){
         otherLetter = 'C';
         otherNumber = 2;
      }
      else if (fromLetter == 'B' && toLetter == 'C'){
         otherLetter = 'A';
         otherNumber = 0;
      }
      else if (fromLetter == 'C' && toLetter == 'A'){
         otherLetter = 'B';
         otherNumber = 1;
      }
      else if (fromLetter == 'C' && toLetter == 'B'){
         otherLetter = 'A';
         otherNumber = 0;
      }
      
      //if (toLetter == 0) return
      if (nDisks == 1){
         StdOut.println(fromLetter + "->" + toLetter);
      }
      else if (nDisks == 2){
         StdOut.println(fromLetter + "->" + otherLetter);
         StdOut.println(fromLetter + "->" + toLetter);
         StdOut.println(otherLetter + "->" + toLetter);
         
      }
      else{
         solve(nDisks-1, from, otherNumber);
         StdOut.println(fromLetter + "->" + toLetter);
         solve(nDisks-1, otherNumber, to);
      }
   }
   public static void main(String[] args){
      int a = Integer.parseInt(args[0]);
      int b = Integer.parseInt(args[1]);
      int c = Integer.parseInt(args[2]);
      solve(a, b, c);
   }
}