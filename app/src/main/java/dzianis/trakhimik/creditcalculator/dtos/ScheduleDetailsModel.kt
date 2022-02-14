package dzianis.trakhimik.creditcalculator.dtos

import dzianis.trakhimik.creditcalculator.helpers.CalculateHelper
import java.io.Serializable

class ScheduleDetailsModel(val amount: Int = 0, val paymentsCount: Int = 0, val percent: Float = 0f) : Serializable {
    val monthlyPayment: Float
    val payments: MutableList<MonthlyPaymentDetailsModel> = mutableListOf()
    var overPayment: Float = 0f
    init {
        monthlyPayment = CalculateHelper.getMonthlyPayment(amount = amount, paymentsCount = paymentsCount, percent = percent)
        var restOfAmount = amount.toFloat()

        repeat(paymentsCount) {
            val percentsPayment = CalculateHelper.getMonthlyPaymentOfPercents(restOfAmount, monthlyPayment, percent)
            val mainCreditPayment = CalculateHelper.getMonthlyPaymentOfMainCredit(monthlyPayment, percentsPayment)
            overPayment += percentsPayment
            restOfAmount -= mainCreditPayment
            payments.add(MonthlyPaymentDetailsModel(it, percentsPayment, mainCreditPayment))
        }
    }
}