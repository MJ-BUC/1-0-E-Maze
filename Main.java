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
        // System.out.println(startVal);
        Stack<Character> mystack = new Stack<Character>();
        int mazeVal = startVal;
        
        // move up
        if (!(mazeVal < 20)) {
            if (charByCharArray[mazeVal-20] == '0' || charByCharArray[mazeVal-20] == 'E') {
                mystack.push(charByCharArray[mazeVal-20]);
            }
        }
        // move down
        if (!(mazeVal > 379)) {
            if (charByCharArray[mazeVal+20] == '0' || charByCharArray[mazeVal+20] == 'E') {
                mystack.push(charByCharArray[mazeVal+20]);
            }
        }
        // move left

        //move right


        
        // ### debug for stack ###
        int stackSize = mystack.size();
        for (int i = 0; i < stackSize; i++) {
            System.out.println(mystack.pop());
        }
        // System.out.println(mystack.pop());
        // ### debug for stack ###
        
    }
}