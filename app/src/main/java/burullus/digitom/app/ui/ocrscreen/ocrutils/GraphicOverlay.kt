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

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import burullus.digitom.app.ui.ocrscreen.ocrutils.GraphicOverlay.Graphic
import com.google.android.gms.vision.CameraSource
import java.util.*

/**
 *
 */
class GraphicOverlay<T : Graphic?>(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val lock = Any()
    private var previewWidth = 0
    private var widthScaleFactor = 1.0f
    private var previewHeight = 0
    private var heightScaleFactor = 1.0f
    private var facing = CameraSource.CAMERA_FACING_BACK
    private val graphics: MutableSet<T> = HashSet()

    /**
     *
     */
    abstract class Graphic(private val mOverlay: GraphicOverlay<*>) {
        /**
         *
         */
        abstract fun draw(canvas: Canvas?)

        /**
         *
         */
        fun contains(): Boolean {
            return contains()
        }

        /**
         *
         */
        abstract fun contains(x: Float, y: Float): Boolean

        @SuppressLint("SyntheticAccessor")
        private fun scaleX(horizontal: Float): Float {
            return horizontal * mOverlay.widthScaleFactor
        }

        @SuppressLint("SyntheticAccessor")
        private fun scaleY(vertical: Float): Float {
            return vertical * mOverlay.heightScaleFactor
        }

        /**
         *
         */
        @SuppressLint("SyntheticAccessor")
        fun translateX(x: Float): Float {
            return if (mOverlay.facing == CameraSource.CAMERA_FACING_FRONT) {
                mOverlay.width - scaleX(x)
            } else {
                scaleX(x)
            }
        }

        /**
         *
         */
        fun translateY(y: Float): Float {
            return scaleY(y)
        }

        /**
         *
         */
        fun translateRect(inputRect: RectF): RectF {
            val returnRect = RectF()
            returnRect.left = translateX(inputRect.left)
            returnRect.top = translateY(inputRect.top)
            returnRect.right = translateX(inputRect.right)
            returnRect.bottom = translateY(inputRect.bottom)
            return returnRect
        }
    }

    /**
     *
     */
    fun clear() {
        synchronized(lock) { graphics.clear() }
        postInvalidate()
    }

    /**
     *
     */
    fun add(graphic: T) {
        synchronized(lock) { graphics.add(graphic) }
        postInvalidate()
    }

    /**
     *
     */
    fun getGraphicAtLocation(rawX: Float, rawY: Float): T? {
        synchronized(lock) {
            val location = IntArray(2)
            getLocationOnScreen(location)
            for (graphic in graphics) {
                if (graphic!!.contains(rawX - location[0], rawY - location[1])) {
                    return graphic
                }
            }
            return null
        }
    }

    /**
     *
     */
    fun setCameraInfo(previewWidth: Int, previewHeight: Int, facing: Int) {
        synchronized(lock) {
            this.previewWidth = previewWidth
            this.previewHeight = previewHeight
            this.facing = facing
        }
        postInvalidate()
    }

    /**
     *
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        synchronized(lock) {
            if (previewWidth != 0 && previewHeight != 0) {
                widthScaleFactor = width.toFloat() / previewWidth.toFloat()
                heightScaleFactor = height.toFloat() / previewHeight.toFloat()
            }
            for (graphic in graphics) {
                graphic?.draw(canvas)
            }
        }
    }
}