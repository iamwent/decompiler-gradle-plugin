package io.github.iamwent.decompiler.sample.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.iamwent.decompiler.sample.MainViewModel
import io.github.iamwent.decompiler.sample.ui.theme.SampleTheme


@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
) {
    val name by viewModel.nameState.collectAsState()
    Greeting(name, modifier)
}

@Composable
private fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SampleTheme {
        Greeting("Android")
    }
}
