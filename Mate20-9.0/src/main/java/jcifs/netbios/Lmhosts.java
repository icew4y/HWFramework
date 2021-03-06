package jcifs.netbios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Hashtable;
import jcifs.Config;
import jcifs.smb.SmbFileInputStream;
import jcifs.util.LogStream;

public class Lmhosts {
    private static final String FILENAME = Config.getProperty("jcifs.netbios.lmhosts");
    private static final Hashtable TAB = new Hashtable();
    private static int alt;
    private static long lastModified = 1;
    private static LogStream log = LogStream.getInstance();

    public static synchronized NbtAddress getByName(String host) {
        NbtAddress byName;
        synchronized (Lmhosts.class) {
            byName = getByName(new Name(host, 32, null));
        }
        return byName;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: jcifs.netbios.NbtAddress} */
    /* JADX WARNING: Multi-variable type inference failed */
    static synchronized NbtAddress getByName(Name name) {
        NbtAddress result;
        synchronized (Lmhosts.class) {
            result = null;
            try {
                if (FILENAME != null) {
                    File f = new File(FILENAME);
                    long lm = f.lastModified();
                    if (lm > lastModified) {
                        lastModified = lm;
                        TAB.clear();
                        alt = 0;
                        populate(new FileReader(f));
                    }
                    result = TAB.get(name);
                }
            } catch (FileNotFoundException fnfe) {
                LogStream logStream = log;
                if (LogStream.level > 1) {
                    log.println("lmhosts file: " + FILENAME);
                    fnfe.printStackTrace(log);
                }
            } catch (IOException ioe) {
                LogStream logStream2 = log;
                if (LogStream.level > 0) {
                    ioe.printStackTrace(log);
                }
            }
        }
        return result;
    }

    static void populate(Reader r) throws IOException {
        String line;
        BufferedReader br = new BufferedReader(r);
        while (true) {
            String line2 = br.readLine();
            if (line2 != null) {
                String line3 = line2.toUpperCase().trim();
                if (line3.length() != 0) {
                    if (line3.charAt(0) == '#') {
                        if (line3.startsWith("#INCLUDE ")) {
                            String url = "smb:" + line3.substring(line3.indexOf(92)).replace('\\', '/');
                            if (alt > 0) {
                                try {
                                    populate(new InputStreamReader(new SmbFileInputStream(url)));
                                    alt--;
                                    do {
                                        line = br.readLine();
                                        if (line == null) {
                                            break;
                                        }
                                    } while (!line.toUpperCase().trim().startsWith("#END_ALTERNATE"));
                                } catch (IOException ioe) {
                                    log.println("lmhosts URL: " + url);
                                    ioe.printStackTrace(log);
                                }
                            } else {
                                populate(new InputStreamReader(new SmbFileInputStream(url)));
                            }
                        } else if (line3.startsWith("#BEGIN_ALTERNATE")) {
                            alt++;
                        } else if (line3.startsWith("#END_ALTERNATE") && alt > 0) {
                            alt--;
                            throw new IOException("no lmhosts alternate includes loaded");
                        }
                    } else if (Character.isDigit(line3.charAt(0))) {
                        char[] data = line3.toCharArray();
                        char c = '.';
                        int i = 0;
                        int ip = 0;
                        while (i < data.length && c == '.') {
                            int b = 0;
                            while (i < data.length) {
                                c = data[i];
                                if (c < '0' || c > '9') {
                                    break;
                                }
                                b = ((b * 10) + c) - 48;
                                i++;
                            }
                            ip = (ip << 8) + b;
                            i++;
                        }
                        while (i < data.length && Character.isWhitespace(data[i])) {
                            i++;
                        }
                        int j = i;
                        while (j < data.length && !Character.isWhitespace(data[j])) {
                            j++;
                        }
                        Name name = new Name(line3.substring(i, j), 32, null);
                        TAB.put(name, new NbtAddress(name, ip, false, 0, false, false, true, true, NbtAddress.UNKNOWN_MAC_ADDRESS));
                    }
                }
            } else {
                return;
            }
        }
    }
}
