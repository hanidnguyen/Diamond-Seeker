package cmpt276.assignment3.diamondseeker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class OptionsPage extends AppCompatActivity {
    private static final String BOARD_NUM_NAME = "Board Number";
    private static final String PREFS_NAME = "AppPrefs";

    public static Intent makeIntent(Context context){
        return new Intent(context,OptionsPage.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_page);
        setSupportActionBar(findViewById(R.id.options_toolbar));
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        createBoardSizeRadioButtons();
    }

    private void createBoardSizeRadioButtons() {
        RadioGroup group = findViewById(R.id.board_size_radio);

        int[] numPanels = getResources().getIntArray(R.array.num_board_size);
        String[] board_optionsDisplay = getResources().getStringArray(R.array.board_options_display);
        //create the buttons
        for(int i = 0; i < numPanels.length; i++){
            final int numPanel = numPanels[i];

            RadioButton button = new RadioButton(this);
            button.setText(board_optionsDisplay[i]);

            button.setOnClickListener(view -> 
                    {
                        saveBoardOption(numPanel);
                    }
                );

            //Add to radio group:
            group.addView(button);

            //Select default button:
            if(numPanel == getNumPanels(this)){
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

    static public int getNumPanels(Context context){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,MODE_PRIVATE);

        int default_board_size = context.getResources().getInteger(R.integer.default_board_size);

        return prefs.getInt(BOARD_NUM_NAME,default_board_size);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);
    }
}