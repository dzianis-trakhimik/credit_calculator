package dzianis.trakhimik.creditcalculator.helpers

import kotlin.math.pow

object CalculateHelper {
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
    fun getMonthlyPayment(amount: Int = 0, paymentsCount: Int = 0, percent: Float = 0f): Float {
        if (amount <=0 || paymentsCount <= 0 || percent <= 0) {
            return 0f
        }

        val P = getP(percent)
        val S = amount
        val n = paymentsCount
        return S * (P + (P / ((1 + P).pow(n) - 1)))
    }

    fun getMonthlyPaymentOfMainCredit(monthlyPayment: Float, monthlyPercentsPayment: Float): Float {
        return monthlyPayment - monthlyPercentsPayment
    }

    fun getMonthlyPaymentOfPercents(restOfAmount: Float, monthlyPayment: Float, yearPercent: Float): Float {
        return restOfAmount * ((yearPercent / 12) / 100)
    }

    // Returns monthly payment / 100
    private fun getP(agePercent: Float): Float {
        return (agePercent / 12) / 100;
    }

    //endregion
}