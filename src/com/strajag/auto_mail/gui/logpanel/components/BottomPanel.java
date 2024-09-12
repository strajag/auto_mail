package com.strajag.auto_mail.gui.logpanel.components;

import com.strajag.auto_mail.gui.logpanel.LogPanel;
import com.strajag.custom_frame.components.Button;
import com.strajag.custom_frame.components.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends Panel implements ActionListener {

    public LogPanel logPanel;

    public Button startStopButton;
    public Button clearButton;

    public BottomPanel(LogPanel logPanel) {
        this.logPanel = logPanel;

        startStopButton = new Button("START");
        clearButton = new Button("CLEAR");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        add(startStopButton);
        gbc.gridy = 1;
        add(clearButton, gbc);

        startStopButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            JButton pressedButton = (JButton) actionEvent.getSource();
            if (pressedButton.equals(startStopButton)) {
                if (logPanel.gui.dataManager.mailSenderRunnable.isRunning)
                {
                    logPanel.gui.dataManager.stop();
                    while (logPanel.gui.dataManager.mailSenderRunnable.isRunning) Thread.onSpinWait();
                } else
                {
                    logPanel.gui.dataManager.start();

                    logPanel.gui.dataPanel.bottomPanel.setTexts();

                    logPanel.gui.dataPanelFrame.setEnabled(false);
                }
            } else if (pressedButton.equals(clearButton)) {
                logPanel.topPanel.logTextPane.setText("");
                logPanel.gui.dataManager.log = "";
            }
        } catch (Exception exception) {
            logPanel.gui.dataPanelFrame.throwException(exception);
        }
    }
}
