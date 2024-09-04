package com.apps.profilepeek.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.profilepeek.core.data.Resource
import com.apps.profilepeek.core.domain.model.PersonUiModel
import com.apps.profilepeek.core.domain.usecase.GetPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPersonUseCase: GetPersonUseCase
) : ViewModel() {

    private val _personList = MutableLiveData<Resource<List<PersonUiModel>>>()
    val personList: LiveData<Resource<List<PersonUiModel>>> = _personList

    fun getPersonList() {
        viewModelScope.launch {
            getPersonUseCase.execute().collectLatest {
                _personList.value = it
            }
        }
    }

}