package com.example.bsd_16.ex3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView3;
    private EditText editText;

    static final String KEY_FOR_SAVED_OUTPUT = "savedOutput";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Get the user's current text output
        final TextView textView = (TextView) findViewById(R.id.textView3);
        CharSequence textOutput = textView.getText();
        // Save the user's current text output
        savedInstanceState.putCharSequence(KEY_FOR_SAVED_OUTPUT, textOutput);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore saved text from saved instance
        CharSequence textOutput = savedInstanceState.getCharSequence(KEY_FOR_SAVED_OUTPUT);
        final TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText(textOutput);
    }

    public void clicked(View v) {
        textView3 = (TextView) findViewById(R.id.textView3);
        editText = (EditText) findViewById(R.id.editText);
        String output_msg;

        try {
            double score = Double.valueOf(editText.getText().toString());
            // If score is greater than 100, the following statement is executed,
            // and then jump to catch (InputTooLargeException e).
            if (score > 100) {
                throw new InputTooLargeException();
            }

            char grade = grade(score);
            output_msg = "Your grade is " + grade;
        } catch (NumberFormatException | InputTooLargeException e) {
            output_msg = getString(R.string.err_msg);
        }

        textView3.setText(output_msg);
    }

    protected char grade(double score) {
        if (score >= 90)
            return 'A';
        if (score >= 80)
            return 'B';
        if (score >= 70)
            return 'C';
        if (score >= 60)
            return 'D';
        if (score >= 50)
            return 'E';
        else
            return 'F';
    }
}
