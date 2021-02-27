package com.meditrack

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.meditrack.data.DataViewModel
import com.meditrack.data.Medication


class UpdateMedicationFragment : Fragment() {

    private val args by navArgs<UpdateMedicationFragmentArgs>()

    private val dataViewModel: DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> {
                AlertDialog.Builder(requireContext())
                    .setMessage("Medication \"${args.currentItem.name}\" will be deleted permanently!")
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        dataViewModel.deleteMedication(args.currentItem)
                        findNavController().navigateUp()
                    }
                    .setNegativeButton(android.R.string.cancel, null)
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_medication, container, false)

        val nameInput = view.findViewById<EditText>(R.id.editTextUpdateName)
        val doseInput = view.findViewById<EditText>(R.id.editTextUpdateDose)
        val unitInput = view.findViewById<EditText>(R.id.editTextUpdateUnit)

        nameInput?.setText(args.currentItem.name)
        doseInput?.setText(args.currentItem.dose.toString())
        unitInput?.setText(args.currentItem.unit)

        view.findViewById<Button>(R.id.updateMedicationButton)?.setOnClickListener {
            val name = nameInput?.text?.toString() ?: ""
            val dose = doseInput?.text?.toString()?.toFloat() ?: 0f
            val unit = unitInput?.text?.toString() ?: ""

            dataViewModel.updateMedication(Medication(args.currentItem.id, name, dose, unit))

            Toast.makeText(requireContext(), "Medication updated", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateMedicationFragment_to_medicationListFragment)
        }

        return view
    }
}
