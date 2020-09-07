package com.example.leaderboard.views.activities;


import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.example.leaderboard.R;
import com.example.leaderboard.data.retrofit.RetrofitApiCalls;
import com.example.leaderboard.data.retrofit.RetrofitClient;
import com.example.leaderboard.databinding.ActivitySubmissionBinding;
import com.example.leaderboard.databinding.DialogConfirmBinding;
import com.example.leaderboard.databinding.DialogFailedBinding;
import com.example.leaderboard.databinding.DialogSuccessBinding;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {
    ActivitySubmissionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_submission);

        binding.imgBtnBack.setOnClickListener(view -> onBackPressed());

        binding.btnSubmit.setOnClickListener(view -> {
            if (!isInputsEmpty()) {
                //Submit Details
                confirmSubmission();
            } else {
                //Warn about empty inputs.
                Snackbar.make(binding.cl, "Kindly fill all inputs", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public boolean isInputsEmpty() {
        if (binding.etFirstName.getText().toString().trim().isEmpty()) {
            return true;
        } else if (binding.etLastName.getText().toString().trim().isEmpty()) {
            return true;
        } else if (binding.etEmailAddress.getText().toString().trim().isEmpty()) {
            return true;
        } else return binding.etProjectLink.getText().toString().trim().isEmpty();

    }

    public void confirmSubmission() {
        DialogConfirmBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(SubmissionActivity.this), R.layout.dialog_confirm, null, false);

        Dialog dialog = new Dialog(this, R.style.Theme_MaterialComponents_Light_Dialog);
        dialog.setContentView(binding.getRoot());
        binding.imgBtnCancel.setOnClickListener(view -> dialog.dismiss());
        binding.btnPositive.setOnClickListener(view -> {
            //Make the actual project submission here.
            submitData();
            Toast.makeText(SubmissionActivity.this, "Submitting data", Toast.LENGTH_LONG).show();
            dialog.dismiss();
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    public void submitData() {
        String email_address = binding.etEmailAddress.getText().toString().trim();
        String first_name = binding.etFirstName.getText().toString().trim();
        String last_name = binding.etLastName.getText().toString().trim();
        String project_link = binding.etProjectLink.getText().toString().trim();

        RetrofitApiCalls google_service = RetrofitClient.getGoogleFormsRetrofitInstance().create(RetrofitApiCalls.class);
        Call<ResponseBody> call = google_service.submitGoogleForm(email_address, first_name, last_name, project_link);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    //Display Success dialog
                    successDialog();
                    clearInputs();
                } else {
                    failedDialog();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //Display failed dialog
                failedDialog();
            }
        });
    }

    public void successDialog() {
        DialogSuccessBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(SubmissionActivity.this), R.layout.dialog_success, null, false);

        Dialog dialog = new Dialog(this, R.style.Theme_MaterialComponents_Light_Dialog);
        dialog.setContentView(binding.getRoot());
        dialog.show();
    }

    public void failedDialog() {
        DialogFailedBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(SubmissionActivity.this), R.layout.dialog_failed, null, false);

        Dialog dialog = new Dialog(this, R.style.Theme_MaterialComponents_Light_Dialog);
        dialog.setContentView(binding.getRoot());
        dialog.show();
    }

    public void clearInputs() {
        binding.etEmailAddress.setText("");
        binding.etProjectLink.setText("");
        binding.etLastName.setText("");
        binding.etFirstName.setText("");
    }
}
