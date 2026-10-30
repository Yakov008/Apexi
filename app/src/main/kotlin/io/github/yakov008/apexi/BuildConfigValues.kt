package io.github.yakov008.apexi

import android.content.Context

/** Reads app metadata from the installed package, so no BuildConfig generation is needed. */
object BuildConfigValues {
    fun versionName(context: Context): String =
        context.packageManager.getPackageInfo(context.packageName, 0).versionName ?: "?"
}
