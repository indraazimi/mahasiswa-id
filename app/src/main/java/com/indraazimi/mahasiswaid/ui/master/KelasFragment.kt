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
import android.widget.ListView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.indraazimi.mahasiswaid.R
import com.indraazimi.mahasiswaid.databinding.FragmentKelasBinding

class KelasFragment : Fragment() {

    private lateinit var binding: FragmentKelasBinding
    private var isTablet: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentKelasBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        isTablet = resources.getBoolean(R.bool.isTablet)
        val kelas = resources.getStringArray(R.array.kelas)
        binding.listView.adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_activated_1, android.R.id.text1, kelas)
        binding.listView.setOnItemClickListener { _, _, position, _ ->
            if (isTablet) {
                findNavController().navigate(R.id.action_global_mainFragment,
                    bundleOf("kelas" to kelas[position]))
                return@setOnItemClickListener
            }

            findNavController().navigate(
                KelasFragmentDirections.actionKelasFragmentToMainFragment(kelas[position])
            )
        }

        if (isTablet) {
            binding.listView.choiceMode = ListView.CHOICE_MODE_SINGLE
            binding.listView.setItemChecked(0, true)
        }
    }
}