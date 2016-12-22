package com.android.alex.robolectric;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username_edit)
    EditText username;
    @BindView(R.id.password_edit)
    EditText password;
    @BindView(R.id.login_btn)
    Button loginBtn;

    private boolean isUsernameCorrect, isPasswordCorrect;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        username.addTextChangedListener(editTextWatcher);
        password.addTextChangedListener(editTextWatcher);

        loginBtn.setEnabled(false); // By default can't login before user's details validation
        loginBtn.setOnClickListener(loginBtnListener);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private TextWatcher editTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            // If the Text written is the same in both EditText, it's still going to work because I'm not checking the Text value but
            // their HashCode : unique generated ID of each object.
            if (username.getText().hashCode() == editable.hashCode()) {
                editTextValidator(username);
            } else if (password.getText().hashCode() == editable.hashCode()) {
                editTextValidator(password);
            }
        }
    };

    private View.OnClickListener loginBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Toast.makeText(LoginActivity.this, "SUCCESS !", Toast.LENGTH_SHORT).show();

            Util.hideSoftKeyboard(LoginActivity.this);

            Intent intent = new Intent(LoginActivity.this, UserActivity.class);
            intent.putExtra("USERNAME", username.getText().toString());

            // Clean fields if logout
            username.setText("");
            password.setText("");

            startActivity(intent);
        }
    };

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private void editTextValidator(EditText editText) {

        int id = editText.getId();
        switch (id) {
            case R.id.username_edit:
                isUsernameCorrect = Util.isUserValid(editText.getText().toString());
                updateEditUI(editText, isUsernameCorrect);
                break;
            case R.id.password_edit:
                isPasswordCorrect = Util.isPassValid(editText.getText().toString());
                updateEditUI(editText, isPasswordCorrect);
                break;
            default:
                isUsernameCorrect = isPasswordCorrect = false;
                updateEditUI(editText, isUsernameCorrect);
                break;
        }

        updateButtonUI(); // Check if both fields are valid
    }

    private void updateEditUI(EditText editText, boolean isValid) {

        if ( isValid ) { // Valid = green
            editText.setBackground(ContextCompat.getDrawable(this, R.drawable.custom_edit_valid));
        } else if ( "".equals(editText.getText().toString()) ) { // Not Valid and Empty = default
            editText.setBackground(ContextCompat.getDrawable(this, R.drawable.custom_edit));
        } else { // Not valid = red
            editText.setBackground(ContextCompat.getDrawable(this, R.drawable.custom_edit_warning));
        }
    }

    private void updateButtonUI() {

        if (isUsernameCorrect && isPasswordCorrect) {
            loginBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.custom_button_valid));
            loginBtn.setEnabled(true);
        } else {
            loginBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.custom_button));
            loginBtn.setEnabled(false);
        }
    }
}
