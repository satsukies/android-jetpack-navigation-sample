package dev.satsukies.navigation_sample

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.shopify.livedataktx.Removable

open class LifecycleDispatcherFragment : Fragment(), FragmentLiveDataScopeProvider, LiveDataRemover {

    private val fragmentLiveDataRemoveHook = FragmentLiveDataRemoveHook()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentLiveDataRemoveHook.dispatchOnAttach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentLiveDataRemoveHook.dispatchOnCreate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentLiveDataRemoveHook.dispatchOnCreateView()
    }

    override fun onStart() {
        super.onStart()
        fragmentLiveDataRemoveHook.dispatchOnStart()
    }

    override fun onResume() {
        super.onResume()
        fragmentLiveDataRemoveHook.dispatchOnResume()
    }

    override fun onPause() {
        fragmentLiveDataRemoveHook.dispatchOnPause()
        super.onPause()
    }

    override fun onStop() {
        fragmentLiveDataRemoveHook.dispatchOnStop()
        super.onStop()
    }

    override fun onDestroyView() {
        fragmentLiveDataRemoveHook.dispatchOnDestroyView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        fragmentLiveDataRemoveHook.dispatchOnDestroy()
        super.onDestroy()
    }

    override fun onDetach() {
        fragmentLiveDataRemoveHook.dispatchOnDetach()
        super.onDetach()
    }

    override fun correspondingLifecycleEvents(): (FragmentEvent) -> FragmentEvent {
        return FragmentLiveDataScopeProvider.CORRESPONDING_EVENTS
    }

    override fun getLastLifecycleEvent(): FragmentEvent = fragmentLiveDataRemoveHook.getLastEvent()

    override fun autoRemove(removable: Removable<*>) {
        fragmentLiveDataRemoveHook.addRemovable(removable, this)
    }
}
