package by.lomazki.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.lomazki.calculator.databinding.FragmentCalculatorBinding
import com.google.android.material.snackbar.Snackbar


class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() :FragmentCalculatorBinding = requireNotNull(_binding)

    private var expression = StringBuilder()
    private val calc = Calc()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCalculatorBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            dot.setOnClickListener {
                expression.append(DOT)
                resultTextView.text = expression.toString()
            }
            number0.setOnClickListener {
                expression.append(ZERO)
                resultTextView.text = expression.toString()
            }
            number1.setOnClickListener {
                expression.append(ONE)
                resultTextView.text = expression.toString()
            }
            number2.setOnClickListener {
                expression.append(TWO)
                resultTextView.text = expression.toString()
            }
            number3.setOnClickListener {
                expression.append(TREE)
                resultTextView.text = expression.toString()
            }
            number4.setOnClickListener {
                expression.append(FOUR)
                resultTextView.text = expression.toString()
            }
            number5.setOnClickListener {
                expression.append(FIVE)
                resultTextView.text = expression.toString()
            }
            number6.setOnClickListener {
                expression.append(SIX)
                resultTextView.text = expression.toString()
            }
            number7.setOnClickListener {
                expression.append(SEVEN)
                resultTextView.text = expression.toString()
            }
            number8.setOnClickListener {
                expression.append(EIGHT)
                resultTextView.text = expression.toString()
            }
            number9.setOnClickListener {
                expression.append(NINE)
                resultTextView.text = expression.toString()
            }
            plus.setOnClickListener {
                expression.append(PLUS)
                resultTextView.text = expression.toString()
            }
            minus.setOnClickListener {
                expression.append(MINUS)
                resultTextView.text = expression.toString()
            }
            div.setOnClickListener {
                expression.append(DIV)
                resultTextView.text = expression.toString()
            }
            multiply.setOnClickListener {
                expression.append(MULTIPLY)
                resultTextView.text = expression.toString()
            }
            equally.setOnClickListener {
                val result = calc.calculate(expression.toString())
                if (result.toDoubleOrNull() == null) {
                    resultTextView.text = expression
                    Snackbar.make(view, result, Snackbar.LENGTH_INDEFINITE)
                        .setAction("ok") {
                        }.show()
                } else {
                    resultTextView.text = result
                    expression.clear()
                }
            }
            backspace.setOnClickListener {
                if (expression.isEmpty()) {
                    expression.append(EMPTY_STRING)
                } else {
                    expression.deleteCharAt(expression.length - 1)
                }
                resultTextView.text = expression
            }
            c.setOnClickListener {
                expression.clear()
                resultTextView.text = EMPTY_STRING
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

// --------------------------------------------------------------------------------------------------

}
