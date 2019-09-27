package dev.satsukies.navigation_sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_episode.*

class EpisodeFragment : Fragment() {

    private val episodeId by lazy {
        EpisodeFragmentArgs.fromBundle(arguments!!).episodeId
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_episode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_episode.text = "episode fragment: $episodeId"

        view.findViewById<Button>(R.id.button_to_series).setOnClickListener {
            findNavController().navigate(EpisodeFragmentDirections.toSeries(0))
        }

        view.findViewById<Button>(R.id.button_to_episode).setOnClickListener {
            findNavController().navigate(EpisodeFragmentDirections.toEpisode(episodeId + 1))
        }

    }


}