package com.example.outlinedtextfieldexemplo

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.outlinedtextfieldexemplo.composables.CustomOutlinedTextField
import com.example.outlinedtextfieldexemplo.ui.theme.OutlinedTextFieldExemploTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OutlinedTextFieldExemploTheme {
                ShowForm()
            }
        }
    }
}

@Composable
fun ShowForm() {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var name by rememberSaveable { mutableStateOf("") }
    var surname by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var validateName by rememberSaveable { mutableStateOf(true) }
    var validateSurname by rememberSaveable { mutableStateOf(true) }
    var validateEmail by rememberSaveable { mutableStateOf(true) }
    var validatePhone by rememberSaveable { mutableStateOf(true) }
    var validatePassword by rememberSaveable { mutableStateOf(true) }
    var validateConfirmPassword by rememberSaveable { mutableStateOf(true) }
    var validatearePasswordsEqual by rememberSaveable { mutableStateOf(true) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    var isConfirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val validateNameError = "Please, input a valid name"
    val validateSurnameError = "Please, input a valid surname"
    val validateEmailError = "The format of the email doesn't seen right"
    val validatePhoneError = "The format of the phone number doesn't seen right"
    val validatePasswordError =
        "Must mix capital and non-capital letters, a number, special character and a minimum length of 8"
    val validateEqualPasswordError = "Passwords must be equal"

    fun validateData(
        name: String,
        surname: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        val passwordRegex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%ˆ&+=]).{8,}".toRegex()

        validateName = name.isNotBlank()
        validateSurname = surname.isNotBlank()
        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        validatePhone = Patterns.PHONE.matcher(phone).matches()
        validatePassword = passwordRegex.matches(password)
        validateConfirmPassword = passwordRegex.matches(confirmPassword)
        validatearePasswordsEqual = password == confirmPassword

        return validateName &&
                validateSurname &&
                validateEmail &&
                validatePhone &&
                validatePassword &&
                validateConfirmPassword &&
                validatearePasswordsEqual
    }

    fun register(
        name: String,
        surname: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String,

        ) {
        if (validateData(name, surname, email, phone, password, confirmPassword)) {
            Log.d(
                MainActivity::class.java.simpleName,
                "Name: $name, " +
                        "Surname: $surname, " +
                        "Email: $email, " +
                        "Phone: $phone, " +
                        "Password $password, " +
                        "Confirm Password $confirmPassword"
            )
        } else {
            Toast.makeText(context, "Please, review fields", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Register to our app",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(vertical = 20.dp),
            color = Color.Blue
        )

        CustomOutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = "Name",
            showError = !validateName,
            errorMessage = validateNameError,
            leadingIconImageVector = Icons.Default.PermIdentity,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        CustomOutlinedTextField(
            value = surname,
            onValueChange = { surname = it },
            label = "Surname",
            showError = !validateSurname,
            errorMessage = validateSurnameError,
            leadingIconImageVector = Icons.Default.PermIdentity,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        CustomOutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email Address",
            showError = !validateEmail,
            errorMessage = validateEmailError,
            leadingIconImageVector = Icons.Default.AlternateEmail,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        CustomOutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = "PhoneNumber",
            showError = !validatePhone,
            errorMessage = validatePhoneError,
            leadingIconImageVector = Icons.Default.Phone,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        CustomOutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            showError = !validatePassword,
            errorMessage = validatePasswordError,
            isPasswordField = true,
            isPasswordVisible = isPasswordVisible,
            onVisibilityChange = { isPasswordVisible = it },
            leadingIconImageVector = Icons.Default.Password,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        CustomOutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirm Password",
            showError = !validateConfirmPassword || !validatearePasswordsEqual,
            errorMessage = if (!validateConfirmPassword) validatePasswordError else validateEqualPasswordError,
            isPasswordField = true,
            isPasswordVisible = isConfirmPasswordVisible,
            onVisibilityChange = { isConfirmPasswordVisible = it },
            leadingIconImageVector = Icons.Default.Password,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
        )
        Button(
            onClick = {
                register(name, surname, email, phone, password, confirmPassword)
            },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(0.9f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text(text = "Register", fontSize = 20.sp)
        }


    }
}