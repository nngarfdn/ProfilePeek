package com.apps.profilepeek.adapter

import androidx.recyclerview.widget.DiffUtil
import com.apps.profilepeek.core.domain.model.PersonUiModel

class PersonDiffCallback : DiffUtil.ItemCallback<PersonUiModel>() {
    override fun areItemsTheSame(oldItem: PersonUiModel, newItem: PersonUiModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PersonUiModel, newItem: PersonUiModel): Boolean {
        return oldItem == newItem
    }

}
