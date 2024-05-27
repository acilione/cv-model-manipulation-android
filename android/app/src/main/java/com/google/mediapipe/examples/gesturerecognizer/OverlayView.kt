/*
 * Copyright 2022 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.mediapipe.examples.gesturerecognizer

import android.content.Context
import android.content.Entity
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.google.mediapipe.tasks.components.containers.Category
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker
import kotlin.math.log
import kotlin.math.max
import kotlin.math.min

class OverlayView(context: Context?, attrs: AttributeSet?) :
    GLSurfaceView(context, attrs) {


    private lateinit var myGLSurfaceViewRenderer: GLSurfaceView.Renderer


    private var results: GestureRecognizerResult? = null
    private var prevResults: GestureRecognizerResult? = null
    private var linePaint = Paint()
    private var pointPaint = Paint()
    private var fullPaint = Paint()

    private var scaleFactor: Float = 1f
    private var imageWidth: Int = 1
    private var imageHeight: Int = 1
    private var i = 0
    var displayMetrics: DisplayMetrics = context!!.resources!!.displayMetrics
    private var x = displayMetrics.widthPixels
    private var y = displayMetrics.heightPixels


    init {

//        myGLSurfaceViewRenderer = MyGLSurfaceViewRenderer()
//        this.setRenderer(myGLSurfaceViewRenderer)

//        initPaints()

        //OPENGL SETUP

        //TRYING TO SET THIS UP DIRECTLY IN THE FRAGMENT
//        setEGLContextClientVersion(2)
//        myGLSurfaceViewRenderer = MyGLSurfaceViewRenderer()
//        setRenderer(myGLSurfaceViewRenderer)
//        renderMode = RENDERMODE_CONTINUOUSLY

    }

    fun clear() {
        results = null
        prevResults = null
        linePaint.reset()
        pointPaint.reset()
        invalidate()
//        initPaints()
    }

//    private fun initPaints() {
//
//        linePaint.color =
//            ContextCompat.getColor(context!!, R.color.mp_color_primary)
//        linePaint.strokeWidth = LANDMARK_STROKE_WIDTH
//        linePaint.style = Paint.Style.STROKE
//
//        pointPaint.color = Color.BLUE
//        pointPaint.strokeWidth = LANDMARK_STROKE_WIDTH
//        pointPaint.style = Paint.Style.FILL
//
//        fullPaint.style = Paint.Style.FILL
//    }


//    override fun draw(canvas: Canvas) {
//        super.draw(canvas)
//        canvas.drawARGB(255, 255, 255, 255);
//
//        canvas.drawPoint(
//            x / 2f * imageWidth * scaleFactor,
//            y / 2f * imageWidth * scaleFactor,
//            pointPaint
//        )
//
//        //act on graphics based on the results
//        results?.let { gestureRecognizerResult ->
//            if (gestureRecognizerResult.gestures().size > 0) {
//                if (gestureRecognizerResult.gestures()[0][0].categoryName().equals("Pointing_Up")) {
//                    y -= 15
//                }
//                else if (gestureRecognizerResult.gestures()[0][0].categoryName().equals("Thumb_Down")) {
//                    y += 15
//                }
//            }
//        }


//            for (landmark in gestureRecognizerResult.landmarks()) {
//                for (normalizedLandmark in landmark) {
////                    canvas.drawRect(Rect(150, 150, 149, 160),
////
////                        pointPaint)
//                    canvas.drawPoint(
//                        normalizedLandmark.x() * imageWidth * scaleFactor,
//                        normalizedLandmark.y() * imageHeight * scaleFactor,
//                        linePaint
//                    )
//                }

//                HandLandmarker.HAND_CONNECTIONS.forEach {
//                    Log.e("DRAWINGGGG", "DRAWING")
//                    canvas.drawRect(
//                        2.0f * imageWidth * scaleFactor,
//                        2.0f * imageWidth * scaleFactor,
//                        2.0f * imageWidth * scaleFactor,
//                        2.0f * imageWidth * scaleFactor,
//                        linePaint
//                    )
//        viewTreeObserver.dispatchOnDraw()
//
//                    Log.e("GESTURE INSIDE CANVAS", gestureRecognizerResult.toString())
////                    while (results.gestures().get(0).equals())
////                    canvas.drawLine(
////                        gestureRecognizerResult.landmarks().get(0).get(it!!.start()).x() * imageWidth * scaleFactor,
////                        gestureRecognizerResult.landmarks().get(0).get(it.start()).y() * imageHeight * scaleFactor,
////                        gestureRecognizerResult.landmarks().get(0).get(it.end()).x() * imageWidth * scaleFactor,
////                        gestureRecognizerResult.landmarks().get(0).get(it.end()).y() * imageHeight * scaleFactor,
////                        linePaint)
//                }

//            }
//    }

    //TODO: find a way to setup variable on the renderer in a clean way, based on gesture

    fun setResults(
        gestureRecognizerResult: GestureRecognizerResult,
        imageHeight: Int,
        imageWidth: Int,
        runningMode: RunningMode = RunningMode.IMAGE
    ) {
        results = gestureRecognizerResult

//        if (prevResults == null) {
//            prevResults = results
//        }

//        this.imageHeight = imageHeight
//        this.imageWidth = imageWidth
//
////        NO NEED OF SCALE FACTOR FOR THIS USE CASE: ONLY TO RENDER HANDS DRAWINGS
//        scaleFactor = when (runningMode) {
//            RunningMode.IMAGE,
//            RunningMode.VIDEO -> {
//                min(width * 1f / imageWidth, height * 1f / imageHeight)
//            }
//
//            RunningMode.LIVE_STREAM -> {
//                // PreviewView is in FILL_START mode. So we need to scale up the
//                // landmarks to match with the size that the captured images will be
//                // displayed.
//                max(width * 1f / imageWidth, height * 1f / imageHeight)
//            }
//        }

//        if (results!!.gestures().size > 0) {
//            if (results!!.gestures().get(0).get(0).categoryName().equals("Victory"))
//                Log.e("PIPPO", "setResults: ", )
//                y -= 1
//        }

//        invalidate() //this triggers the draw function for each frame

//        if (results!!.gestures().size > 0) {
//            Log.e("ENTERIF", " ")
//            if (!(results!!.gestures()
//                    .equals(prevResults!!.gestures()))
//            ) {
//                Log.e("INVALIDATE", "PREV DIFF RES ")
//                invalidate() //the invalidate triggers the draw method
//            }
//        }

    }

    companion object {
        private const val LANDMARK_STROKE_WIDTH = 45F
    }
}
