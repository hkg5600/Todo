package com.example.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

fun <VM : ViewModel> viewModelProvider(
    create: () -> VM
): ViewModelProvider.Factory = SimpleFactory(create)

private class SimpleFactory<VM : ViewModel>(private val create: () -> VM) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val vm = create()
        if (modelClass.isInstance(vm)) {
            return vm as T
        }
        throw IllegalArgumentException("Can no create ViewModel fo $modelClass")
    }

}