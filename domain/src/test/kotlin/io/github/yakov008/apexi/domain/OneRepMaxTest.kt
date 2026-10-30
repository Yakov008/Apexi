package io.github.yakov008.apexi.domain

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class OneRepMaxTest {

    private val tolerance = 0.001

    @Test
    fun `single rep to failure is the one-rep max itself`() {
        assertEquals(100.0, OneRepMax.estimate(weightKg = 100.0, reps = 1, rir = 0), tolerance)
    }

    @Test
    fun `ten reps to failure follow the Epley formula`() {
        // 100 × (1 + 10 / 30) = 133.33
        assertEquals(133.333, OneRepMax.estimate(weightKg = 100.0, reps = 10, rir = 0), tolerance)
    }

    @Test
    fun `reps in reserve count as reps the lifter could still do`() {
        val eightRepsTwoInReserve = OneRepMax.estimate(weightKg = 80.0, reps = 8, rir = 2)
        val tenRepsToFailure = OneRepMax.estimate(weightKg = 80.0, reps = 10, rir = 0)
        assertEquals(tenRepsToFailure, eightRepsTwoInReserve, tolerance)
    }

    @Test
    fun `invalid input is rejected`() {
        assertFailsWith<IllegalArgumentException> { OneRepMax.estimate(weightKg = 0.0, reps = 5) }
        assertFailsWith<IllegalArgumentException> { OneRepMax.estimate(weightKg = 50.0, reps = 0) }
        assertFailsWith<IllegalArgumentException> { OneRepMax.estimate(weightKg = 50.0, reps = 5, rir = -1) }
    }
}
