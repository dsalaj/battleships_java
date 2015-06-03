import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ships {

  private static class InputHelper {
  
    public String getUserInput() {
  
      String input = null;
      System.out.print("enter next move: ");
      try {
  
        BufferedReader is = new BufferedReader(new InputStreamReader( System.in ));
        input = is.readLine();
        if(input.length() == 0) {
          return null;
        }
      }
      catch(IOException e) {
    
        System.out.println("Exception: " + e);
      }
      return input;
    }
  }
  
  private static class Cell {
  
    private int pos_x;
    private int pos_y;
    private boolean alive = true;
    Cell(int x, int y) {

      pos_x = x;
      pos_y = y;
    }
  }
  
  private static class Ship {
  
    private ArrayList<Cell> cells = new ArrayList<Cell>();
    private boolean alive = false;

    public void addCell(int x, int y) {

      cells.add(new Cell(x, y));
    }

    public int checkCell(int x, int y) {

      for(Cell c : cells) {
        if(c.pos_x == x && c.pos_y == y)
          if(c.alive) {
            return 1;
          } else {
            return 2;
          }
      }
      return 0;
    }

    public int hitCell(int x, int y) {

      for(Cell c : cells) {
        if(c.pos_x == x && c.pos_y == y)
          c.alive = false;
      }
      return 0;
    }
  }

  private static class Printer {

    public static void print(ArrayList<Ship> ships, int field_size) {

      for(int y = 0; y < field_size; y++) {

        for(int x = 0; x < field_size; x++) {
          boolean found = false; 
          for(Ship s : ships) {

            if(s.checkCell(x,y) == 1) {
              found = true;
              System.out.print("o");
            } else if (s.checkCell(x,y) == 2) {
              found = true;
              System.out.print("x");
            }
          }
          if(!found) {
            System.out.print("~");
          }
        }
        System.out.println("");
      }
    }
  }

  public static Ship spawnShip( int size, ArrayList<Ship> ships, int field_size) {

    int guess_y = (int) (Math.random() * field_size);
    int guess_x = (int) (Math.random() * field_size);

    boolean direction = Math.random() < 0.5;

    for(Ship s : ships) {
      if(s.checkCell(guess_x, guess_y) != 0) {
        return spawnShip(size, ships, field_size);
      }
    }

    int cells_rem = size;
    int g_x = guess_x;
    int g_y = guess_y;

    while(cells_rem > 0) {
      if(direction) {
        g_x++;
        if(g_x >= field_size) {
          return spawnShip(size, ships, field_size);
        }
        for(Ship s : ships) {
          if(s.checkCell(g_x, g_y) != 0 || g_x >= field_size) {
            return spawnShip(size, ships, field_size);
          }
        }
      } else {
        g_y++;
        if(g_y >= field_size) {
          return spawnShip(size, ships, field_size);
        }
        for(Ship s : ships) {
          if(s.checkCell(g_x, g_y) != 0) {
            return spawnShip(size, ships, field_size);
          }
        }
      }
      cells_rem--;
    }

    Ship spawned = new Ship();
    for(int c = 0; c < size; c++) {
      if(direction) {
        spawned.addCell(guess_x + c, guess_y);
      } else {
        spawned.addCell(guess_x, guess_y + c);
      }
    }
    return spawned;

  }

  //TODO: refactor main
  //TODO: divide into classes
  //TODO: optimize the speed of spawning (prevent stack overflow)
  // idea: only start with random number + continue with linear search

  public static void main( String[] args ) {

    InputHelper input = new InputHelper();

    ArrayList<Ship> ships = new ArrayList<Ship>();
    int field_size = Integer.parseInt(args[0]);
    //FIXME: can not input ships the length of playing field

    int sum = 0;
    for(int i = 1; i < args.length; i++) {
      if(Integer.parseInt(args[i]) >= field_size) {
        System.out.println("You defined a ship that can not fit on your playing field!");
        System.exit(1);
      }
      sum += Integer.parseInt(args[i]);
    }
    if(sum > field_size*field_size) {
      System.out.println("This many ships can not fit your playing field!");
      System.exit(1);
    }
    for(int i = 1; i < args.length; i++) {
      ships.add(spawnShip(Integer.parseInt(args[i]), ships, field_size));
    }

    Printer p = new Printer();
    int num_moves = 0;
    int living = sum;

    while(living != 0) {
      p.print(ships, field_size);
      String move = input.getUserInput();
      num_moves++;
      try {
        if(move.length() == 2) {
          int x = Integer.parseInt(Character.toString(move.charAt(0)));
          int y = Integer.parseInt(Character.toString(move.charAt(1)));
          boolean hit = false;
          for(Ship s : ships) {
            if(s.checkCell(x,y) == 1) {
              s.hitCell(x,y);
              System.out.println("hit");
              living--;
              hit = true;
              break;
            }
          }
          if(!hit)
            System.out.println("miss");
        } else {
          System.out.println("ERROR: Invalid move. Should be two digits between 0 and 5");
        }
      } catch (Exception e) {
        System.out.println("ERROR: Invalid move. Should be two digits between 0 and 5");
      }
    }
    System.out.println("You completed the game in " + num_moves + " moves.");
  }
}
