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

package metis.dictionary.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import metis.dictionary.regex.Automaton;
import metis.dictionary.regex.RegExp;
import metis.dictionary.regex.RunAutomaton;

/**
 * An engine that performs match operations on a character sequence by interpreting a pattern.
 *
 * @author Gabor_Bata
 *
 */
public class MatcherAdapter {

    /** Automaton used for pattern matching. */
    private final RunAutomaton automaton;

    /**
     * Creates a new pattern matcher instance.
     *
     * @param pattern the regular expression pattern to match
     */
    public MatcherAdapter(final String pattern) {
        RegExp regExp = new RegExp(pattern, RegExp.NONE);
        this.automaton = new RunAutomaton(ignoreCase(regExp.toAutomaton(), pattern));
    }

    /**
     * Attempts to match the entire input sequence against the pattern.
     *
     * @param input The character sequence to be matched
     * @return <code>true</code> if, and only if, the entire input sequence matches this matcher's pattern
     */
    public boolean matches(final String input) {
        return this.automaton.run(input);
    }

    /**
     * Constructs automaton which accepts the same strings as the given automaton
     * but ignores upper/lower case of characters contained in the pattern.
     *
     * @param a an automaton
     * @return an automaton which ignores upper/lower case characters
     */
    private Automaton ignoreCase(final Automaton a, final String pattern) {
        Map<Character, Set<Character>> map = new HashMap<Character, Set<Character>>();
        for (char ch : pattern.toCharArray()) {
            if (Character.isLetter(ch) && !map.containsKey(ch)) {
                Set<Character> ws = new HashSet<Character>();
                char upper = Character.toUpperCase(ch);
                char lower = Character.toLowerCase(ch);
                ws.add(upper);
                ws.add(lower);
                map.put(upper, ws);
                map.put(lower, ws);
            }
        }
        return a.subst(map);
    }
}
