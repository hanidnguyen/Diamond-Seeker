package cmpt276.assignment3.diamondseeker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import cmpt276.assignment3.diamondseeker.model.GameOptions;

public class MainMenu extends AppCompatActivity {
    private GameOptions options = new GameOptions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setSupportActionBar(findViewById(R.id.main_menu_toolbar));
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        setupPlayGame();
        setupOptions();
        setupHelp();
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
        });
    }

    private void setupHelp() {
        Button help_btn = findViewById(R.id.help);
        help_btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenu.this,HelpPage.class);
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

}