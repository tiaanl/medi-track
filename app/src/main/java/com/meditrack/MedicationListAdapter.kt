package com.meditrack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.meditrack.data.Medication

class MedicationListAdapter : RecyclerView.Adapter<MedicationListAdapter.ViewHolder>() {

    private var medication = emptyList<Medication>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.medication_list_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = medication[position]

        holder.medicationName.text = currentItem.name
        holder.dose.text = currentItem.dose.toString() + currentItem.unit

        holder.listItem.setOnClickListener {
            val action =
                MedicationListFragmentDirections.actionMedicationListFragmentToUpdateMedicationFragment(
                    currentItem
                )
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = medication.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val listItem = view.findViewById<ConstraintLayout>(R.id.listItem)!!
        val medicationName = view.findViewById<TextView>(R.id.textMedicationName)!!
        val dose = view.findViewById<TextView>(R.id.textDose)!!
    }

    fun setData(data: List<Medication>) {
        medication = data
        notifyDataSetChanged()
    }
}
