package cmpt276.assignment3.diamondseeker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OptionsPage extends AppCompatActivity {
    private int board_option, mines_option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_page);
        setSupportActionBar(findViewById(R.id.options_toolbar));
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        get_from_MainMenu();
        createBoardSizeRadioButtons();
    }

    private void get_from_MainMenu() {
        Intent intent = getIntent();
        board_option = intent.getIntExtra("BOARD_OPTION",1);
        mines_option = intent.getIntExtra("MINES_OPTION",1);

    }

    private void createBoardSizeRadioButtons() {
        RadioGroup group = findViewById(R.id.board_size_radio);

        int[] numPanels = getResources().getIntArray(R.array.num_board_size);
        String[] optionsDisplay = getResources().getStringArray(R.array.board_options_display);
        //create the buttons
        for(int i = 0; i < numPanels.length; i++){
            final int numPanel = numPanels[i];

            RadioButton button = new RadioButton(this);
            button.setText(optionsDisplay[i]);

            //TODO: Set on-click callbacks
            button.setOnClickListener(view -> {
                board_option = numPanel;
            });

            //Add to radio group:
            group.addView(button);
        }

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        intent.putExtra("BOARD_OPTION",board_option);
        intent.putExtra("MINES_OPTION",mines_option);

        setResult(RESULT_OK,intent);

        finish();
        return super.onOptionsItemSelected(item);
    }
}