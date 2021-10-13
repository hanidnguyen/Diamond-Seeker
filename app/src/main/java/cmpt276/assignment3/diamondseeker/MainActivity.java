package cmpt276.assignment3.diamondseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMainMenuButton();
    }

    private void setMainMenuButton() {
        Button btn = findViewById(R.id.main_menu_btn);
        btn.setOnClickListener(view -> {

        });
    }
}