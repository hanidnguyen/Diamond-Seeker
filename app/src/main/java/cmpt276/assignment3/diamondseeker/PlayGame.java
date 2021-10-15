package cmpt276.assignment3.diamondseeker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Objects;
import java.util.Random;

public class PlayGame extends AppCompatActivity {

    private static int NUM_ROWS = MainMenu.getRowNum();
    private static int NUM_COLS = MainMenu.getColNum();
    private static int diamonds = MainMenu.getMinesNum();
    Button[][] buttons = new Button[NUM_ROWS][NUM_COLS];
    Boolean[][] diamonds_location = new Boolean[NUM_ROWS][NUM_COLS];
    Boolean[][] buttons_revealed = new Boolean[NUM_ROWS][NUM_COLS];

    @Override
    protected void onResume() {
        super.onResume();
        NUM_ROWS = MainMenu.getRowNum();
        NUM_COLS = MainMenu.getColNum();
        Log.e("PlayGame","getMinesNum(): " + MainMenu.getMinesNum());
        diamonds = MainMenu.getMinesNum();
        buttons = new Button[NUM_ROWS][NUM_COLS];
        diamonds_location = new Boolean[NUM_ROWS][NUM_COLS];
        buttons_revealed = new Boolean[NUM_ROWS][NUM_COLS];

        if (diamonds > (NUM_ROWS * NUM_COLS)) throw new AssertionError();

        setupBooleanArrays();
        populateMines();
        populateTableOfButtons();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange, getTheme())));

        if (diamonds > (NUM_ROWS * NUM_COLS)) throw new AssertionError();

        setupBooleanArrays();
        populateMines();
    }

    private void setupBooleanArrays() {
        for(int row = 0; row < NUM_ROWS; row++){
            for(int col = 0; col < NUM_COLS; col++){
                diamonds_location[row][col] = false;
                buttons_revealed[row][col] = false;
            }
        }
    }

    private void populateMines() {
        Random random = new Random();
        int counter = diamonds;
        while(counter != 0){
            int random_col = random.nextInt(NUM_COLS);
            int random_row = random.nextInt(NUM_ROWS);
            if(!diamonds_location[random_row][random_col]){
                diamonds_location[random_row][random_col] = true;
                counter--;
            }
        }

    }


    private void populateTableOfButtons() {
        TableLayout table = findViewById(R.id.tableForButtons);

        Log.e("PlayGame","NUM_ROWS: " + NUM_ROWS);

        for(int row = 0; row < NUM_ROWS;row++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);

            for(int col = 0; col < NUM_COLS;col++){
                final int FINAL_COL = col;
                final int FINAL_ROW = row;
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                //Make text not clip on small buttons
                button.setPadding(0,0,0,0);
                button.setOnClickListener(view -> {
                    buttons_revealed[FINAL_ROW][FINAL_COL] = true;
                    if(diamonds_location[FINAL_ROW][FINAL_COL]) {
                        showDiamond(FINAL_ROW, FINAL_COL);

                    }
                    refreshGrid();
                });
                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void refreshGrid() {
        for(int row = 0; row < NUM_ROWS; row++){
            for(int col = 0; col < NUM_COLS; col++){
                if(buttons_revealed[row][col]){
                    int diamonds_number = displayNumberOfDiamonds(row,col);
                    Button button = buttons[row][col];
                    button.setText(""+diamonds_number);
                }
            }
        }
    }

    //ROW and COL are given location of button to be scanned
    //Scan the corresponsing row and column for diamonds, then
    //return number of diamonds.
    private int displayNumberOfDiamonds(int ROW, int COL) {
        Button button = buttons[ROW][COL];
        int diamonds_number = 0;
        for(int col = 0; col < NUM_COLS; col++){
            if(diamonds_location[ROW][col] && (col != COL)){
                diamonds_number++;
            }
        }

        for(int row = 0; row < NUM_ROWS; row++){
            if(diamonds_location[row][COL] && (row != ROW)){
                diamonds_number++;
            }
        }

        if(diamonds_location[ROW][COL]){
            diamonds_number++;
        }
        return diamonds_number;
    }

    private void showDiamond(int row, int col) {
        Toast.makeText(this,"button clicked: " + row + "," + col,Toast.LENGTH_SHORT).show();
        Button button = buttons[row][col];

        //Lock Button sizes:
        lockButtonSizes();

        //Scale image to buttons
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.diamond_icon);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap,newWidth,newHeight,true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource,scaledBitmap));

        //set diamond location to be false because diamond is found
        diamonds_location[row][col] = false;

    }

    private void lockButtonSizes() {
        for(int row = 0; row < NUM_ROWS; row++){
            for(int col = 0; col < NUM_COLS; col++){
                Button button = buttons[row][col];
                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

}