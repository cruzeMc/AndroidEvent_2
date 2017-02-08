package com.events;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.events.authentication.VerifyResponse;
import com.events.custom.CustomActivity;
import com.events.model.Login;
import com.events.services.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.events.sync.ServerCommunicator;

public class LoginActivity extends CustomActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = (Button) findViewById(R.id.login);
//        final Button register = (Button) findViewById(R.id.register);

//        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                EditText email = (EditText) findViewById(R.id.email);
                String emailText = email.getText().toString();

                // Get password
                EditText password = (EditText) findViewById(R.id.password);
                String passwordText = password.getText().toString();

                Login user = new Login(emailText, passwordText);

                TransmitData(user);
                break;

//            case R.id.register:
//                // transition to RegisterActivity
//                Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);
//                break;

            default:
                break;
        }
    }

    private void TransmitData(Login user) {
        APIService service = ServerCommunicator.getServerCommunicator().create(APIService.class);
        Call<VerifyResponse> call = service.postLogin(user);

        call.enqueue(new Callback<VerifyResponse>() {
            @Override
            public void onResponse(Call<VerifyResponse> call, Response<VerifyResponse> response) {
                if (response.isSuccessful() && response.body() != null  ) {
                    if (response.body().getSuccess()) {
                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.success_login);
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        // transition to MainActivity upon login
                        Intent i = new Intent(LoginActivity.this, CategoryActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    } else {

                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.fail_login);
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Something went wrong";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<VerifyResponse> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = getString(R.string.network_error);
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
