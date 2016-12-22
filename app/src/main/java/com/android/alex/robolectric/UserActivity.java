package com.android.alex.robolectric;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandrediguida on 22/12/2016.
 */

public class UserActivity extends Activity {

    @BindView(R.id.header_text)
    TextView header;
    @BindView(R.id.logout_btn)
    TextView logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        String username = getIntent().getStringExtra("USERNAME");
        header.setText("Hello " + username + " !!");

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserActivity.this.finish();
            }
        });
    }

}
