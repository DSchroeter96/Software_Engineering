package ch.epfl.sweng;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The main entry point of the application.
 */
public final class MainActivity extends AppCompatActivity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainGoButton = findViewById(R.id.mainGoButton);
        Intent intent = new Intent(this, NewsActivity.class);
        mainGoButton.setOnClickListener(v -> startActivity(intent));
    }
}
