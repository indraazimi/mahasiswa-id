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
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue

class MahasiswaLiveData(private val db: DatabaseReference) : LiveData<List<Mahasiswa>>() {

    private val data = ArrayList<Mahasiswa>()

    init {
        value = data.toList()
    }

    private val listener = object : ChildEventListener {
        override fun onCancelled(error: DatabaseError) { }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) { }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            val mahasiswa = snapshot.getValue<Mahasiswa>() ?: return
            mahasiswa.id = snapshot.key ?: return

            val pos = data.indexOfFirst { it.id == mahasiswa.id }
            data[pos] = mahasiswa
            value = data.toList()
        }

        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val mahasiswa = snapshot.getValue<Mahasiswa>() ?: return
            mahasiswa.id = snapshot.key ?: return

            data.add(mahasiswa)
            value = data.toList()
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
            val id = snapshot.key ?: return

            val pos = data.indexOfFirst { it.id == id }
            data.removeAt(pos)
            value = data.toList()
        }
    }

    override fun onActive() {
        db.addChildEventListener(listener)
    }

    override fun onInactive() {
        db.removeEventListener(listener)
        data.clear()
    }
}