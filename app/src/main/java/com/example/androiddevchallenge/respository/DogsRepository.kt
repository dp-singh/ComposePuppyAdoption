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
package com.example.androiddevchallenge.respository

import android.content.res.AssetManager
import com.example.androiddevchallenge.respository.dtos.DogsDetail
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DogsRepository @Inject constructor(
    private val assets: AssetManager,
    private val moshi: Moshi
) {

    fun getDogsList(): List<DogsDetail> {
        return with(Dispatchers.IO) {
            val jsonString = assets.open(FILE_NAME).bufferedReader().use { reader ->
                reader.readText()
            }
            val listMyData = Types.newParameterizedType(
                MutableList::class.java,
                DogsDetail::class.java
            )
            val jsonAdapter = moshi.adapter<List<DogsDetail>>(listMyData)
            jsonAdapter.fromJson(jsonString).orEmpty()
        }
    }

    fun getDogDetails(name: String): DogsDetail {
        return getDogsList().findLast { it.name == name }
            ?: throw IllegalStateException("Dog with $name not found in the db")
    }

    companion object {
        private const val FILE_NAME = "list.json"
    }
}
