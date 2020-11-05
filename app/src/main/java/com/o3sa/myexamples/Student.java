package com.o3sa.myexamples;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Student extends BaseObservable {
    private String txtName;
    private String txtEmail;

    public Student(String txtName,String txtEmail){
        this.txtName=txtName;
        this.txtEmail=txtEmail;
    }


    @Bindable
    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {

        this.txtName = txtName;
        notifyPropertyChanged(BR.txtName);
    }

    public String getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }
}
