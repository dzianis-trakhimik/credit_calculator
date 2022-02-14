package dzianis.trakhimik.creditcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout.LayoutParams as llLP
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import dzianis.trakhimik.creditcalculator.dtos.ScheduleDetailsModel

class PaymentsScheduleActivity : AppCompatActivity() {
    val matchParentLP = llLP(llLP.MATCH_PARENT, llLP.WRAP_CONTENT)
    val wrapContentLP = llLP(llLP.WRAP_CONTENT, llLP.WRAP_CONTENT)
    lateinit var tbl: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payments_schedule)
        initViews()
        fillTable(intent.getSerializableExtra("data") as ScheduleDetailsModel)
    }

    private fun fillTable(data: ScheduleDetailsModel) {
        for(payment in data.payments) {
            tbl.addView(row(arrayListOf(
                payment.monthIndex.toString(),
                String.format("%.2f", data.monthlyPayment),
                String.format("%.2f", payment.creditDownPayment),
                String.format("%.2f", payment.percentsPayment)
            )), matchParentLP)
        }
    }

    private fun row(cellsValues: ArrayList<String>): TableRow {
        return with(TableRow(this)) {
            layoutParams = matchParentLP
            for (cellValue in cellsValues) {
                addView(textView(cellValue))
            }
            for (view in children) {
                (view.layoutParams as TableRow.LayoutParams).weight = 1f
            }
            this
        }
    }

    private fun textView(value: String = "_"): TextView {
        return with(TextView(this)) {
            text = value
            this
        }
    }

    private fun initViews() {
        tbl = findViewById(R.id.tbl)
    }
}