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

import java.util.List;
import java.util.Locale;

import javax.swing.SwingWorker;

import metis.dictionary.ui.DictionaryFrame;
import metis.dictionary.ui.HelpDialog;
import metis.dictionary.util.Searcher;

/**
 * Class for handling action commands.
 *
 * @author Gabor_Bata
 *
 */
public class CommandHandler {

    /**
     * Handles action commands.
     *
     * @param command action command
     */
    public static void handleCommand(final String command) {
        final DictionaryFrame frame = DictionaryFrame.getInstance();

        if (DictionaryFrame.COMMAND_SEARCH.equals(command)) {
            final String inputText = frame.getInputField().getText();
            if (inputText == null || inputText.trim().isEmpty()) {
                return;
            }
            frame.getTable().clearTable();
            frame.setProcessingState(true);
            final boolean isFirstLanguage = frame.isFirstLanguageSelected();
            final long start = System.currentTimeMillis();

            SwingWorker<List<String[]>, Void> worker = new SwingWorker<List<String[]>, Void>() {
                @Override
                protected List<String[]> doInBackground() throws Exception {
                    List<String[]> results;
                    try {
                        results = Searcher.search(inputText, isFirstLanguage);
                    } catch (Throwable t) {
                        throw new Exception(t.getMessage());
                    }
                    return results;
                }

                @Override
                protected void done() {
                    super.done();
                    frame.setProcessingState(false);
                    try {
                        List<String[]> results = get();
                        String status = ".";
                        if (results == null) {
                            return;
                        } else if (!results.isEmpty()) {
                            long end = System.currentTimeMillis();
                            status = String.format(Locale.US, " in %.2f seconds.", (end - start) / 1000.0);
                        }
                        frame.getTable().fillTable(results);
                        frame.getStatusPanel().setText(results.size() + " record(s) found" + status);
                        frame.getTable().requestFocus();
                    } catch (Exception e) {
                        String msg;
                        if (e.getCause() != null) {
                            msg = e.getCause().getMessage();
                        } else {
                            msg = e.getMessage();
                        }
                        frame.getStatusPanel().setText("Error: " + msg);
                    }
                }
            };

            worker.execute();
        } else if (DictionaryFrame.COMMAND_CHANGE.equals(command)) {
            frame.changeLanguage();
            handleCommand(DictionaryFrame.COMMAND_SEARCH);
        } else if (DictionaryFrame.COMMAND_HELP.equals(command)) {
            new HelpDialog(frame);
        } else if (DictionaryFrame.COMMAND_EXIT.equals(command)) {
            System.exit(0);
        }
    }
}
