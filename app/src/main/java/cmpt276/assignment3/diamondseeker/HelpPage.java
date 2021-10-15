package cmpt276.assignment3.diamondseeker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

/*
    The help activity class
    -   Displays all references for this Diamond Seeker game
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
        TextView txt = findViewById(R.id.ref1);
        txt.setMovementMethod(LinkMovementMethod.getInstance());
        TextView txt2 = findViewById(R.id.ref2);
        txt2.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}