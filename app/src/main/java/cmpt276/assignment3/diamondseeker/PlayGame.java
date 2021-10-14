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

import java.util.Random;

public class PlayGame extends AppCompatActivity {

    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 5;
    private int diamonds = 5;
    Button[][] buttons = new Button[NUM_ROWS][NUM_COLS];
    Boolean[][] diamonds_location = new Boolean[NUM_ROWS][NUM_COLS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange, getTheme())));

        if (diamonds > (NUM_ROWS * NUM_COLS)) throw new AssertionError();

        setupMinesArray();
        populateMines();
        populateTableOfButtons();
    }

    private void setupMinesArray() {
        for(int row = 0; row < NUM_ROWS; row++){
            for(int col = 0; col < NUM_COLS; col++){
                diamonds_location[row][col] = false;
            }
        }
    }

    private void populateMines() {
        Random random = new Random();

        while(diamonds != 0){
            int random_col = random.nextInt(NUM_COLS);
            int random_row = random.nextInt(NUM_ROWS);
            if(!diamonds_location[random_row][random_col]){
                diamonds_location[random_row][random_col] = true;
                diamonds--;
            }
        }

        for(int row = 0; row < NUM_ROWS;row++){
            for(int col = 0; col < NUM_COLS; col++){
                Log.e("PlayGame.java","row: " + row + ", col: " + col +
                        ", mines: " + diamonds_location[row][col] + "\n");
            }
        }

    }


    private void populateTableOfButtons() {
        TableLayout table = findViewById(R.id.tableForButtons);

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
                    button.setText(FINAL_ROW + "," + FINAL_COL);
                    if(diamonds_location[FINAL_ROW][FINAL_COL]) {
                        gridButtonClicked(FINAL_ROW, FINAL_COL);
                    }
                });
                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int row, int col) {
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