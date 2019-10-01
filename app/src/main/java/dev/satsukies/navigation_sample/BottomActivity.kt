package dev.satsukies.navigation_sample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bottom.*

class BottomActivity : AppCompatActivity() {

    init {
        DebugLifecycleObserver("bottom").also {
            lifecycle.addObserver(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom)

        button_open.setOnClickListener {
            Intent(this, TranslucentActivity::class.java).also {
                startActivity(it)
            }
        }

        Log.d("debug:lifecycle:bottom", "lifecycle:onCreate")
    }

    override fun onStart() {
        super.onStart()

        Log.d("debug:lifecycle:bottom", "lifecycle:onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.d("debug:lifecycle:bottom", "lifecycle:onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.d("debug:lifecycle:bottom", "lifecycle:onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.d("debug:lifecycle:bottom", "lifecycle:onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("debug:lifecycle:bottom", "lifecycle:onDestroy")
    }
}
