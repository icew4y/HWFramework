package jcifs.smb;

class SmbComWriteAndX extends AndXServerMessageBlock {
    private static final int CLOSE_BATCH_LIMIT = 0;
    private static final int READ_ANDX_BATCH_LIMIT = 0;
    private byte[] b;
    private int dataLength;
    private int dataOffset;
    private int fid;
    private int off;
    private long offset;
    private int pad;
    private int remaining;
    int writeMode;

    static {
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: jcifs.smb.SmbComWriteAndX.<clinit>():void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:113)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:256)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
Caused by: jadx.core.utils.exceptions.DecodeException:  in method: jcifs.smb.SmbComWriteAndX.<clinit>():void
	at jadx.core.dex.instructions.InsnDecoder.decodeInsns(InsnDecoder.java:46)
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:98)
	... 5 more
Caused by: java.lang.IllegalArgumentException: bogus opcode: 0073
	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1197)
	at com.android.dx.io.OpcodeInfo.getFormat(OpcodeInfo.java:1212)
	at com.android.dx.io.instructions.DecodedInstruction.decode(DecodedInstruction.java:72)
	at jadx.core.dex.instructions.InsnDecoder.decodeInsns(InsnDecoder.java:43)
	... 6 more
*/
        /*
        // Can't load method instructions.
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.smb.SmbComWriteAndX.<clinit>():void");
    }

    SmbComWriteAndX() {
        super(null);
        this.command = (byte) 47;
    }

    SmbComWriteAndX(int fid, long offset, int remaining, byte[] b, int off, int len, ServerMessageBlock andx) {
        super(andx);
        this.fid = fid;
        this.offset = offset;
        this.remaining = remaining;
        this.b = b;
        this.off = off;
        this.dataLength = len;
        this.command = (byte) 47;
    }

    void setParam(int fid, long offset, int remaining, byte[] b, int off, int len) {
        this.fid = fid;
        this.offset = offset;
        this.remaining = remaining;
        this.b = b;
        this.off = off;
        this.dataLength = len;
        this.digest = null;
    }

    int getBatchLimit(byte command) {
        if (command == 46) {
            return READ_ANDX_BATCH_LIMIT;
        }
        if (command == 4) {
            return CLOSE_BATCH_LIMIT;
        }
        return 0;
    }

    int writeParameterWordsWireFormat(byte[] dst, int dstIndex) {
        int start = dstIndex;
        this.dataOffset = (dstIndex - this.headerStart) + 26;
        this.pad = (this.dataOffset - this.headerStart) % 4;
        this.pad = this.pad == 0 ? 0 : 4 - this.pad;
        this.dataOffset += this.pad;
        ServerMessageBlock.writeInt2((long) this.fid, dst, dstIndex);
        dstIndex += 2;
        ServerMessageBlock.writeInt4(this.offset, dst, dstIndex);
        int i = 0;
        int dstIndex2 = dstIndex + 4;
        while (i < 4) {
            dstIndex = dstIndex2 + 1;
            dst[dstIndex2] = (byte) -1;
            i++;
            dstIndex2 = dstIndex;
        }
        ServerMessageBlock.writeInt2((long) this.writeMode, dst, dstIndex2);
        dstIndex = dstIndex2 + 2;
        ServerMessageBlock.writeInt2((long) this.remaining, dst, dstIndex);
        dstIndex += 2;
        dstIndex2 = dstIndex + 1;
        dst[dstIndex] = (byte) 0;
        dstIndex = dstIndex2 + 1;
        dst[dstIndex2] = (byte) 0;
        ServerMessageBlock.writeInt2((long) this.dataLength, dst, dstIndex);
        dstIndex += 2;
        ServerMessageBlock.writeInt2((long) this.dataOffset, dst, dstIndex);
        dstIndex += 2;
        ServerMessageBlock.writeInt4(this.offset >> 32, dst, dstIndex);
        return (dstIndex + 4) - start;
    }

    int writeBytesWireFormat(byte[] dst, int dstIndex) {
        int start = dstIndex;
        while (true) {
            int i = this.pad;
            this.pad = i - 1;
            if (i > 0) {
                int dstIndex2 = dstIndex + 1;
                dst[dstIndex] = (byte) -18;
                dstIndex = dstIndex2;
            } else {
                System.arraycopy(this.b, this.off, dst, dstIndex, this.dataLength);
                return (dstIndex + this.dataLength) - start;
            }
        }
    }

    int readParameterWordsWireFormat(byte[] buffer, int bufferIndex) {
        return 0;
    }

    int readBytesWireFormat(byte[] buffer, int bufferIndex) {
        return 0;
    }

    public String toString() {
        return new String("SmbComWriteAndX[" + super.toString() + ",fid=" + this.fid + ",offset=" + this.offset + ",writeMode=" + this.writeMode + ",remaining=" + this.remaining + ",dataLength=" + this.dataLength + ",dataOffset=" + this.dataOffset + "]");
    }
}
