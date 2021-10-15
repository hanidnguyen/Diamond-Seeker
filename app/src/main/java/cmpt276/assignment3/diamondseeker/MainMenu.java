package cmpt276.assignment3.diamondseeker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

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
        //Refresh num panels
        int boardPanels = OptionsPage.getBoardNumPanels(this);
        int diamondsPanels = OptionsPage.getDiamondsNumPanels(this);

        options.setBoard(boardPanels);
        options.setDiamonds(diamondsPanels);
    }

    public static int getRowNum(){
        return options.getRows();
    }
    public static int getColNum(){
        return options.getColumns();
    }
    public static int getDiamondsNum(){
        return options.getDiamonds();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

}