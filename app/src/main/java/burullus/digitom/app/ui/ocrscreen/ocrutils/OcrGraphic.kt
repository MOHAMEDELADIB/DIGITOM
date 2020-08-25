package burullus.digitom.app.ui.ocrscreen.ocrutils

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import burullus.digitom.app.data.network.api.KKSPattern
import burullus.digitom.app.data.network.api.KKSPatternlength
import burullus.digitom.app.ui.ocrscreen.OcrCaptureActivity
import burullus.digitom.app.ui.ocrscreen.OcrCaptureActivity.Companion.kkssearch
import com.google.android.gms.vision.text.TextBlock
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 *
 */
class OcrGraphic internal constructor(
    overlay: GraphicOverlay<*>,
    /**
     *
     */
    val textBlock: TextBlock
) :
    GraphicOverlay.Graphic(overlay) {
    private var kksLength = 0
    private var kksString: String = ""

    /**
     *
     */
    var context: OcrCaptureActivity? = null

    /**
     *
     */
    var id: Int = 0

    init {
        if (rectPaint == null) {
            rectPaint = Paint()
            rectPaint!!.color = Color.WHITE
            rectPaint!!.style = Paint.Style.STROKE
            rectPaint!!.strokeWidth = 4.0f
        }
        if (textPaint == null) {
            textPaint = Paint()
            textPaint!!.color = 0
            textPaint!!.textSize = 54.0f
        }
    }

    /**
     *
     */
    override fun contains(x: Float, y: Float): Boolean {
        var rect = RectF(textBlock.boundingBox)
        rect = translateRect(rect)
        return rect.contains(x, y)
    }

    /**
     *
     */
    override fun draw(canvas: Canvas?) {
        var rect = RectF(textBlock.boundingBox)
        rect = translateRect(rect)
        canvas?.drawRect(rect, rectPaint ?: return)
        val textComponents = textBlock.components
        loop1@ for (currentText in textComponents) {
            val left = translateX(currentText.boundingBox.left.toFloat())
            val bottom = translateY(currentText.boundingBox.bottom.toFloat())
            val kksno: String = currentText.value.toUpperCase(Locale.ENGLISH)
            if (isKKSValid(kksno)) {
                canvas?.drawText(kksno, left, bottom, textPaint ?: return)
                (OcrCaptureActivity.getkks() ?: return).text = kksString
                kkssearch = kksString
                break@loop1
            }
        }

    }

    private fun isKKSValid(kks: String): Boolean {
        var pattern: Pattern
        var matcher: Matcher
        var name = ""
        var result = false
        if (kks.length > 11) {
            name = kks.replace("O", "0")
            loop2@ for (i: Int in KKSPattern.indices) {
                this.kksLength = KKSPatternlength[i]
                kksString =
                    if (name.length > kksLength) name.subSequence(0, kksLength).toString() else name
                val element = KKSPattern[i]
                pattern = Pattern.compile(element)
                matcher = pattern.matcher(kksString)
                result = matcher.matches()
                if (result) break@loop2 else kksLength = 0
            }
        }
        return result
    }

    companion object {
        private var rectPaint: Paint? = null
        private var textPaint: Paint? = null
    }
}