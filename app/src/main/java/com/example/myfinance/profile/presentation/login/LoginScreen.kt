package com.example.myfinance.profile.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.R


@Composable
fun Login(
    state: LoginState,
    onLogin: (String, String) -> Unit,
    navHostController: NavHostController
) {
    if( state.isSignedIn )
        navHostController.navigate("home")

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image (
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Somethign",
            modifier = Modifier
                .aspectRatio(1 / 1f)
        )

        OutlinedTextField (
            value = state.email,
            onValueChange = { state.email = it },
            label = { Text(text = "Username") }
        )

        OutlinedTextField (
            value = state.password,
            onValueChange = { state.password = it },
            label = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                onLogin(state.email, state.password)
                navHostController.navigate("home")
            }
        ) {
            if( state.isLoading )
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            else
                Text("Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Login(LoginState("SomeUsername", "SomePassword", isLoading = true),
        {r,e -> Unit},
        rememberNavController()
    )
}