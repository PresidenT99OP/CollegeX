package com.example.collegex

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.collegex.databinding.MainFragmentBinding
import kotlinx.coroutines.InternalCoroutinesApi

class MainFragment : Fragment(),
    NotesListAdapter.ListItemListener{


    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: NotesListAdapter


    @InternalCoroutinesApi
    override fun onCreateView(

            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(true)

        binding = MainFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        with(binding.recyclerView) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                    context, LinearLayoutManager(context).orientation
            )
            addItemDecoration(divider)

        }


        viewModel.notesList?.observe(viewLifecycleOwner, Observer {
            Log.i("Note Logging", it.toString())
            adapter = NotesListAdapter(it,this@MainFragment)
            binding.recyclerView.adapter = adapter

            binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        val menuId =
            if (this::adapter.isInitialized &&
                adapter.selectedNotes.isNotEmpty()){
                R.menu.menu_main_selected_items
            }else{
                R.menu.menu_main
            }


        inflater.inflate(menuId , menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    @InternalCoroutinesApi
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_sample_data -> addSampleData()
            R.id.action_delete -> deleteSelectedNotes()
            R.id.action_delete_all -> deleteAll()

            else -> super.onOptionsItemSelected(item)
        }


    }

    private fun deleteAll(): Boolean {

        viewModel.deleteAllNote()
    }

    @InternalCoroutinesApi
    private fun deleteSelectedNotes(): Boolean {

        viewModel.deleteNotes(adapter.selectedNotes)
        Handler(Looper.getMainLooper()).postDelayed({
            adapter.selectedNotes.clear()
            requireActivity().invalidateOptionsMenu()
        }, 100)
        return true
    }

    @InternalCoroutinesApi
    private fun addSampleData():Boolean {

        viewModel.addSampleData()
        return true
    }


    override fun onItemClick(noteId: Int) {

        Log.i(TAG, "On Item Click : received id $noteId")
        Toast.makeText(activity , "$noteId", Toast.LENGTH_SHORT).show()
        val action = MainFragmentDirections.actionEditNote(noteId)
        findNavController().navigate(action)
    }

    override fun onItemSelectionChange() {
        requireActivity().invalidateOptionsMenu()
    }

}