package com.example.calculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private var resultState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
    }

    fun numberClick(clickedView: View) {
        val vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator


        if (clickedView is TextView) {
            if (resultState) {
                resultTextView.text = ""
                resultState = false
            }

            vibe.vibrate(100)
            var viewText = resultTextView.text.toString()
            val btnText = clickedView.text.toString()

            if (viewText == "0")
                viewText = ""

            resultTextView.append(btnText)

        }
    }

    fun operationClick(operationView: View) {
        val vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (operationView is Button) {
            vibe.vibrate(100)
            resultTextView.append(operationView.text)

        }
    }

    fun delete(clickedView: View) {
        if (clickedView is Button) {
            resultTextView.text = ""
        }
    }

    fun onEqual(view: View) {
        if (view is Button) {
            resultState = true
            val str = resultTextView.text.toString()
            val expression = ExpressionBuilder(str).build()

            resultTextView.text = expression.evaluate().toString()
        }
    }
}