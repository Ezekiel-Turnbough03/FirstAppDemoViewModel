package com.example.firstappdemo


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LoginScreen(modifier: Modifier = Modifier.Companion, viewModel: AuthViewModel) {

     val isAuthenticated by viewModel.isAuthenticated.collectAsState()
     val email by viewModel.userEmail.collectAsState("")
     var password by remember { mutableStateOf("") }
     Column(
             modifier.fillMaxWidth(),
         horizontalAlignment = Alignment.Companion.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
     ) {
         Text(
                 "Enter Your Login Information",
         modifier = Modifier.Companion.padding(top = 16.dp),
         fontSize = 24.sp, fontWeight = FontWeight.Companion.Bold
         )
         Image(
                 modifier = modifier.fillMaxWidth(0.5f),
         painter = painterResource(R.drawable.ic_launcher_foreground),
         contentScale = ContentScale.Companion.FillWidth,
         colorFilter = if (isAuthenticated ?: false) ColorFilter.tint(Color.Green) else ColorFilter.tint(Color.Red),
         contentDescription = null
         )
         OutlinedTextField(
                 value = email,
         onValueChange = { viewModel.setUserEmail(it) },
         placeholder = {
             Text("Enter Your Email:")
             })
         OutlinedTextField(
                 value = password,
         onValueChange = { password = it },
         placeholder = {
             Text("Enter password: ")
             },
         visualTransformation = PasswordVisualTransformation()
         )
         ElevatedButton(onClick = {
            viewModel.authenticate(email, password)
             }, modifier = Modifier) {
         Text("Sign In")
         }
         when(isAuthenticated)
             {
                 true -> Text("Welcome")
                 false -> Text("Invalid Login")
                 null -> {}
             }
         }
     }