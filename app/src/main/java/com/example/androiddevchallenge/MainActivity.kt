/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.detail.DogDetailsViewEvent
import com.example.androiddevchallenge.ui.detail.DogsDetailsScreen
import com.example.androiddevchallenge.ui.detail.DogsDetailsScreenViewModel
import com.example.androiddevchallenge.ui.list.DogsListScreen
import com.example.androiddevchallenge.ui.list.DogsListScreenViewModel
import com.example.androiddevchallenge.ui.theme.MyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModelList: DogsListScreenViewModel by viewModels()
    private val viewModelDetail: DogsDetailsScreenViewModel by viewModels()

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                val navController: NavHostController = rememberNavController()
                NavHost(navController, startDestination = URI_DOG_LIST) {
                    composable(URI_DOG_LIST) {
                        DogsListScreen(
                            navController,
                            viewModelList
                        )
                    }
                    composable(
                        URI_DOG_DETAILS,
                        arguments = listOf(
                            navArgument(PARAM_DOG_NAME) { type = NavType.StringType },
                        )
                    ) { backStackEntry ->
                        val dogName = backStackEntry.arguments?.getString(PARAM_DOG_NAME).orEmpty()
                        viewModelDetail.onViewEvent(DogDetailsViewEvent.Init(dogName))
                        DogsDetailsScreen(dogName = dogName, viewModelDetail) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}

const val PARAM_DOG_NAME = "dogName"
const val URI_DOG_LIST = "dog_list"
const val URI_DOG_DETAILS = "dog_detail/{$PARAM_DOG_NAME}"
fun NavHostController.navigateToDogDetails(dogName: String) {
    this.navigate(URI_DOG_DETAILS.replace("{$PARAM_DOG_NAME}", dogName))
}
