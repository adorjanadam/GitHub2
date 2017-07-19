package hu.adamdev.adamadorjan.github2

import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
/*  val editText = findViewById(R.id.editText) as EditText
  val button = findViewById(R.id.button) as Button*/
    button.setOnClickListener {
      if (editText.text.toString() != "") {
        showSnackbar(editText.text.toString(), button, SNACKBAR_MODE_MESSAGE, "Hide")
        editText.setText("")
        editText.error = null

      } else {
        showSnackbar("Enter a message!", button, SNACKBAR_MODE_ERROR)
        editText.error = "Enter a message!"
      }
    }
  }

  private fun showSnackbar(text: String, view: View, mode: Int= SNACKBAR_MODE_MESSAGE,buttonText:String="Ok") {
    @ColorInt val color: Int
    when (mode) {
      SNACKBAR_MODE_ERROR -> color = Color.RED
      SNACKBAR_MODE_MESSAGE -> color = Color.GREEN
      else -> color = Color.WHITE
    }

    val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE)
    snackbar.setAction(buttonText) { snackbar.dismiss() }
    snackbar.setActionTextColor(color).show()

  }

  companion object {

    private val SNACKBAR_MODE_MESSAGE = 1
    private val SNACKBAR_MODE_ERROR = 0
  }
}
