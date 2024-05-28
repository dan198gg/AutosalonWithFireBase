package com.example.fireproject

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.fireproject.ui.theme.FIreProjectTheme
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import androidx.activity.compose.rememberLauncherForActivityResult as rememberLauncherForActivityResult

class AddCarModelActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.setContent {
            stroke(this)
//            bitmapToByteArray(context = LocalContext.current)
        }
    }

}


@Composable
fun stroke(myContext: Context) {
    var text1 by rememberSaveable { mutableStateOf("") }
    var text2 by rememberSaveable { mutableStateOf("") }
    var text3 by rememberSaveable { mutableStateOf("") }
    var text4 by rememberSaveable { mutableStateOf("") }
    var text5 by rememberSaveable { mutableStateOf("") }
    var text6 by rememberSaveable { mutableStateOf("") }
    var text7 by rememberSaveable { mutableStateOf("") }
    val context=LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = text1,
            onValueChange = {
                text1 = it
            },
            label = { Text("Введите марку авто:") },
            modifier = Modifier
                .size(350.dp, 50.dp)
                .offset(30.dp, 150.dp)
        )

        TextField(
            value = text2,
            onValueChange = {
                text2 = it
            },

            label = { Text("Введите модель авто:") },
            modifier = Modifier
                .size(350.dp, 50.dp)
                .offset(30.dp, 250.dp)
        )
        TextField(
            value = text3,
            onValueChange = {
                text3 = it
            },

            label = { Text("Кол-во ЛС:") },
            modifier = Modifier
                .size(150.dp, 50.dp)
                .offset(30.dp, 350.dp)
        )
        TextField(
            value = text4,
            onValueChange = {
                text4 = it
            },

            label = { Text("Тип КПП:") },
            modifier = Modifier
                .size(150.dp, 50.dp)
                .offset(230.dp, 350.dp)
        )
        TextField(
            value = text5,
            onValueChange = {
                text5 = it
            },

            label = { Text("Двигатель:") },
            modifier = Modifier
                .size(150.dp, 50.dp)
                .offset(30.dp, 450.dp)
        )
        TextField(
            value = text6,
            onValueChange = {
                text6 = it
            },

            label = { Text("Привод:") },
            modifier = Modifier
                .size(150.dp, 50.dp)
                .offset(230.dp, 450.dp)
        )
        TextField(
            value = text7,
            onValueChange = {
                text7 = it
            },

            label = { Text(" Цена, руб:") },
            modifier = Modifier
                .size(250.dp, 50.dp)
                .offset(80.dp, 550.dp)
        )
        val storage=Firebase.storage.reference.child("машины🥸")
        val launcher= rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()){
            uri->
            if(uri==null){
                return@rememberLauncherForActivityResult
            }
            var uriStr=uri.toString()
            for (i in  (uriStr.length-1) downTo 0){
                   if(uriStr[i]=='/'){
                       val len=uriStr.length
                       uriStr=uriStr.slice(i+1 .. len-1)
                       break
               }
            }
            storage.child("$uriStr.jpg").putBytes(bitmapToByteArray(myContext,uri))

        }
        Button(onClick = {
                launcher.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageAndVideo))

        }, Modifier.offset(120.dp,650.dp)) {
            Text(text = "Добавить фото")

        }
        IconButton(onClick = {

        }, modifier = Modifier
            .offset(30.dp, 700.dp)
            .size(250.dp,100.dp).alpha(0.2f)) {
            Icon(imageVector = Icons.Rounded.Done, contentDescription = "", modifier = Modifier
                .size(50.dp)
                .border(3.dp, Color.Black))
            Text(text = "Подтвердить", modifier = Modifier.offset(80.dp), fontWeight = FontWeight.Bold)
        }

    }
    
}




//@Composable
//fun click1(myContext: Context){
//    val launcher= rememberLauncherForActivityResult1(contract = ActivityResultContracts.PickVisualMedia()){
//
//    }
//}

//@Composable
//fun dropDownMenu() {
//    var expanded by remember {
//        mutableStateOf(false)
//    }
//    var list = listOf("red", "green", "blue", "white", "black")
//    var selectedColor by remember {
//        mutableStateOf("")
//    }
//    var textFieldSize by remember {
//        mutableStateOf(Size.Zero)
//    }
//    val icon = if (expanded) {
//        Icons.Filled.KeyboardArrowUp
//    } else {
//        Icons.Filled.KeyboardArrowDown
//    }
//    Column {
//
//
//   OutlinedTextField(
    //        value = selectedColor,
//            onValueChange = { selectedColor= it },
//            modifier = Modifier
//                .fillMaxWidth()
//                .onGloballyPositioned { coordinates ->
//                    textFieldSize = coordinates.size.toSize()
//                },
//            label = Text(text = "Выберите цвет"),
//            trailingIcon = {
//                Icon(icon, "", Modifier.clickable { expanded = !expanded })
//       )
//    }
//}


