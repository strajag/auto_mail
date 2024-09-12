package com.strajag.auto_mail.gui.logpanel.components;

import com.strajag.auto_mail.Settings;
import com.strajag.auto_mail.gui.logpanel.LogPanel;
import com.strajag.custom_frame.components.Panel;
import com.strajag.custom_frame.components.ScrollPane;
import com.strajag.custom_frame.components.TextPane;

public class TopPanel extends Panel {

    public LogPanel logPanel;

    public TextPane logTextPane;
    public ScrollPane logScrollPane;

    public TopPanel(LogPanel logPanel) {
        this.logPanel = logPanel;

        logTextPane = new TextPane();
        logScrollPane = new ScrollPane(logTextPane);

        logScrollPane.setBorder(Settings.Borders.getBevelTitleBorder("LOG"));

        add(logScrollPane);
    }
}
