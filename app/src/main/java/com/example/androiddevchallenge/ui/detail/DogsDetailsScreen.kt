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
package com.example.androiddevchallenge.ui.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Height
import androidx.compose.material.icons.rounded.MonitorWeight
import androidx.compose.material.icons.rounded.Nightlife
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.respository.dtos.Response
import com.example.androiddevchallenge.ui.common.ErrorScreen
import com.example.androiddevchallenge.ui.common.LoadingScreen
import com.example.androiddevchallenge.ui.list.DogImage

@ExperimentalFoundationApi
@Composable
fun DogsDetailsScreen(
    dogName: String = "",
    viewModel: DogsDetailsScreenViewModel,
    onBackPress: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            AppBar(dogName = dogName, onBackPress = onBackPress)
        }
    ) {
        when (val state = viewModel.viewState.observeAsState(Response.Loading).value) {
            is Response.Error -> ErrorScreen {
                viewModel.onViewEvent(DogDetailsViewEvent.Retry(dogName))
            }
            Response.Loading -> LoadingScreen()
            is Response.Success -> {
                Column(Modifier.fillMaxSize()) {
                    DogImage(
                        imageUrl = state.item.dogImage.url,
                        dogName = state.item.name,
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5F)
                    )

                    DogTemperament(state.item.temperament.orEmpty())
                    DogInfoItem(
                        imageVector = Icons.Rounded.MonitorWeight,
                        details = stringResource(R.string.wait_text, state.item.weight.metric)
                    )
                    DogInfoItem(
                        imageVector = Icons.Rounded.Height,
                        details = stringResource(R.string.height_text, state.item.height.metric)
                    )
                    DogInfoItem(
                        imageVector = Icons.Rounded.Nightlife,
                        details = stringResource(R.string.life_span_text, state.item.lifeSpan)
                    )
                }
            }
        }
    }
}

@Composable
private fun AppBar(dogName: String, onBackPress: () -> Unit = {}) {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable {
                        onBackPress()
                    }
            )
        },
        title = { Text(text = dogName) },
        backgroundColor = MaterialTheme.colors.primary
    )
}
