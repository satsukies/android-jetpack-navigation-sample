package dev.satsukies.navigation_sample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class EpisodeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_episode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_to_series).setOnClickListener {
            Intent(context, SeriesActivity::class.java).also {
                startActivity(it)
            }
        }

        view.findViewById<Button>(R.id.button_to_episode).setOnClickListener {
            Intent(context, EpisodeActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}