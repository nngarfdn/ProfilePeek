package com.apps.profilepeek.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apps.profilepeek.core.domain.model.PersonUiModel
import com.apps.profilepeek.databinding.ItemPersonBinding
import com.bumptech.glide.Glide

class PersonAdapter(
    private val onItemClick: (PersonUiModel) -> Unit
) : ListAdapter<PersonUiModel, PersonAdapter.PersonViewHolder>(PersonDiffCallback()) {

    inner class PersonViewHolder(private val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person: PersonUiModel) {
            binding.textName.text = person.name
            binding.textCity.text = person.city
            Glide.with(binding.root)
                .load(person.avatar)
                .circleCrop()
                .into(binding.imageAvatar)

            // Set the click listener
            binding.root.setOnClickListener {
                onItemClick(person)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = getItem(position)
        holder.bind(person)
    }
}
