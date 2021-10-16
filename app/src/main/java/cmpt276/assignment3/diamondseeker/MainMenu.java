package cmpt276.assignment3.diamondseeker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import cmpt276.assignment3.diamondseeker.model.GameOptions;

/*
       Main Menu Activity:
       -    sets up three buttons to start three activities: PlayGame, OptionsPage and HelpPage
       -    gets options from the OptionsPage class through a public getter method
       -    refresh the saved options on resume activity.
 */

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
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
        return super.onOptionsItemSelected(item);
    }


    //Exit app tutorial from
    // https://stackoverflow.com/questions/17719634/
    // how-to-exit-an-android-app-programmatically#:~:text=You%20can%20exit%20from%20the,ActionMain)%3B%20intent.
    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
        super.onBackPressed();
    }
}