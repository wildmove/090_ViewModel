package com.example.viewmodellearn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.viewmodellearn.Data.DataForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CobaViewModel: ViewModel() {
    var namaUser: String by mutableStateOf("")
        private set
    var noTlp: String by mutableStateOf("")
        private set
    var email: String by mutableStateOf("")
    var jenisKl: String by mutableStateOf("")
        private set
    var statusNikah: String by mutableStateOf("")
        private set
    var alamat: String by mutableStateOf("")
        private set
    private val _uiState = MutableStateFlow(DataForm())
    val uiState: StateFlow<DataForm> = _uiState.asStateFlow()

    fun insertData(nm: String, tlp: String, jk: String, eml: String, stts: String, almt: String) {
        namaUser = nm;
        noTlp = tlp;
        jenisKl = jk;
        statusNikah = stts;
        email = eml;
        alamat = almt;
    }

    fun setJenisK(pilihJK: String) {
        _uiState.update { currentState -> currentState.copy(sex = pilihJK) }
    }

    fun setStatus(pilihStatus: String) {
        _uiState.update { currentState -> currentState.copy(statusPernikahan = pilihStatus) }
    }
}