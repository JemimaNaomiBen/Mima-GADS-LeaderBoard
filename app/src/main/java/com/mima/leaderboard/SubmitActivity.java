package com.mima.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.mima.leaderboard.services.TaskService;
import com.mima.leaderboard.services.ServiceBuilder;

import org.jetbrains.annotations.NotNull;

public class SubmitActivity extends AppCompatActivity {

    private TextInputEditText firstName;
    private TextInputEditText lastName;
    private TextInputEditText email;
    private TextInputEditText github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        github = findViewById(R.id.githubLink);

        MaterialButton button = findViewById(R.id.submitButton);
        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> finish());

        button.setOnClickListener(v -> {
            TextInputEditText fields[] = {firstName, lastName, email, github};
            if(validateFields(fields))
                showConfirmDialog();

        });


    }

    private void showConfirmDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_confirm);
        dialog.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm(firstName.getText().toString(),
                        lastName.getText().toString(),
                        email.getText().toString(),
                        github.getText().toString());

                dialog.dismiss();
            }

        });
        dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }

        });
        dialog.show();
        setDialogWindowProperties(dialog);

    }
    private void showSuccessDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_success);
        dialog.show();
        setDialogWindowProperties(dialog);

    }
    private void showFailureDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_failure);
        dialog.show();
        setDialogWindowProperties(dialog);
    }

    private void setDialogWindowProperties(Dialog dialog) {
        Window window =dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    private void submitForm(String firstName, String lastName, String email, String link) {

        TaskService service = ServiceBuilder.buildService(TaskService.class);
        Call<Void> call = service.submit(ServiceBuilder.FORM_URL,firstName,lastName,email,link);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showSuccessDialog();
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, Throwable t) {
                showFailureDialog();
            }
        });
    }


    private boolean validateFields(TextInputEditText[] inputs){
        String inputEmail = email.getText().toString().trim();
        String inputGit = github.getText().toString().trim();

        for (TextInputEditText input : inputs){

            if (input.getText().toString().length()<=0){
                Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                return false;
            }

            if( ! Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches() ){
                Toast.makeText(this, "invalid email retry", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(!Patterns.WEB_URL.matcher(inputGit).matches()){
                Toast.makeText(this, "invalid url retry", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }





}