package dzianis.trakhimik.creditcalculator

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dzianis.trakhimik.creditcalculator.dtos.ScheduleDetailsModel
import java.lang.StringBuilder


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var et_amount: EditText
    private lateinit var et_percent: EditText
    private lateinit var et_paymentsCount: EditText
    private lateinit var btn_calculate: Button
    private lateinit var btn_showSchedule: Button
    private lateinit var tv_result: TextView

    private var amount = 0
    private var yearPercent = 0f
    private var paymentsCount = 0
    private var detailsModel: ScheduleDetailsModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setListeners()
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn_calculate -> calculate()
            R.id.btn_show_schedule -> showScheduleActivity()
        }
    }

    private fun calculate() {
        refreshValues()
        detailsModel = ScheduleDetailsModel(amount = amount, paymentsCount = paymentsCount, percent = yearPercent)
        btn_showSchedule.isEnabled = true
        var detailsStr = with(StringBuilder()) {
            this.append("Monthly payment: ${detailsModel?.monthlyPayment}\n")
            this.append("Overpayment: ${detailsModel?.overPayment}")
            this.toString()
        }

        tv_result.text = detailsStr
    }

    private fun showScheduleActivity() {
        startActivity(with(Intent(this, PaymentsScheduleActivity::class.java)) {
            this.putExtra("data", detailsModel)
            this
        })
    }

    private fun refreshValues() {
        et_amount.text.toString()?.let { if (it.isNotEmpty()) amount = it.toInt() }
        et_percent.text.toString()?.let { if (it.isNotEmpty()) yearPercent = it.toFloat() }
        et_paymentsCount.text.toString()?.let { if (it.isNotEmpty()) paymentsCount = it.toInt() }
    }

    private fun initViews() {
        et_amount = findViewById(R.id.et_amount)
        et_percent = findViewById(R.id.et_percent)
        et_paymentsCount = findViewById(R.id.et_payments_count)
        btn_calculate = findViewById(R.id.btn_calculate)
        btn_showSchedule = findViewById(R.id.btn_show_schedule)
        tv_result = findViewById(R.id.tv_result)
        tv_result.movementMethod = ScrollingMovementMethod()
    }

    private fun setListeners() {
        btn_calculate.setOnClickListener(this)
        btn_showSchedule.setOnClickListener(this)
    }
}