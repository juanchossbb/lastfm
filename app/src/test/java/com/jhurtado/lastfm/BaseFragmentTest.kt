package com.jhurtado.lastfm

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import org.junit.Before
import org.robolectric.Robolectric

abstract class BaseFragmentTest<T> : BaseUnitTest() {
    private lateinit var activity: TestActivity
    var fragment: T? = null

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(TestActivity::class.java).setup().get()
        val tmpFrag = setFragment()
        fragment = tmpFrag as T
        if (!tmpFrag.isAdded)
            startFragment(fragment)
    }

    fun startFragment(fragment: T?) {
        val fragmentManager: FragmentManager = activity.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(fragment as Fragment, null)
        fragmentTransaction.commit()
    }

    abstract fun setFragment(): Fragment

}