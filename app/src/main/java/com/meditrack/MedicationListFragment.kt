package com.meditrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.meditrack.data.DataViewModel

class MedicationListFragment : Fragment() {

    private val dataViewModel: DataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_medication_list, container, false)

        val newAdapter = MedicationListAdapter()

        view.findViewById<RecyclerView>(R.id.recycler)?.apply {
            adapter = newAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        dataViewModel.all.observe(viewLifecycleOwner) { newData ->
            newAdapter.setData(newData)
        }

        view.findViewById<FloatingActionButton>(R.id.addButton)?.setOnClickListener {
            findNavController().navigate(R.id.action_medicationListFragment_to_medicationFragment)
        }

        return view
    }
}
