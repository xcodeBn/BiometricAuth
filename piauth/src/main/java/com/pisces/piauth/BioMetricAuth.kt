package com.pisces.piauth

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity

/**
 * A helper class for handling biometric authentication in Android applications.
 *
 * This class provides methods to check the availability of biometric authentication on the device
 * and to prompt the user for biometric authentication using the BiometricPrompt API.
 *
 * @param context The context of the application or activity.
 */
class BioMetricAuth(
    private val context:Context
) {
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private val biometricManager =  BiometricManager.from(context)
    private lateinit var biometricPrompt: BiometricPrompt

    /**
     * Checks the availability of biometric authentication on the device.
     *
     * @return A [BioMetricAuthStatus] indicating the availability of biometric authentication.
     */
    fun isBiometricAuthAvailable(): BioMetricAuthStatus {
        return when(biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)){

            BiometricManager.BIOMETRIC_SUCCESS-> BioMetricAuthStatus.READY
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE-> BioMetricAuthStatus.NOT_AVAILABLE
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE-> BioMetricAuthStatus.TEMPEROARY_NOT_AVAILABLE
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED-> BioMetricAuthStatus.AVAILABLE_BUT_NOT_ENROLLED
            else -> BioMetricAuthStatus.NOT_AVAILABLE

        }
    }

    /**
     * Prompts the user for biometric authentication.
     *
     * @param title The title to be displayed in the biometric prompt dialog.
     * @param subtitle The subtitle to be displayed in the biometric prompt dialog.
     * @param negativeButtonText The text for the negative button in the biometric prompt dialog.
     * @param fragmentActivity The fragment activity used to display the biometric prompt dialog.
     * @param onSuccess A lambda function to be called when biometric authentication succeeds.
     * @param onFailed A lambda function to be called when biometric authentication fails.
     * @param onError A lambda function to be called when an error occurs during biometric authentication.
     */
    fun promptBiometricAuth(
        title:String,
        subtitle:String,
        negativeButtonText:String,
        fragmentActivity: FragmentActivity,
        onSuccess:(result:BiometricPrompt.AuthenticationResult)->Unit,
        onFailed:()->Unit,
        onError: (errorCode: Int,errorString:String)->Unit
    ){
        when(isBiometricAuthAvailable()){
            BioMetricAuthStatus.READY -> {

            }
            BioMetricAuthStatus.NOT_AVAILABLE -> {
                onError(BioMetricAuthStatus.NOT_AVAILABLE.id,"Not available for this device")
                return

            }
            BioMetricAuthStatus.TEMPEROARY_NOT_AVAILABLE -> {
                onError(BioMetricAuthStatus.TEMPEROARY_NOT_AVAILABLE.id,"Not available at this moment")
                return

            }
            BioMetricAuthStatus.AVAILABLE_BUT_NOT_ENROLLED -> {
                onError(BioMetricAuthStatus.AVAILABLE_BUT_NOT_ENROLLED.id,"Available but not enrolled, you should add fingerprint or faceid to your device security first")
                return
            }

            else ->Unit
        }

        biometricPrompt = BiometricPrompt(
            fragmentActivity,
            object : BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    onSuccess(result)
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    onError(errorCode,errString.toString())
                }


                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    onFailed()
                }
            }
        )

        promptInfo = BiometricPrompt.PromptInfo.Builder().setTitle(title).setSubtitle(subtitle).setNegativeButtonText(negativeButtonText).build()
        biometricPrompt.authenticate(promptInfo)
    }
}
