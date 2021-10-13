package cmpt276.assignment3.diamondseeker.model;

public class GameOptions {
    private int rows, columns, mines;
    private static GameOptions instance;

    public GameOptions() {
        setBoard(1);
        setMines(1);
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
                break;
            case 2:
                this.rows = 5;
                this.columns = 10;
                break;
            case 3:
                this.rows = 6;
                this.columns = 15;
                break;
            default:
                break;
        }
    }

    public void setMines(int optionNum){
        switch (optionNum){
            case 1:
                mines = 6;
                break;
            case 2:
                mines = 10;
                break;
            case 3:
                mines = 15;
                break;
            case 4:
                mines = 20;
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
