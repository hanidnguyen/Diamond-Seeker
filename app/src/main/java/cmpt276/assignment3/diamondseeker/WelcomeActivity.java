package cmpt276.assignment3.diamondseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    Animation topAnim;
    ImageView image;
    TextView title;
    TextView author;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);

        image = findViewById(R.id.welcome_diamond_icon);
        title = findViewById(R.id.game_title);
        author = findViewById(R.id.author);

        image.setAnimation(topAnim);
        title.setAnimation(topAnim);
        author.setAnimation(topAnim);



        setMainMenuButton();
    }

    private void setMainMenuButton() {
        Button btn = findViewById(R.id.main_menu_btn);
        btn.setOnClickListener(view -> {
            Intent intent = new Intent(WelcomeActivity.this,MainMenu.class);
            startActivity(intent);
        });
    }
}