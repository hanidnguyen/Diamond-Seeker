package cmpt276.assignment3.diamondseeker;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cmpt276.assignment3.diamondseeker.model.GameOptions;

public class MainMenu extends AppCompatActivity {
    private GameOptions options = new GameOptions();

    @Override
    protected void onResume() {
        super.onResume();
        refreshScreen();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setSupportActionBar(findViewById(R.id.main_menu_toolbar));
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        options = GameOptions.getInstance();

        setupPlayGame();
        setupOptions();
        setupHelp();
        refreshScreen();
    }



    private void setupPlayGame() {
        Button play_btn = findViewById(R.id.play_game);
        play_btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenu.this,PlayGame.class);

        });
    }

    private void setupOptions() {

        Button option_btn = findViewById(R.id.options);
        option_btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenu.this,OptionsPage.class);
            intent.putExtra("BOARD_OPTION",options.getBoard_option());
            intent.putExtra("MINES_OPTION",options.getMines_option());
            startActivity(intent);
        });
    }

    private void setupHelp() {
        Button help_btn = findViewById(R.id.help);
        help_btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenu.this,HelpPage.class);
            startActivity(intent);
        });
    }

    private void refreshScreen() {
        //Refresh num panels display
        TextView tvNumPanels = findViewById(R.id.board_number);
        int numPanels = OptionsPage.getNumPanels(this);
        tvNumPanels.setText(""+numPanels);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

}