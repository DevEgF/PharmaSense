package com.egitof.firebase.data.datasource

import com.egitof.auth.data.datasource.FirebaseAuthDataSource
import com.egitof.auth.domain.model.AuthError
import com.egitof.utils.data.ResultStatus
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FirebaseAuthDataSourceTest {

    private val firebaseAuth = mockk<FirebaseAuth>(relaxed = true)
    private lateinit var dataSource: FirebaseAuthDataSource
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        dataSource = FirebaseAuthDataSource(firebaseAuth)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun  `given valid credentials when login then should return Success`() = runTest {
        val mockTask = mockk<Task<AuthResult>>(relaxed = true) {
            coEvery { isSuccessful } returns true
            coEvery { isComplete } returns true
            coEvery { exception } returns null
            coEvery { result } returns mockk(relaxed = true)
        }

        coEvery {
            firebaseAuth.signInWithEmailAndPassword("test@example.com", "senha123")
        } returns mockTask

        val result = dataSource.login("test@example.com", "senha123")
        assertTrue(result is ResultStatus.Success)
    }

    @Test
    fun `given valid registration data when register then should return Success`() = runTest {
        val mockTask = mockk<Task<AuthResult>>(relaxed = true) {
            coEvery { isSuccessful } returns true
            coEvery { isComplete } returns true
            coEvery { exception } returns null
            coEvery { result } returns mockk(relaxed = true)
        }

        coEvery {
            firebaseAuth.createUserWithEmailAndPassword("new@example.com", "senha123")
        } returns mockTask

        val result = dataSource.register("new@example.com", "senha123")

        assertTrue(result is ResultStatus.Success)
    }

    @Test
    fun `given valid email when sendPasswordResetEmail then should return Success`() = runTest {
        val mockTask = mockk<Task<Void>>(relaxed = true) {
            coEvery { isSuccessful } returns true
            coEvery { isComplete } returns true
            coEvery { exception } returns null
        }

        coEvery {
            firebaseAuth.sendPasswordResetEmail("new@example.com")
        } returns mockTask

        val result = dataSource.sendPasswordResetEmail("new@example.com")

        assertTrue(result is ResultStatus.Success)
    }

    @Test
    fun `given valid user when getCurrentUser then should return User`() = runTest {
        val mockUser = mockk<com.google.firebase.auth.FirebaseUser>(relaxed = true) {
            coEvery { uid } returns "12345"
            coEvery { email } returns "new@example.com"
            coEvery { isEmailVerified } returns true
        }

        coEvery { firebaseAuth.currentUser } returns mockUser

        val result = dataSource.getCurrentUser()
        assertTrue(result != null)
        assertTrue(result?.id == "12345")
        assertTrue(result?.email == "new@example.com")
        assertTrue(result?.isEmailVerified == true)
    }

    @Test
    fun `given invalid credentials when login then should return Error`() = runTest {
        val mockException = mockk<FirebaseAuthInvalidCredentialsException>(relaxed = true) {
            every { message } returns "Invalid credentials"
        }

        val mockTask = mockk<Task<AuthResult>>(relaxed = true) {
            coEvery { isSuccessful } returns false
            coEvery { isComplete } returns true
            coEvery { exception } returns mockException
        }

        coEvery {
            firebaseAuth.signInWithEmailAndPassword("new@example.com", "senha123")
        } returns mockTask

        val result = dataSource.login("new@example.com", "senha123")

        assertTrue(result is ResultStatus.Error)
        assertTrue((result as ResultStatus.Error).error is AuthError.InvalidCredentials)
    }

    @Test
    fun `given invalid credentials when register then should return Error`() = runTest {
        val mockException = mockk<FirebaseAuthInvalidCredentialsException>(relaxed = true) {
            every { message } returns "Invalid credentials"
        }

        val mockTask = mockk<Task<AuthResult>>(relaxed = true) {
            coEvery { isSuccessful } returns false
            coEvery { isComplete } returns true
            coEvery { exception } returns mockException
        }

        coEvery {
            firebaseAuth.createUserWithEmailAndPassword("newexample.com", "senha123")
        } returns mockTask

        val result = dataSource.register("newexample.com", "senha123")
        assertTrue(result is ResultStatus.Error)
        assertTrue((result as ResultStatus.Error).error is AuthError.InvalidCredentials)
    }
}
