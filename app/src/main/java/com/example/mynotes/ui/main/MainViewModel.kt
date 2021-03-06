package com.example.mynotes.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.mynotes.models.TaskList

class MainViewModel(private val sharedPreferences : SharedPreferences) : ViewModel() {
    //actions after list on listadded
    lateinit var onListTaskAdded: (() -> Unit)
    lateinit var onListAdded: (() -> Unit)
    lateinit var list: TaskList


    val lists: MutableList<TaskList> by lazy {
        retrieveLists()
    }

    private fun retrieveLists() : MutableList<TaskList>{
        val sharedPreferencesContents = sharedPreferences.all
        val taskLists = ArrayList<TaskList>()
        for( taskList in sharedPreferencesContents){
            val itemHashSet = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key,itemHashSet)
            taskLists.add(list)
        }
    return taskLists
    }
    fun saveList(list: TaskList){
        sharedPreferences.edit().putStringSet(list.name,list.tasks.toHashSet()).apply()
        lists.add(list)
        onListAdded.invoke()
    }
    fun updateList(list: TaskList) {
        sharedPreferences.edit().putStringSet(list.name, list.tasks.toHashSet()).apply()
        refreshLists()
    }

    fun refreshLists() {
        lists.clear()
        lists.addAll(retrieveLists())
    }

    fun addTask(task: String) {
        list.tasks.add(task)
        onListTaskAdded.invoke()
    }
}