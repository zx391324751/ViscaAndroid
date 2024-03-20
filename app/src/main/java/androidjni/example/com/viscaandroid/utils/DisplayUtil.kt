package androidjni.example.com.viscaandroid.utils

import android.content.Context

/**
 * 2024/3/19
 * created by zhangxu
 */
fun Float.toPx(context: Context): Float {
    val density = context.resources.displayMetrics.density
    return this * density + 0.5f
}