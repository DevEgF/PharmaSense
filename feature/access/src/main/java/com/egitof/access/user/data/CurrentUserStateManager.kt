package com.egitof.access.user.data

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentUserStateManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    var isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = sharedPreferences.edit { putBoolean(KEY_IS_LOGGED_IN, value) }

    fun clearAuthState() {
        sharedPreferences.edit {
            remove(KEY_IS_LOGGED_IN)
            apply()
        }
    }

    companion object {
        const val PREF_AUTH_STATE = "auth_state_prefs"
        const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
}