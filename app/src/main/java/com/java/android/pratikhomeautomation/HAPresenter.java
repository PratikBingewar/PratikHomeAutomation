package com.java.android.pratikhomeautomation;

public class HAPresenter {

    MainActivity mainActivity;
    HAModel haModel;
    UpdateStatusModel updateStatusModel;

    public HAPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void getStatus() {
        haModel = new HAModel(this,mainActivity);
        haModel.authenticate();
    }

    public void onFailedDataFetch() {
        mainActivity.onFailedDataFetch();
    }


    public void onSuccessfulDataFetch(String switch1, String switch2, String switch3, String switch4, String light1, String light2, String light3, String fan) {
        mainActivity.onSuccessfulDataFetch(switch1,switch2,switch3,switch4,light1,light2,light3,fan);
    }

    public void updateStatus(int buttonNumber, int status) {
        updateStatusModel = new UpdateStatusModel(this,mainActivity,buttonNumber,status);
        updateStatusModel.authenticate();
    }


    public void onFailedUpdate() {
        mainActivity.onFailedUpdate();
    }

    public void onSuccessfulUpdate(Integer buttonNumber, Integer status) {
        mainActivity.updateButtonStatus(buttonNumber,status);
    }
}
