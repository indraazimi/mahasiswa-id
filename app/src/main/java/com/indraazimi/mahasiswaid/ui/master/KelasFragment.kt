/*
 * Copyright (c) 2021 Indra Azimi. All rights reserved.
 *
 * Dibuat untuk kelas Pemrograman untuk Perangkat Bergerak 2.
 * Dilarang melakukan penggandaan dan atau komersialisasi,
 * sebagian atau seluruh bagian, baik cetak maupun elektronik
 * terhadap project ini tanpa izin pemilik hak cipta.
 */

package com.indraazimi.mahasiswaid.ui.master

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.indraazimi.mahasiswaid.R
import com.indraazimi.mahasiswaid.databinding.FragmentKelasBinding

class KelasFragment : Fragment() {

    private lateinit var binding: FragmentKelasBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentKelasBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val kelas = resources.getStringArray(R.array.kelas)
        binding.listView.adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_1, android.R.id.text1, kelas)
        binding.listView.setOnItemClickListener { _, _, position, _ ->
            findNavController().navigate(R.id.action_kelasFragment_to_mainFragment)
        }
    }
}