package com.example.prsrs6_glazirinng;

public class Weather {
    String date;
    String temp;
    String we;
    public Weather(String date, String temp, String we)
    {
        this.date= date;
        this.temp= temp;
        this.we= we;
    }
    public String GetDate()
    {
        return this.date;
    }
    public void SetDate(String date)
    {
        this.date= date;
    }
    public String GetUrl()
    {
        return this.we;
    }
    public String GetTemp()
    {
        return this.temp;
    }

}
