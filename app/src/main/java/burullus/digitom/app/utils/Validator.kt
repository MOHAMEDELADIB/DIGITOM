package burullus.digitom.app.utils

import android.annotation.SuppressLint
import burullus.digitom.app.R
import com.google.android.material.textfield.TextInputLayout


/**
 *
 */
/**
 *
 */
class Validator {
    private var validations = mutableListOf<TextInputLayout>()

    /**
     *
     */
    /**
     *
     */
    fun add(value: TextInputLayout): Validator {
        validations.add(value)
        return this
    }

    @SuppressLint("ResourceType")
    /*private fun validateForValidMail(): Boolean {
        val check2 = validations.filter {
            var know :Int =it.editText!!.inputType
            val pattern: Pattern
            val matcher: Matcher
            val valid:Boolean
            val emailPattern = ("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
            pattern = Pattern.compile(emailPattern)
            matcher = pattern.matcher( it.editText?.text!!)
            matcher.matches()
        }

        check2.map {
            //  declare Drawable
            // val errorD = ContextCompat.getDrawable(APPContext.applicationContext()
            , R.drawable.error)
            //it.error = APPContext.applicationContext().resources.getString(R.string.error_empty)
            it.errorIconDrawable = null
            it.setErrorIconDrawable(R.drawable.error)
            it.editText?.setBackgroundResource(R.drawable.angle44)
            it.editText?.setError("Not Valid Mail,null")

        }

        return check2.isEmpty()
    }*/
    private fun validateForNotEmpty(): Boolean {

        val check = validations.filter {
            it.editText?.text.isNullOrEmpty()
        }

        check.map {
            //  declare Drawable
            // val errorD = ContextCompat.getDrawable(APPContext.applicationContext(),
            // R.drawable.error)
            //it.error = APPContext.applicationContext().resources.getString(R.string.error_empty)
            it.errorIconDrawable = null
            it.setErrorIconDrawable(R.drawable.error)
            it.editText?.setBackgroundResource(R.drawable.angle44)
            it.editText?.setError("This Field is Required", null)

        }

        return check.isEmpty()
    }


    /**
     *
     */
    /**
     *
     */
    fun result(): Boolean {
        validations.map {
            it.error = null
            it.errorIconDrawable = null
            it.editText?.setBackgroundResource(R.drawable.angle4)
        }
        //&&validateForValidMail()
        return (validateForNotEmpty())
    }
}

