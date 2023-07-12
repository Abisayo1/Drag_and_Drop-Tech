package com.abisayo.practice

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val footballView: ImageView = findViewById(R.id.footballImageView)
        val leftLayout: View = findViewById(R.id.leftLayout)
        val rightLayout: View = findViewById(R.id.rightLayout)

        val originalX = footballView.x
        val originalY = footballView.y

        footballView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Capture the initial touch position
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    // Update the position of the football
                    footballView.x = event.rawX - footballView.width / 2
                    footballView.y = event.rawY - footballView.height / 2

                    true
                }
                MotionEvent.ACTION_UP -> {
                    // Check if the football is dropped on the left layout
                    if (isViewIntersecting(footballView, leftLayout)) {
                        // Move the football to the center of the left layout
                        footballView.x = leftLayout.x + (leftLayout.width - footballView.width) / 2
                        footballView.y = leftLayout.y + (leftLayout.height - footballView.height) / 2
                    }
                    // Check if the football is dropped on the right layout
                    else if (isViewIntersecting(footballView, rightLayout)) {
                        // Move the football to the center of the right layout
                        footballView.x = rightLayout.x + (rightLayout.width - footballView.width) / 2
                        footballView.y = rightLayout.y + (rightLayout.height - footballView.height) / 2
                    }
                    // If the football is dropped outside the layouts, reset its position
                    else {
                        footballView.x = originalX
                        footballView.y = originalY
                    }

                    true
                }
                else -> false
            }
        }
    }

    // Function to check if two views intersect each other
    private fun isViewIntersecting(view1: View, view2: View): Boolean {
        val rect1 =
            Rect()
        view1.getHitRect(rect1)
        val rect2 = Rect()
        view2.getHitRect(rect2)
        return Rect.intersects(rect1, rect2)
    }
}
