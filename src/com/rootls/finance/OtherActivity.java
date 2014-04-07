package com.rootls.finance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.rootls.util.URLHelper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 14-4-6
 * Time: 下午9:44
 * To change this template use File | Settings | File Templates.
 */
public class OtherActivity extends Activity implements View.OnClickListener {


    private static final String TAG = "OtherActivity";
    private Button list_button;
    private Button add_button;
    private Button exit_button;
    private EditText money_editText;
    private EditText week_editText;
    private EditText date_editText;
    private EditText desc_editText;
    private EditText user_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);

        list_button = (Button)this.findViewById(R.id.list_button);
        add_button = (Button)this.findViewById(R.id.add_button);
        exit_button = (Button)this.findViewById(R.id.exit_button);

        add_button.setOnClickListener(this);

        money_editText = (EditText)this.findViewById(R.id.money_editText);
        week_editText = (EditText)this.findViewById(R.id.week_editText);
        date_editText = (EditText)this.findViewById(R.id.date_editText);
        desc_editText = (EditText)this.findViewById(R.id.desc_editText);
        user_editText = (EditText)this.findViewById(R.id.user_editText);

        Intent intent = getIntent();
        String username= intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        user_editText.setText(username+"/"+password);

    }

    public static String finance_add_url = "http://luowei.duapp.com/finance/addDaytip";

    @Override
    public void onClick(View view) {
        Float money = Float.valueOf(money_editText.getText().toString().trim());
        String week = week_editText.getText().toString().trim();
        String date = date_editText.getText().toString().trim();
        String desc = desc_editText.getText().toString().trim();

//        Log.d(TAG, URLUtil.guessUrl(finance_add_url));

        String result = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(finance_add_url+"?money=" + money + "&week=" + week + "&tipDateStr=" + date + "&desc=" + desc);
            urlConnection = (HttpURLConnection)url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = readInStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        Toast.makeText(this,"money:"+money+" week:"+week+" date:"+date+" desc:"+desc,3000).show();

        Toast.makeText(this,"=====result:"+result,3000).show();
    }

    private String readInStream(InputStream in) {
        Scanner scanner = new Scanner(in).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

}
