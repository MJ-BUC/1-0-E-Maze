// Maze Project
// Mark Bucaro

import java.util.Stack;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Maze {

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
        int start = 0;
        start = collumn - 1;
        for (int i = 0; i < row - 1; i++) {
            start += 20;
        }

        maze[start] = 'S';
        
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


        // using a stack in java
        Stack mystack = new Stack<>();
        mystack.push(0);
        mystack.push(1);
        mystack.push(2);
        mystack.push(3);
        mystack.push(4);
        mystack.push(5);

        // for (int i = 0; i < 6; i ++) {
        //     System.out.println(mystack.pop());
        // }

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

        Echo(charByCharArray);
    }
}