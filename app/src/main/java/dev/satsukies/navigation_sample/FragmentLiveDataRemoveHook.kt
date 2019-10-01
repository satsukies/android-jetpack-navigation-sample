package dev.satsukies.navigation_sample

import androidx.lifecycle.Lifecycle
import com.shopify.livedataktx.Removable
import java.util.HashSet

/**
 * [Removable]を解除するためのクラス
 *
 * [addRemovable]で与えられた[Removable]がどのタイミングで解除されるかは、
 * [LiveDataScopeProvider.correspondingLifecycleEvents]によって定義されます。
 *
 * [Lifecycle.addObserver]でライフサイクルを検知していない理由は、一部の端末(Airstick(6.0.1), FireTVStick(旧))で
 * 透過のActivityが重なった際のonPause時に[Lifecycle.Event.ON_STOP]まで流れてしまう問題があるためです。
 * そのため、実際のActivity/Fragmentのライフサイクルイベントにフックして、[Removable]を解除しています。
 *
 * [Removable]を解除するタイミングとして、[Lifecycle.Event.ON_RESUME]よりも前のタイミングは想定していないので
 * [Lifecycle.Event.ON_RESUME]よりも前に解除タイミングが定義してある場合は無視されます。
 */
class FragmentLiveDataRemoveHook {

    private val onPauseRemovables = HashSet<Removable<*>>()
    private val onStopRemovables = HashSet<Removable<*>>()
    private val onDestroyViewRemovables = HashSet<Removable<*>>()
    private val onDestroyRemovables = HashSet<Removable<*>>()
    private val onDetachRemovables = HashSet<Removable<*>>()

    private var lastEvent = FragmentEvent.ATTACH

    fun addRemovable(removable: Removable<*>, provider: FragmentLiveDataScopeProvider) {
        val lastEvent = provider.getLastLifecycleEvent()
        val removableEvent = provider.correspondingLifecycleEvents().invoke(lastEvent)
        when (removableEvent) {
            FragmentEvent.PAUSE -> onPauseRemovables.add(removable)
            FragmentEvent.STOP -> onStopRemovables.add(removable)
            FragmentEvent.DESTROY_VIEW -> onDestroyViewRemovables.add(removable)
            FragmentEvent.DESTROY -> onDestroyRemovables.add(removable)
            FragmentEvent.DETACH -> onDetachRemovables.add(removable)
            else -> Unit // ignore ATTACH, CREATE, CREATE_VIEW, START, RESUME
        }
    }

    fun getLastEvent(): FragmentEvent = lastEvent

    fun dispatchOnAttach() {
        lastEvent = FragmentEvent.ATTACH
    }

    fun dispatchOnCreate() {
        lastEvent = FragmentEvent.CREATE
    }

    fun dispatchOnCreateView() {
        lastEvent = FragmentEvent.CREATE_VIEW
    }

    fun dispatchOnStart() {
        lastEvent = FragmentEvent.START
    }

    fun dispatchOnResume() {
        lastEvent = FragmentEvent.RESUME
    }

    fun dispatchOnPause() {
        lastEvent = FragmentEvent.PAUSE
        onPauseRemovables.removeAll()
    }

    fun dispatchOnDestroyView() {
        lastEvent = FragmentEvent.DESTROY_VIEW
        onDestroyViewRemovables.removeAll()
    }

    fun dispatchOnStop() {
        lastEvent = FragmentEvent.STOP
        onStopRemovables.removeAll()
    }

    fun dispatchOnDestroy() {
        lastEvent = FragmentEvent.DESTROY
        onDestroyRemovables.removeAll()
    }

    fun dispatchOnDetach() {
        lastEvent = FragmentEvent.DETACH

        // 念の為、onDestroyですべてのremovableを解除する
        onPauseRemovables.removeAll()
        onDestroyViewRemovables.removeAll()
        onStopRemovables.removeAll()
        onDestroyRemovables.removeAll()
    }

    private fun HashSet<Removable<*>>.removeAll() {
        this.forEach { removable -> removable.removeObserver() }
        this.clear()
    }
}
