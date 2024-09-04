package com.apps.profilepeek.detailperson

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.profilepeek.core.domain.model.PersonUiModel
import com.apps.profilepeek.core.domain.usecase.GetDetailPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailPersonUseCase: GetDetailPersonUseCase) :ViewModel(){
    private val _detail =  MutableLiveData<PersonUiModel>()
    val detail = _detail

    fun getDetailPerson(id: String){
        viewModelScope.launch {
            getDetailPersonUseCase.execute(id).collectLatest {
                _detail.value = it.data
            }
        }
    }
}