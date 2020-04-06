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

package metis.dictionary.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 * Dictionary table for showing search results.
 *
 * @author Gabor_Bata
 *
 */
public class DictionaryTable extends JTable {

    private static final long serialVersionUID = 1L;

    /** Alternate row color. */
    public static final Color ALTERNATIVE_COLOR = new Color(0xeeeeee);

    /** Creates a new dictionary table. */
    public DictionaryTable() {
        super(0, 2);
        setShowHorizontalLines(false);
        setRowHeight(getRowHeight() + getRowMargin() + 2);
        setRowMargin(0);
        getTableHeader().setReorderingAllowed(false);

        Action tabOutAction = new AbstractAction() {
            private static final long serialVersionUID = 2L;

            @Override
            public void actionPerformed(ActionEvent ae) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
            }
        };

        getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tabOut");
        getActionMap().put("tabOut", tabOutAction);
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        if (!isCellSelected(row, column)) {
            if (row % 2 == 0) {
                component.setBackground(ALTERNATIVE_COLOR);
            } else {
                component.setBackground(getBackground());
            }
        }
        return component;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    /**
     * Fills the table content.
     *
     * @param rows row data
     */
    public void fillTable(final List<String[]> rows) {
        if (rows == null) {
            return;
        }
        clearTable();
        DefaultTableModel model = (DefaultTableModel) getModel();
        for (String[] rowData : rows) {
            model.addRow(rowData);
        }
    }

    /** Clears content of the table. */
    public void clearTable() {
        clearSelection();
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.getDataVector().clear();
    }
}
