import java.util.Arrays;
import java.util.Scanner;

public class ConnectFour {

    static Scanner scnr = new Scanner(System.in);
    static int player1ColumnChoice;
    static int player2ColumnChoice;
    static int rowNum;

    //method prints the board for the user (at beginning, after each time a player inserts a chip)
    public static void printBoard(char[][] array)
    {
        //.replace replaces the square brackets and commas with appropriate empty spaces
        for (int i = array.length-1; i >= 0; i--)
        {
            System.out.println(Arrays.toString(array[i])
                    .replace("[","")
                    .replace(",", " ")
                    .replace("]", ""));
        }
    }

    //method initializes the board with "-" printed in each spot, representing a space with no chip in it
    public static void initializeBoard(char[][] array)
    {
        //goes from left to right, top to bottom filling in the array
        //i is height, j is length
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[0].length; j++)
            {
                array[i][j] = '-';
            }
        }
    }

    //method inserts the chip into the next empty space in the row, accounting for spots already occupied
    //returns the row in which the chip was placed
    public static int insertChip(char[][] array, int col, char chipType)
    {
        rowNum = 0;

        //allows for loop through spots column until empty space is found
        while ( rowNum < array.length )
        {
            //if spot doesn't already have a chip in it then insert a chip
            if ( array[rowNum][col] == 'x' || array[rowNum][col] == 'o')
            {
                rowNum++;
            }
            else
            {
                array[rowNum][col] = chipType;
                break;
            }
        }
        return rowNum;
    }

    //method checks if there is are four chips of same type vertically or horizontally for a win
    //if there is a win the method returns true, otherwise returns false
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType)
    {
        //checks for vertical win
        String checkRow = "";
        char rowPiece;
        String chipsInRow = "";
        String checkCol = "";
        char colPiece;
        String chipsInCol = "";

        //constructs string of pieces in row to check
        for ( int i = 0; i < array[0].length; i++)
        {
            rowPiece = array[row][i];
            checkRow += rowPiece;
        }
        //constructs string of chips in the row that are of the aforementioned chip type
        for ( int j = 0; j <=3; j++ )
        {
            chipsInRow += chipType;
        }
        //checks that pieces in row match string of four aforementioned chips
        if ( checkRow.indexOf(chipsInRow) != -1 )
        {
            return true;
        }

        //checks for horizontal win
        //constructs string of pieces in column to check
        for ( int k = 0; k < array.length; k++)
        {
            colPiece = array[k][col];
            checkCol += colPiece;
        }
        //constructs string of chips in the column that are of the aforementioned chip type
        for ( int h = 0; h <=3; h++ )
        {
            chipsInCol += chipType;
        }
        //checks that pieces in column match string of four aforementioned chips
        if ( checkCol.indexOf(chipsInCol) != -1 )
        {
            return true;
        }

        return false;
    }

    //method is where main body of game is contained
    public static void main (String[] args)
    {
        int numRows;
        int numColumns;
        int i = 0;

        //asks user to give desired board dimensions
        System.out.print("What would you like the height of the board to be? ");
        numRows = scnr.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        numColumns = scnr.nextInt();

        //creates the board with said dimensions by calling correct methods
        char [][] connectFourBoard = new char[numRows][numColumns];
        initializeBoard(connectFourBoard);
        printBoard(connectFourBoard);

        System.out.println("Player 1: x\nPlayer 2: o");

        //players take turn placing their tokens and playing game until a player wins, loses, or there is a tie
        //i < numColumns*numRows lets game play until board is filled up
        while( true )
        {
            if ( i >= (numColumns*numRows))
            {
                System.out.println("Draw. Nobody wins.");
                break;
            }

            //player1 inserts chip, checks for winner, checks for draw
            System.out.print("Player 1: Which column would you like to choose? ");
            player1ColumnChoice = scnr.nextInt();
            insertChip(connectFourBoard,player1ColumnChoice,'x');
            printBoard(connectFourBoard);
            checkIfWinner(connectFourBoard, player1ColumnChoice, rowNum, 'x');
            if (checkIfWinner(connectFourBoard, player1ColumnChoice, rowNum, 'x'))
            {
                System.out.println("Player 1 won the game!");
                break;
            }
            i++;
//            if ( i >= numColumns*numRows )
//            {
//                System.out.println("Draw. Nobody wins.");
//                break;
//            }

            //player2 inserts chip, checks for winner, checks for draw
            System.out.print("Player 2: Which column would you like to choose? ");
            player2ColumnChoice = scnr.nextInt();
            insertChip(connectFourBoard,player2ColumnChoice,'o');
            printBoard(connectFourBoard);
            checkIfWinner(connectFourBoard, player2ColumnChoice, rowNum, 'o');
            if (checkIfWinner(connectFourBoard, player2ColumnChoice, rowNum, 'o'))
            {
                System.out.println("Player 2 won the game!");
                break;
            }
            i++;
//            if ( i >= numColumns*numRows )
//            {
//                System.out.println("Draw. Nobody wins.");
//                break;
//            }
        }
    }
}