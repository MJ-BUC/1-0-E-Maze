import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

class Mazev2 {

    // ### global variables ###
    public static int startCol = 0;
    public static int startRow = 0;
    public static boolean win = false;
    // ### global variables ###

    public static void Echo(char[][] maze) {
        // echo the original maze
        int count = 0;

        // printing columns
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

        for (int i = 0; i < 20; i++) {
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
            for (int j = 0; j < 20; j++) {
                System.out.print(maze[i][j] + "\t");
                count++;
            }

            if (count == 20) {
                row++;
                System.out.print("\n   |\n");
                count = 0;
            }
        }
    }

    public static char[][] StartingPoint(char[][] maze, int row, int column) {
        // int startVal = 0;
        startCol = column - 1;
        startRow = row - 1;

        maze[startRow][startCol] = 'S';
        
        return maze;
    }

    // structure and algorithm for searching the maze for the exit with recurrsion
    public static char[][] FindMazePath(char[][] charByCharArray, int currentCol, int currentRow) {

        // moving left
        if (currentCol != 0) {
            if (charByCharArray[currentRow][currentCol-1] == 'E') {
                if (!(charByCharArray[currentRow][currentCol] == 'S')) {
                    charByCharArray[currentRow][currentCol] = '+';
                }
                return charByCharArray;
            }
            if (charByCharArray[currentRow][currentCol-1] == '0') {
                if (!(charByCharArray[currentRow][currentCol] == 'S')) {
                    charByCharArray[currentRow][currentCol] = '+';
                }
                currentCol--;
                FindMazePath(charByCharArray, currentCol, currentRow);
            }
        }

        //moving right
        if (currentCol != 19) {
            if (charByCharArray[currentRow][currentCol+1] == 'E') {
                if (!(charByCharArray[currentRow][currentCol] == 'S')) {
                    charByCharArray[currentRow][currentCol] = '+';
                }
                return charByCharArray;
            }
            if (charByCharArray[currentRow][currentCol+1] == '0') {
                if (!(charByCharArray[currentRow][currentCol] == 'S')) {
                    charByCharArray[currentRow][currentCol] = '+';
                }
                currentCol++;
                FindMazePath(charByCharArray, currentCol, currentRow);
            }
        }

        // moving up
        if (currentRow != 0) {
            if (charByCharArray[currentRow-1][currentCol] == 'E') {
                if (!(charByCharArray[currentRow][currentCol] == 'S')) {
                    charByCharArray[currentRow][currentCol] = '+';
                }
                return charByCharArray;
            }
            if (charByCharArray[currentRow-1][currentCol] == '0') {
                if (!(charByCharArray[currentRow][currentCol] == 'S')) {
                    charByCharArray[currentRow][currentCol] = '+';
                }
                currentRow--;
                FindMazePath(charByCharArray, currentCol, currentRow);
            }
        }

        // moving down
        if (currentRow != 19) {
            if (charByCharArray[currentRow+1][currentCol] == 'E') {
                if (!(charByCharArray[currentRow][currentCol] == 'S')) {
                    charByCharArray[currentRow][currentCol] = '+';
                }
                return charByCharArray;
            }
            if (charByCharArray[currentRow+1][currentCol] == '0') {
                if (!(charByCharArray[currentRow][currentCol] == 'S')) {
                    charByCharArray[currentRow][currentCol] = '+';
                }
                currentRow++;
                FindMazePath(charByCharArray, currentCol, currentRow);
            }
        }
        return charByCharArray;
    }


    public static void main(String args[]) {
        String[] lineByLineArray = new String[20];
        char[][] charByCharArray = new char[20][20];

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
                charByCharArray[index][i] = line.charAt(i);
            }
            index++;
        }

        Echo(charByCharArray);


        // User enters the rows and collumns
        Scanner rowCollumnSelect = new Scanner(System.in);
        
        System.out.println("\n\nSelect a row and collum to start at in the maze.");
        System.out.println("Enter a row: ");
        int row = rowCollumnSelect.nextInt();
        System.out.println("Enter a collumn: ");
        int column = rowCollumnSelect.nextInt();
        System.out.print("\n\n");

        // using the rows and collumns to create a starting place in the maze
        charByCharArray = StartingPoint(charByCharArray, row, column);

        // printing out the maze showing the START and EXIT
        Echo(charByCharArray);

        int currentCol = startCol;
        int currentRow = startRow;

        // recursively solve the maze and save to the 2d array
        charByCharArray = FindMazePath(charByCharArray, currentCol, currentRow);


        Stack<Character> mystack = new Stack<Character>();
        
        // push all sides to a stack to find the current value's surrounding values
        if (currentCol != 0) {
            if (charByCharArray[currentRow][currentCol-1] == '0' || charByCharArray[currentRow][currentCol-1] == 'E') {
                mystack.push(charByCharArray[currentRow][currentCol-1]);
            }
        }
        if (currentCol != 19) {
            if (charByCharArray[currentRow][currentCol+1] == '0' || charByCharArray[currentRow][currentCol+1] == 'E') {
                mystack.push(charByCharArray[currentRow][currentCol+1]);
            }
        }
        if (currentRow != 0) {
            if (charByCharArray[currentRow-1][currentCol] == '0' || charByCharArray[currentRow-1][currentCol] == 'E') {
                mystack.push(charByCharArray[currentRow-1][currentCol]);
            }
        }
        if (currentRow != 19) {
            if (charByCharArray[currentRow+1][currentCol] == '0' || charByCharArray[currentRow+1][currentCol] == 'E') {
                mystack.push(charByCharArray[currentRow+1][currentCol]);
            }
        }


        if (mystack.isEmpty()) {
            System.out.println("\n\nHelp, I Am Trapped!\n");
            Echo(charByCharArray);
        }
        else {
            // printing the final result
            System.out.println("\n\nResulting Solved Maze:\n");
            Echo(charByCharArray);
        }


        // structure and algorithm for searching the maze for the exit
        // while (win == false) {
        //     if (currentCol != 0) {
        //         System.out.println(charByCharArray[currentRow][currentCol-1]);
        //     }
        //     if (currentCol != 19) {
        //         System.out.println(charByCharArray[currentRow][currentCol+1]);
        //     }
        //     if (currentRow != 0) {
        //         System.out.println(charByCharArray[currentRow-1][currentCol]);
        //     }
        //     if (currentRow != 19) {
        //         System.out.println(charByCharArray[currentRow+1][currentCol]);
        //     }
        //     win = true;
        // }
    }
}
