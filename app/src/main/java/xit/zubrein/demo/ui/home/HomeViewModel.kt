package xit.zubrein.demo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import xit.zubrein.demo.model.ModelSchedule
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    val homeRepository: HomeRepository
) : ViewModel() {
    val getScheduleData : LiveData<List<ModelSchedule>> = homeRepository.getScheduleData.flowOn(
        Dispatchers.Main)
        .asLiveData(context =  viewModelScope.coroutineContext)
}
