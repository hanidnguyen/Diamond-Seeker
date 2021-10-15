package cmpt276.assignment3.diamondseeker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import cmpt276.assignment3.diamondseeker.model.GameOptions;

public class MainMenu extends AppCompatActivity {
    private static GameOptions options = new GameOptions();

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
            startActivity(intent);
        });
    }

    private void setupOptions() {

        Button option_btn = findViewById(R.id.options);
        option_btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenu.this,OptionsPage.class);
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
        TextView tvBoardPanels = findViewById(R.id.board_number);
        TextView tvMinesPanels = findViewById(R.id.mines_number);
        int boardPanels = OptionsPage.getBoardNumPanels(this);
        int minesPanels = OptionsPage.getMinesNumPanels(this);
        tvBoardPanels.setText(""+boardPanels);
        tvMinesPanels.setText(""+minesPanels);

        options.setBoard(boardPanels);
        options.setMines(minesPanels);
    }

    public static int getRowNum(){
        Log.e("MainMenu.java","options rows: " + options.getRows());
        return options.getRows();
    }
    public static int getColNum(){
        Log.e("MainMenu.java","options col: " + options.getColumns());
        return options.getColumns();
    }
    public static int getMinesNum(){
        Log.e("MainMenu.java","options mines: " + options.getMines());
        return options.getMines();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

}