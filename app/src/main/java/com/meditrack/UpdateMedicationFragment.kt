package com.meditrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

class UpdateMedicationFragment : Fragment() {

    private val args by navArgs<UpdateMedicationFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_medication, container, false)

        view.findViewById<EditText>(R.id.editTextUpdateName)?.setText(args.currentItem.name)
        view.findViewById<EditText>(R.id.editTextUpdateDose)
            ?.setText(args.currentItem.dose.toString())
        view.findViewById<EditText>(R.id.editTextUpdateUnit)?.setText(args.currentItem.unit)

        return view
    }
}