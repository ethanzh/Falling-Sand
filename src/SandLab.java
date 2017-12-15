import java.awt.*;
import java.util.*;

public class SandLab {
  public static void main(String[] args) {
    SandLab lab = new SandLab(120, 80);
    lab.run();
  }

  private int[][] grid;
  private SandDisplay display; //initializes SandDisplay object

    public static final int EMPTY = 0;
    public static final int METAL = 1;
    public static final int SAND = 2;
    public static final int WATER = 3;
    public static final int ICE = 4;
    // These constants are just to keep things easier to understand, so we don't get bogged down with remembering specific numbers for different colors
  
  public SandLab(int numRows, int numCols) {
    String[] names;
    names = new String[5];
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND] = "Sand";
    names[WATER] = "Water";
    names[ICE] = "Ice";
    // Associate the 0th, 1st, 2nd, etc items of 'names' with material names that match their constant int label

    display = new SandDisplay("Falling Sand", numRows, numCols, names);
    grid = new int[numRows][numCols];

    System.out.println(grid.length);
    System.out.println(grid[0].length);
    System.out.println(grid[1].length);
    // Tests just to see the lengths of grid's different attributes

  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool) {
      grid[row][col] = tool;

      // Whenever the user uses the 'tool' (the pointer) to click, the location is taken
  }

  //copies each element of grid into the display
  public void updateDisplay(){
      for (int i = 0; i < grid.length; ++i) {

          // NOTE: Grid length here is 120, but both the [0] and [1] lengths seem to be 80 (?)
          // Assume this function is running CONSTANTLY

          for (int j = 0; j < grid[1].length; ++j) {

              int tool = grid[i][j];
              // Gets position of current pointer (I don't know why I can't use tool from locationClicked, but this seems to work so oh well

              System.out.println(tool);
              // For testing purposes -- seems to always equal 0

              switch (tool) {
                  case EMPTY:
                      display.setColor(i, j, Color.BLACK);
                      break;
                  case METAL:
                      display.setColor(i, j, Color.DARK_GRAY);
                      break;
                  case SAND:
                      display.setColor(i, j, Color.YELLOW);
                      break;
                  case WATER:
                      display.setColor(i, j, Color.BLUE);
                      break;
                  case ICE:
                      display.setColor(i, j, Color.WHITE);
              }

          }
      }
  }

  //called repeatedly.
  //causes one random particle to maybe do something.
  public void step() {
  }
  
  //do not modify
  public void run() {
    while (true)
    {
      for (int i = 0; i < display.getSpeed(); i++)
        step();
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
    }
  }
}
