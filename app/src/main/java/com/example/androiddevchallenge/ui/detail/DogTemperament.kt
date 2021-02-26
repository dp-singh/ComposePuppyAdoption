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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@ExperimentalFoundationApi
@Composable
fun DogTemperament(temperament: String) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(temperament.split(",")) { item ->
            OutlinedButton(
                onClick = {},
                shape = RoundedCornerShape(50),
                modifier = Modifier.padding(2.dp)
            ) {
                Text(
                    text = item.capitalize(),
                    maxLines = 1,
                    softWrap = false,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
@ExperimentalFoundationApi
fun DogTemperamentPreview() {
    MyTheme {
        DogTemperament("Strong Willed, Stubborn, Friendly, Clownish, Affectionate, Loyal, Obedient, Intelligent, Courageous")
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
@ExperimentalFoundationApi
fun DogTemperamentDarkPreview() {
    MyTheme(darkTheme = true) {
        DogTemperament("Strong Willed, Stubborn, Friendly, Clownish, Affectionate, Loyal, Obedient, Intelligent, Courageous")
    }
}
