package com.chobitech.lib.android

import android.util.Log


object DebugLog {

    var isShow = true
    var tag: String = "DEBUG"

    private val clsName = DebugLog::class.java.name
    private val iName = ::i.name
    private val wName = ::w.name
    private val eName = ::e.name

    private const val COMPOSABLE_SINGLETON_PREFIX = "ComposableSingletons$"

    private fun getMethodName(st: StackTraceElement?): String =
        st?.let {

            val cName = st.className.substringAfterLast(".")
                .let { s ->
                    when (s.startsWith(COMPOSABLE_SINGLETON_PREFIX)) {
                        true -> s.substringAfter(COMPOSABLE_SINGLETON_PREFIX)
                        false -> s
                    }.substringBefore("$")
                }
                .removeSuffix("Kt")


            val methodName = st.methodName
                .substringBefore("$")

            " (${cName}.${methodName}(), ${st.fileName}:${st.lineNumber})"
        } ?: ""

    @JvmOverloads
    fun i(o: Any?, tempTag: String? = null) {
        if (!isShow) {
            return
        }

        val st = getCallStackTrace(iName)
        Log.i(tempTag ?: tag, "$o${getMethodName(st)}")
    }

    @JvmOverloads
    fun w(o: Any?, tempTag: String? = null) {
        if (!isShow) {
            return
        }

        val st = getCallStackTrace(wName)
        Log.w(tempTag ?: tag, "$o${getMethodName(st)}")
    }

    @JvmOverloads
    fun e(o: Any?, tempTag: String? = null) {
        if (!isShow) {
            return
        }

        val st = getCallStackTrace(eName)
        Log.e(tempTag ?: tag, "$o${getMethodName(st)}")
    }

    @JvmOverloads
    fun e(e: Throwable, tempTag: String? = null) {
        if (!isShow) {
            return
        }

        val st = getCallStackTrace(eName)
        Log.e(tempTag ?: tag, "${e.localizedMessage}${getMethodName(st)}", e)
    }


    private fun getCallStackTrace(mName: String): StackTraceElement? {
        val stackTrace = Thread.currentThread().stackTrace

        val selfIndex = stackTrace.indexOfLast { st ->
            st.className == clsName && st.methodName.substringBefore("$") == mName
        }

        return when (selfIndex >= 0) {
            true -> stackTrace.getOrNull(selfIndex + 1)
            false -> null
        }
    }
}
