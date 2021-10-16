package cmpt276.assignment3.diamondseeker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

/*
    The help activity class
    -   Displays all references for this Diamond Seeker game app
 */

public class HelpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page);
        setSupportActionBar(findViewById(R.id.help_toolbar));
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        setupHyperlinks();
    }

    private void setupHyperlinks() {
        TextView author_ref = findViewById(R.id.author_ref);
        TextView txt1 = findViewById(R.id.ref1);
        TextView txt2 = findViewById(R.id.ref2);
        TextView txt3 = findViewById(R.id.ref3);
        TextView txt4 = findViewById(R.id.ref4);
        TextView txt5 = findViewById(R.id.ref5);
        TextView txt6 = findViewById(R.id.ref6);
        TextView txt7 = findViewById(R.id.ref7);
        TextView txt8 = findViewById(R.id.ref8);
        TextView txt9 = findViewById(R.id.ref9);
        TextView txt10 = findViewById(R.id.ref10);
        TextView txt11 = findViewById(R.id.ref11);


        author_ref.setMovementMethod(LinkMovementMethod.getInstance());
        txt1.setMovementMethod(LinkMovementMethod.getInstance());
        txt2.setMovementMethod(LinkMovementMethod.getInstance());
        txt3.setMovementMethod(LinkMovementMethod.getInstance());
        txt4.setMovementMethod(LinkMovementMethod.getInstance());
        txt5.setMovementMethod(LinkMovementMethod.getInstance());
        txt6.setMovementMethod(LinkMovementMethod.getInstance());
        txt7.setMovementMethod(LinkMovementMethod.getInstance());
        txt8.setMovementMethod(LinkMovementMethod.getInstance());
        txt9.setMovementMethod(LinkMovementMethod.getInstance());
        txt10.setMovementMethod(LinkMovementMethod.getInstance());
        txt11.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}