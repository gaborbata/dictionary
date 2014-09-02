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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.swing.ImageIcon;

/**
 * Class for loading resources from classpath.
 *
 * @author Gabor_Bata
 *
 */
public final class ResourceUtils {

    private static final String ENCODING = "UTF-8";

    private static final String RESOURCE_PATH = "resources/";

    private static final String IMAGE_EXTENSION = ".png";

    private ResourceUtils() {
        // utility class
    }

    public static InputStream getResourceAsStream(String name) {
        return ResourceUtils.class.getClassLoader().getResourceAsStream(RESOURCE_PATH + name);
    }

    public static ImageIcon getIcon(String name) {
        try {
            return new ImageIcon(ResourceUtils.class.getClassLoader().getResource(RESOURCE_PATH + name + IMAGE_EXTENSION));
        } catch (Exception e) {
            System.err.println("Image not found: " + name);
            return null;
        }
    }

    public static Properties loadProperties(String name) {
        Properties properties = new Properties();
        try {
            InputStream is = getResourceAsStream(name);
            properties.load(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getResourceAsString(String name) {
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream is = getResourceAsStream(name);
            bufferedReader = new BufferedReader(new InputStreamReader(is, ENCODING));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
