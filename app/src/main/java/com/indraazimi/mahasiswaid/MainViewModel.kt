/*
 * Copyright (c) 2021 Indra Azimi. All rights reserved.
 *
 * Dibuat untuk kelas Pemrograman untuk Perangkat Bergerak 2.
 * Dilarang melakukan penggandaan dan atau komersialisasi,
 * sebagian atau seluruh bagian, baik cetak maupun elektronik
 * terhadap project ini tanpa izin pemilik hak cipta.
 */

package com.indraazimi.mahasiswaid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indraazimi.mahasiswaid.data.Mahasiswa
import com.indraazimi.mahasiswaid.data.MahasiswaDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val db : MahasiswaDao) : ViewModel() {

    val data = db.getData()

    fun insertData(mahasiswa: Mahasiswa) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insertData(mahasiswa)
            }
        }
    }

    fun deleteData(ids: List<String>) {
        val newIds = ids.toList()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.deleteData(newIds)
            }
        }
    }
}