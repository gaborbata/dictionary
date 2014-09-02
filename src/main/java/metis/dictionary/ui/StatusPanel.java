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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

/**
 * Class for representing a status bar.
 *
 * @author Gabor_Bata
 *
 */
public class StatusPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    /** JLabel for holding status text. */
    private final JLabel label;

    /** Progress bar for indicating processing state. */
    private final JProgressBar progressBar;

    /** Creates a new status panel instance. */
    public StatusPanel() {
        super(new BorderLayout());
        setBorder(new EmptyBorder(2, 2, 2, 2));
        this.label = new JLabel();
        this.progressBar = new JProgressBar();
        add(this.label, BorderLayout.CENTER);
        add(this.progressBar, BorderLayout.EAST);
        setProcessingState(false);
    }

    /**
     * Sets status text.
     *
     * @param text status text
     */
    public void setText(final String text) {
        this.label.setText(text);
    }

    /**
     * Gets status text.
     *
     * @return status text
     */
    public String getText() {
        return this.label.getText();
    }

    /**
     * Sets processing state.
     *
     * @param processing processing state
     */
    public void setProcessingState(final boolean processing) {
        this.progressBar.setVisible(processing);
        this.progressBar.setIndeterminate(processing);
        setText(processing ? "Searching..." : " ");
    }
}
