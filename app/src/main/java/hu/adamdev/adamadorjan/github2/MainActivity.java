package hu.adamdev.adamadorjan.github2;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

  private static final int SNACKBAR_MODE_MESSAGE = 1;
  private static final int SNACKBAR_MODE_ERROR = 0;
  private static final int SNACKBAR_MODE_WARNING = 2;
  TextInputLayout textInputLayout;
  EditText editText;
  EditText editTextButtonText;
  ImageButton buttonMessage;
  Button buttonWarning;
  Button buttonError;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
    editText = (EditText) findViewById(R.id.editText);
    editTextButtonText = (EditText) findViewById(R.id.editTextButtonText);

    buttonMessage = (ImageButton) findViewById(R.id.button);
    buttonMessage.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        buttonPressedEvent(SNACKBAR_MODE_MESSAGE);
      }
    });

    buttonWarning = (Button) findViewById(R.id.button2);
    buttonWarning.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        buttonPressedEvent(SNACKBAR_MODE_WARNING);
      }
    });

    buttonError = (Button) findViewById(R.id.button3);
    buttonError.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        buttonPressedEvent(SNACKBAR_MODE_ERROR);
      }
    });
  }

  private void buttonPressedEvent(int mode) {
    switch (mode) {
      case SNACKBAR_MODE_WARNING:
        showSnackbar("Warning message", buttonMessage, SNACKBAR_MODE_WARNING);
        break;
      case SNACKBAR_MODE_ERROR:
        showSnackbar("Error message", buttonMessage, SNACKBAR_MODE_ERROR);
        break;

      default: {
        if (editText.getText().toString().length() >= 5) {
          showSnackbar(editText.getText().toString(), buttonMessage, SNACKBAR_MODE_MESSAGE);
          editText.setText("");
          editTextButtonText.setText("");
          textInputLayout.setError(null);
        } else if (editText.getText().toString().length() > 0) {
          showSnackbar("Too short message", buttonMessage, SNACKBAR_MODE_WARNING);
          textInputLayout.setError("Min length: 5");
        } else {
          showSnackbar("Enter a message!", buttonMessage, SNACKBAR_MODE_ERROR);
          textInputLayout.setError("Enter a message!");
        }
      }
      break;
    }
  }

  private void showSnackbar(String text, View view, int mode) {
    @ColorInt int color;
    switch (mode) {
      case SNACKBAR_MODE_ERROR:
        color = Color.RED;
        break;
      case SNACKBAR_MODE_MESSAGE:
        color = Color.GREEN;
        break;
      case SNACKBAR_MODE_WARNING:
        color = Color.YELLOW;
        break;
      default:
        color = Color.WHITE;
        break;
    }

    final Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE);
    String buttonText;
    if (!editTextButtonText.getText().toString().equals("")) {
      buttonText = editTextButtonText.getText().toString();
    } else {
      buttonText = "Ok";
    }
    snackbar.setAction(buttonText, new View.OnClickListener() {
      @Override public void onClick(View view) {
        snackbar.dismiss();
      }
    });
    snackbar.setActionTextColor(color).show();
  }
}
