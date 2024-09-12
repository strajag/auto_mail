package com.strajag.auto_mail.gui;

import com.strajag.auto_mail.Settings;
import com.strajag.auto_mail.gui.logpanel.LogPanel;
import com.strajag.auto_mail.application.DataManager;
import com.strajag.auto_mail.gui.datapanel.DataPanel;
import com.strajag.custom_frame.components.frame.Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUI {

    public DataManager dataManager;

    public Settings settings;

    public Frame dataPanelFrame;
    public Frame logPanelFrame;

    public DataPanel dataPanel;
    public LogPanel logPanel;

    public GUI(DataManager dataManager) {
        this.dataManager = dataManager;

        dataPanelFrame = new Frame("auto_mail");
        logPanelFrame = new Frame("auto_mail");

        dataPanel = new DataPanel(this);
        dataPanelFrame.setMinimumSize(new Dimension(650, 600));
        dataPanelFrame.setSize(new Dimension(1300, 800));
        dataPanelFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dataPanelFrame.setComponent(dataPanel);
        dataPanelFrame.setLocationRelativeTo(null);

        logPanel = new LogPanel(this);
        logPanelFrame.setMinimumSize(new Dimension(600, 600));
        logPanelFrame.setSize(new Dimension(700, 800));
        logPanelFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        logPanelFrame.setLocationRelativeTo(null);
        logPanelFrame.setComponent(logPanel);

        dataPanel.setData();

        settings = new Settings();

        Image image = null;
        try {
            image = ImageIO.read(new File("./data/AutoMailIcon.png"));
            settings.readSettings();
        } catch (Exception exception) { exception.printStackTrace(); dataPanelFrame.throwException(exception); }
        dataPanelFrame.setIconImage(image);
        logPanelFrame.setIconImage(image);

        dataManager.setGUI(this);
    }

    public void exit() {
        if (logPanelFrame.isVisible() || dataPanelFrame.isVisible()) { return; }
        try { settings.writeSettings(); } catch (Exception exception) { dataPanelFrame.throwException(exception); }
        System.exit(1);
    }
}
