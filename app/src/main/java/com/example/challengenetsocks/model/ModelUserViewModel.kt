package com.example.challengenetsocks.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.challengenetsocks.domian.Repository
import com.example.challengenetsocks.utils.Result
import kotlinx.coroutines.Dispatchers

class ModelUserViewModel(private val loginRepo: Repository) : ViewModel() {

    fun singIn(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Succes(loginRepo.singUp(email, password)))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }

    fun createUser(user:UserAuth) = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Succes(loginRepo.register(user)))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }


}


class AuthFactory(private val loginRepo: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ModelUserViewModel(loginRepo) as T
    }

}
