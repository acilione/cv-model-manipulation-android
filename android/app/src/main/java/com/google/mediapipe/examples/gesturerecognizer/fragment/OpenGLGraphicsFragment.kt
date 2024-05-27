package com.google.mediapipe.examples.gesturerecognizer.fragment

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.mediapipe.examples.gesturerecognizer.MyGLSurfaceViewRenderer
import com.google.mediapipe.examples.gesturerecognizer.OverlayView
//import com.google.mediapipe.examples.gesturerecognizer.OverlayView
import com.google.mediapipe.examples.gesturerecognizer.databinding.FragmentCameraBinding
import com.google.mediapipe.examples.gesturerecognizer.databinding.FragmentGraphicsLayerBinding
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult

class OpenGLGraphicsFragment : Fragment() {

    private lateinit var glSurfaceView: OverlayView
    private lateinit var myGLSurfaceViewRenderer: MyGLSurfaceViewRenderer
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("OPENGL FRAG", "onCreateView: ")
        FragmentGraphicsLayerBinding.inflate(inflater, container, false)
        glSurfaceView = OverlayView(requireActivity(), attrs = null)
        glSurfaceView.setEGLContextClientVersion(2)
        myGLSurfaceViewRenderer = MyGLSurfaceViewRenderer()
        glSurfaceView.setRenderer(myGLSurfaceViewRenderer)
        glSurfaceView.renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
        return glSurfaceView.rootView
    }

    override fun onPause() {
        Log.e("OPENGL FRAG", "onPause: ")
        super.onPause()
        glSurfaceView.onPause()
    }

    override fun onResume() {
        Log.e("OPENGL FRAG", "onResume: ")
        super.onResume()
        glSurfaceView.onResume()
    }

    fun setResults(
        gestureRecognizerResult: GestureRecognizerResult,
        imageHeight: Int,
        imageWidth: Int,
        runningMode: RunningMode
    ) {
        //THIS IS FOR HANDLING THE MOVEMENT HERE
//        myGLSurfaceViewRenderer.setRendererVariable(gestureRecognizerResult)
//        glSurfaceView.requestRender()
        if (gestureRecognizerResult.gestures().size > 0) {
            if (gestureRecognizerResult.gestures()[0][0].categoryName().equals("Open_Palm")) {
                myGLSurfaceViewRenderer.moveCubeLeft()
                glSurfaceView.requestRender()
            }
            else if (gestureRecognizerResult.gestures()[0][0].categoryName().equals("Closed_Fist")) {
                myGLSurfaceViewRenderer.moveCubeRight()
                glSurfaceView.requestRender()
            }
            if (gestureRecognizerResult.gestures()[0][0].categoryName().equals("Thumb_Up")) {
                myGLSurfaceViewRenderer.rotateClockwise()
                glSurfaceView.requestRender()
            }
            if (gestureRecognizerResult.gestures()[0][0].categoryName().equals("Thumb_Down")) {
                myGLSurfaceViewRenderer.rotateAntiClockwise()
                glSurfaceView.requestRender()
            }
        }
    }
}