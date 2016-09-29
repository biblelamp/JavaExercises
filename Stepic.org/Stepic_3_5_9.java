class Stepic_3_5_9 {

    public static void main(String[] args) {
        new Stepic_3_5_9().test();
    }

    void test() {
        String[] bad_words = {"shit", "fack"};
        SpamAnalyzer sa = new SpamAnalyzer(bad_words);
        NegativeTextAnalyzer nta = new NegativeTextAnalyzer();
        TooLongTextAnalyzer tlta = new TooLongTextAnalyzer(20);
        TextAnalyzer[] ta = {sa, nta, tlta};
        System.out.println(checkLabels(ta, "Good text for test."));
        System.out.println(checkLabels(ta, "Bad text for test, becouse it's shit."));
        System.out.println(checkLabels(ta, "Bad text for test, becouse it's very =("));
        System.out.println(checkLabels(ta, "Bad text for test, becouse it's longer that twenty characters."));
    }

    enum Label {
        SPAM, NEGATIVE_TEXT, TOO_LONG, OK
    }

    Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer analyzer : analyzers) {
            Label result;
            if ((result = analyzer.processText(text)) != Label.OK) {
                return result;
            }
        }
        return Label.OK;
    }

    interface TextAnalyzer {
        Label processText(String text);
    }

    abstract class KeywordAnalyzer implements TextAnalyzer {
        protected abstract String[] getKeywords();
        protected abstract Label getLabel();
        @Override
        public Label processText(String text) {
            for (String s : getKeywords()) {
                if (text.contains(s)) return getLabel();
            }
            return Label.OK;
        }
    }

    class SpamAnalyzer extends KeywordAnalyzer {
        private String[] keywords;
        SpamAnalyzer(String[] keywords) {
            this.keywords = keywords;
        }
        @Override
        protected String[] getKeywords() {
            return keywords;
        }
        @Override
        protected Label getLabel() {
            return Label.SPAM;
        }
    }

    class NegativeTextAnalyzer extends KeywordAnalyzer {
        private final String[] sadSequence = {":(", " =(", " :|"};
        @Override
        protected String[] getKeywords() {
            return sadSequence;
        }
        @Override
        protected Label getLabel() {
            return Label.NEGATIVE_TEXT;
        }
    }

    class TooLongTextAnalyzer implements TextAnalyzer {
        private int maxLength;
        TooLongTextAnalyzer(int maxLength) {
            this.maxLength = maxLength;
        }
        @Override
        public Label processText(String text) {
            return text.length() > maxLength ? Label.TOO_LONG : Label.OK;
        }
    }
}