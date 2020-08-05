/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package burullus.digitom.app.ui.ocrscreen.ocrutils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.ViewGroup
import androidx.annotation.RequiresPermission
import burullus.digitom.app.utils.CameraSource
import java.io.IOException
import kotlin.math.max
import kotlin.math.min

/**
 *
 */
/**
 *
 */
/**
 *
 */
class CameraSourcePreview(
    context: Context, attrs: AttributeSet?
) : ViewGroup(context, attrs) {
    private val surfaceView: SurfaceView = SurfaceView(context)
    private var startRequested = false
    private var surfaceAvailable = false
    private var cameraSource: CameraSource? = null
    private var overlay: GraphicOverlay<*>? = null

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    @RequiresPermission(Manifest.permission.CAMERA)
    @Throws(IOException::class, SecurityException::class)
    fun start(cameraSource: CameraSource?) {
        if (cameraSource == null) {
            stop()
        }
        this.cameraSource = cameraSource
        if (this.cameraSource != null) {
            startRequested = true
            startIfReady()
        }
    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    @RequiresPermission(Manifest.permission.CAMERA)
    @Throws(IOException::class, SecurityException::class)
    fun start(
        cameraSource: CameraSource?,
        overlay: GraphicOverlay<*>?
    ) {
        this.overlay = overlay
        start(cameraSource)
    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    fun stop() {
        if (cameraSource != null) {
            (cameraSource ?: return).stop()
        }
    }

    @RequiresPermission(Manifest.permission.CAMERA)
    @Throws(IOException::class, SecurityException::class)
    internal fun startIfReady() {
        if (startRequested && surfaceAvailable) {
            (cameraSource ?: return).start(surfaceView.holder)
            if (overlay != null) {
                val size = (cameraSource ?: return).previewSize
                val min = min(size?.width ?: return, size.height)
                val max = max(size.width, size.height)
                if (isPortraitMode) {
                    (overlay ?: return).setCameraInfo(
                        min,
                        max,
                        (cameraSource ?: return).cameraFacing
                    )
                } else {
                    (overlay ?: return).setCameraInfo(
                        max,
                        min,
                        (cameraSource ?: return).cameraFacing
                    )
                }
                (overlay ?: return).clear()
            }
            startRequested = false
        }
    }

    private inner class SurfaceCallback : SurfaceHolder.Callback {
        @SuppressLint("SyntheticAccessor")
        override fun surfaceCreated(surface: SurfaceHolder) {
            surfaceAvailable = true
            try {
                startIfReady()
            } catch (se: SecurityException) {
            }
        }

        override fun surfaceChanged(
            holder: SurfaceHolder,
            format: Int,
            width: Int,
            height: Int
        ) {
        }

        @SuppressLint("SyntheticAccessor")
        override fun surfaceDestroyed(surface: SurfaceHolder) {
            surfaceAvailable = false
        }
    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    override fun onLayout(
        changed: Boolean,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        var previewWidth = 100
        var previewHeight = 200
        if (cameraSource != null) {
            val size = (cameraSource ?: return).previewSize
            if (size != null) {
                previewWidth = size.width
                previewHeight = size.height
            }
        }
        if (isPortraitMode) {
            val tmp = previewWidth
            previewWidth = previewHeight
            previewHeight = tmp
        }
        val viewWidth = right - left
        val viewHeight = bottom - top
        val childWidth: Int
        val childHeight: Int
        var childXOffset = 0
        var childYOffset = 0
        val widthRatio = viewWidth.toFloat() / previewWidth.toFloat()
        val heightRatio = viewHeight.toFloat() / previewHeight.toFloat()
        if (widthRatio > heightRatio) {
            childWidth = viewWidth
            childHeight = (previewHeight.toFloat() * widthRatio).toInt()
            childYOffset = (childHeight - viewHeight) / 2
        } else {
            childWidth = (previewWidth.toFloat() * heightRatio).toInt()
            childHeight = viewHeight
            childXOffset = (childWidth - viewWidth) / 2
        }
        for (i in 0 until childCount) {
            getChildAt(i).layout(
                -1 * childXOffset, -1 * childYOffset,
                childWidth - childXOffset, childHeight - childYOffset
            )
        }
        try {
            startIfReady()
        } catch (se: SecurityException) {

        }
    }

    private val isPortraitMode: Boolean
        get() {
            val orientation = context.resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                return false
            }
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                return true
            }
            return false
        }

    companion object;
    init {
        surfaceView.holder.addCallback(SurfaceCallback())
        addView(surfaceView)
    }
}