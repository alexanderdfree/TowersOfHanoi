import java.util.Stack;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class TowersOfHanoiGUI{

   private int nDisks; //the number of total disks in the puzzle
   private Stack pinA; //the stack of disks on pin A
   private Stack pinB; //the stack of disks on pin B
   private Stack pinC; //the stack of disks on pin C
   
   public TowersOfHanoiGUI(int nDisks, int startPin){
      /*Initialize a new TowersOfHanoiGUI object for nDisks
      Input:
         int nDisks: the total number of disks in the puzzle
         int startPin: the pin to start the disks on
            0 = pin A
            1 = pin B
            2 = pin C
      Output:
         this: a TowersOfHanoiGUI object for n disks
      */
      //set up the board
      this.nDisks = nDisks;
      this.pinA = new Stack();
      this.pinB = new Stack();
      this.pinC = new Stack();
      //start the disks on the appropriate pin
      if(startPin % 3 == 0){
         for(int i = nDisks; i >= 1; i--){
            this.pinA.push(i);
         }
      }
      else if(startPin % 3 == 1){
         for(int i = nDisks; i >= 1; i--){
            this.pinB.push(i);
         }
      }
      else{
         for(int i = nDisks; i >= 1; i--){
            this.pinC.push(i);
         }
      }
      this.draw();
   }
   
   public boolean moveDisk(int from, int to){
      /*Attempt to move a disk from pin from to pin to
      Input:
         int from: the pin we are attempting to move the top disk from
         int to: the pin we are attempting to move the top disk to
      Output:
         return: a boolean indicating whether or not we were able to move
         the top disk from from to to
         print: a message if you tried an illegal move
      Side Effects: if the move was legal, move the disk
      Ex.
      TowersOfHanoiGUI t = new TowersOfHanoiGUI(5, 0)
      t.print()
      1
      2
      3
      4
      5
      - - -
      t.moveDisk(0, 1) -> true
      t.print()
      
      2
      3
      4
      5 1
      - - -
      t.moveDisk(0, 1) -> false
      You can't put disk 2 on top of disk 1
      t.print()
      
      2
      3
      4
      5 1
      - - -
      */
      //grab the pins you are trying to move from and to
      Stack pinFrom, pinTo;
      //pin from
      if(from % 3 == 0) pinFrom = this.pinA;
      else if(from % 3 == 1) pinFrom = this.pinB;
      else pinFrom = this.pinC;
      //pin to
      if(to % 3 == 0) pinTo = this.pinA;
      else if(to % 3 == 1) pinTo = this.pinB;
      else pinTo = this.pinC;
      //check the disks you are trying to move
      int diskFrom = Integer.MAX_VALUE;
      int diskTo = Integer.MAX_VALUE;
      if(pinFrom.empty()){
         StdOut.println("Can't move from pin "+ from + ", it's empty!");
         return false;
      }
      diskFrom = (int)(Integer)pinFrom.peek();
      if(!(pinTo.empty())){
         diskTo = (int)(Integer)pinTo.peek();
      }
      //check if the move is valid
      if(diskFrom > diskTo){
         //print error message
         StdOut.println("Can't put disk "+diskFrom+" on disk "+diskTo+"!");
         //return fail code
         return false;
      }
      else{
         //move the disks
         pinTo.push(pinFrom.pop());
         return true;
      }
   }   
   
   public void print(){
      /*Print a representation of this TowersOfHanoiGUI Object
      Input:
         this: a TowersOfHanoiGUI Object
      Output:
         StdOut: a printed represention of this TowersOfHanoiGUI
      Ex.
      TowersOfHanoiGUI t = new TowersOfHanoiGUI(5, 0)
      t.print()
      1
      2
      3
      4
      5
      -  -  -
      */
      List pinA = this.pinContents(0);
      List pinB = this.pinContents(1);
      List pinC = this.pinContents(2);
      for(int i = this.nDisks; i >= 1; i--){
         String a = " ";
         String b = " ";
         String c = " ";
         if(i <= pinA.size()){
            a = pinA.get(i-1).toString();
         }
         if(i <= pinB.size()){
            b = pinB.get(i-1).toString();
         }
         if(i <= pinC.size()){
            c = pinC.get(i-1).toString();
         }
         StdOut.println(a + " " + b + " " + c);
      }
      StdOut.println("- - -");
   }
   
   public List pinContents(int whichPin){
      /*Returns the contents of whichPin in an ArrayList
      Input:
         this: a TowersOfHanoiGUI object
         int whichPin: the pin you want to see
            0 = pin A
            1 = pin B
            2 = pin C
       Output:
         return: an ArrayList containing the disks on whichPin
         in order from bottom = 0 to top = n
       Ex.
       TowersOfHanoiGUI t = new TowersOfHanoi(5, 0)
       t.pinContents(0) -> [5, 4, 3, 2, 1]
       t.pinContents(1) -> []
       */
       ArrayList contents = new ArrayList();
       Stack pin;
       //which pin are we talking about
       if(whichPin % 3 == 0){
         pin = this.pinA;
       }
       else if(whichPin % 3 == 1){
         pin = this.pinB;
       }
       else{
         pin = this.pinC;
       }
       //pop the contents of pin to contents
       while(!(pin.empty())){
         contents.add(0, pin.pop());
       }
       //re-push the contents of contents to pin
       for(int i = 0; i < contents.size(); i++){
         pin.push(contents.get(i));
       }
       //return the contents
       return contents;
    }
    
    public void draw(){
      /*Draw the current state of TowersOfHanoiGUI to StdDraw
      Input:
         this: a TowersOfHanoiGUI object
      Output:
         StdDraw: draws a picture of the current game state to StdDraw
      */
      //clear the previous screen
      StdDraw.clear();
      //draw the pins
      double pinDiameter = 0.015; //1.5% of screen width
      double pinHeight = 0.66; //66% of screen height
      Color pinColor = Color.GRAY;
      double xPinA = 1.0/6;
      double xPinB = 0.5;
      double xPinC = 5.0/6;
      StdDraw.setPenColor(pinColor);
      StdDraw.filledRectangle(xPinA, 0.5*pinHeight, 0.5*pinDiameter, 0.5*pinHeight);
      StdDraw.filledRectangle(xPinB, 0.5*pinHeight, 0.5*pinDiameter, 0.5*pinHeight);
      StdDraw.filledRectangle(xPinC, 0.5*pinHeight, 0.5*pinDiameter, 0.5*pinHeight);
      
      //draw the disks
      double diskHeight = pinHeight/(this.nDisks+1);
      double diskUnitWidth = 1.0/3/(this.nDisks+1);
      StdDraw.setPenColor(Color.BLACK);
      //get the disks to draw
      List disksA = this.pinContents(0);
      List disksB = this.pinContents(1);
      List disksC = this.pinContents(2);
      //draw the disks on pin A
      for(int i = 0; i < disksA.size(); i++){
         int disk = (int)(Integer)disksA.get(i);
         double diskWidth = disk*diskUnitWidth;
         double yDisk = (i+0.5)*diskHeight;
         StdDraw.filledRectangle(xPinA, yDisk, 0.5*diskWidth, 0.5*diskHeight);
      }
      //draw the disks on pin B
      for(int i = 0; i < disksB.size(); i++){
         int disk = (int)(Integer)disksB.get(i);
         double diskWidth = disk*diskUnitWidth;
         double yDisk = (i+0.5)*diskHeight;
         StdDraw.filledRectangle(xPinB, yDisk, 0.5*diskWidth, 0.5*diskHeight);
      }
      //draw the disks on pin C
      for(int i = 0; i < disksC.size(); i++){
         int disk = (int)(Integer)disksC.get(i);
         double diskWidth = disk*diskUnitWidth;
         double yDisk = (i+0.5)*diskHeight;
         StdDraw.filledRectangle(xPinC, yDisk, 0.5*diskWidth, 0.5*diskHeight);
      }
      //show the changes
      StdDraw.show();
   }
   
   public static void main(String[] args){
      //parse a command line argument for the number of disks
      int nDisks = Integer.parseInt(args[0]);
      //parse a command line argument for the start pin
      int startPin = Integer.parseInt(args[1]);
      //setup the board
      TowersOfHanoiGUI t = new TowersOfHanoiGUI(nDisks, startPin);
      //enable double buffering for smooth animation
      StdDraw.enableDoubleBuffering();
      StdDraw.setCanvasSize(800, 300);

      //draw the board
      t.draw();
      //ask the user for moves
      while(true){
         //Ask the user what move they would like to make
         StdOut.print("How would you like to move? (ex. A->B): ");
         String command = StdIn.readLine();
         //Parse their command
         String sFrom = command.split("->")[0];
         String sTo = command.split("->")[1];
         int from = -1;
         int to = -1;
         //parse pin from
         if(sFrom.equals("A")){
            from = 0;
         }
         else if(sFrom.equals("B")){
            from = 1;
         }
         else if(sFrom.equals("C")){
            from = 2;
         }
         else{
            StdOut.println("Unsupported Pin From: "+sFrom);
         }
         //parse pin to
         if(sTo.equals("A")){
            to = 0;
         }
         else if(sTo.equals("B")){
            to = 1;
         }
         else if(sTo.equals("C")){
            to = 2;
         }
         else{
            StdOut.println("Unsupported Pin To: "+sTo);
         }
         //if a valid command, execute
         if((from != -1) && (to != -1)){
            t.moveDisk(from, to);
            t.draw();
         }
         //take a pause to slow things down
         //StdDraw.pause(500);
      }
   }
      
       
}
         
      
   