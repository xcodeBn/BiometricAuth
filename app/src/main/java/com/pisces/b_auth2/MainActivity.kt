package com.pisces.b_auth2

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.pisces.b_auth2.ui.theme.B_Auth2Theme



class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val biometricAuthenticator = BioMetricAuth(this)

        setContent {
            B_Auth2Theme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally
                    ) {
                        val activity = LocalContext.current as FragmentActivity
                        var message by remember {
                            mutableStateOf("")
                        }

                       TextButton(onClick = { biometricAuthenticator.promptBiometricAuth(
                           title= "Login",
                           subtitle = "Use your finger print or faceid",
                           negativeButtonText = "Cancel",
                           fragmentActivity = activity,
                           onSuccess = {
                               message = "Success + ${it.authenticationType}"
                           },
                           onFailed = {
                               message = "Failed"
                           },
                           onError = {
                                   id,error ->
                               if(id== BioMetricAuthStatus.NOT_AVAILABLE.id){

                               }

                               message = "Error: + $error"
                           }

                       ) }) {
                           Text(text = "Login with fingerprint or face id")
                       }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = message)

                    }
                }
            }
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
    B_Auth2Theme {
        Greeting("Android")
    }
}