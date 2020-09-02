package com.java.android.pratikhomeautomation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Switch1Btn, Switch2Btn, Switch3Btn, Switch4Btn, Light1Btn, Light2Btn, Light3Btn, FanBtn;
    HAPresenter haPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch1Btn =  findViewById(R.id.btn_switch1);
        Switch2Btn =  findViewById(R.id.btn_switch2);
        Switch3Btn =  findViewById(R.id.btn_switch3);
        Switch4Btn =  findViewById(R.id.btn_switch4);

        Light1Btn =  findViewById(R.id.btn_light1);
        Light2Btn =  findViewById(R.id.btn_light2);
        Light3Btn =  findViewById(R.id.btn_light3);
        FanBtn =  findViewById(R.id.btn_fan);

        Switch1Btn.setOnClickListener(this);
        Switch2Btn.setOnClickListener(this);
        Switch3Btn.setOnClickListener(this);
        Switch4Btn.setOnClickListener(this);

        Light1Btn.setOnClickListener(this);
        Light2Btn.setOnClickListener(this);
        Light3Btn.setOnClickListener(this);
        FanBtn.setOnClickListener(this);

        HAPresenter haPresenter = new HAPresenter(this);
        haPresenter.getStatus();
    }

    @Override
    public void onClick(View v) {
        HAPresenter haPresenter = new HAPresenter(this);
        switch (v.getId()) {
            case R.id.btn_switch1:
                if(Switch1Btn.getText().equals("ON")) {
                    Log.d("btn","1: "+Switch1Btn.getText());
                    haPresenter.updateStatus(1,0);
                }
                else if(Switch1Btn.getText().equals("OFF")){
                    Log.d("btn","1: "+Switch1Btn.getText());
                    haPresenter.updateStatus(1,1);
                }
                break;
            case R.id.btn_switch2:
                if(Switch2Btn.getText().equals("ON")) {
                    haPresenter.updateStatus(2,0);
                }
                else if(Switch2Btn.getText().equals("OFF")){
                    haPresenter.updateStatus(2,1);
                }
                break;
            case R.id.btn_switch3:
                if(Switch3Btn.getText().equals("ON")) {
                    haPresenter.updateStatus(3,0);
                }
                else if(Switch3Btn.getText().equals("OFF")){
                    haPresenter.updateStatus(3,1);
                }
                break;
            case R.id.btn_switch4:
                if(Switch4Btn.getText().equals("ON")) {
                    haPresenter.updateStatus(4,0);
                }
                else if(Switch4Btn.getText().equals("OFF")){
                    haPresenter.updateStatus(4,1);
                }
                break;
            case R.id.btn_light1:
                if(Light1Btn.getText().equals("ON")) {
                    haPresenter.updateStatus(5,0);
                }
                else if(Light1Btn.getText().equals("OFF")){
                    haPresenter.updateStatus(5,1);
                }
                break;
            case R.id.btn_light2:
                if(Light2Btn.getText().equals("ON")) {
                    haPresenter.updateStatus(6,0);
                }
                else if(Light2Btn.getText().equals("OFF")){
                    haPresenter.updateStatus(6,1);
                }
                break;
            case R.id.btn_light3:
                if(Light3Btn.getText().equals("ON")) {
                    haPresenter.updateStatus(7,0);
                }
                else if(Light3Btn.getText().equals("OFF")){
                    haPresenter.updateStatus(7,1);
                }
                break;
            case R.id.btn_fan:
                if(FanBtn.getText().equals("ON")) {
                    haPresenter.updateStatus(8,0);
                }
                else if(FanBtn.getText().equals("OFF")){
                    haPresenter.updateStatus(8,1);
                }
                break;
        }
    }

    public void onFailedDataFetch() {
        Toast.makeText(this,"Data Fetch UnSuccessful",Toast.LENGTH_SHORT).show();
    }

    public void onSuccessfulDataFetch(String switch1, String switch2, String switch3, String switch4, String light1, String light2, String light3, String fan) {

        if(switch1.equals("0")) Switch1Btn.setText("OFF");
        if(switch2.equals("0")) Switch2Btn.setText("OFF");
        if(switch3.equals("0")) Switch3Btn.setText("OFF");
        if(switch4.equals("0")) Switch4Btn.setText("OFF");

        if(light1.equals("0")) Light1Btn.setText("OFF");
        if(light2.equals("0")) Light2Btn.setText("OFF");
        if(light3.equals("0")) Light3Btn.setText("OFF");
        if(fan.equals("0")) FanBtn.setText("OFF");

        if(switch1.equals("1")) Switch1Btn.setText("ON");
        if(switch2.equals("1")) Switch2Btn.setText("ON");
        if(switch3.equals("1")) Switch3Btn.setText("ON");
        if(switch4.equals("1")) Switch4Btn.setText("ON");

        if(light1.equals("1")) Light1Btn.setText("ON");
        if(light2.equals("1")) Light2Btn.setText("ON");
        if(light3.equals("1")) Light3Btn.setText("ON");
        if(fan.equals("1")) FanBtn.setText("ON");

        Toast.makeText(this,"Data Fetch Successful",Toast.LENGTH_SHORT).show();
    }

    public void updateButtonStatus(Integer buttonNumber, Integer status) {
        Log.d("btn","DataFetch: "+buttonNumber+" "+status);
        if(buttonNumber == 1 && status == 0) Switch1Btn.setText("OFF");
        if(buttonNumber == 1 && status == 1) Switch1Btn.setText("ON");

        if(buttonNumber == 2 && status == 0) Switch2Btn.setText("OFF");
        if(buttonNumber == 2 && status == 1) Switch2Btn.setText("ON");

        if(buttonNumber == 3 && status == 0) Switch3Btn.setText("OFF");
        if(buttonNumber == 3 && status == 1) Switch3Btn.setText("ON");

        if(buttonNumber == 4 && status == 0) Switch4Btn.setText("OFF");
        if(buttonNumber == 4 && status == 1) Switch4Btn.setText("ON");

        if(buttonNumber == 5 && status == 0) Light1Btn.setText("OFF");
        if(buttonNumber == 5 && status == 1) Light1Btn.setText("ON");

        if(buttonNumber == 6 && status == 0) Light2Btn.setText("OFF");
        if(buttonNumber == 6 && status == 1) Light2Btn.setText("ON");

        if(buttonNumber == 7 && status == 0) Light3Btn.setText("OFF");
        if(buttonNumber == 7 && status == 1) Light3Btn.setText("ON");

        if(buttonNumber == 8 && status == 0) FanBtn.setText("OFF");
        if(buttonNumber == 8 && status == 1) FanBtn.setText("ON");

        Toast.makeText(this,"Updated Successfully",Toast.LENGTH_SHORT).show();
    }

    public void onFailedUpdate() {
        Toast.makeText(this,"Updated UnSuccessful",Toast.LENGTH_SHORT).show();
    }
}
