package com.strajag.auto_mail;

import com.strajag.auto_mail.application.DataManager;
import com.strajag.auto_mail.gui.GUI;

public class Main
{
    public static void main(String[] args)
    {
        try {
            new GUI(new DataManager());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}