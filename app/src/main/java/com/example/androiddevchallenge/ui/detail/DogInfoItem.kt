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

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MonitorWeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun DogInfoItem(imageVector: ImageVector, details: String) {
    Card(Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
        Row(
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = details,
                tint = MaterialTheme.colors.primaryVariant,
                modifier = Modifier
                    .size(32.dp)
            )
            Text(
                text = details,
                maxLines = 2,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun DogInfoPreview() {
    MyTheme {
        DogInfoItem(
            imageVector = Icons.Rounded.MonitorWeight,
            details = "100 kgs"
        )
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DogInfoDarkPreview() {
    MyTheme(darkTheme = true) {
        DogInfoItem(
            imageVector = Icons.Rounded.MonitorWeight,
            details = "100 kgs"
        )
    }
}
