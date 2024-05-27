package com.google.mediapipe.examples.gesturerecognizer

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.util.Log
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class MyGLSurfaceViewRenderer : GLSurfaceView.Renderer {
    //    private lateinit var mTriangle: Triangle
    private var red = 0.0f
    private var green = 0.0f
    private var blue = 0.0f

//    private var mTriangle: Triangle? = null
//
//    private var triangleX = 0.0f
//    private var triangleY = 0.0f
//    private var angle = 0.0f
//
//    private var velocityX = 0.1f // Adjust the velocity as needed
//    private var velocityY = 0.1f

    var cubePositionX: Float = 0.0f
    var cubePositionY: Float = 0.0f

    //CUBE RENDERING VARIABLES
    private var cube: Cube? = null
    private val rotationMatrix = FloatArray(16)
    private val mvpMatrix = FloatArray(16)
    private val viewMatrix = FloatArray(16)
    private val projectionMatrix = FloatArray(16)
    private val scratchMatrix = FloatArray(16)

    private var angle = 0.0f

    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        //CUBE RENDERING
        cube = Cube()
        // Set up the view matrix
        val eyeX = 0.0f
        val eyeY = 0.0f
        val eyeZ = -3.0f
        val lookX = 0.0f
        val lookY = 0.0f
        val lookZ = 0.0f
        val upX = 0.0f
        val upY = 1.0f
        val upZ = 0.0f
        Matrix.setLookAtM(viewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ)
        GLES20.glEnable(GLES20.GL_DEPTH_TEST)

    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        //CUBE
        GLES20.glViewport(0, 0, width, height)
        val ratio: Float = width.toFloat() / height.toFloat()
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1.0f, 1.0f, 2.0f, 10.0f)


        //TRIANGLE RENDERING
//        Log.e("CHANGED", "width: $width, height: $height")
//        GLES20.glViewport(0, 0, width, height);
    }


    fun moveCubeLeft() {
        cubePositionX += 0.03f
    }

    fun moveCubeRight() {
        cubePositionX -= 0.03f
    }

    fun rotateClockwise() {
        angle += 1.5f
    }

    fun rotateAntiClockwise() {
        angle -= 1.5f
    }

    override fun onDrawFrame(gl: GL10) {
        //CUBE
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)

        // Create a rotation transformation for the cube
        Matrix.setRotateM(rotationMatrix, 0, angle, 0.0f, 1.0f, 0.0f)

        // Translate the cube along the x-axis
        Matrix.translateM(rotationMatrix, 0, cubePositionX, 0.0f, 0.0f)

        // Combine the rotation matrix with the projection and view transformation
        Matrix.multiplyMM(scratchMatrix, 0, viewMatrix, 0, rotationMatrix, 0)
        Matrix.multiplyMM(mvpMatrix, 0, projectionMatrix, 0, scratchMatrix, 0)

        // Draw the cube
        cube?.draw(mvpMatrix)

    }

    //TODO: handle the gesture filtering on the fragment to avoid useless rendering or return something here
    fun setRendererVariable(gestureRecognizerResult: GestureRecognizerResult) {
//        if (gestureRecognizerResult.gestures().size > 0) {
//            Log.e(
//                "SET RENDERER",
//                "setRendererVariable: ${gestureRecognizerResult.gestures().toString()}"
//            )
//            if (gestureRecognizerResult.gestures()[0][0].categoryName().equals("Thumb_Up")) {
//                    triangleY += 0.01f
//            }
//            if (gestureRecognizerResult.gestures()[0][0].categoryName().equals("Open_Palm")) {
//                angle += 1f
//            } else if (gestureRecognizerResult.gestures()[0][0].categoryName()
//                    .equals("Thumb_Down")
//            ) {
//                    triangleY -= 0.01f
//            } else if (gestureRecognizerResult.gestures()[0][0].categoryName()
//                    .equals("Closed_Fist")
//            ) {
//                angle -= 1.0f
//            } else {
//                //DO NOTHING
//            }
//        }
    }

    companion object {
        fun loadShader(type: Int, shaderCode: String?): Int {
            // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
            // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)

            val shader = GLES20.glCreateShader(type)

            // add the source code to the shader and compile it
            GLES20.glShaderSource(shader, shaderCode)
            GLES20.glCompileShader(shader)

            return shader
        }
    }

    private fun updateTrianglePosition() {
        // Update triangle position based on velocity
//        triangleX += velocityX
//        triangleY += velocityY
//
//        // Check boundaries and change direction if necessary
//        if (triangleX >= 1.0f || triangleX <= -1.0f) {
//            velocityX = -velocityX
//        }
//        if (triangleY >= 1.0f || triangleY <= -1.0f) {
//            velocityY = -velocityY
//        }

        // Update triangle coordinates
//        mTriangle?.updatePosition(triangleX, triangleY, angle)
    }


}