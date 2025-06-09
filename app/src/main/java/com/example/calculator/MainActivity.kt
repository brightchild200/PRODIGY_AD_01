package com.example.calculator

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.objecthunter.exp4j.ExpressionBuilder
import com.google.android.material.button.MaterialButton


class MainActivity : AppCompatActivity() {

    private lateinit var solutionTextView: TextView
    private lateinit var resultTextView: TextView
    private var expression: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        solutionTextView = findViewById(R.id.solution)
        resultTextView = findViewById(R.id.result)

        val buttonIds = listOf(
            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
            R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7,
            R.id.button_8, R.id.button_9, R.id.button_dot, R.id.button_plus,
            R.id.button_subtract, R.id.button_multiply, R.id.button_divide,
            R.id.button_c, R.id.button_ac, R.id.button_equalsto,
            R.id.button_opening_bracket, R.id.button_closing_bracket
        )

        for (id in buttonIds) {
            val button = findViewById<MaterialButton>(id)
            button.setOnClickListener { onButtonClick(button.text.toString()) }
        }
    }

    private fun onButtonClick(text: String) {
        when (text) {
            "=" -> {
                try {
                    val exp = ExpressionBuilder(expression).build()
                    val result = exp.evaluate()
                    resultTextView.text = result.toString()
                } catch (e: Exception) {
                    resultTextView.text = "Error"
                }
            }
            "AC" -> {
                expression = ""
                solutionTextView.text = ""
                resultTextView.text = "0"
            }
            "C" -> {
                if (expression.isNotEmpty()) {
                    expression = expression.dropLast(1)
                    solutionTextView.text = expression
                }
            }
            else -> {
                expression += text
                solutionTextView.text = expression
            }
        }
    }
}