package com.example.prac;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    MutableLiveData<Integer> options=new MutableLiveData<>();
    MutableLiveData<Integer> rotate=new MutableLiveData<>();
    MutableLiveData<Integer> status=new MutableLiveData<>();
    MutableLiveData<Integer> color=new MutableLiveData<>();
    MutableLiveData<String> text=new MutableLiveData<>();
    MutableLiveData<Integer> size=new MutableLiveData<>();
    public void puttext(String i)
    {
        text.setValue(i);
    }

    public  MutableLiveData<String> gettext()
    {
        return text;
    }
public void putOptions(int i)
{
    options.setValue(i);
}
    public  MutableLiveData<Integer> getcolor()
    {
        return color;
    }

    public void putcolor(int i)
    {color.setValue(i); }

public  MutableLiveData<Integer> getOptions()
{
    return options;
}

    public void putrotate(int i)
    {rotate.setValue(i); }

    public  MutableLiveData<Integer> getrotate()
    {
        return rotate;
    }

    public void putSize(int i)
    {
        size.setValue(i);
    }

    public  MutableLiveData<Integer> getSize()
    {
        return size;
    }

    public void putstatus(int i)
    {
        status.setValue(i);
    }

    public  MutableLiveData<Integer> getstatus()
    {
        return status;
    }
}
