package com.dumanyusuf.roomretrofit.presentation.detail_view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import coil.compose.rememberAsyncImagePainter
import com.dumanyusuf.roomretrofit.R
import com.dumanyusuf.roomretrofit.domain.model.Flag

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailPage(flag: Flag,navController: NavController) {


    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row (verticalAlignment = Alignment.CenterVertically){
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(painter = painterResource(R.drawable.back), contentDescription = "")
                    }
                    Text(text = flag.name)
                } })
        },
        content = {
            Column (modifier = Modifier.padding(it)){
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop,
                    painter = rememberAsyncImagePainter(model = flag.flag), contentDescription = "")
                Text(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp),
                    text = "Region:${flag.region}")
                Text(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp),
                    text = "Language:${flag.language}")

            }
        }
    )


}