package com.dumanyusuf.roomretrofit

 sealed class Screen (val route:String){

     object HomepageView:Screen("home")
     object DetailPageView:Screen("detail")

 }