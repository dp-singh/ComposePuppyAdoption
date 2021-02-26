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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.respository.DogsRepository
import com.example.androiddevchallenge.respository.dtos.DogsDetail
import com.example.androiddevchallenge.respository.dtos.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsDetailsScreenViewModel @Inject constructor(private val repository: DogsRepository) :
    ViewModel() {
    private val _viewState = MutableLiveData<Response<DogsDetail>>()
    val viewState: LiveData<Response<DogsDetail>>
        get() = _viewState

    private fun fetchDogList(name: String) {
        _viewState.value = Response.Loading
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getDogDetails(name)
            }.onSuccess {
                _viewState.value = Response.Success(it)
            }.onFailure {
                _viewState.value = Response.Error(it)
            }
        }
    }

    fun onViewEvent(viewEvent: DogDetailsViewEvent) {
        when (viewEvent) {
            is DogDetailsViewEvent.Retry -> fetchDogList(viewEvent.name)
            is DogDetailsViewEvent.Init -> fetchDogList(viewEvent.name)
        }
    }
}

sealed class DogDetailsViewEvent {
    data class Retry(val name: String) : DogDetailsViewEvent()
    data class Init(val name: String) : DogDetailsViewEvent()
}
