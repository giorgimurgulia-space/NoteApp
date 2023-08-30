package com.space.noteapp

import android.app.Application
import com.space.noteapp.data.local.database.dataBaseModule
import com.space.noteapp.domain.di.repositoryModule
import com.space.noteapp.domain.di.useCaseModule
import com.space.noteapp.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class NotesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NotesApp)
            modules(
                dataBaseModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}