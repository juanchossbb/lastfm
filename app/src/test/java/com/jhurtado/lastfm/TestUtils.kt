package com.jhurtado.lastfm

import androidx.paging.PagedList
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

object TestUtils {

    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
        Mockito.`when`(pagedList[ArgumentMatchers.anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        Mockito.`when`(pagedList.size).thenReturn(list.size)
        return pagedList
    }

    inline fun <reified T> mockList(size: Int): List<T> {
        val list = mutableListOf<T>()
        for (i in 0 until size) {
            list.add(Mockito.mock(T::class.java))
        }
        return list
    }
}