package com.sonukg97.jetpackcomposecomponents.components

import android.text.style.BackgroundColorSpan
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sonukg97.fruitbasketapp.R


@Composable
fun SignUp(){
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue(text = "")) }
    var confirmPass by remember { mutableStateOf(TextFieldValue(text = "")) }
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)) {
        val painter = painterResource(id = R.drawable.ic_launcher_foreground)
        Image(painter = painter, contentDescription = "Logo")
        Text(text = "Sign Up")
        Spacer(modifier = Modifier.padding(40.dp))
        OutlinedTextField(
            value = name,
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Username") },
            placeholder = { Text(text = "Enter username") },
            onValueChange = {
                name = it
            }
        )
        OutlinedTextField(
            value = email,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            modifier = Modifier
                .padding(8.dp)
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
            trailingIcon = { Icon(imageVector = Icons.Default.Edit, contentDescription = null) },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Enter password") },
            onValueChange = {
                pass = it
            }
        )

        OutlinedTextField(
            value = confirmPass,
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
            trailingIcon = { Icon(imageVector = Icons.Default.Edit, contentDescription = null) },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(text = "Confirm Password") },
            placeholder = { Text(text = "Enter confirm password") },
            onValueChange = {
                confirmPass = it
            }
        )

        OutlinedButton(onClick = {  }, modifier = Modifier.padding(top = 40.dp, bottom = 20.dp),
            true,
            colors = ButtonDefaults.outlinedButtonColors()
        ) {
            Text(text = "SignUp", modifier = Modifier.padding(10.dp))
        }

    }
}
//
@Composable
@Preview
fun SignUpPreview(){
    SignUp()
}