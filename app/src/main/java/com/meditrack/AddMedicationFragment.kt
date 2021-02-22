package com.meditrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.meditrack.data.DataViewModel
import com.meditrack.data.Medication

class AddMedicationFragment : Fragment() {

    private val viewModel: DataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_medication, container, false)

        view.findViewById<Button>(R.id.addMedicationButton)?.setOnClickListener {
            val name = view.findViewById<EditText>(R.id.editTextAddName)?.text?.toString() ?: ""
            val dose =
                view.findViewById<EditText>(R.id.editTextAddDose)?.text?.toString()?.toFloat() ?: 0f
            val unit = view.findViewById<EditText>(R.id.editTextAddUnit)?.text?.toString() ?: ""

            viewModel.addMedication(Medication(0, name, dose, unit))
            Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_medicationFragment_to_medicationListFragment)
        }

        return view
    }
}
