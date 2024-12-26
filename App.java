import java.util.Scanner;
import java.util.Random;

public class App{
    private static final int BOARD_SIZE =10;
    private static final char EMPTY = '.';
    private static final char SNAKE = 'S';
    private static final char FOOD = 'F';
    
    private static int[][] board;
    private static int[] snakehead = {0, 0};
    private static int snakelength = 1;
    private static int[] food = new int[2];
    private static boolean gameover = false;

    public static void main(String[] args){
        board = new int[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
        spawnFood();
        Scanner scanner = new Scanner(System.in);
         
        while(!gameover) {
            printBoard();
            System.out.println("Enter move (WASD)");
            char move = scanner.next().toUpperCase().charAt(0);
            processMove(move);

        }
        System.out.println("Game Over! Your Score:"+(snakelength -1));
        scanner.close();
    }
    private static void initializeBoard(){
        for(int i=0; i<BOARD_SIZE; i++){
            for(int j=0; j<BOARD_SIZE; j++){
                board[i][j] = EMPTY;

            }
        }
        board[snakehead[0]][snakehead[1]] = SNAKE;

    }
    private static void spawnFood(){
        Random random = new Random();
        do { 
            food[0] = random.nextInt(BOARD_SIZE);
            food[1] = random.nextInt(BOARD_SIZE);
        } while (board[food[0]][food[1]] == SNAKE);
        board[food[0]][food[1]] = FOOD;
    }

    private static void printBoard(){
        for(int i=0; i<BOARD_SIZE; i++){
            for(int j=0; j<BOARD_SIZE; j++){
                if(i == snakehead[0] && j== snakehead[1]){
                    System.out.print(SNAKE + " ");
                }
                else if(i == food[0] &&  j == food[1]){
                    System.out.print(FOOD + " ");
                }else{
                    System.out.print(EMPTY + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    private static void processMove(char move){
        int newRow = snakehead[0];
        int newCol = snakehead[1];

        switch(move){
            case 'W' -> newRow--;
            case 'A' -> newCol--;
            case 'S' -> newRow++;
            case 'D' -> newCol++;
            default -> {
                System.out.println("INVALID MOVE!");
                return;
            }
        }
        if(newRow < 0 || newCol < 0 || newRow >= BOARD_SIZE || newCol >= BOARD_SIZE){
            gameover = true;
            return;
        }
        if(newRow ==food[0] && newCol == food[1]){
            snakelength++;
            spawnFood();
        }else if(board[newRow][newCol] == SNAKE){
            gameover = true;
            return;
        }
        board[snakehead[0]][snakehead[1]] = EMPTY;
        snakehead[0] = newRow;
        snakehead[1] = newCol;
        board[snakehead[0]][snakehead[1]] = SNAKE;
    }
}