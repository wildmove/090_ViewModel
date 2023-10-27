package com.example.viewmodellearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodellearn.Data.DataForm
import com.example.viewmodellearn.Data.DataSource.jenis
import com.example.viewmodellearn.Data.DataSource.status
import com.example.viewmodellearn.ui.theme.ViewModelLearnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewModelLearnTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TampilForm()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TampilForm(cobaViewModel: CobaViewModel = viewModel()) {
    var textNama by remember { mutableStateOf("") }
    var textTlp by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current
    val dataForm: DataForm
    val uiState by cobaViewModel.uiState.collectAsState()
    dataForm = uiState;

    /*Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .height(10.dp)

    ) {
        Text(text = "Register")
    }*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)

    ) {
        Text(text = "Create Your Account")

        OutlinedTextField(
            value = textNama,
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Nama Lengkap") },
            onValueChange = {
                textNama = it
            }
        )

        OutlinedTextField(
            value = textTlp,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Nomor Telepon") },
            onValueChange = {
                textTlp = it
            }
        )

        OutlinedTextField(
            value = email,
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Email") },
            onValueChange = {
                email = it
            }
        )

        Text(text = "Jenis Kelamin:")
        Row {
            SelectJK(
                options = jenis.map { id -> context.resources.getString(id) },
                onSelectionChanged = { cobaViewModel.setJenisK(it) }
            )
        }


        Text(text = "Status:")
        SelectStatus(
            options = status.map { id -> context.resources.getString(id) },
            onSelectionChanged = { cobaViewModel.setStatus(it) }
        )

        OutlinedTextField(
            value = alamat,
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Alamat") },
            onValueChange = {
                alamat = it
            }
        )



        Button(modifier = Modifier.fillMaxWidth(),
            onClick = { cobaViewModel.insertData(textNama, textTlp, email,  dataForm.sex, dataForm.statusPernikahan, alamat) })
        {
            Text(text = "Submit")
        }
        TeksHasil( jenisK = cobaViewModel.jenisKl, status= cobaViewModel.statusNikah, alamat= cobaViewModel.alamat, email = cobaViewModel.email,)
    }
}

@Composable
fun SelectJK(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Row(modifier = Modifier.padding(10.dp)) {
        options.forEach { item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = selectedValue == item, onClick = {
                    selectedValue = item
                    onSelectionChanged(item)
                })
                Text(item)
            }
        }
    }
}

@Composable
fun SelectStatus(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Row(modifier = Modifier.padding(5.dp)) {
        options.forEach { item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = selectedValue == item, onClick = {
                    selectedValue = item
                    onSelectionChanged(item)
                })
                Text(item)
            }
        }
    }
}

@Composable
fun TeksHasil(jenisK: String, status: String, alamat: String, email: String) {
    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom){
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .fillMaxWidth()


        ) {/*
            Text(
                text = "Nama Lengkap : " + nama,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            )
            Text(
                text = "Nomor Telepon : " + telpon,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            )*/
            Text(
                text = "Jenis Kelamin : " + jenisK,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            )
            Text(
                text = "Status : " + status,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            )
            Text(
                text = "Alamat : " + alamat,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            )
            Text(
                text = "Email : " + email,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ViewModelLearnTheme {
        TampilForm()

    }
}