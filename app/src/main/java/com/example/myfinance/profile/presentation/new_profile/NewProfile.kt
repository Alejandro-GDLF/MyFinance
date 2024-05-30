package com.example.myfinance.profile.presentation.new_profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun NewProfile(
    navController: NavHostController,
    state: NewProfileState,
    onCreate: (NewProfileState) -> Unit,
    updateName: (String) -> Unit,
    updateEmail: (String) -> Unit
) {
    if( state.isCreated ) {
        navController.navigate("main") {
            popUpTo("create_profile") {inclusive = true}
        }
        return
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create new profile",
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            value = state.name,
            onValueChange = updateName,
            label = { Text(text = "Name") },
            enabled = !state.isLoading
        )

        OutlinedTextField(
            value = state.email,
            onValueChange = updateEmail,
            label = { Text(text = "Email") },
            enabled = !state.isLoading
        )
        
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { onCreate(state) }
        ) {
            if( state.isLoading )
                CircularProgressIndicator(
                    modifier = Modifier.height(40.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            else
                Text(text = "Create")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewProfilePreview() {
    NewProfile(
        rememberNavController(),
        NewProfileState(
            name = "Somename",
            email = "ajsd@gmail.com"
        ),
        { },
        {},
        {}
    )
}