public class MemberSession {
    private static MemberSession instance;

    private String account;

    private MemberSession(String account) {
        this.account = account;
    }

    public static MemberSession createInstance(String account) {
        if (instance == null) {
            instance = new MemberSession(account);
        } else {
            instance.account = account;
        }
        return instance;
    }

    public static MemberSession getInstance() {
        return instance;
    }

    public String getAccount() {
        return account;
    }
}