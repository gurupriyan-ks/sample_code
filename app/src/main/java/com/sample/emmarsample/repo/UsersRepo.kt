package com.sample.emmarsample.repo

import android.util.Log
import com.google.gson.Gson
import com.sample.emmarsample.models.datamodels.User
import com.sample.emmarsample.models.servicemodels.Users
import com.sample.emmarsample.network.NetworkApi
import com.sample.emmarsample.room.UsersDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object UsersRepo : KoinComponent {
    private val db: UsersDao by inject()
    private val apiInterface: NetworkApi by inject()
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    fun getUsers(page: Int = 0, onSuccess: (users: Users) -> Unit, onError: (error: String) -> Unit) {
        //check users in db if found return else call api and store in db
        scope.launch {
            try {
                //check db
                val localUsers = db.loadAllByIds(page)
                if (!localUsers.isNullOrEmpty()) {
                    lateinit var usersJson: Users
                    localUsers.forEach {
                        usersJson = Gson().fromJson(it.userJson, Users::class.java)
                    }
                    onSuccess.invoke(usersJson)
                    return@launch
                } else {
                    //load from api to db and return
                    val response = apiInterface.getUsers().await()
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val user = User(userJson = Gson().toJson(it), page = it.info?.page)
                            db.insertAll(user)
                            onSuccess.invoke(it)
                            return@launch
                        }
                    } else {
                        onError.invoke(response.message())
                        return@launch
                    }
                }
            } catch (e: Exception) {
                onError.invoke("Unknown error occurred")
                return@launch
            }

        }
    }
}