package com.app.kotlinbasews.custom


import android.content.Context
import android.content.res.TypedArray
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatEditText
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.app.kotlinbasews.R
import com.app.kotlinbasews.helper.Functions


class MyEditTextWithCloseBtn : AppCompatEditText {
    var contextInstance: Context?=null
    private val imgCloseButton = resources.getDrawable(R.drawable.ic_cancel)
    private var tintColor: Int = 0

    constructor(context: Context) : super(context) {
        this.contextInstance = context
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        this.contextInstance = context
        if (!isInEditMode) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.TfEditTextClose, 0, 0)
            try {
                tintColor = a.getColor(R.styleable.TfEditTextClose_tintColor, ContextCompat.getColor(context, R.color.white))
            } finally {
                a.recycle()
            }
        }
        init()

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.contextInstance = context
        if (!isInEditMode) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.TfEditTextClose, 0, 0)
            try {
                tintColor = a.getColor(R.styleable.TfEditTextClose_tintColor, ContextCompat.getColor(context, R.color.white))
            } finally {
                a.recycle()
            }
        }
        init()
    }

    internal fun init() {
        setPadding(18, 0, 10, 0)
        imgCloseButton?.setBounds(0, 0, imgCloseButton.intrinsicWidth,
                imgCloseButton.intrinsicHeight)
        imgCloseButton?.setColorFilter(tintColor, android.graphics.PorterDuff.Mode.SRC_IN)
        //setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Dosis-Regular.otf")/*, -1*/);
        handleClearButton()
        this.setOnTouchListener(android.view.View.OnTouchListener { v, event ->
            val et = this@MyEditTextWithCloseBtn

            if (et.compoundDrawables[2] == null)
                return@OnTouchListener false

            if (event.action != MotionEvent.ACTION_UP)
                return@OnTouchListener false

            if (event.x > (et.width - et.paddingRight
                            - imgCloseButton!!.intrinsicWidth)) {
                et.setText("")
                this@MyEditTextWithCloseBtn.handleClearButton()
            }
            false
        })

        this.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                       count: Int) {

                this@MyEditTextWithCloseBtn.handleClearButton()
            }

            override fun afterTextChanged(arg0: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {
            }
        })
        typeface = Functions.getRegularFont(context)
    }

    internal fun handleClearButton() {
        if (this.text.toString() == "") {
            this.setCompoundDrawables(this.compoundDrawables[0],
                    this.compoundDrawables[1], null,
                    this.compoundDrawables[3])
        } else {
            this.setCompoundDrawables(this.compoundDrawables[0],
                    this.compoundDrawables[1], imgCloseButton,
                    this.compoundDrawables[3])

        }
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (this.text.toString() == "") {
            this.setCompoundDrawables(this.compoundDrawables[0], this.compoundDrawables[1], null, this.compoundDrawables[3])
        } else {
            if (focused) {
                this.setCompoundDrawables(this.compoundDrawables[0], this.compoundDrawables[1], imgCloseButton, this.compoundDrawables[3])
            } else {
                this.setCompoundDrawables(this.compoundDrawables[0], this.compoundDrawables[1], null, this.compoundDrawables[3])
            }

        }

    }
}