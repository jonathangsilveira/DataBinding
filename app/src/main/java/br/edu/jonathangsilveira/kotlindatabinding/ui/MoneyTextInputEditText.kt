package br.edu.jonathangsilveira.kotlindatabinding.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.design.widget.TextInputEditText
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import br.edu.jonathangsilveira.kotlindatabinding.R
import br.edu.jonathangsilveira.kotlindatabinding.util.format
import java.math.BigDecimal
import java.math.RoundingMode

class MoneyTextInputEditText : TextInputEditText {

    private val valueBuilder = StringBuilder()

    private var _value: BigDecimal = BigDecimal.valueOf(0.0)

    private val devider = BigDecimal(100)

    private val mClearImage: Drawable? = null

    private var ownTextWatcher: MoneyTextWatcher? = null

    private var afterTextChanged: ((value: Double) -> Unit)? = null

    var max = 999999999.99

    private val isEmpty: Boolean
        get() = valueBuilder.isEmpty()

    var value: Double
        get() = _value.toDouble()
        set(value) = updateValue(value)

    private fun updateValue(value: Double) {
        val newValue = value * 100
        _value = BigDecimal.valueOf(newValue)
        valueBuilder.clear()
        setText(newValue.toLong().toString())
        invalidate()
        requestLayout()
    }

    constructor(context: Context) : super(context) {
        setup(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setup(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        setup(attrs)
    }

    private fun setup(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.MoneyTextInputEditText,
            0, 0).apply {
            try {
                val attrValue = getFloat(R.styleable.MoneyTextInputEditText_value, 0F)
                _value = attrValue.toBigDecimal()
            } finally {
                recycle()
            }
        }
        /*mClearImage =
                ResourcesCompat.getDrawable(getResources(), R.drawable.round_clear_black_24, null);
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);*/
        inputType = InputType.TYPE_CLASS_NUMBER
        ownTextWatcher = MoneyTextWatcher()
        addTextChangedListener(ownTextWatcher)
        //setOnTouchListener(new OnClearPressed());
    }

    private fun isValid(valueToAppend: String): Boolean {
        val newValue = StringBuilder(valueBuilder)
        newValue.append(valueToAppend)
        return try {
            val cents = calculate(newValue)
            cents.toDouble() <= max
        } catch (e: Exception) {
            false
        }
    }

    private fun calculate(newValue: StringBuilder): BigDecimal {
        val rawValue = newValue.toString().toLong()
        val value = BigDecimal(rawValue)
        return value.divide(devider, 2, RoundingMode.UNNECESSARY)
    }

    /*fun setValue(value: Double) {
        val newValue = value * 100
        _value = BigDecimal.valueOf(newValue)
        valueBuilder.clear()
        setText(newValue.toLong().toString())
    }*/

    fun setAfterTextChangedListener(action: (value: Double) -> Unit) {
        afterTextChanged = action
    }

    /**
     * TextWatcher to handle the user input for monetary values.
     */
    private inner class MoneyTextWatcher : TextWatcher {

        override fun beforeTextChanged(text: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
            var beforeCount = before
            val value = text.toString()
            val inserting = count > 0
            val deleting = beforeCount > 0
            if (deleting && !isEmpty) {
                if (beforeCount > 1)
                    beforeCount--
                var regionStart = valueBuilder.length - beforeCount
                if (regionStart < 0) {
                    regionStart = 0
                }
                valueBuilder.delete(regionStart, valueBuilder.length)
            }
            if (inserting) {
                val valueToAppend = value.substring(start, start + count)
                if (isValid(valueToAppend)) {
                    valueBuilder.append(valueToAppend)
                }
            }
        }

        override fun afterTextChanged(editable: Editable) {
            _value = if (isEmpty)
                BigDecimal(0)
            else
                calculate(valueBuilder)
            removeTextChangedListener(this)
            val formattedValue = value.format()
            setText(formattedValue)
            setSelection(formattedValue.length)
            addTextChangedListener(this)
            afterTextChanged?.let { action ->
                action(value)
            }
            /*if (_value.doubleValue() > 0.00) {
                setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mClearImage, null);
            } else {
                setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
            }*/
        }

    }

    /*private class OnClearPressed implements OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (getRight() - getCompoundPaddingRight())) {
                    performClick();
                    setValue(0);
                    return true;
                }
            }
            return false;
        }

    }*/

}
