package io.github.yakov008.apexi.domain

/**
 * Estimated one-rep max (1RM) from a submaximal set.
 *
 * Uses the Epley formula, counting reps left in reserve (RIR) as reps the lifter could still do:
 *
 *     1RM ≈ weight × (1 + (reps + rir) / 30)
 *
 * Used only for progress charts and warm-up suggestions, never to prescribe working weights
 * (see docs/stage2-progression-rules.md, rule 7). Accuracy drops above roughly 10–12 total reps,
 * so the UI labels the result as an estimate.
 */
object OneRepMax {

    /**
     * @param weightKg weight lifted, in kilograms; must be positive.
     * @param reps repetitions completed; must be at least 1.
     * @param rir repetitions in reserve reported for the set; must be 0 or more.
     * @return the estimated 1RM in kilograms.
     */
    fun estimate(weightKg: Double, reps: Int, rir: Int = 0): Double {
        require(weightKg > 0.0) { "weightKg must be positive, was $weightKg" }
        require(reps >= 1) { "reps must be at least 1, was $reps" }
        require(rir >= 0) { "rir must not be negative, was $rir" }

        val repsToFailure = reps + rir
        // A single rep taken to failure is the 1RM itself; Epley would overestimate it by ~3%.
        if (repsToFailure == 1) return weightKg
        return weightKg * (1.0 + repsToFailure / 30.0)
    }
}
