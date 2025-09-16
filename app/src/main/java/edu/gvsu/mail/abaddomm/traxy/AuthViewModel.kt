package edu.gvsu.mail.abaddomm.traxy

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.update

class AuthViewModel: ViewModel() {
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

    private val _userState = MutableStateFlow(UserState())
    val userState = _userState.asStateFlow()



    private  val _isAuthenticated = MutableStateFlow<Boolean?>(null)
    private val _userEmail = MutableStateFlow("")

    val isAuthenticated = _isAuthenticated.asStateFlow()
    val userEmail = _userEmail.asStateFlow()

    fun authenticate(email: String, password: String) {
        _isAuthenticated.value = null
        if (email.isBlank() || password.isBlank() || !email.matches(EMAIL_REGEX.toRegex())) {
            _isAuthenticated.value = false
            return
        }
        _isAuthenticated.value = password.lowercase().contains("traxy")
    }
    fun setUserEmail(e: String) {
        _isAuthenticated.value = null
        _userEmail.value = e
        _userState.update { it.copy(authenticated = null, email = "ma@test.org") }
    }
}

data class UserState (
    val email: String = "",
    val isAuthenticated: Boolean? = null

)