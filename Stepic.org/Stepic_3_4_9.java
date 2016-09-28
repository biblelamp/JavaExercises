class Stepic_3_4_9 {

    public static void main(String[] args) {
        new Stepic_3_4_9().test();
    }

    void test() {
        ComplexNumber a = new ComplexNumber(1, 1);
        ComplexNumber b = new ComplexNumber(1, 1);
        System.out.println(a.equals(b)); // must return true
        System.out.println(a.hashCode()); // must be equal to b.hashCode()
        System.out.println(b.hashCode());
    }

    /*
     * Override methods equals() and hashCode() so that the equals() comparing ComplexNumber copies
     * the contents re and im fields and hashCode() would be consistent with the implementation of equals()
     */
    final class ComplexNumber {
        private final double re;
        private final double im;

        ComplexNumber(double re, double im) {
            this.re = re;
            this.im = im;
        }

        @Override
        public int hashCode() {
            int result = 17;
            long longBits = Double.doubleToLongBits(re);
            result = 37 * result + (int)(longBits - (longBits >>> 32));
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;

            ComplexNumber other = (ComplexNumber) obj;
            if (re != other.re) return false;
            if (im != other.im) return false;
            return true;
        }

        double getRe() { return re; }
        double getIm() { return im; }
    }
}