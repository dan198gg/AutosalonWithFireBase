package com.example.fireproject

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.typography

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.example.fireproject.data.CarModel
import com.google.api.Context
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import java.io.ByteArrayOutputStream
import java.net.URI


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val fs=Firebase.firestore
        FirebaseFirestore.setLoggingEnabled(true)
//        fs.collection("cars2").document().set(mapOf("someCar" to "Subaru"))
//        fs.collection("cars").document().set(CarModel("BMW"
//            ,"7-series"
//            , mapOf("ЛС" to "320",
//                "Мотор" to "B57D30",
//                "Тип двигателя" to "дизель",
//                "КПП" to "АКПП",
//                "привод" to "полный привод (4WD)"),
//            mutableListOf("Red","Blue"),"9500000руб","https://firebasestorage.googleapis.com/v0/b/autosalon2-f4d9b.appspot.com/o/машины\uD83E\uDD78%2Fbmw7ser.jpeg?alt=media&token=cf32808b-254d-4486-a9fa-fff64a994acf"))
//            fs.collection("cars").document().set(CarModel("BMW"
//            ,"330i"
//            , mapOf("ЛС" to "231",
//                "Мотор" to "B47D20",
//                "Тип двигателя" to "бензин",
//                "КПП" to "АКПП",
//                "привод" to "задний привод"),
//            mutableListOf("Red","Blue"),"6000000руб","https://firebasestorage.googleapis.com/v0/b/autosalon2-f4d9b.appspot.com/o/машины\uD83E\uDD78%2Fbmw330i2019.jpeg?alt=media&token=d00722b8-2083-4888-8248-58ba4f2893b8"))
        setContent {
            GreetingPreview()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val context=LocalContext.current
    val fs=Firebase.firestore
    val storage=Firebase.storage.reference.child("машины🥸")
    val lst= remember {
        mutableStateOf(emptyList<CarModel>())
    }
    fs.collection("cars").get().addOnCompleteListener{
        task->
        if(task.isSuccessful){
            lst.value=task.result.toObjects(CarModel::class.java)
        }
    }
    Column(modifier = Modifier
        .border(10.dp, Color.Green)
        .fillMaxHeight(1f)
        .fillMaxWidth(1f)

    ) {
        LazyColumn(
            Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.8f)
                .border(10.dp, Color.Red)) {
            items(lst.value){
                car->
                Card(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(10.dp)

                    )
                {
                    Row(modifier = Modifier.fillMaxWidth(1f).fillMaxHeight(0.9f)) {
                        AsyncImage(model = car.imageUrl, contentDescription = null, modifier = Modifier.padding(10.dp).size(150.dp,100.dp).border(10.dp, color = Color.Gray))
                        Log.i("IMGURL",car.imageUrl)
                        Text("${car.brand} ${car.model}", fontSize = 25.sp,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
        Button(onClick = {
//                         storage.child("bmw330i2019(3).jpeg").putBytes(bitmapToByteArray(context))
            context.startActivity(Intent(context, AddCarModelActivity::class.java))
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "добавить автомобиль", fontSize = 25.sp)
        }
    }
}

fun bitmapToByteArray(context:android.content.Context,uri: Uri): ByteArray{
    val inputStream=context.contentResolver.openInputStream(uri)
    val bitmap=BitmapFactory.decodeStream(inputStream)
    val baos=ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos)
    return baos.toByteArray()
}
