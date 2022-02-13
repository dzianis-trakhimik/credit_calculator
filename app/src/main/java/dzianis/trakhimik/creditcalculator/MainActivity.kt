package dzianis.trakhimik.creditcalculator

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var btn_calculate: Button
    lateinit var tv_result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setListeners()
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn_calculate -> calculate()
        }
    }

    private fun calculate() {
        tv_result.text = with(StringBuilder()) { repeat(100) {
                this.append("Row number $it \n")
            }
            this.toString()
        }
    }

    private fun initViews() {
        btn_calculate = findViewById(R.id.btn_calculate)
        tv_result = findViewById(R.id.tv_result)
        tv_result.movementMethod = ScrollingMovementMethod()
    }

    private fun setListeners() {
        btn_calculate.setOnClickListener(this)
    }
}