package com.straykovsky.automail;

import com.straykovsky.automail.application.DataManager;
import com.straykovsky.automail.gui.GUI;

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