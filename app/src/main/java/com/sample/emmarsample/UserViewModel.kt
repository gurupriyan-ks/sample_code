package com.sample.emmarsample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.emmarsample.UsersFragment.STATUS_ERROR
import com.sample.emmarsample.UsersFragment.STATUS_SUCCESS
import com.sample.emmarsample.models.servicemodels.Results
import com.sample.emmarsample.repo.UsersRepo.getUsers

class UserViewModel : ViewModel() {
    val users = MutableLiveData<UserScreenModel>()
    lateinit var selectedUser: Results

    fun loadUsers(page: Int = 1) {
        getUsers(1,
            { usersResponse ->
                users.postValue(UserScreenModel(STATUS_SUCCESS, usersResponse.results))
            }
        ) { error: String ->
            users.postValue(UserScreenModel(STATUS_ERROR, null))
        }
    }
}

data class UserScreenModel(
    var status: Int?,
    var users: List<Results>?
)