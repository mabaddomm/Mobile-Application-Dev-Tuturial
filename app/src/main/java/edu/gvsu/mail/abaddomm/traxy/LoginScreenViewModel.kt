package edu.gvsu.mail.abaddomm.traxy


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LogicScreenViewModel: ViewModel() {
    val EMAIL_REGEX = "^[A-ZA-z](.*)()[@]{1}(.1{1,})(\\.)(.{1,})"
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
    }
}
