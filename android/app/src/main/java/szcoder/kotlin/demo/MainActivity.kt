package szcoder.kotlin.demo

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = TextView(this);
        view.setText("Tap me to crash!")
        view.setOnClickListener() {
            MyLibrary.callMeToCrash()
        }
        setContentView(view)
    }
}