package com.example.frce.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frce.network.Item
import com.example.frce.network.ItemApi
import kotlinx.coroutines.launch

private val TAG: String = MainViewModel::class.java.simpleName

class MainViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Item>>()

    val items: LiveData<List<Item>> = _items

    init {
        getItems()
    }

    private fun getItems() {
        viewModelScope.launch {
            try {
                var listResult: MutableList<Item> = ItemApi.retrofitService.getItems()
                    .filter { it.name != "" && it.name != null } as MutableList<Item>
                listResult =
                    listResult.sortedWith(compareBy({ it.listId }, { it.id })) as MutableList<Item>
                _items.value = listResult
            } catch (e: Exception) {
                Log.d(TAG, "Retrieve data unsuccessfully", e)
            }
        }
    }
}