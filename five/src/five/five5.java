package five;
import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.util.Arrays;

 

import static java.lang.StrictMath.*;
public class five5 {
	public static final int N = 23;

	 

    int cnt, mod;

    int[] f = new int[N], cand = new int[N], sz = new int[N];

    int[][] bits = new int[1<<12][], bits0 = new int[1<<12][];

 

    boolean contains(int bit, int maska) {

        return ((1<<bit) & maska) != 0;

    }

 

    long[] tk = new long[N];

 

    void gen(int i, int n, int s, int me, int mv, long kf) {

        if (i == n+1) {

            cnt += mv*tk[me]%mod*kf%mod;

            if (cnt >= mod) cnt -= mod;

            return;

        } 

 

        if (sz[i-1] < me) {

            f[i] = 0;

            cand[i] = 1 << 1;

            sz[i] = 1;

            gen(i+1, n, s, me, mv, kf*(me-sz[i-1]));

        }

 

        if (me < s) {

            f[i] = 0;

            cand[i] = 1 << 1;

            sz[i] = 1;

            gen(i+1, n, s, me+1, mv, kf);

        }

 

        int c1 = cand[i-1]&4095;

        for (int v: bits[c1]) {

            f[i] = v;

            sz[i] = sz[v];

            cand[i] = cand[v];        

            if (contains(f[v+1], cand[i])) {

                --sz[i];

                cand[i] ^= 1 << f[v+1];

            }

            if (!contains(v+1, cand[i])) {

                ++sz[i];

                cand[i] |= 1 << v+1;

            }

            gen(i+1, n, s, me, max(mv, v), kf);

        } 

 

        int c2 = cand[i-1]>>12;

        for (int v0: bits0[c2]) {

            int v = 12+v0;

            f[i] = v;

            sz[i] = sz[v];

            cand[i] = cand[v];

            if (contains(f[v+1], cand[i])) {

                cand[i] ^= 1 << f[v+1];

                --sz[i];

            }

            if (!contains(v+1, cand[i])) {

                cand[i] |= 1 << v+1;

                ++sz[i];

            }

            gen(i+1, n, s, me, max(mv, v), kf);

        }

    }

 

    public void run(InputReader reader, OutputWriter writer) throws IOException {

        bits0[0] = bits[0] = new int[0];

        for (int maska = 1; maska < 1<<12; ++maska) {

            int bc = Integer.bitCount(maska);

            bits0[maska] = new int[bc];

            int j = maska&1;

            bits[maska] = new int[bc - j];

            if (j != 0) bits0[maska][0] = 0;

            for (int bit = 1, i = 0; bit < 12; ++bit) {

                if (((1<<bit) & maska) != 0) {

                    bits[maska][i++] = bits0[maska][j++] = bit;

                }

            }

        }

        f[0] = -1;

        f[1] = 0;

        cand[1] = 1<<1;

        sz[1] = 1;

        cnt = 0;

        int n = 6, m = 5; 

        mod = (int) 1e9;

        n = reader.nextInt(); m = reader.nextInt(); mod = reader.nextInt();

        tk[1] = m%mod;

        for (int i = 2; i <= min(n, m); ++i) {

            tk[i] = tk[i-1]*(m-i+1)%mod;

        }

 

        gen(2, n, m, 1, 0, 1);

        writer.println(cnt);

    }

 

    public static void main(String[] args) throws IOException {

 

        try (OutputWriter writer = new OutputWriter(System.out)) {

            new five5().run(new InputReader(System.in), writer);

        }

    } 

 

}

 

class InputReader {

 

    public static final int BUFFER_SIZE = 1<<15;

 

    private InputStream stream;

 

    public InputReader(InputStream stream) {

        this.stream = stream;

        Arrays.fill(isDigit, 48, 58, true);

    }

 

    public byte next() throws IOException {

        if (cursor == BUFFER_SIZE) {

            buffer[stream.read(buffer, cursor = 0, BUFFER_SIZE)] = (byte) 0;

        }

        return buffer[cursor++];

    }

 

    public int nextInt() throws IOException {

        byte c = 0;

        while (!isDigit[c = next()]);

 

        int r = c&15;

        while (isDigit[c = next()]) {

            r = (r<<3) + (r<<1) + (c&15);

        }

 

        return r;

    }

 

    private int cursor = BUFFER_SIZE;

    private boolean[] isDigit = new boolean[1<<7];

    private byte[] buffer = new byte[BUFFER_SIZE+1];

}

 

class OutputWriter implements AutoCloseable {

 

    public static final int BUFFER_SIZE = 1<<15;

 

    public static final byte LN = (byte) 10;

    public static final byte SPACE = (byte) 32;

 

    private OutputStream stream;

 

    public OutputWriter(OutputStream stream) {

        this.stream = stream;

    }

 

    @Override

    public void close() throws IOException {

        stream.write(buffer, 0, cursor);

    }

 

    public void pc(byte c) throws IOException {

        buffer[cursor++] = c;

        if (cursor == BUFFER_SIZE) {

            stream.write(buffer, cursor = 0, BUFFER_SIZE);

        }

    }

 

    public void println(int x) throws IOException {

        int u = 0;

        do o[u++] = (int) (x%10); while ((x /= 10) != 0);

        do pc((byte) (o[--u] | 48)); while (u > 0);

        pc(LN);

    }

 

    private int o[] = new int[23];

    private int cursor = 0;

    private byte buffer[] = new byte[BUFFER_SIZE];

}
