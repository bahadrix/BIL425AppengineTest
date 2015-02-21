package me.bahadir.bil425.xoxgame;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import me.bahadir.bil425.xoxgame.backend.userApi.model.User;
import me.bahadir.bil425.xoxgame.services.UserService;


public class RegisterActivity extends ActionBarActivity {

    private TextView txtUsername, txtName, txtMail, txtPass, txtPassCheck;
    private AsyncTask<Void, Void, Void> registerTask = new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... params) {
            register();
            return null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtUsername = (TextView) findViewById(R.id.txtUsername);
        txtMail = (TextView) findViewById(R.id.txtMail);
        txtName = (TextView) findViewById(R.id.txtName);
        txtPass = (TextView) findViewById(R.id.txtPass);
        txtPassCheck = (TextView) findViewById(R.id.txtPassCheck);

        Button btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerTask.execute();
            }
        });
    }

    private void register() {

        if(!txtPass.getText().toString().equals(txtPassCheck.getText().toString())) {
            AlertDialog.Builder ab = new AlertDialog.Builder(this);
            ab.setTitle("Validation error");
            ab.setMessage("Passwords must match");
            ab.show();

        } else {

            User user = new User();
            user.setUsername(txtUsername.getText().toString());
            user.setName(txtName.getText().toString());
            user.setMail(txtMail.getText().toString());
            user.setPassword(txtPass.getText().toString());

            try {
                UserService.getInstance().register(user);
            } catch (IOException e) {
                Log.e("Registration error", e.getMessage());
            }


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
