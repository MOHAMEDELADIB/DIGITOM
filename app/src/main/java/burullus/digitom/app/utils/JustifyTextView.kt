package burullus.digitom.app.utils

import android.content.Context
import android.graphics.Canvas
import android.text.StaticLayout
import android.util.AttributeSet

/**
 *
 */
class JustifyTextView(
    context: Context?,
    attrs: AttributeSet?
) : androidx.appcompat.widget.AppCompatTextView(context!!, attrs) {
    private var mLineY = 0
    private var mViewWidth = 0

    /**
     *
     */
    override fun onDraw(canvas: Canvas) {
        val paint = this.paint
        paint.color = this.currentTextColor
        paint.drawableState = this.drawableState
        mViewWidth = this.measuredWidth
        val text = this.text as String
        mLineY = 0
        mLineY = (mLineY.toFloat() + this.textSize).toInt()
        val layout = this.layout
        for (i in 0 until layout.lineCount) {
            val lineStart = layout.getLineStart(i)
            val lineEnd = layout.getLineEnd(i)
            val line = text.substring(lineStart, lineEnd)
            val width =
                StaticLayout.getDesiredWidth(text, lineStart, lineEnd, paint)
            if (needScale(line) && i < layout.lineCount - 1) {
                drawScaledText(canvas, line, width)
            } else {
                canvas.drawText(line, 0.0f, mLineY.toFloat(), paint)
            }
            mLineY += this.lineHeight
        }
    }

    private fun drawScaledText(
        canvas: Canvas,
        line: String,
        lineWidth: Float
    ) {
        var nline = line
        var x = 0.0f
        if (isFirstLineOfParagraph(nline)) {
            val blanks = "  "
            canvas.drawText(blanks, x, mLineY.toFloat(), this.paint)
            val bw = StaticLayout.getDesiredWidth(blanks, this.paint)
            x += bw
            nline = nline.substring(3)
        }
        val d =
            (mViewWidth.toFloat() - lineWidth) / nline.length.toFloat() - 1.0f
        for (element in nline) {
            val c = element.toString()
            val cw = StaticLayout.getDesiredWidth(c, this.paint)
            canvas.drawText(c, x, mLineY.toFloat(), this.paint)
            x += cw + d
        }
    }

    private fun isFirstLineOfParagraph(line: String): Boolean {
        return line.length > 3 && line[0] == ' ' && line[1] == ' '
    }

    private fun needScale(line: String): Boolean {
        return if (line.isEmpty()) {
            false
        } else {
            line[line.length - 1] != '\n'
        }
    }
}