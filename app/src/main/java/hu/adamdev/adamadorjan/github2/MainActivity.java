package hu.adamdev.adamadorjan.github2;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

  private static final int SNACKBAR_MODE_MESSAGE = 1;
  private static final int SNACKBAR_MODE_ERROR = 0;
  private static final int SNACKBAR_MODE_WARNING=2;
  EditText editText;
  Button button;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    editText = (EditText) findViewById(R.id.editText);
    button = (Button) findViewById(R.id.button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        buttonPressedEvent();
      }
    });
  }

  private void buttonPressedEvent() {
    if (editText.getText().toString().length()>=5) {
      showSnackbar(editText.getText().toString(), button, SNACKBAR_MODE_MESSAGE);
      editText.setText("");
      editText.setError(null);
    }
    else if(editText.getText().toString().length()>0)
    {
      showSnackbar("Too short message", button, SNACKBAR_MODE_WARNING);
      editText.setError("Min length: 5");
    } else
     {
      showSnackbar("Enter a message!", button, SNACKBAR_MODE_ERROR);
      editText.setError("Enter a message!");
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
    snackbar.setAction("Ok", new View.OnClickListener() {
      @Override public void onClick(View view) {
        snackbar.dismiss();

      }
    });
    snackbar.setActionTextColor(color).show();

  }
}
