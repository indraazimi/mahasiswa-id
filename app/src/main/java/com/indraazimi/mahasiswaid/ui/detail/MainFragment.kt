/*
 * Copyright (c) 2021 Indra Azimi. All rights reserved.
 *
 * Dibuat untuk kelas Pemrograman untuk Perangkat Bergerak 2.
 * Dilarang melakukan penggandaan dan atau komersialisasi,
 * sebagian atau seluruh bagian, baik cetak maupun elektronik
 * terhadap project ini tanpa izin pemilik hak cipta.
 */

package com.indraazimi.mahasiswaid.ui.detail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.indraazimi.mahasiswaid.R
import com.indraazimi.mahasiswaid.data.Mahasiswa
import com.indraazimi.mahasiswaid.data.MahasiswaDb
import com.indraazimi.mahasiswaid.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var myAdapter: MainAdapter
    private var actionMode: ActionMode? = null

    private val viewModel: MainViewModel by lazy {
        val dataSource = MahasiswaDb.getInstance(requireContext()).dao
        val factory = MainViewModelFactory(dataSource)
        ViewModelProvider(requireActivity(), factory).get(MainViewModel::class.java)
    }

    private val handler = object : MainAdapter.ClickHandler {
        override fun onClick(position: Int, mahasiswa: Mahasiswa) {
            if (actionMode != null) {
                myAdapter.toggleSelection(position)
                if (myAdapter.getSelection().isEmpty())
                    actionMode?.finish()
                else
                    actionMode?.invalidate()
                return
            }

            val message = getString(R.string.mahasiswa_klik, mahasiswa.nama)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        override fun onLongClick(position: Int): Boolean {
            if (actionMode != null) return false

            myAdapter.toggleSelection(position)
            actionMode = (requireActivity() as AppCompatActivity)
                .startSupportActionMode(actionModeCallback)
            return true
        }
    }

    private val actionModeCallback = object : ActionMode.Callback {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            if (item?.itemId == R.id.menu_delete) {
                deleteData()
                return true
            }
            return false
        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.main_mode, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.title = myAdapter.getSelection().size.toString()
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            actionMode = null
            myAdapter.resetSelection()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = MainFragmentArgs.fromBundle(requireArguments())
        binding.fab.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToMainDialog(args.kelas)
            )
        }

        myAdapter = MainAdapter(handler)
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            setHasFixedSize(true)
            adapter = myAdapter
        }

        viewModel.data.observe(viewLifecycleOwner, {
            myAdapter.submitList(it)
            binding.emptyView.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        })
    }

    private fun deleteData() = AlertDialog.Builder(requireContext()).apply {
        setMessage(R.string.pesan_hapus)
        setPositiveButton(R.string.hapus) { _, _ ->
            viewModel.deleteData(myAdapter.getSelection())
            actionMode?.finish()
        }
        setNegativeButton(R.string.batal) { dialog, _ ->
            dialog.cancel()
            actionMode?.finish()
        }
        show()
    }
}