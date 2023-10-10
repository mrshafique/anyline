package com.shafik.anyline.di

import com.shafik.anyline.screens.MainRepo
import org.koin.dsl.module

val repositoryModule = module {
    single { MainRepo(get()) } //MainRepo
}