// Maze Project
// Mark Bucaro

import java.util.Stack;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Maze {
    // ### global variables ###
    public static int startVal = 0;
    public static boolean win = false;
    // ### global variables ###

    public static void Echo(char[] maze) {
        // echo the original maze
        int count = 0;

        // printing collumns
        System.out.print("\t");
        for (int i = 1; i < 21; i++){
            System.out.print(i + "\t");
        }

        System.out.print("\n\t");
        for (int i = 0; i < 155; i++) {
            System.out.print("_");
        }

        System.out.print("\n\n");

        // prints the maze and rows
        int row = 1;

        for (char val:maze) {
            if (count == 0) {
                System.out.print(row);
                if (row < 10) {
                    System.out.print("  |");
                }
                else {
                    System.out.print(" |");
                }
                
                System.out.print("\t");
            }
            System.out.print(val + "\t");
            count++;
            if (count == 20) {
                row++;
                System.out.print("\n   |\n");
                count = 0;
            }
        }
    }

    public static char[] StartingPoint(char[] maze, int row, int collumn) {
        // int startVal = 0;
        startVal = collumn - 1;
        for (int i = 0; i < row - 1; i++) {
            startVal += 20;
        }

        maze[startVal] = 'S';
        
        return maze;
    }

    public static void main(String args[]) {
        // reading file
        String[] lineByLineArray = new String[20];
        char[] charByCharArray = new char[400];

        int index = 0;

        File mazeFile = new File("C:\\Users\\lunke\\OneDrive\\Documents\\Github\\10EMaze\\maze.txt");

        try {
            Scanner maze = new Scanner(mazeFile);
            while(maze.hasNext()) {
                String item = maze.next();
                lineByLineArray[index] = item;
                index++;
            }
            maze.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("file not found!");
        }

        // for loop to insert each character of each line into a single array; one char per index
        index = 0;
        for (String line:lineByLineArray) {
            for (int i = 0; i < 20; i++) {
                charByCharArray[index] = line.charAt(i);
                index++;
            }
        }



        // printing out the maze
        Echo(charByCharArray);



        // User enters the rows and collumns
        Scanner rowCollumnSelect = new Scanner(System.in);
        
        System.out.println("\n\nSelect a row and collum to start at in the maze.");
        System.out.println("Enter a row: ");
        int row = rowCollumnSelect.nextInt();
        System.out.println("Enter a collumn: ");
        int collumn = rowCollumnSelect.nextInt();
        System.out.print("\n\n");

        // using the rows and collumns to create a starting place in the maze
        StartingPoint(charByCharArray, row, collumn);

        // printing out the maze
        Echo(charByCharArray);



        // search algorithm for finding the exit
        Stack<Character> mystack = new Stack<Character>();
        int mazeVal = startVal;
        
        // move up
        if (!(mazeVal < 20)) {
            if (charByCharArray[mazeVal-20] == '0' || charByCharArray[mazeVal-20] == 'E') {
                // mystack.push((char) ((mazeVal-20)+'0'));
                mystack.push(charByCharArray[mazeVal-20]);
            }
        }
        // move down
        if (!(mazeVal > 379)) {
            if (charByCharArray[mazeVal+20] == '0' || charByCharArray[mazeVal+20] == 'E') {
                // mystack.push((char) ((mazeVal+20)+'0'));
                mystack.push(charByCharArray[mazeVal+20]);
            }
        }
        // move left
        if (!(mazeVal == 0 || mazeVal == 20 || mazeVal == 40 || mazeVal == 60 || mazeVal == 80
        || mazeVal == 100 || mazeVal == 120 || mazeVal == 140 || mazeVal == 160 || mazeVal == 180
        || mazeVal == 200 || mazeVal == 220 || mazeVal == 240 || mazeVal == 260 || mazeVal == 280
        || mazeVal == 300 || mazeVal == 320 || mazeVal == 340 || mazeVal == 360 || mazeVal == 380)) {
            if (charByCharArray[mazeVal-1] == '0' || charByCharArray[mazeVal-1] == 'E') {
                // mystack.push((char) ((mazeVal-1)+'0'));
                mystack.push(charByCharArray[mazeVal-1]);
            }
        }
        //move right
        if (!(mazeVal == 19 || mazeVal == 39 || mazeVal == 59 || mazeVal == 79 || mazeVal == 99
        || mazeVal == 119 || mazeVal == 139 || mazeVal == 159 || mazeVal == 179 || mazeVal == 199
        || mazeVal == 219 || mazeVal == 239 || mazeVal == 259 || mazeVal == 279 || mazeVal == 299
        || mazeVal == 319 || mazeVal == 339 || mazeVal == 359 || mazeVal == 379 || mazeVal == 399)) {
            if (charByCharArray[mazeVal+1] == '0' || charByCharArray[mazeVal+1] == 'E') {
                // mystack.push((char) ((mazeVal+1)+'0'));
                mystack.push(charByCharArray[mazeVal+1]);
            }
        }

        
        // ### debug for stack ###
        if (mystack.isEmpty()) {
            System.out.println("\nHelp, I Am Trapped!");
        }
        else {
            System.out.println("\nItems In The Stack: ");
            int stackSize = mystack.size();
            for (int i = 0; i < stackSize; i++) {
                System.out.println(mystack.pop());
            }
        }
        // ### debug for stack ###
        
    }
}