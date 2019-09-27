package dev.satsukies.navigation_sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_video_navigation.*

class VideoNavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_navigation)

        val hostFragment = host_fragment as NavHostFragment
        val inflater = hostFragment.navController.navInflater
        val graph: NavGraph = inflater.inflate(R.navigation.nav_video)

        val dst = intent?.extras?.getString("dst") ?: ""

        val startDestinationId = when (dst) {
            "episode" -> R.id.nav_fragment_episode
            "series" -> R.id.nav_fragment_series
            else -> throw IllegalArgumentException("Could not find destination: $dst")
        }

        val arg: Bundle? = when (dst) {
            "episode" -> EpisodeFragmentArgs.Builder(0).build().toBundle()
            "series" -> SeriesFragmentArgs.Builder(0).build().toBundle()
            else -> null
        }

        graph.startDestination = startDestinationId

        Bundle().apply {
            putString("episodeId", "episodeId")
            putString("seriesId", "seriesId")
        }

        hostFragment.navController.setGraph(graph, arg)
    }

    override fun onBackPressed() {
        if (findNavController(R.id.host_fragment).navigateUp()) return

        super.onBackPressed()
    }

    companion object {
        fun createSeriesIntent(context: Context, seriesId: String): Intent {
            return Intent(context, VideoNavigationActivity::class.java).apply {
                putExtra("dst", "series")
                putExtra("id", seriesId)
            }
        }

        fun createEpisodeIntent(context: Context, episodeId: String): Intent {
            return Intent(context, VideoNavigationActivity::class.java).apply {
                putExtra("dst", "episode")
                putExtra("id", episodeId)
            }
        }
    }

}