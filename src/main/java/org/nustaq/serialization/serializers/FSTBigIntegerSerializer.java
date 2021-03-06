package org.nustaq.serialization.serializers;

import org.nustaq.serialization.FSTBasicObjectSerializer;
import org.nustaq.serialization.FSTClazzInfo;
import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;

import java.io.IOException;
import java.math.BigInteger;

/**
 * author: nicoruti
 * submitted via #53
 *
 */
public class FSTBigIntegerSerializer  extends FSTBasicObjectSerializer {

    @Override
    public void writeObject(FSTObjectOutput out, Object toWrite, FSTClazzInfo clzInfo, FSTClazzInfo.FSTFieldInfo referencedBy,
                            int streamPosition) throws IOException {
        byte[] value = ((BigInteger) toWrite).toByteArray();
        out.writeInt(value.length);
        out.write(value);
    }

    @Override
    public Object instantiate(Class objectClass, FSTObjectInput in, FSTClazzInfo serializationInfo, FSTClazzInfo.FSTFieldInfo referencee,
                              int streamPositioin) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        byte[] buf = new byte[in.readInt()];
        in.read(buf);
        BigInteger bigInteger = new BigInteger(buf);
        in.registerObject(bigInteger,streamPositioin,serializationInfo,referencee);
        return bigInteger;
    }
}
