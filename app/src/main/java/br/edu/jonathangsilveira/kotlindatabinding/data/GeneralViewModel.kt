package br.edu.jonathangsilveira.kotlindatabinding.data

import android.app.Application
import android.databinding.Observable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField

class GeneralViewModel(application: Application) : ObservableViewModel(application)  {

    private val propertyChangeCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            handlePropertyChanges(propertyId)
        }
    }

    val value: ObservableField<String> = ObservableField()

    val isShowingValues: ObservableBoolean = ObservableBoolean(false)

    val paymentMethod : ObservableField<String> = ObservableField("")

    init {
        addOnPropertyChangedCallback(propertyChangeCallback)
    }

    private fun handlePropertyChanges(propertyId: Int) {

    }

}