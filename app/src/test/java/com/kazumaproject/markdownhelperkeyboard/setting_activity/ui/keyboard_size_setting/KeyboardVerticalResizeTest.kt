package com.kazumaproject.markdownhelperkeyboard.setting_activity.ui.keyboard_size_setting

import org.junit.Assert.assertEquals
import org.junit.Test

class KeyboardVerticalResizeTest {

    @Test
    fun bottomHandleKeepsTopEdgeFixedWhileShrinking() {
        val result = KeyboardVerticalResize.fromBottomHandle(
            initialHeightPx = 440,
            initialBottomMarginPx = 0,
            dragDeltaYPx = -80f,
            minHeightPx = 200,
            maxHeightPx = 840
        )

        assertEquals(360, result.heightPx)
        assertEquals(80, result.bottomMarginPx)
        assertEquals(
            topEdge(parentHeightPx = 1_000, heightPx = 440, bottomMarginPx = 0),
            topEdge(
                parentHeightPx = 1_000,
                heightPx = result.heightPx,
                bottomMarginPx = result.bottomMarginPx
            )
        )
    }

    @Test
    fun bottomHandleUsesAppliedHeightDeltaWhenMinimumHeightIsReached() {
        val result = KeyboardVerticalResize.fromBottomHandle(
            initialHeightPx = 440,
            initialBottomMarginPx = 20,
            dragDeltaYPx = -400f,
            minHeightPx = 200,
            maxHeightPx = 840
        )

        assertEquals(200, result.heightPx)
        assertEquals(260, result.bottomMarginPx)
        assertEquals(
            topEdge(parentHeightPx = 1_000, heightPx = 440, bottomMarginPx = 20),
            topEdge(
                parentHeightPx = 1_000,
                heightPx = result.heightPx,
                bottomMarginPx = result.bottomMarginPx
            )
        )
    }

    @Test
    fun bottomHandleStopsAtParentBottomWhileGrowing() {
        val result = KeyboardVerticalResize.fromBottomHandle(
            initialHeightPx = 440,
            initialBottomMarginPx = 60,
            dragDeltaYPx = 100f,
            minHeightPx = 200,
            maxHeightPx = 840
        )

        assertEquals(500, result.heightPx)
        assertEquals(0, result.bottomMarginPx)
        assertEquals(
            topEdge(parentHeightPx = 1_000, heightPx = 440, bottomMarginPx = 60),
            topEdge(
                parentHeightPx = 1_000,
                heightPx = result.heightPx,
                bottomMarginPx = result.bottomMarginPx
            )
        )
    }

    private fun topEdge(parentHeightPx: Int, heightPx: Int, bottomMarginPx: Int): Int =
        parentHeightPx - bottomMarginPx - heightPx
}
