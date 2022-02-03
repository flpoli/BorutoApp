package com.example.borutoapp.presentation.screens.search

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems

@ExperimentalMaterialApi
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = {

                    searchViewModel.updateSearchQuery(query = it)
                },
                onCloseClicked = {

                    navController.popBackStack()
                },
                onSearchClicked = {

                    searchViewModel.searchHeroes(query = it)
                },
            )
        }
    ) {
        
    }
}



