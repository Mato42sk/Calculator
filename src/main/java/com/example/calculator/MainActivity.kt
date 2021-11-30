package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view:View){

        textField.append((view as Button).text)
        lastNumeric = true

    }

    fun clear(view: View){
        textField.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View){
        if (lastNumeric && ! lastDot){  // && ! = and not
            textField.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View){
// -
        if (lastNumeric){
            var tfValue = textField.text.toString()
            var prefix = ""
            try {
                if (tfValue.startsWith("-")) {
                    prefix = "-"
                    tfValue = tfValue.substring(1)
                }
            }finally {

            }
            try {
                if (tfValue.contains("-")){
                    val splitValue = tfValue.split("-")

                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if (!prefix.isEmpty()){ // is not empty
                        firstValue = prefix + firstValue
                    }

                    textField.text = removeZeroAfterDot((firstValue.toDouble() - secondValue.toDouble()).toString())
// +
                }else if (tfValue.contains("+")){
                    val splitValue = tfValue.split("+")

                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if (!prefix.isEmpty()){ // is not empty
                        firstValue = prefix + firstValue
                    }

                    textField.text = removeZeroAfterDot((firstValue.toDouble() + secondValue.toDouble()).toString())
// *
                }else if (tfValue.contains("*")){
                    val splitValue = tfValue.split("*")

                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if (!prefix.isEmpty()){ // is not empty
                        firstValue = prefix + firstValue
                    }

                    textField.text = removeZeroAfterDot((firstValue.toDouble() * secondValue.toDouble()).toString())
// /
                }else if (tfValue.contains("/")){
                    val splitValue = tfValue.split("/")
                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if (!prefix.isEmpty()){
                        firstValue = prefix + firstValue
                    }

                    textField.text = removeZeroAfterDot((firstValue.toDouble() / secondValue.toDouble()).toString())
                }

            }catch (e: ArithmeticException) {
                e.printStackTrace()
            }
      }
    }

    private fun removeZeroAfterDot(result: String):String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length - 2)
            return value
    }

    fun onOperator(view: View){
        if (lastNumeric && !isOperatorAdded(textField.text.toString()))
            textField.append((view as Button).text)
        lastNumeric = false
        lastDot = false

    }

    private fun isOperatorAdded(value:String):Boolean{
        return if (value.startsWith("-")){
            false
        }else
            value.contains("/") || value.contains("*") ||
                    value.contains("+") || value.contains("-")
    }
}