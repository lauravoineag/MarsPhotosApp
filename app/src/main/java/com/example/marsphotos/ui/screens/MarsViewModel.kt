/*
 * Copyright (C) 2023 The Android Open Source Project
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
package com.example.marsphotos.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphotos.network.MarsApi
import kotlinx.coroutines.launch

class MarsViewModel : ViewModel() {
        /** The mutable State that stores the status of the most recent request
         * update`MutableState within theViewModel using data you get from the internet.*/
        var marsUiState: String by mutableStateOf("")
            private set

        /**
         * Call getMarsPhotos() on init so we can display status immediately.
         */
        init {
            getMarsPhotos()
        }

        /**
         * Gets Mars photos information from the Mars API Retrofit service and updates the
         *  you use getMarsPhotos() to display the data fetched from the server.
         * [MarsPhoto] [List] [MutableList].
         */

        //7. launch the coroutine using viewModelScope.launch
        // A viewModelScope is the built-in coroutine scope defined for each ViewModel in your app. Any coroutine launched in this scope is automatically canceled if the ViewModel is cleared.
        // You can use viewModelScope to launch the coroutine and make the web service request in the background. viewModelScope belongs to the ViewModel, the request continues even if the app goes through a configuration change.
        private fun getMarsPhotos() { viewModelScope.launch {
            val listResult = MarsApi.retrofitService.getPhotos()//use the singleton object MarsApi to call the getPhotos() method from the retrofitService interface
            marsUiState = listResult//Assign the result just received from the backend server to the marsUiState
        }}
    }
