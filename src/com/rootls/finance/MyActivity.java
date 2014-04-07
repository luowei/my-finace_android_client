package com.rootls.finance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MyActivity extends Activity {

    private Button login_button;
    private EditText username_editText;
    private EditText password_editText;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        username_editText = (EditText)this.findViewById(R.id.username_editText);

        password_editText = (EditText)this.findViewById(R.id.password_editText);

        login_button = (Button)this.findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyActivity.this,OtherActivity.class);
                intent.putExtra("username",username_editText.getText().toString());
                intent.putExtra("password",password_editText.getText().toString());

                startActivity(intent);
            }
        });
    }
}
