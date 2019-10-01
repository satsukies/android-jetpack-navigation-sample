package dev.satsukies.navigation_sample

import com.shopify.livedataktx.Removable

/**
 * [Removable]を特定のタイミングでRemoveするためのInterface
 */
interface LiveDataRemover {

    /**
     * @param removable 解除したい[Removable]
     */
    fun autoRemove(removable: Removable<*>)
}
