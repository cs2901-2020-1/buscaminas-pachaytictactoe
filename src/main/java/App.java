import java.util.Random;
import java.util.Scanner;

public class App {

    private static final int SIZE = 9;
    static int[][] mine_board = new int[9][9];
    static char[][] player_board = new char[9][9];
    static boolean playing = true;

    static void gameOver(){
        System.out.println("Game over!\n");
    }

    static boolean exist(int x, int y){
        if( x >= 0 && y >= 0 && x < SIZE && y < SIZE){
            if(player_board[x][y] == '-'){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }

    }

    static char countMines(int x, int y){
        int counter = 0;
        if(exist(x+1,y))
        if(mine_board[x+1][y] == 1){
            counter++;
        }
        if(exist(x+1,y+1))
        if(mine_board[x+1][y+1] == 1){
            counter++;
        }

        if(exist(x+1,y-1))
        if(mine_board[x+1][y-1] == 1){
            counter++;
        }

        if(exist(x,y+1))
        if(mine_board[x][y+1] == 1){
            counter++;
        }

        if(exist(x,y-1))
        if(mine_board[x][y-1] == 1){
            counter++;
        }

        if(exist(x-1,y+1))
        if(mine_board[x-1][y+1] == 1){
            counter++;
        }

        if(exist(x-1,y))
        if(mine_board[x-1][y] == 1){
            counter++;
        }

        if(exist(x-1,y-1))
        if(mine_board[x-1][y-1] == 1){
            counter++;
        }
        return (char)(counter+'0');
    }

    static void reveal(int x, int y){
        if(exist(x,y)) {
            if (mine_board[x][y] == 0) {
                char count = countMines(x,y);
                player_board[x][y] = count;
                if(count == '0'){
                    reveal(x+1,y);
                    reveal(x+1,y+1);
                    reveal(x+1,y-1);
                    reveal(x,y+1);
                    reveal(x,y-1);
                    reveal(x-1,y);
                    reveal(x-1,y+1);
                    reveal(x-1,y-1);
                }
            }
            else {
                player_board[x][y]='X';
                printMatrix('b');
                playing = false;
            }
        }
    }

    static int[] input(){

        int[] coords = new int[2];
        Scanner in = new Scanner(System.in);
        int x;
        int y;

        System.out.println("Input X coordinate: ");
        x = in.nextInt();
        System.out.println("Input Y coordinate: ");
        y = in.nextInt();

        coords[0] = x;
        coords[1] = y;

        return coords;

    }

    static void printMatrix(char x){

        if(x == 'a'){
            for(int i = 0; i < SIZE; i++){
                for(int j = 0; j < SIZE; j++){
                    System.out.print(mine_board[i][j]+"\t");
                }
                System.out.print("\n");
            }
        }

        else if(x == 'b'){
            for(int i = 0; i < SIZE; i++){
                for(int j = 0; j < SIZE; j++){
                    System.out.print(player_board[i][j]+"\t");
                }
                System.out.print("\n");
            }
        }
    }

    static void initBoard(){
        Random rand = new Random();

        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                int aux = rand.nextInt(15);
                if(aux%5==0){
                    mine_board[i][j] = 1;

                }
                else{
                    mine_board[i][j] = 0;
                }
                player_board[i][j] = '-';
            }
        }
    }

    public static void main(String[] args){
        initBoard();
        printMatrix('a');
        while(playing){
            printMatrix('b');
            int[] choice = input();
            reveal(choice[0],choice[1]);
        }
        System.out.println("Hola munda");
    }

}
