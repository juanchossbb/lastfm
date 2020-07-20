package com.jhurtado.lastfm

import android.os.Build
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
open class BaseUnitTest {
    @Before
    open fun setUp() {
    }

}