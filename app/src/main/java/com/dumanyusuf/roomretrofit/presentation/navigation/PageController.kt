package com.dumanyusuf.roomretrofit.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dumanyusuf.roomretrofit.Screen
import com.dumanyusuf.roomretrofit.domain.model.Flag
import com.dumanyusuf.roomretrofit.presentation.detail_view.DetailPage
import com.dumanyusuf.roomretrofit.presentation.home_view.view.HomePage
import com.google.gson.Gson
import java.net.URLDecoder

@Composable
fun PageController() {

    val navController= rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HomepageView.route){
        composable(Screen.HomepageView.route){
           HomePage(navController)
        }
        composable(Screen.DetailPageView.route+"/{flag}",
            arguments = listOf(
                navArgument("flag"){type=NavType.StringType}
            )
        ){
            val jsonNews = it.arguments?.getString("flag")
            val decodedJsonNews = URLDecoder.decode(jsonNews, "UTF-8")
            val flag = Gson().fromJson(decodedJsonNews, Flag::class.java)
           DetailPage(flag = flag,navController)
        }
    }

}