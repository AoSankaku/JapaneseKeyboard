package com.kazumaproject.markdownhelperkeyboard.setting_activity.ui.keyboard_size_setting

internal data class KeyboardVerticalResizeResult(
    val heightPx: Int,
    val bottomMarginPx: Int
)

internal object KeyboardVerticalResize {

    fun fromBottomHandle(
        initialHeightPx: Int,
        initialBottomMarginPx: Int,
        dragDeltaYPx: Float,
        minHeightPx: Int,
        maxHeightPx: Int
    ): KeyboardVerticalResizeResult {
        require(initialHeightPx > 0)
        require(initialBottomMarginPx >= 0)
        require(minHeightPx > 0)
        require(maxHeightPx >= minHeightPx)

        val requestedHeightPx = (initialHeightPx + dragDeltaYPx)
            .toInt()
            .coerceIn(minHeightPx, maxHeightPx)
        val largestHeightKeepingTopFixedPx = initialHeightPx + initialBottomMarginPx
        val heightPx = requestedHeightPx.coerceAtMost(largestHeightKeepingTopFixedPx)
        val appliedHeightDeltaPx = heightPx - initialHeightPx

        return KeyboardVerticalResizeResult(
            heightPx = heightPx,
            bottomMarginPx = initialBottomMarginPx - appliedHeightDeltaPx
        )
    }
}
