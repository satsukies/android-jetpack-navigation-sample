package dev.satsukies.navigation_sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_series.*

class SeriesFragment : Fragment() {

    private val seriesId by lazy {
        SeriesFragmentArgs.fromBundle(arguments!!).seriesId
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_series, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_series.text = "series fragment: $seriesId"

        view.findViewById<Button>(R.id.button_to_series).setOnClickListener {
            findNavController().navigate(SeriesFragmentDirections.toSeries(seriesId + 1))
        }

        view.findViewById<Button>(R.id.button_to_episode).setOnClickListener {
            findNavController().navigate(SeriesFragmentDirections.toEpisode(0))
        }

    }
}