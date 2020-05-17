package ir.hamyiar.hamyshow.model;

public enum MPARating {
    G("G"), PG("PG"), PG13("PG-13"), R("R"), NC17("NC-17");

    private final String mpa;

    MPARating(String mpa) {
        this.mpa = mpa;
    }

    public String getMpa() {
        return mpa;
    }
}
