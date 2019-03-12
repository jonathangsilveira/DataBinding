package br.edu.jonathangsilveira.kotlindatabinding.data

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry

open class ObservableViewModel(application: Application) : AndroidViewModel(application), Observable {

    private val _callback: PropertyChangeRegistry = PropertyChangeRegistry()

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        _callback.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        _callback.add(callback)
    }

    @Suppress("unused")
    fun notifyChange() {
        _callback.notifyCallbacks(this, 0, null)
    }

    fun notifyPropertyChanged(fieldId: Int) {
        _callback.notifyCallbacks(this, fieldId, null)
    }

}