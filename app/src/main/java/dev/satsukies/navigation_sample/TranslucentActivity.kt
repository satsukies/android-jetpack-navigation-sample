package dev.satsukies.navigation_sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class TranslucentActivity : AppCompatActivity() {

    init {
        DebugLifecycleObserver("trans").also {
            lifecycle.addObserver(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translucent)

        Log.d("debug:lifecycle:trans", "lifecycle:onCreate")
    }

    override fun onStart() {
        super.onStart()

        Log.d("debug:lifecycle:trans", "lifecycle:onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.d("debug:lifecycle:trans", "lifecycle:onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.d("debug:lifecycle:trans", "lifecycle:onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.d("debug:lifecycle:trans", "lifecycle:onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("debug:lifecycle:trans", "lifecycle:onDestroy")
    }
}