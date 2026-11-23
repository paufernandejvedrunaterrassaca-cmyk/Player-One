package com.victor.player1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class LoginViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun validateEmail(): Boolean {
        val emailValue = _email.value ?: ""

        if (emailValue.isEmpty()) {
            _emailError.value = "El email es obligatorio"
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS
                .matcher(emailValue).matches()) {
            _emailError.value = "El email no es válido"
            return false
        }

        _emailError.value = null
        return true
    }

    fun validatePassword(): Boolean {
        val passwordValue = _password.value ?: ""

        if (passwordValue.isEmpty()) {
            _passwordError.value = "La contraseña es obligatoria"
            return false
        }

        if (passwordValue.length < 6) {
            _passwordError.value =
                "La contraseña debe tener al menos 6 caracteres"
            return false
        }

        _passwordError.value = null
        return true
    }

    fun validateLogin(): Boolean {
        val emailValid = validateEmail()
        val passwordValid = validatePassword()

        return emailValid && passwordValid
    }
}