package com.example.myhw

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.myhw.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    companion object{
        const val MAX_GRADE = 100.0
        const val MIN_GRADE = 0.0
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                // Not used
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not used
            }

            override fun afterTextChanged(s: Editable?) {
                // Call calculateGrade function to recalculate grade when user changes input
                calculateGrade()
            }
        }

        with(binding) {
            homework1EditText.addTextChangedListener(textWatcher)
            homework2EditText.addTextChangedListener(textWatcher)
            homework3EditText.addTextChangedListener(textWatcher)
            homework4EditText.addTextChangedListener(textWatcher)
            homework5EditText.addTextChangedListener(textWatcher)
            participationEditText.addTextChangedListener(textWatcher)
            midterm1EditText.addTextChangedListener(textWatcher)
            midterm2EditText.addTextChangedListener(textWatcher)
            groupProjectEditText.addTextChangedListener(textWatcher)
            finalExamEditText.addTextChangedListener(textWatcher)

            // Add click listener to calculateButton to calculate the grade when clicked
            calculateButton.setOnClickListener {
                calculateGrade()
            }
        }
    }

    private fun calculateGrade() {
        // Get the value of each EditText field and convert it to a Double
        with(binding) {
            val homework1 = homework1EditText.text.toString().toDoubleOrNull() ?: 0.0
            val homework2 = homework2EditText.text.toString().toDoubleOrNull() ?: 0.0
            val homework3 = homework3EditText.text.toString().toDoubleOrNull() ?: 0.0
            val homework4 = homework4EditText.text.toString().toDoubleOrNull() ?: 0.0
            val homework5 = homework5EditText.text.toString().toDoubleOrNull() ?: 0.0
            val participation = participationEditText.text.toString().toDoubleOrNull() ?: 0.0
            val midterm1 = midterm1EditText.text.toString().toDoubleOrNull() ?: 0.0
            val midterm2 = midterm2EditText.text.toString().toDoubleOrNull() ?: 0.0
            val groupProject = groupProjectEditText.text.toString().toDoubleOrNull() ?: 0.0
            val finalExam = finalExamEditText.text.toString().toDoubleOrNull() ?: 0.0
            // Check if the input values are within the allowed range
            val allInputsInRange = listOf(
                homework1,
                homework2,
                homework3,
                homework4,
                homework5,
                participation,
                midterm1,
                midterm2,
                groupProject,
                finalExam
            ).all { it in MIN_GRADE..MAX_GRADE }

            // If any of the input values are outside the allowed range, show an error message
            if (!allInputsInRange) {
                gradeTextView.text = getString(R.string.grade_error_message)
                return
            }
            val homeworkGrade = (homework1 + homework2 + homework3 + homework4 + homework5) / 5
            val midtermGrade = (midterm1 + midterm2) / 2
            val finalGrade =
                homeworkGrade * 0.2 + participation * 0.1 + midtermGrade * 0.2 + groupProject * 0.2 + finalExam * 0.3

            gradeTextView.text = getString(R.string.final_grade_message, finalGrade)
        }
    }
}
