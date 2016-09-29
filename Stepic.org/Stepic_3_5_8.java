class Stepic_3_5_8 {

    public static void main(String[] args) {
        new Stepic_3_5_8().test();
    }

    void test() {
        byte[] data = {65, 66, 67, 68, 69};
        AsciiCharSequence acs = new AsciiCharSequence(data);
        System.out.println(acs);
        System.out.println(acs.length());
        System.out.println(acs.charAt(2));
        System.out.println(acs.subSequence(2, 4));
    }
    
    /*
     * Implement a class AsciiCharSequence, which
     * - implements the interface java.lang.CharSequence
     * - have a constructor that takes an array of bytes
     * - defines the methods length(), charAt(), subSequence() and toString()
     */

    class AsciiCharSequence implements CharSequence {
        private byte[] data;

        public AsciiCharSequence(byte[] data) {
            this.data = data;
        }

        @Override
        public int length() {
            return data.length;
        }

        @Override
        public char charAt(int index) {
            return (char) (data[index] & 0xff);
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            int length = end - start;
            byte[] bytes = new byte[length];
            for (int i = 0, j = start; i < length; i++, j++) {
                bytes[i] = data[j];
            }
            return new AsciiCharSequence(bytes);
        }

        @Override
        public String toString() {
            return new String(data);
        }
    }
}