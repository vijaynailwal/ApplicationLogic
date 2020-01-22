package com.example.applicationlogic

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.provider.SyncStateContract
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*
import java.text.DecimalFormat
import java.util.regex.Pattern

class ApplicationLogic : AppCompatActivity() {
    var price1 = ""
    var price2 = ""
    var price3 = ""
    var price4 = ""
    var selectedDate = ""
    var TAG = "ApplicationLogic "
    var mindate = 0L

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        price1 = setPriceDecimal("12.")
        price2 = setPriceDecimal("12.10")
        price3 = setPriceDecimal("12.11")
        price4 = setPriceDecimal("12")
        Log.e(TAG, "price1 " + price1)
        Log.e(TAG, "price2 " + price2)
        Log.e(TAG, "price3 " + price3)
        Log.e(TAG, "price4 " + price4)

        btn_opencalender.setOnClickListener {
            setMaxMinCalendar(age)
        }
        btn_alphanumeric.setOnClickListener {
            if (alphaNumericPassword(et_password.text.toString())) {
                Log.e("valid ", "valid")
            } else {
                Log.e("valid ", "In valid")
            }
        }
//        #####,###.00
        decimalFormat()
        btn_sumofinteger.setOnClickListener {
            val value = getSum(et_amount.text.toString().toInt())
            Log.e("value", value.toString())
        }


//decimal filter before and after
        price.addTextChangedListener(
                object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        var string = s
                        if (string != null) {
                            if (string.length == 0) {
                            }
                        }
                    }

                    override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        if (s != null) {
                            if (s.length > 0) {
                                if (s.contains(".")) {

                                    if (s.contains(".")) {
                                        price.setFilters(arrayOf<InputFilter>(InputFilterDecimal(7, 2)))
                                    } else {
                                        price.setFilters(arrayOf<InputFilter>(InputFilterDecimal(7, 2)))
                                    }
                                }
                            }

                        }
                    }
                })

//comma seperated input
        edCommaSeperated.addTextChangedListener(NumberTextWatcherForThousand(edCommaSeperated))
        NumberTextWatcherForThousand.trimCommaOfString(edCommaSeperated.getText().toString())


        amountFormat()
    }

    //1.
    private fun setPriceDecimal(number: String): String {
        var finalValue = ""
        val sb = StringBuilder()
        if (number.contains(".")) {
            val sp = number.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (sp.size == 2) {
                val getValue = sp[1]
                if (getValue.length == 2) {
                    finalValue = number
                } else if (getValue.length == 1) {
                    val s3 = getValue.substring(0)
                    if (s3 == "0") {
                        sb.append(s3)
                        sb.append("0")
                        finalValue = sp[0] + "." + sb.toString()
                    } else {
                        sb.append(getValue.substring(0))
                        sb.append("0")
                        finalValue = sp[0] + "." + sb.toString()
                    }
                } else if (getValue == "") {
                    finalValue = "$number.00"
                }
            } else if (sp.size == 1) {
                finalValue = sp[0] + ".00"
            }
        } else {
            finalValue = "$number.00"
        }
        return finalValue
    }

    //2.
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMaxMinCalendar(txtFromDate: TextView): Unit {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val mDatePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    selectedDate = "" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year
//                   add before zero if date unit place only once
                    selectedDate = getTime(selectedDate)
                    txtFromDate.text = selectedDate
                }, year, month, day
        )


        val string_date = "12-12-2019"
        val f = SimpleDateFormat("dd-MM-yyyy")
        try {
            val date = f.parse(string_date)
            mindate = date.getTime()
        } catch (e: Exception) {
        }
        mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis())
        mDatePicker.getDatePicker().setMinDate(mindate)
        mDatePicker.show()
    }

    //3.
    fun alphaNumericPassword(pass: String): Boolean {
        val str = pass
        var valid = true

        // Password policy check
        // Password should be minimum minimum 8 characters long
        if (str.length < 6) {
            valid = false
        }
        // Password should contain at least one number
        var exp = ".*[0-9].*"
        var pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
        var matcher = pattern.matcher(str)
        if (!matcher.matches()) {
            valid = false
        }
        // Password should contain at least one small letter
        exp = ".*[a-z].*"
        pattern = Pattern.compile(exp)
        matcher = pattern.matcher(str)
        if (!matcher.matches()) {
            valid = false
        }
        // Password should contain at least one special character
        // Allowed special characters : "~!@#$%^&*()-_=+|/,."';:{}[]<>?"
        exp = ".*[~!@#\$%\\^&*()\\-_=+\\|\\[{\\]};:'\",<.>/?].*"
        pattern = Pattern.compile(exp)
        matcher = pattern.matcher(str)
        if (!matcher.matches()) {
            valid = false
        }
        return valid
    }

    //4.
    fun decimalFormat() {
        val df = DecimalFormat(resources.getString(R.string.decimalformat))
        Log.e("decimalformat ", df.format("123456789.00".toBigDecimal()))

    }

    //5. sum of integer
    fun getSum(n: Int): Int {
        var n = n
        var sum = 0
        while (n != 0) {
            sum = sum + n % 10
            n = n / 10
        }
        return sum
    }


    //amount format
    fun amountFormat() {
        var price_actual = "1234567.000"
        val df = DecimalFormat("#####,###.00")
        tvamountFormat.setText(df.format(price_actual.toBigDecimal()))

    }
}



