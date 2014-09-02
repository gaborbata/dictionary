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
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import metis.dictionary.util.ResourceUtils;

/**
 * Help dialog for the dictionary application.
 *
 * @author Gabor_Bata
 *
 */
public class HelpDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;

    /** Border for buttons. */
    private static final Border BORDER_BUTTON = BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(new Color(0xffffff), new Color(0x000000)),
            BorderFactory.createEmptyBorder(3, 8, 3, 8));

    /**
     * Creates a new help dialog showing the {@code help.html} file with a License and Close button.
     *
     * @param parent parent frame
     */
    public HelpDialog(final Frame parent) {
        super(parent);
        initDialog(parent, true);
    }

    /**
     * Creates a new help dialog showing the {@code license.txt} file with a Close button.
     *
     * @param parent parent frame
     */
    private HelpDialog(final Dialog parent) {
        super(parent);
        initDialog(parent, false);
    }

    /**
     * Initializes the help dialog.
     *
     * @param parent parent component
     * @param showLicense showing the License button
     */
    private void initDialog(final Component parent, final boolean showLicense) {
        setTitle(showLicense ? "Help" : "License");
        setModal(true);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        if (showLicense) {
            String text = ResourceUtils.getResourceAsString("help.html");
            getContentPane().add(new JScrollPane(new JLabel(text)), BorderLayout.CENTER);

            JButton licenseButton = new JButton("License");
            licenseButton.setMnemonic(KeyEvent.VK_L);
            licenseButton.setActionCommand("license");
            licenseButton.addActionListener(this);
            licenseButton.setBorder(BORDER_BUTTON);
            buttonPanel.add(licenseButton);
        } else {
            String text = ResourceUtils.getResourceAsString("license.txt");
            JTextArea area = new JTextArea(text);
            area.setLineWrap(true);
            area.setWrapStyleWord(true);
            area.setEditable(false);
            area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            getContentPane().add(new JScrollPane(area), BorderLayout.CENTER);
        }

        JButton closeButton = new JButton("Close");
        closeButton.setMnemonic(KeyEvent.VK_C);
        closeButton.setActionCommand("close");
        closeButton.addActionListener(this);
        closeButton.setBorder(BORDER_BUTTON);
        getRootPane().setDefaultButton(closeButton);
        buttonPanel.add(closeButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setResizable(false);
        if (showLicense) {
            pack();
        } else {
            setSize(600, 400);
        }

        setLocationRelativeTo(parent);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if ("close".equals(command)) {
            dispose();
        } if ("license".equals(command)) {
            new HelpDialog(this);
        }
    }
}
