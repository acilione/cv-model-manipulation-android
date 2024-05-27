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

import android.os.Bundle
import android.view.SurfaceView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.mediapipe.examples.gesturerecognizer.databinding.ActivityMainBinding
import com.google.mediapipe.examples.gesturerecognizer.fragment.CameraFragment
import com.google.mediapipe.examples.gesturerecognizer.fragment.OpenGLGraphicsFragment
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult

class MainActivity : AppCompatActivity(), FragmentCommunication {
    private lateinit var activityMainBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        if (savedInstanceState == null) {
            // Add Fragment1
            supportFragmentManager.commit {
                replace(R.id.fragment_container_1, CameraFragment())
            }

            // Add Fragment2
            supportFragmentManager.commit {
                replace(R.id.fragment_container_2, OpenGLGraphicsFragment())
            }
        }

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
//        val navController = navHostFragment.navController

//        activityMainBinding.navigation.setupWithNavController(navController)
//
//        activityMainBinding.navigation.setOnNavigationItemReselectedListener {
//            // ignore the reselection
//        }
    }

    override fun onBackPressed() {
        finish()
    }

    //TODO: add lifecycle check and protections
    override fun setResults(
        gestureRecognizerResult: GestureRecognizerResult,
        imageHeight: Int,
        imageWidth: Int,
        runningMode: RunningMode
    ) {
        //get instance of fragment2 and call setResults method inside openGlFragment
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container_2) as? OpenGLGraphicsFragment
        fragment?.setResults(gestureRecognizerResult, imageHeight, imageWidth, runningMode)
    }

}
