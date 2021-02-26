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
package com.example.androiddevchallenge.ui.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.navigateToDogDetails
import com.example.androiddevchallenge.respository.dtos.Response
import com.example.androiddevchallenge.ui.common.ErrorScreen
import com.example.androiddevchallenge.ui.common.LoadingScreen
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
private fun AppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Rounded.Palette,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        },
        title = { Text(text = stringResource(R.string.app_title)) },
        backgroundColor = MaterialTheme.colors.primary
    )
}

@Composable
fun DogsListScreen(
    navController: NavHostController,
    viewModel: DogsListScreenViewModel
) {
    Scaffold(topBar = { AppBar() }) {
        when (val state = viewModel.viewState.observeAsState(Response.Loading).value) {
            is Response.Success -> {
                LazyColumn {
                    items(state.item) { dogDetail ->
                        DosListItem(
                            title = dogDetail.name,
                            subtitle = dogDetail.temperament.orEmpty(),
                            imageUrl = dogDetail.dogImage.url,
                            onListItemClick = navController::navigateToDogDetails
                        )
                    }
                }
            }
            is Response.Error -> ErrorScreen { viewModel.onViewEvent(DogListViewEvent.Retry) }
            Response.Loading -> LoadingScreen()
        }
    }
}

@Composable
fun DogImage(imageUrl: String, dogName: String, modifier: Modifier = Modifier) {
    CoilImage(
        data = imageUrl,
        contentDescription = dogName,
        fadeIn = true,
        contentScale = ContentScale.Crop,
        modifier = modifier,
    )
}
