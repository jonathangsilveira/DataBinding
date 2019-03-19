package br.edu.jonathangsilveira.kotlindatabinding

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import br.edu.jonathangsilveira.kotlindatabinding.data.FakeRepository

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("br.edu.jonathangsilveira.kotlindatabinding", appContext.packageName)
    }

    @Test
    fun dataLoad_success() {
        FakeRepository.apply {
            load(InstrumentationRegistry.getTargetContext().resources)
        }.also {
            it.filter(0.0, 1).forEach { transaction ->
                println(transaction)
            }
        }
    }

}
