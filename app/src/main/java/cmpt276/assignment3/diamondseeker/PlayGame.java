package cmpt276.assignment3.diamondseeker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Objects;
import java.util.Random;

/*
    Play game activity:
    -   Construct a grid of buttons according to given grid and number of diamonds
    -   Upon a click:
            -> a sound will play, blip.mp3 if not diamond, chime.mp3 if found a diamond
            -> animation will play for buttons being scanned for diamonds
            -> updates number of diamonds for all revealed buttons
            -> updates the stats: diamonds found and number of scans
            -> if the button is already pressed, none of the above feature will play
    -   Once all diamonds found, a dialog will appear to congratulate user.
 */

public class PlayGame extends AppCompatActivity {

    private static int NUM_ROWS = MainMenu.getRowNum();
    private static int NUM_COLS = MainMenu.getColNum();
    private static int total_diamonds = MainMenu.getDiamondsNum();
    private int total_scans = 0;
    private int diamonds_found = 0;
    Button[][] buttons = new Button[NUM_ROWS][NUM_COLS];
    Boolean[][] diamonds_location = new Boolean[NUM_ROWS][NUM_COLS];
    Boolean[][] buttons_revealed = new Boolean[NUM_ROWS][NUM_COLS];

    MediaPlayer player;

    @Override
    protected void onRestart() {
        super.onRestart();
        NUM_ROWS = MainMenu.getRowNum();
        NUM_COLS = MainMenu.getColNum();
        total_diamonds = MainMenu.getDiamondsNum();
        total_scans = 0;
        diamonds_found = 0;

        buttons = new Button[NUM_ROWS][NUM_COLS];
        diamonds_location = new Boolean[NUM_ROWS][NUM_COLS];
        buttons_revealed = new Boolean[NUM_ROWS][NUM_COLS];

        if (total_diamonds > (NUM_ROWS * NUM_COLS)) throw new AssertionError();

        setupBooleanArrays();
        populateDiamonds();
        showStats();
        populateTableOfButtons();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        if (total_diamonds > (NUM_ROWS * NUM_COLS)) throw new AssertionError();

        setupBooleanArrays();
        populateDiamonds();
        showStats();
        populateTableOfButtons();
    }

    private void setupBooleanArrays() {
        for(int row = 0; row < NUM_ROWS; row++){
            for(int col = 0; col < NUM_COLS; col++){
                diamonds_location[row][col] = false;
                buttons_revealed[row][col] = false;
            }
        }
    }

    private void populateDiamonds() {
        Random random = new Random();
        int counter = total_diamonds;
        while(counter != 0){
            int random_col = random.nextInt(NUM_COLS);
            int random_row = random.nextInt(NUM_ROWS);
            if(!diamonds_location[random_row][random_col]){
                diamonds_location[random_row][random_col] = true;
                counter--;
            }
        }

    }

    private void showStats() {
        TextView tv_diamonds_found = findViewById(R.id.found_diamond);
        TextView tv_total_diamonds = findViewById(R.id.total_diamonds);
        TextView tv_number_scans = findViewById(R.id.number_scans);

        tv_diamonds_found.setText(""+0);
        tv_total_diamonds.setText("" + total_diamonds);
        tv_number_scans.setText(""+0);
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

                    if(!buttons_revealed[FINAL_ROW][FINAL_COL]){
                        scanAnimation(FINAL_ROW,FINAL_COL);
                        if(!diamonds_location[FINAL_ROW][FINAL_COL]){
                            player=MediaPlayer.create(PlayGame.this,R.raw.blip);
                        } else{
                            player=MediaPlayer.create(PlayGame.this,R.raw.chime);
                        }
                        player.start();
                        total_scans++;
                        buttons_revealed[FINAL_ROW][FINAL_COL] = true;
                    }
                    if(diamonds_location[FINAL_ROW][FINAL_COL]) {
                        player=MediaPlayer.create(PlayGame.this,R.raw.chime);
                        player.start();
                        diamonds_found++;
                        showDiamond(FINAL_ROW, FINAL_COL);
                    }

                    refreshGrid();
                });
                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void scanAnimation(int ROW, int COL) {
        Button button = buttons[ROW][COL];
        final Animation button_anim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(button_anim);
        for(int col = 0; col < NUM_COLS; col++){
            if(col != COL){
                buttons[ROW][col].startAnimation(button_anim);
            }
        }

        for(int row = 0; row < NUM_ROWS; row++){
            if(row != ROW){
                buttons[row][COL].startAnimation(button_anim);
            }
        }
    }


    //ROW and COL are given location of button to be scanned
    //Scan the corresponding row and column for diamonds, then
    //return number of diamonds.
    private int displayNumberOfDiamonds(int ROW, int COL) {

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

    private void refreshGrid() {
        TextView diamonds_count = findViewById(R.id.found_diamond);
        TextView scans = findViewById(R.id.number_scans);
        diamonds_count.setText("" + diamonds_found);
        scans.setText("" + total_scans);
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                if (buttons_revealed[row][col]) {
                    int diamonds_number = displayNumberOfDiamonds(row, col);
                    Button button = buttons[row][col];
                    button.setText("" + diamonds_number);
                }
            }
        }

        if (diamonds_found == total_diamonds) {
            new AlertDialog.Builder(PlayGame.this)
                    .setTitle("Congratulations!")
                    .setMessage("Good work on finding all the diamonds!")
                    .setPositiveButton("OK", (dialog, which) -> finish())
                    .setIcon(getDrawable(R.drawable.smiley_icon))
                    .show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

}