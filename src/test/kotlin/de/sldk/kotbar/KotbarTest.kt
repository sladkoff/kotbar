package de.sldk.kotbar

import org.junit.Before
import org.junit.Test

/**
 * This doesn't test any functionality but can be used to see some progress bars in action
 */
class KotbarTest {

    @Before
    fun setUp() {

    }

    @Test
    fun `test draw`() {

        val kotbar = Kotbar(10)

        for (i in 1..10) {
            kotbar.inc()
            Thread.sleep(500)
        }

        kotbar.done()
    }

    @Test
    fun `test draw in block`() {

        val intRange = 1..10

        intRange.forEachWithProgressBar {
            Thread.sleep(500)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test draw with failure`() {

        val intRange = 1..10

        intRange.forEachWithProgressBar {
            Thread.sleep(500)
            if (it == 4) {
                throw IllegalArgumentException("4 is bad")
            }
        }
    }

}