package xit.zubrein.demo.Utils

import android.content.Context
import android.content.Intent
import android.os.Bundle

class Navigator {

    companion object {
        val sharedInstance = Navigator()
    }

    fun <T> navigate(context: Context, target: Class<T>) {
        val intent = Intent(context, target)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        context.startActivity(intent)
    }

    fun <T> back(context: Context, target: Class<T>) {
        val intent = Intent(context, target)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        context.startActivity(intent)
    }

    fun <T> navigateWithBundle(
        context: Context,
        target: Class<T>,
        bundleKey: String,
        bundle: Bundle
    ) {
        val intent = Intent(context, target)
        intent.putExtra(bundleKey, bundle)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        context.startActivity(intent)
    }

    fun <T> backWithBundle(
        context: Context,
        target: Class<T>,
        bundleKey: String,
        bundle: Bundle
    ) {
        val intent = Intent(context, target)
        intent.putExtra(bundleKey, bundle)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        context.startActivity(intent)
    }
}