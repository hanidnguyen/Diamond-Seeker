package cmpt276.assignment3.diamondseeker.model;

public class GameOptions {
    private int rows, columns, mines;
    private int board_option, mines_option;
    private static GameOptions instance;

    public GameOptions() {
        board_option = 1;
        mines_option = 1;
        setBoard(board_option);
        setMines(mines_option);
    }

    //Applies the Singleton model class
    public static GameOptions getInstance(){
        if(instance == null){
            instance = new GameOptions();
        }
        return instance;
    }

    public void setBoard(int optionNum){
        switch(optionNum){
            case 1:
                this.rows = 4;
                this.columns = 6;
                board_option = 1;
                break;
            case 2:
                this.rows = 5;
                this.columns = 10;
                board_option = 2;
                break;
            case 3:
                this.rows = 6;
                this.columns = 15;
                board_option = 3;
                break;
            default:
                break;
        }
    }

    public void setMines(int optionNum){
        switch (optionNum){
            case 1:
                mines = 6;
                mines_option = 1;
                break;
            case 2:
                mines = 10;
                mines_option = 2;
                break;
            case 3:
                mines = 15;
                mines_option = 3;
                break;
            case 4:
                mines = 20;
                mines_option = 4;
                break;
            default:
                break;

        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getMines() {
        return mines;
    }
}
