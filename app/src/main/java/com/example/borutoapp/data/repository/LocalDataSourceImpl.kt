package com.example.borutoapp.data.repository

import com.example.borutoapp.data.local.BorutoDataBase
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(borutoDatabase: BorutoDataBase): LocalDataSource {

    private val heroDao = borutoDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {

        return heroDao.getSelectedHero(heroId = heroId)
    }
}