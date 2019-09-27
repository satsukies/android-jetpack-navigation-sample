package dev.satsukies.navigation_sample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class TimetableFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timetable, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_to_feed).setOnClickListener {
            Intent(context, FeedActivity::class.java).also {
                startActivity(it)
            }
        }

        view.findViewById<Button>(R.id.button_to_episode).setOnClickListener {
            VideoNavigationActivity.createEpisodeIntent(requireContext(), "episodeId").also {
                startActivity(it)
            }
        }

        view.findViewById<Button>(R.id.button_to_series).setOnClickListener {
            VideoNavigationActivity.createSeriesIntent(requireContext(), "seriesId").also {
                startActivity(it)
            }
        }

    }

}