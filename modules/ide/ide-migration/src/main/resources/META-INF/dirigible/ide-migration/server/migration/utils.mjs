export class Utils {
    static arrayCopy(src, srcIndex, dest, destIndex, length) {
        dest.splice(destIndex, length, ...src.slice(srcIndex, srcIndex + length));
    }

    static intToByteArray(int) {
        return org.eclipse.dirigible.api.v3.io.BytesFacade.intToByteArray(int, "LITTLE_ENDIAN");
    }

    static byteArrayToInt(arr) {
        return org.eclipse.dirigible.api.v3.io.BytesFacade.byteArrayToInt(arr, "LITTLE_ENDIAN");
    }
}
