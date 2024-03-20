package com.example.learnfast

import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale
import kotlin.reflect.KFunction1

@Composable
fun PlayScreen(
    getSpeech: ()-> Unit
){

    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "A for APPLE",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
            )


        Card(
            modifier = Modifier
                .size(200.dp)
                .background(color = Color.White),
        ) {
            Image(painter = painterResource(id = R.drawable.apple),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize())
        }

        Card(
            modifier = Modifier.size(200.dp),
            shape = CircleShape
        ) {
            Button(
                onClick = {
                    if(!SpeechRecognizer.isRecognitionAvailable(context)){
                        Toast.makeText(context, "Speech not available", Toast.LENGTH_LONG).show()
                    }else {

                        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

                        intent.putExtra(
                            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                            RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH
                        )

                        intent.putExtra(
                            RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()
                        )

                        intent.putExtra(
                            RecognizerIntent.EXTRA_PROMPT, "Speak Something"
                        )


                        getSpeech()

                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White),

                content = {
                  Text(text = "Click on this button to record.")
                }
            )
        }
    }
}

/*private fun getSpeech(context: Context){

    if(!SpeechRecognizer.isRecognitionAvailable(context)){
        Toast.makeText(context, "Speech not available", Toast.LENGTH_LONG).show()
    }else {

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH
        )

        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()
        )

        intent.putExtra(
            RecognizerIntent.EXTRA_PROMPT, "Speak Something"
        )




    }

}*/






