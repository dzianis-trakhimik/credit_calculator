package dzianis.trakhimik.creditcalculator.dtos

import java.io.Serializable

class MonthlyPaymentDetailsModel (val monthIndex: Int, val percentsPayment: Float, val creditDownPayment: Float) : Serializable { }