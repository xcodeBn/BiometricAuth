package com.pisces.piauth


/**
 * Enum class representing the various states of biometric authentication availability.
 *
 * This enum class provides identifiers for different states of biometric authentication,
 * which can be used to determine the availability and readiness of biometric authentication
 * on the device.
 *
 * @property id The unique identifier associated with each biometric authentication status.
 * @constructor Creates a new instance of [BioMetricAuthStatus] with the specified identifier.
 */
enum class BioMetricAuthStatus(val id:Int) {

    /**
     * Biometric authentication is ready to be used.
     */
    READY(1),

    /**
     * Biometric authentication is not available on this device.
     */
    NOT_AVAILABLE(-1),

    /**
     * Biometric authentication is temporarily not available on this device.
     */
    TEMPEROARY_NOT_AVAILABLE(-2),

    /**
     * Biometric authentication is available but not enrolled on this device.
     */
    AVAILABLE_BUT_NOT_ENROLLED(-3);
}
