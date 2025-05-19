package com.sonukg97.jetpackcomposecomponents.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Vertical
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sonukg97.fruitbasketapp.R
import androidx.compose.ui.graphics.Color.Companion.White as ColorWhite


@Composable
fun LoginUI(){
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue(text = "")) }
    var confirmPass by remember { mutableStateOf(TextFieldValue(text = "")) }
    Column ( verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)){
    val painter = painterResource(id = R.drawable.ic_launcher_foreground)
        val painterVisibility = painterResource(id = R.drawable.baseline_visibility_24)
        val painterVisibilityOff = painterResource(id = R.drawable.baseline_visibility_24)
        Image(painter = painter, contentDescription = "Logo")
        Text(text = "Login",)
        Spacer(modifier = Modifier.padding(top = 80.dp))
       // Text(text = "Login", modifier = Modifier.padding(10.dp), fontSize = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Center, color = Color.LightGray)
        OutlinedTextField(
            value = email,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Email address") },
            placeholder = { Text(text = "Enter email") },
            onValueChange = {
                email = it
            }
        )
        OutlinedTextField(
            value = pass,
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Enter password") },
            onValueChange = {
                pass = it
            }
        )

        OutlinedButton(onClick = {  }, modifier = Modifier.padding(top = 40.dp, bottom = 20.dp),
            true,
            colors = ButtonDefaults.outlinedButtonColors()
        ) {
            Text(text = "SignIn", modifier = Modifier.padding(10.dp))
        }

    }
}
//
@Composable
@Preview
fun LoginUIPreview(){
    LoginUI()
}
