/*
 * Copyright (c) 2021 Indra Azimi. All rights reserved.
 *
 * Dibuat untuk kelas Pemrograman untuk Perangkat Bergerak 2.
 * Dilarang melakukan penggandaan dan atau komersialisasi,
 * sebagian atau seluruh bagian, baik cetak maupun elektronik
 * terhadap project ini tanpa izin pemilik hak cipta.
 */

package com.indraazimi.mahasiswaid

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.indraazimi.mahasiswaid.data.Mahasiswa
import com.indraazimi.mahasiswaid.data.MahasiswaDb
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    companion object {
        private val MAHASISWA_DUMMY = Mahasiswa(0, "6706180001", "Tika Aulia Utami")
    }

    @Before
    fun setUp() {
        // Lakukan penghapusan database setiap kali test akan dijalankan.
        InstrumentationRegistry.getInstrumentation().targetContext
            .deleteDatabase(MahasiswaDb.DATABASE_NAME)
    }

    @Test
    fun testInsert() {
        // Jalankan MainActivity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Lakukan aksi untuk menambah data baru
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.nimEditText)).perform(typeText(MAHASISWA_DUMMY.nim))
        onView(withId(R.id.namaEditText)).perform(typeText(MAHASISWA_DUMMY.nama))
        onView(withText(R.string.simpan)).perform(click())

        // Cek apakah data tersebut muncul
        onView(withText(MAHASISWA_DUMMY.nim)).check(matches(isDisplayed()))
        onView(withText(MAHASISWA_DUMMY.nama)).check(matches(isDisplayed()))

        // Tes selesai, tutup activity nya
        activityScenario.close()
    }
}