package cmpt276.assignment3.diamondseeker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/*
    Options Activity:
    -   Sets up radio buttons for user to select grid and number of diamonds to play
    -   Save options using SharedPreferences
    -   Constructed public getters to access options chosen
 */

public class OptionsPage extends AppCompatActivity {
    private static final String BOARD_NUM_NAME = "Board Number";
    private static final String DIAMONDS_NUM_NAME = "Diamonds Number";
    private static final String PREFS_NAME = "AppPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_page);
        setSupportActionBar(findViewById(R.id.options_toolbar));
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        createBoardSizeRadioButtons();
        createDiamondsRadioButtons();
    }



    private void createBoardSizeRadioButtons() {
        RadioGroup group = findViewById(R.id.board_size_radio);

        int[] numPanels = getResources().getIntArray(R.array.num_board_size);
        String[] board_options_display = getResources().getStringArray(R.array.board_options_display);
        //create the buttons
        for(int i = 0; i < numPanels.length; i++){
            final int numPanel = numPanels[i];

            RadioButton button = new RadioButton(this);
            button.setText(board_options_display[i]);

            button.setOnClickListener(view ->
                    saveBoardOption(numPanel)
            );

            //Add to radio group:
            group.addView(button);

            //Select default button:
            if(numPanel == getBoardNumPanels(this)){
                button.setChecked(true);
            }
        }

    }

    private void createDiamondsRadioButtons() {
        RadioGroup group = findViewById(R.id.diamonds_size_radio);
        int[] numPanels = getResources().getIntArray(R.array.diamonds_number);
        String[] diamonds_option_display = getResources().getStringArray(R.array.diamonds_options_display);

        for(int i = 0; i < numPanels.length; i++){
            final int numPanel = numPanels[i];

            RadioButton button = new RadioButton(this);
            button.setText(diamonds_option_display[i]);

            button.setOnClickListener(view -> saveDiamondsOption(numPanel));
            group.addView(button);
            if(numPanel == getDiamondsNumPanels(this)){
                button.setChecked(true);
            }
        }
    }

    private void saveBoardOption(int numPanel) {
        SharedPreferences prefs = this.getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(BOARD_NUM_NAME,numPanel);
        editor.apply();
    }

    private void saveDiamondsOption(int numPanel) {
        SharedPreferences prefs = this.getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(DIAMONDS_NUM_NAME,numPanel);
        editor.apply();
    }

    static public int getBoardNumPanels(Context context){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,MODE_PRIVATE);

        int default_board_size = context.getResources().getInteger(R.integer.default_board_size);

        return prefs.getInt(BOARD_NUM_NAME,default_board_size);
    }

    static public int getDiamondsNumPanels(Context context){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,MODE_PRIVATE);

        int default_diamonds_size = context.getResources().getInteger(R.integer.default_diamond);

        return prefs.getInt(DIAMONDS_NUM_NAME,default_diamonds_size);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);
    }
}