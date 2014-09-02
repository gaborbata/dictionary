/*
 * Metis Dictionary
 *
 * Copyright (c) 2008-2012 Gabor Bata
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package metis.dictionary.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import metis.dictionary.ui.action.DictionaryAction;
import metis.dictionary.util.ResourceUtils;

/**
 * Dictionary application frame.
 *
 * @author Gabor_Bata
 *
 */
public final class DictionaryFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    /** Indicator for language 1. */
    private static final String LANG1_PROPERTY = "lang1";

    /** Indicator for language 2. */
    private static final String LANG2_PROPERTY = "lang2";

    /** Border for buttons. */
    private static final Border BORDER_BUTTON = BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(new Color(0xffffff), new Color(0x000000)),
            BorderFactory.createEmptyBorder(2, 4, 2, 4));

    /** Border for buttons. */
    private static final Border BORDER_INPUT = BorderFactory.createEtchedBorder(new Color(0xffffff), new Color(0x000000));

    /** The dictionary frame instance. */
    private static volatile DictionaryFrame INSTANCE;

    /** Actions. */
    private final Map<String, JButton> buttons = new LinkedHashMap<String, JButton>();

    /** Application properties */
    private final Properties properties;

    /** Status panel. */
    private final StatusPanel statusPanel = new StatusPanel();

    /** Dictionary table */
    private final DictionaryTable table = new DictionaryTable();

    /** Which language is selected. */
    private boolean firstLanguageSelected = false;

    /** Language label. */
    private final JLabel languageLabel = new JLabel("Word:");

    /** Input text. */
    private final JTextField inputField = new JTextField();

    /** Search command name. */
    public static final String COMMAND_SEARCH = "search";

    /** Change command name. */
    public static final String COMMAND_CHANGE = "change";

    /** Help command name. */
    public static final String COMMAND_HELP = "help";

    /** Exit command name. */
    public static final String COMMAND_EXIT = "exit";


    /** Creates the dictionary frame instance. */
    private DictionaryFrame() {
        super("Metis Dictionary");
        try {
            setIconImage(ResourceUtils.getIcon("book").getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // creating actions
        this.buttons.put(COMMAND_SEARCH, new JButton(new DictionaryAction(null, COMMAND_SEARCH, KeyEvent.VK_S)));
        this.buttons.put(COMMAND_CHANGE, new JButton(new DictionaryAction(null, COMMAND_CHANGE, KeyEvent.VK_C)));
        this.buttons.put(COMMAND_HELP, new JButton(new DictionaryAction("Help", COMMAND_HELP, KeyEvent.VK_H)));
        this.buttons.put(COMMAND_EXIT, new JButton(new DictionaryAction("Exit", COMMAND_EXIT, KeyEvent.VK_X)));

        this.properties = ResourceUtils.loadProperties("dictionary.prop");
        changeLanguage();

        this.statusPanel.setText("Metis Dictionary v0.3.8 Copyright (c) 2008-2012 G\u00e1bor Bata");

        // initialize north
        this.inputField.setBorder(BORDER_INPUT);
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        topPanel.add(this.languageLabel, BorderLayout.WEST);
        topPanel.add(this.inputField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 0, 5, 5));
        for (JButton button : this.buttons.values()) {
            button.setBorder(BORDER_BUTTON);
            buttonPanel.add(button);
        }
        getRootPane().setDefaultButton(this.buttons.get(COMMAND_SEARCH));

        topPanel.add(buttonPanel, BorderLayout.EAST);
        getContentPane().add(topPanel, BorderLayout.NORTH);

        // initialize center
        JScrollPane scrollPane = new JScrollPane(this.table);
        scrollPane.getViewport().setBackground(new Color(0xffffff));
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // initialize south
        getContentPane().add(this.statusPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(650, 500);
        setMinimumSize(new Dimension(400, 200));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /** Gets the dictionary frame instance. */
    public static DictionaryFrame getInstance() {
        if (INSTANCE == null) {
            synchronized (DictionaryFrame.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DictionaryFrame();
                }
            }
        }
        return INSTANCE;
    }

    /** Changes the language of search */
    public void changeLanguage() {
        this.firstLanguageSelected = !this.firstLanguageSelected;
        String lang1;
        String lang2;
        if (this.firstLanguageSelected) {
            lang1 = this.properties.getProperty(LANG1_PROPERTY, "");
            lang2 = this.properties.getProperty(LANG2_PROPERTY, "");
        } else {
            lang1 = this.properties.getProperty(LANG2_PROPERTY, "");
            lang2 = this.properties.getProperty(LANG1_PROPERTY, "");
        }
        this.table.getColumnModel().getColumn(0).setHeaderValue(lang1);
        this.table.getColumnModel().getColumn(1).setHeaderValue(lang2);
        this.table.clearTable();
        this.table.updateUI();

        this.languageLabel.setIcon(ResourceUtils.getIcon(this.firstLanguageSelected ? LANG1_PROPERTY : LANG2_PROPERTY));
        this.languageLabel.setToolTipText("Language: " + lang1);

        this.buttons.get(COMMAND_SEARCH).setToolTipText("Search in " + lang1);
        this.buttons.get(COMMAND_CHANGE).setToolTipText("Change language to " + lang2);

        this.statusPanel.setText(" ");
    }

    /**
     * Sets the state of the components on the application form and changes the
     * progress indicator.
     *
     * @param processing sets the enabled/diabled state
     */
    public void setProcessingState(final boolean processing) {
        for (JButton button : this.buttons.values()) {
            button.setEnabled(!processing);
        }
        this.table.setEnabled(!processing);
        this.inputField.setEnabled(!processing);
        this.statusPanel.setProcessingState(processing);
    }

    /**
     * Gets the dictionary table.
     *
     * @return dictionary table
     */
    public DictionaryTable getTable() {
        return this.table;
    }

    /**
     * Returns whether the first language is selected or not.
     *
     * @return <code>true</code> if the first language is selected, otherwise <code>false</code>
     */
    public boolean isFirstLanguageSelected() {
        return this.firstLanguageSelected;
    }

    /**
     * Gets the input field.
     *
     * @return inputField field
     */
    public JTextField getInputField() {
        return this.inputField;
    }

    /**
     * Gets the status panel component.
     *
     * @return status panel component
     */
    public StatusPanel getStatusPanel() {
        return this.statusPanel;
    }
}
