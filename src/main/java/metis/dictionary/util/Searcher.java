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

package metis.dictionary.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Searcher utility class.
 *
 * @author Gabor_Bata
 *
 */
public final class Searcher {

    /** Word boundaries. */
    private static final String WORD_BOUNDARY = "[ !\\?\\.,:;\"'\\)\\(/]";

    /** Dictionary encoding. */
    private static final String ENCODING = "UTF-8";

    /** Maximum number of result rows. */
    private static final int SEARCH_LIMIT = 1000;

    /** Result comparator. */
    private static final Comparator<String[]> RESULT_COMPARATOR = (array1, array2) -> {
        int ret;
        if (array1[0].equals(array2[0])) {
            ret = array1[1].compareToIgnoreCase(array2[1]);
        } else {
            ret = array1[0].compareToIgnoreCase(array2[0]);
        }
        return ret;
    };

    private Searcher() {
        // utility class
    }

    /**
     * Transforms the input text to make regexp usage more user-friendly.
     *
     * @param text input text based on the documentation in help.html
     */
    private static String transformInputText(final String text) {
        String ret = text;
        ret = ret.replaceAll("\\\\", "\\\\\\\\")
            .replaceAll("\\*+", "*")
            .replaceAll("\\.", "\\\\.")
            .replaceAll("\\+", "\\\\+")
            .replaceAll("\\&", "\\\\&")
            .replaceAll("\\$", "\\\\\\$")
            .replaceAll("\\{", "\\\\{")
            .replaceAll("\\}", "\\\\}")
            .replaceAll("\\?", ".")
            .replaceAll("\\*", ".*");
        return ret;
    }

    /**
     * Searches records in dictionary data.
     *
     * @param input input text (simplified regexp)
     * @param firstLanguage search in first or second language
     * @return list of string arrays
     * @throws Throwable if any error occurred
     */
    public static List<String[]> search(final String input, final boolean firstLanguage) throws Throwable {
        List<String[]> results = new ArrayList<>();

        String inputText = transformInputText(input).trim();
        if (inputText.isEmpty()) {
            return results;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(".*").append(WORD_BOUNDARY).append("(").append(inputText).append(")").append(WORD_BOUNDARY).append(".*");
        if (firstLanguage) {
            builder.append("::.*");
        } else {
            builder.insert(0, ".*::");
        }

        MatcherAdapter pattern = new MatcherAdapter(builder.toString());

        BufferedReader bufferedReader = null;
        try {
            InputStream is = ResourceUtils.getResourceAsStream("dictionary.data");
            bufferedReader = new BufferedReader(new InputStreamReader(is, ENCODING));
            String line;
            while ((line = bufferedReader.readLine()) != null && results.size() < SEARCH_LIMIT) {
                if (pattern.matches(line)) {
                    int index = line.indexOf(" :: ");
                    if (firstLanguage) {
                        results.add(new String[] {line.substring(1, index), line.substring(index + 4, line.length() - 1)});
                    } else {
                        results.add(new String[] {line.substring(index + 4, line.length() - 1), line.substring(1, index)});
                    }
                }
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        if (!results.isEmpty()) {
            Collections.sort(results, RESULT_COMPARATOR);
        }
        return results;
    }
}
