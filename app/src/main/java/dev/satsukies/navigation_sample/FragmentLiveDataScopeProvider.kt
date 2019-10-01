package dev.satsukies.navigation_sample

interface FragmentLiveDataScopeProvider {

    fun correspondingLifecycleEvents(): (FragmentEvent) -> FragmentEvent

    fun getLastLifecycleEvent(): FragmentEvent

    companion object {
        val CORRESPONDING_EVENTS = { fragmentEvent: FragmentEvent ->
            when (fragmentEvent) {
                FragmentEvent.ATTACH -> FragmentEvent.DETACH
                FragmentEvent.CREATE -> FragmentEvent.DESTROY
                FragmentEvent.CREATE_VIEW -> FragmentEvent.DESTROY_VIEW
                FragmentEvent.START -> FragmentEvent.STOP
                FragmentEvent.RESUME -> FragmentEvent.PAUSE
                FragmentEvent.PAUSE -> FragmentEvent.STOP
                FragmentEvent.STOP -> FragmentEvent.DESTROY_VIEW
                FragmentEvent.DESTROY_VIEW -> FragmentEvent.DESTROY
                else -> {
                    if (BuildConfig.DEBUG) {
                        throw IllegalStateException("cannot observe livedata when last event is DESTROY")
                    }
                    FragmentEvent.DESTROY
                }
            }
        }
    }
}
