package cmpt276.assignment3.diamondseeker.model;

/*
    Game Options logic
    -   stores game's rows columns and number of diamonds.
    -   also uses Singleton model, has its own static instance.
 */

public class GameOptions {
    private int rows, columns, diamonds;
    private int board_option, diamonds_option;
    private static GameOptions instance;

    public GameOptions() {
        board_option = 1;
        diamonds_option = 1;
        setBoard(board_option);
        setDiamonds(diamonds_option);
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

    public void setDiamonds(int optionNum){
        switch (optionNum){
            case 1:
                diamonds = 6;
                diamonds_option = 1;
                break;
            case 2:
                diamonds = 10;
                diamonds_option = 2;
                break;
            case 3:
                diamonds = 15;
                diamonds_option = 3;
                break;
            case 4:
                diamonds = 20;
                diamonds_option = 4;
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

    public int getDiamonds() {
        return diamonds;
    }
}
