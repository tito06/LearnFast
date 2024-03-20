package com.example.learnfast

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learnfast.ui.theme.LearnFastTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    var outputTxt by mutableStateOf("Click button for Speech text ")


    private lateinit var voiceLauncer: ActivityResultLauncher<Intent>

    private  fun registerForIntentLauncher(){
        voiceLauncer =  registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){result :ActivityResult ->

            when(result.resultCode){
                Activity.RESULT_OK -> {
                    var res = result.data?.getStringArrayExtra(RecognizerIntent.EXTRA_RESULTS)

                    outputTxt = res?.get(0).toString()
                }

            }

        }
    }


    private fun getSpeech(){



            voiceLauncer.launch(intent)




    }

    override fun onCreate(savedInstanceState: Bundle?) {


        registerForIntentLauncher()

        super.onCreate(savedInstanceState)
        setContent {
            LearnFastTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                   // Start()

                    PlayScreen({ getSpeech() })
                }
            }
        }
    }
}

@Composable
fun Start(){
    val navController  = rememberNavController()


NavHost(navController = navController, startDestination = Screen.Greeting.route){
    composable(route = Screen.Greeting.route){
        Greeting(name = "android", navController = navController)
    }

    composable(route = Screen.PlayScreen.route){

       // PlayScreen()
    }
}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, navController: NavController) {

    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Button(onClick = {
                         navController.navigate(Screen.PlayScreen.route)
        },
            modifier
                .width(250.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )) {
          
            Text(text = "Alphabets  ->")
        }
        
        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { /*TODO*/ },
            modifier
                .width(250.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )) {

            Text(text = "Numbers  ->")
        }
    }




}






