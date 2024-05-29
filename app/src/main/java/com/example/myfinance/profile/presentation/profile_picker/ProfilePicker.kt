package com.example.myfinance.profile.presentation.profile_picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.core.Constants
import com.example.myfinance.profile.domain.Profile

@Composable
fun ProfilePicker(
    state: ProfilePickerState,
    selectProfile: (Profile) -> Unit,
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Choose your profile",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(10.dp))

        if( state.isLoading ) {
            CircularProgressIndicator(
                modifier = Modifier.size(70.dp)
            )

            return
        }

        state.profiles.forEachIndexed{ index, profile ->
            OutlinedButton(
                modifier = Modifier.width(200.dp),
                onClick = { selectProfile(profile).also {
                    navHostController.navigate("home")
                } }
            ) {
                Text(text = profile.name)
            }
        }

        OutlinedButton(
            modifier = Modifier
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(),
            onClick = { navHostController.navigate("create_profile") }
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add profile"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePickerPreview() {
    val profile = Profile(
        id = 32L,
        name = "Number",
        email = "email@gmail.com"
    )
    ProfilePicker(
        ProfilePickerState(
            profiles = listOf(profile),
            isLoading = true
        ),
        {},
        rememberNavController()
    )
}