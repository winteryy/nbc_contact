package com.nbcteam5.nbccontact

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.nbcteam5.nbccontact.databinding.ViewDetatilContentBinding

class DetailContentView(context: Context, attrs: AttributeSet): CardView(context, attrs) {
    private var binding: ViewDetatilContentBinding = ViewDetatilContentBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DetailContentView)

        val title = typedArray.getString(R.styleable.DetailContentView_title_text)
        binding.titleTextView.text = title

        val content = typedArray.getString(R.styleable.DetailContentView_content_text)
        binding.contentTextView.text= content

        typedArray.recycle()
    }
}