package com.example.milestokilometers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.milestokilometers.ui.theme.MilesToKilometersTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MilesToKilometersTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MilesToKilometersScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MilesToKilometersScreen(modifier: Modifier = Modifier) {

    var milesInput by remember { mutableStateOf("") }

    val kilometers = milesInput.toDoubleOrNull()?.let {
        if (it > 0) it * 1.609 else 0.0
    } ?: 0.0

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = stringResource(R.string.app_title),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = milesInput,
            onValueChange = { milesInput = it },
            label = { Text(stringResource(R.string.miles_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = String.format(
                Locale.getDefault(),
                "%.2f %s",
                kilometers,
                stringResource(id = R.string.km_unit)
            ),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
