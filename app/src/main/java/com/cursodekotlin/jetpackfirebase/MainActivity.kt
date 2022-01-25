package com.cursodekotlin.jetpackfirebase

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.cursodekotlin.jetpackfirebase.ui.theme.JetpackfirebaseTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackfirebaseTheme {
                Scaffold (
                    content = {
                        FormRegistro(context = this)
                    }
                )      // A surface container using the 'background' color from the theme
            }
        }
    }
}
@Composable
fun FormRegistro(context: ComponentActivity) {
    val auth = Firebase.auth
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        val emailValue = remember {
            mutableStateOf(TextFieldValue())
        }

        val passowordValue = remember {
            mutableStateOf(TextFieldValue())
        }

        OutlinedTextField(
            label = {
              Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Email ),
            value = emailValue.value,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                emailValue.value = it
            })
        OutlinedTextField(
            label = {
                Text(text = "Password")
            },
            keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Password ),
            value = passowordValue.value,
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                passowordValue.value = it
            })
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                auth.createUserWithEmailAndPassword(
                    emailValue.value.text.trim(),
                    passowordValue.value.text.trim()
                )
                    .addOnCompleteListener(context) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("AUTH", "signInWithEmail:success")

                            //val user = auth.currentUser
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Auth", "Failed ${task.exception}")
                        }
                    }
            }) {
            Text(text = "Registrar")
        }
    }
}