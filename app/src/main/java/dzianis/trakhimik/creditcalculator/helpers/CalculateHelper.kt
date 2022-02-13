package dzianis.trakhimik.creditcalculator.helpers

import kotlin.math.pow

class CalculateHelper {
    //region Annuity schedule

    /**
     *  Gets monthly payment for Annuity schedule
     *
     *                    P
     *  x = S * (P + --------------- )
     *                (1 + P)^n - 1
     *
     * Where:
     * S = credit amount
     * P = percent per mont / 100
     * N = count of months
     */
    fun getMonthlyPayment(creditAmount: Int = 0, paymentsCount: Int = 0, agePercent: Float = 0f): Float {
        if (creditAmount <=0 || paymentsCount <= 0 || agePercent <= 0) {
            return 0f
        }

        val P = getP(agePercent)
        val S = creditAmount
        val n = paymentsCount
        return S * (P + (P / ((1 + P).pow(n) - 1)))
    }

    // Returns monthly payment / 100
    private fun getP(agePercent: Float): Float {
        return (agePercent / 12) / 100f;
    }

    private fun getMonthlyPaymentOfMainCredit(monthlyPayment: Float, monthlyPercentsPayment: Float): Float {
        return monthlyPayment - monthlyPercentsPayment
    }

    private fun getMonthlyPaymentOfPercents(restOfAmount: Float, monthlyPayment: Float, yearPercent: Float): Float {
        return monthlyPayment - (restOfAmount * (yearPercent / 12))
    }

    //endregion
}