/*
 * Metis Dictionary
 *
 * Copyright (c) 2008-2020 Gabor Bata
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

package metis.dictionary.ui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import metis.dictionary.util.ResourceUtils;

/**
 * Action class for dictionary frame.
 *
 * @author Gabor_Bata
 *
 */
public class DictionaryAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new dictionary action.
     *
     * @param text title of the action that appears on UI
     * @param command action command
     * @param mnemonic accelerator key
     */
    public DictionaryAction(final String text, final String command, final Integer mnemonic) {
        super(null, ResourceUtils.getIcon(command));
        if (text != null) {
            putValue(SHORT_DESCRIPTION, text);
        }
        putValue(ACTION_COMMAND_KEY, command);
        if (mnemonic != null) {
            putValue(MNEMONIC_KEY, mnemonic);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        CommandHandler.handleCommand(event.getActionCommand());
    }
}
