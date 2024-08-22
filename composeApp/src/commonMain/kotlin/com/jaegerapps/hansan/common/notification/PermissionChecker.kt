package com.jaegerapps.hansan.common.notification

expect class PermissionChecker {
    fun checkPostPermission(): Boolean
    fun requestPermission(): Boolean
}