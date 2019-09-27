package dev.satsukies.navigation_sample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_to_video).setOnClickListener {
            Intent(context, VideoActivity::class.java).also {
                startActivity(it)
            }
        }

        view.findViewById<Button>(R.id.button_to_timetable).setOnClickListener {
            Intent(context, TimetableActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}