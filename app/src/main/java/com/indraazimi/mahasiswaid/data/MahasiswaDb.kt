/*
 * Copyright (c) 2021 Indra Azimi. All rights reserved.
 *
 * Dibuat untuk kelas Pemrograman untuk Perangkat Bergerak 2.
 * Dilarang melakukan penggandaan dan atau komersialisasi,
 * sebagian atau seluruh bagian, baik cetak maupun elektronik
 * terhadap project ini tanpa izin pemilik hak cipta.
 */

package com.indraazimi.mahasiswaid.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MahasiswaDb private constructor() {

    // Tidak seperti Room yang otomatis membuatkan implementasi Dao,
    // di Firebase kita harus membuat implementasi Dao sendiri.
    val dao = object : MahasiswaDao {
        override fun insertData(mahasiswa: Mahasiswa) {

        }

        override fun getData(): LiveData<List<Mahasiswa>> {
            val data = MutableLiveData<List<Mahasiswa>>()
            data.value = ArrayList()
            return data
        }

        override fun deleteData(ids: List<Int>) {

        }
    }

    companion object {
        @Volatile
        private var INSTANCE: MahasiswaDb? = null

        fun getInstance(): MahasiswaDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = MahasiswaDb()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}