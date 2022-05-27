package com.leeddev.agecalculator;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.Duration;
import java.util.Calendar;

public class AgeDifferences extends AppCompatActivity {
    TextView textViewFinalYears1, textViewFinalMonths1, textViewFinalDays1, textViewCalculate3,
            textViewClear1, textViewCurrentDay11;
    ImageView imageViewCalenderFirst1, imageViewCalenderSecond2;
    EditText editTextBirthDay2, editTextBirthMonth2, editTextBirthYear2, editTextCurrentDay1,
            textViewCurrentDay2, editTextCurrentMonth1, editTextCurrentYear1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_differences);
        textViewFinalYears1 = findViewById(R.id.textViewFinalYears1);
        textViewFinalMonths1 = findViewById(R.id.textViewFinalMonths1);
        textViewFinalDays1 = findViewById(R.id.textViewFinalDays1);
        textViewCalculate3 = findViewById(R.id.textViewCalculate1);
        textViewClear1 = findViewById(R.id.textViewClear1);
        imageViewCalenderFirst1 = findViewById(R.id.imageViewCalenderFirst1);
        imageViewCalenderSecond2 = findViewById(R.id.imageViewCalenderSecond2);
        editTextCurrentMonth1 = findViewById(R.id.editTextCurrentMonth1);
        editTextCurrentYear1 = findViewById(R.id.editTextCurrentYear1);
        editTextCurrentDay1 = findViewById(R.id.editTextCurrentDay1);
        editTextBirthDay2 = findViewById(R.id.editTextBirthDay2);
        editTextBirthMonth2 = findViewById(R.id.editTextBirthMonth2);
        editTextBirthYear2 = findViewById(R.id.editTextBirthYear2);

        imageViewCalenderSecond2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.imageViewCalenderSecond2) {
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            editTextBirthDay2.setText(addZero(dayOfMonth));
                            editTextBirthMonth2.setText(addZero(monthOfYear + 1));
                            editTextBirthYear2.setText(String.valueOf(year));

                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });
        imageViewCalenderFirst1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.imageViewCalenderFirst1) {
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            editTextCurrentDay1.setText(addZero(dayOfMonth));
                            editTextCurrentMonth1.setText(addZero(monthOfYear + 1));
                            editTextCurrentYear1.setText(String.valueOf(year));
                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });


//
//            }
//        });
        textViewCalculate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == textViewCalculate3) {
                    if (!TextUtils.isEmpty(editTextBirthDay2.getText()) && !TextUtils.isEmpty(editTextBirthMonth2.getText()) && !TextUtils.isEmpty(editTextBirthYear2.getText())) {
                        calculateAge();
                        //nextBirthday();
                    } else {
                        //Toast.warning(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT, true).show();
                    }
                }
            }
        });
        textViewClear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == textViewClear1) {
                    editTextCurrentDay1.setText("");
                    editTextBirthDay2.setText("");
                    editTextCurrentMonth1.setText("");
                    editTextBirthMonth2.setText("");
                    editTextCurrentYear1.setText("");
                    editTextBirthYear2.setText("");
                    textViewFinalDays1.setText("00");
                    textViewFinalMonths1.setText("00");
                    textViewFinalYears1.setText("00");

                    //  Toasty.success(MainActivity.this, "Successfully reset", Toast.LENGTH_SHORT, true).show();
                }
            }
        });

    }

    private String addZero(int number) {
        String n;
        if (number < 10) {
            n = "0" + number;
        } else {
            n = String.valueOf(number);
        }
        return n;
    }

    private void calculateAge() {
        int currentDay = Integer.parseInt(editTextCurrentDay1.getText().toString());
        int currentMonth = Integer.parseInt(editTextCurrentMonth1.getText().toString());
        int currentYear = Integer.parseInt(editTextCurrentYear1.getText().toString());

        Calendar now = Calendar.getInstance();
        now.set(currentYear, currentMonth - 1, currentDay, 0, 0);

        int birthDay = Integer.parseInt(editTextBirthDay2.getText().toString());
        int birthMonth = Integer.parseInt(editTextBirthMonth2.getText().toString());
        int birthYear = Integer.parseInt(editTextBirthYear2.getText().toString());

        Calendar dob = Calendar.getInstance();
        dob.set(birthYear, birthMonth - 1, birthDay, 0, 0);

       Calendar diff = Calendar.getInstance();
       if(dob.getTimeInMillis()>now.getTimeInMillis()) {
           diff.setTimeInMillis(dob.getTimeInMillis() - now.getTimeInMillis());
       }
       else if (dob.getTimeInMillis()< now.getTimeInMillis()){
           diff.setTimeInMillis(now.getTimeInMillis()- dob.getTimeInMillis()) ;
       }
       else if(dob.getTimeInMillis()==now.getTimeInMillis())
       {
           diff.setTimeInMillis(00);
       }

        int y = diff.get(Calendar.YEAR)-1970;
        int m = diff.get(Calendar.MONTH);
        int d = diff.get(Calendar.DAY_OF_MONTH)-1;


       textViewFinalDays1.setText(String.valueOf(d));
        textViewFinalMonths1.setText(String.valueOf(m));
        textViewFinalYears1.setText(String.valueOf(y));

    }
}