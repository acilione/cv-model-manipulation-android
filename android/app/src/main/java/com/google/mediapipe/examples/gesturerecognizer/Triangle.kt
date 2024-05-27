package com.google.mediapipe.examples.gesturerecognizer

import android.opengl.GLES20
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import kotlin.math.cos
import kotlin.math.sin

class Triangle {
    private var vertexBuffer: FloatBuffer? = null
    private var mProgram = 0
    private var mPositionHandle = 0
    private var mColorHandle = 0

    private val vertexShaderCode = "attribute vec4 vPosition;" +
            "void main() {" +
            "  gl_Position = vPosition;" +
            "}"

    private val fragmentShaderCode = "precision mediump float;" +
            "uniform vec4 vColor;" +
            "void main() {" +
            "  gl_FragColor = vColor;" +
            "}"

    var color: FloatArray = floatArrayOf(0.63671875f, 0.76953125f, 0.22265625f, 1.0f)

    var triangleCoords: FloatArray = floatArrayOf( // in counterclockwise order:
        0.0f, 0.62200844f, 0.0f,  // top
        -0.5f, -0.31100425f, 0.0f,  // bottom left
        0.5f, -0.31100425f, 0.0f // bottom right
    )

    val initialCoords = triangleCoords.copyOf()

    private val COORDS_PER_VERTEX: Int = 3
    private val vertexCount: Int = triangleCoords.size / COORDS_PER_VERTEX

    private val vertexStride = COORDS_PER_VERTEX * 4 // 4 bytes per vertex

    fun updatePosition(centerX: Float, centerY: Float, angle: Float) {
        // Update triangle coordinates
        val radians = Math.toRadians(angle.toDouble()).toFloat()
        val cos = cos(radians.toDouble()).toFloat()
        val sin = sin(radians.toDouble()).toFloat()

        for (i in initialCoords.indices step COORDS_PER_VERTEX) {
            val x = initialCoords[i]
            val y = initialCoords[i + 1]
            triangleCoords[i] = centerX + (x * cos - y * sin)
            triangleCoords[i + 1] = centerY + (x * sin + y * cos)
        }

        // Recreate vertex buffer with updated coordinates
        vertexBuffer?.clear()
        vertexBuffer?.put(triangleCoords)
        vertexBuffer?.position(0)
    }

    init {
        val vertexShader: Int = MyGLSurfaceViewRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode)
        val fragmentShader: Int =
            MyGLSurfaceViewRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode)
        mProgram = GLES20.glCreateProgram();             // create empty OpenGL ES Program
        GLES20.glAttachShader(mProgram, vertexShader);   // add the vertex shader to program
        GLES20.glAttachShader(mProgram, fragmentShader); // add the fragment shader to program
        GLES20.glLinkProgram(mProgram);                  // creates OpenGL ES program executables
        // initialize vertex byte buffer for shape coordinates
        val bb = ByteBuffer.allocateDirect( // (number of coordinate values * 4 bytes per float)
            triangleCoords.size * 4
        )
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder())
        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer()
        // add the coordinates to the FloatBuffer
        vertexBuffer?.put(triangleCoords)
        // set the buffer to read the first coordinate
        vertexBuffer?.position(0)

    }

    fun draw() {
        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram)
        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition")
        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle)
        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(
            mPositionHandle, COORDS_PER_VERTEX,
            GLES20.GL_FLOAT, false,
            vertexStride, vertexBuffer
        )
        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor")
        // Set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0)
        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle)

    }

}