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
    public static boolean FindMazePath(char[][] charByCharArray, int currentCol, int currentRow, Stack mazePosStack, int count) {
        
        if (charByCharArray[currentRow][currentCol] == 'E') {
            return win = true;
        }
        
        else {
            // moving up
            if (currentRow != 0) {
                if (charByCharArray[currentRow-1][currentCol] == 'E') {
                    if (!(charByCharArray[currentRow][currentCol] == 'S')) {
                        charByCharArray[currentRow][currentCol] = 'V';
                        mazePosStack.push(currentCol);
                        mazePosStack.push(currentRow);
                    }
                    return win = true;
                }
                if (charByCharArray[currentRow-1][currentCol] == '0') {
                    if (!(charByCharArray[currentRow][currentCol] == 'S')) {
                        charByCharArray[currentRow][currentCol] = 'V';
                        mazePosStack.push(currentCol);
                        mazePosStack.push(currentRow);
                    }
                    currentRow--;
                    charByCharArray[currentRow][currentCol] = 'V';
                    return FindMazePath(charByCharArray, currentCol, currentRow, mazePosStack, count);
                }
            }

            // moving left
            if (currentCol != 0) {
                if (charByCharArray[currentRow][currentCol-1] == 'E') {
                    if (!(charByCharArray[currentRow][currentCol] == 'S')) {
                        charByCharArray[currentRow][currentCol] = 'V';
                        mazePosStack.push(currentCol);
                        mazePosStack.push(currentRow);
                    }
                    return win = true;
                }
                if (charByCharArray[currentRow][currentCol-1] == '0') {
                    if (!(charByCharArray[currentRow][currentCol] == 'S')) {
                        charByCharArray[currentRow][currentCol] = 'V';
                        mazePosStack.push(currentCol);
                        mazePosStack.push(currentRow);
                    }
                    currentCol--;
                    charByCharArray[currentRow][currentCol] = 'V';
                    return FindMazePath(charByCharArray, currentCol, currentRow, mazePosStack, count);
                }
            }

            //moving right
            if (currentCol != 19) {
                if (charByCharArray[currentRow][currentCol+1] == 'E') {
                    if (!(charByCharArray[currentRow][currentCol] == 'S')) {
                        charByCharArray[currentRow][currentCol] = 'V';
                        mazePosStack.push(currentCol);
                        mazePosStack.push(currentRow);
                    }
                    return win = true;
                }
                if (charByCharArray[currentRow][currentCol+1] == '0') {
                    if (!(charByCharArray[currentRow][currentCol] == 'S')) {
                        charByCharArray[currentRow][currentCol] = 'V';
                        mazePosStack.push(currentCol);
                        mazePosStack.push(currentRow);
                    }
                    currentCol++;
                    charByCharArray[currentRow][currentCol] = 'V';
                    return FindMazePath(charByCharArray, currentCol, currentRow, mazePosStack, count);
                }
            }

            // moving down
            if (currentRow != 19) {
                if (charByCharArray[currentRow+1][currentCol] == 'E') {
                    if (!(charByCharArray[currentRow][currentCol] == 'S')) {
                        charByCharArray[currentRow][currentCol] = 'V';
                        mazePosStack.push(currentCol);
                        mazePosStack.push(currentRow);
                    }
                    return win = true;
                }
                if (charByCharArray[currentRow+1][currentCol] == '0') {
                    if (!(charByCharArray[currentRow][currentCol] == 'S')) {
                        charByCharArray[currentRow][currentCol] = 'V';
                        mazePosStack.push(currentCol);
                        mazePosStack.push(currentRow);
                    }
                    currentRow++;
                    charByCharArray[currentRow][currentCol] = 'V';
                    return FindMazePath(charByCharArray, currentCol, currentRow, mazePosStack, count);
                }
            }

            // backtracking to find new path
            if (mazePosStack.isEmpty() == false) {
                currentRow = (int) mazePosStack.pop();
                currentCol = (int) mazePosStack.pop();
                // debugging
                // System.out.println(currentRow);
                // System.out.println(currentCol);
            }
            if (count == 200) {
                return win = false;
            }
            count++;
            if (charByCharArray[currentRow][currentCol] == 'V' || charByCharArray[currentRow][currentCol] == 'S') {
                return FindMazePath(charByCharArray, currentCol, currentRow, mazePosStack, count);
            }
        }
        return win = false;
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
        Scanner rowColumnSelect = new Scanner(System.in);
        
        System.out.println("\n\nSelect a row and collum to start at in the maze.");
        System.out.println("Enter a row: ");
        int row = rowColumnSelect.nextInt();
        System.out.println("Enter a collumn: ");
        int column = rowColumnSelect.nextInt();
        System.out.print("\n\n");

        // using the rows and collumns to create a starting place in the maze
        charByCharArray = StartingPoint(charByCharArray, row, column);

        // printing out the maze showing the START and EXIT
        Echo(charByCharArray);

        int currentCol = startCol;
        int currentRow = startRow;

        // maze position stack
        Stack<Integer> mazePosStack = new Stack<Integer>();
        // push the inital starting position to the stack
        mazePosStack.push(currentCol);
        mazePosStack.push(currentRow);

        int count = 0;

        // recursively solve the maze and save to the 2d array
        FindMazePath(charByCharArray, currentCol, currentRow, mazePosStack, count);

        // Stack<Character> mystack = new Stack<Character>();
        
        // push all sides to a stack to find the current value's surrounding values
        // if (currentCol != 0) {
        //     if (charByCharArray[currentRow][currentCol-1] == '0' || charByCharArray[currentRow][currentCol-1] == 'E') {
        //         mystack.push(charByCharArray[currentRow][currentCol-1]);
        //     }
        // }
        // if (currentCol != 19) {
        //     if (charByCharArray[currentRow][currentCol+1] == '0' || charByCharArray[currentRow][currentCol+1] == 'E') {
        //         mystack.push(charByCharArray[currentRow][currentCol+1]);
        //     }
        // }
        // if (currentRow != 0) {
        //     if (charByCharArray[currentRow-1][currentCol] == '0' || charByCharArray[currentRow-1][currentCol] == 'E') {
        //         mystack.push(charByCharArray[currentRow-1][currentCol]);
        //     }
        // }
        // if (currentRow != 19) {
        //     if (charByCharArray[currentRow+1][currentCol] == '0' || charByCharArray[currentRow+1][currentCol] == 'E') {
        //         mystack.push(charByCharArray[currentRow+1][currentCol]);
        //     }
        // }

        // marking the final path (if there is one) with '+'s
        while (mazePosStack.isEmpty() == false) {
            currentRow = mazePosStack.pop();
            currentCol = mazePosStack.pop();

            charByCharArray[currentRow][currentCol] = '+';

        }

        // removing the unused 'V's from the maze and changing them back to '0'
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (charByCharArray[i][j] == 'V') {
                    charByCharArray[i][j] = '0';
                }
            }
        }

        if (win == false) {
            System.out.println("\n\nHelp, I Am Trapped!\n");
            Echo(charByCharArray);
        }
        else {
            // printing the final result
            System.out.println("\n\nI Am Free!\n");
            Echo(charByCharArray);
        }
        
        //debugging
        // System.out.println(win);

        // System.out.println(mazePosStack.pop());
        // System.out.println(mazePosStack.pop());
        // System.out.println(mazePosStack.pop());
        // System.out.println(mazePosStack.pop());
        // for (Integer x:mazePosStack) {
        //     System.out.println(x);
        // }
    }
}
