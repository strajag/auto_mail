package com.strajag.auto_mail.gui.logpanel;

import com.strajag.auto_mail.Settings;
import com.strajag.auto_mail.gui.GUI;
import com.strajag.auto_mail.gui.logpanel.components.BottomPanel;
import com.strajag.auto_mail.gui.logpanel.components.TopPanel;
import com.strajag.custom_frame.components.Panel;

import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogPanel extends Panel {

    public GUI gui;

    public TopPanel topPanel;
    public BottomPanel bottomPanel;

    public LogPanel(GUI gui) {
        this.gui = gui;

        topPanel = new TopPanel(this);
        bottomPanel = new BottomPanel(this);

        add(topPanel);
        add(bottomPanel, BorderLayout.SOUTH);

        gui.logPanelFrame.titleBarPanel.leftPanel.menuButton.addActionListener(e -> gui.dataPanelFrame.setVisible(true));
        gui.logPanelFrame.titleBarPanel.rightPanel.exitButton.addActionListener(actionEvent -> { gui.logPanelFrame.setVisible(false); gui.exit(); });
    }

    public void append(String string)
    {
        appendToPane(new SimpleDateFormat("[dd.MM.yyyy HH:mm:ss]").format(Calendar.getInstance().getTime()), Settings.Colors.borderTitleFontColor);
        appendToPane("\n", Settings.Colors.fontColor);
        appendToPane(string, Settings.Colors.fontColor);
        appendToPane("\n", Settings.Colors.fontColor);
    }

    private void appendToPane(String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, Settings.Fonts.font, "SansSerif");
        //aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = topPanel.logTextPane.getDocument().getLength();
        topPanel.logTextPane.setCaretPosition(len);
        topPanel.logTextPane.setCharacterAttributes(aset, false);
        topPanel.logTextPane.replaceSelection(msg);
    }
}
