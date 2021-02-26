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
package com.example.androiddevchallenge.respository.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogsDetail(
    @Json(name = "bred_for")
    val bredFor: String? = null,
    @Json(name = "breed_group")
    val breedGroup: String? = null,
    @Json(name = "country_code")
    val countryCode: String? = null,
    @Json(name = "height")
    val height: Height,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val dogImage: DogImage,
    @Json(name = "life_span")
    val lifeSpan: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "reference_image_id")
    val referenceImageId: String,
    @Json(name = "temperament")
    val temperament: String? = null,
    @Json(name = "weight")
    val weight: Weight
)
