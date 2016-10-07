public static class UntrustworthyMailWorker implements MailService {
    MailService[] mailServices = null;
    RealMailService realMailService = new RealMailService();
    public UntrustworthyMailWorker(MailService[] mailServices){
        this.mailServices = mailServices;
    }
    public RealMailService getRealMailService(){
        return this.realMailService;
    }
    @Override
    public Sendable processMail(Sendable mail)  {
        Sendable someMail = mail;
        for (int i = 0; i < this.mailServices.length; i++) {
            someMail = this.mailServices[i].processMail(someMail);
        }
        return realMailService.processMail(someMail);
    }
}

public static class Spy implements MailService {
    protected static Logger spy = null;
    public Spy(Logger logger) {
        spy = logger;
    }
    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailMessage) {
            if (mail.getFrom().equals(Person.AUSTIN_POWERS) || mail.getTo().equals(Person.AUSTIN_POWERS)) {
                spy.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\"", new Object[]{mail.getFrom(), mail.getTo(), ((MailMessage) mail).getMessage()});
            } else spy.log(Level.INFO, "Usual correspondence: from {0} to {1}", new Object[]{mail.getFrom(), mail.getTo()});
        }
        return mail;
    }
}

public static class Thief implements MailService {
    protected int stolenValue = 0;
    protected int minimalCost = 0;
    public Thief(int minimalCost) {
        this.minimalCost = minimalCost;
    }
    public int getStolenValue() {
        return stolenValue;
    }
    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailPackage) {
            if (((MailPackage) mail).getContent().getPrice() >= minimalCost) {
                stolenValue += ((MailPackage) mail).getContent().getPrice();
                return new MailPackage(mail.getFrom(), mail.getTo(), new Package("stones instead of "
                        + ((MailPackage) mail).getContent().getContent(), 0));
            }
            else return mail;
        } else return mail;
    }
}

public static class Inspector implements MailService {
    @Override
    /*throws IllegalPackageException, StolenPackageException*/
    public Sendable processMail(Sendable mail) throws IllegalPackageException,StolenPackageException {
        if (mail instanceof MailPackage) {
            if (((MailPackage) mail).getContent().getContent().equals(Person.WEAPONS) || ((MailPackage) mail).getContent().getContent().equals(Person.BANNED_SUBSTANCE)) {
                throw new IllegalPackageException();
            }
            else if (((MailPackage) mail).getContent().getContent().contains("stones")){
                throw new StolenPackageException();
            }
        }
        return mail;
    }
}

public static class StolenPackageException extends RuntimeException {
    public StolenPackageException(){}
}

public static class IllegalPackageException extends RuntimeException {
    public IllegalPackageException(){}
}

public static class Person {
    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";
}