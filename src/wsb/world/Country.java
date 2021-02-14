package wsb.world;

public enum Country {
    POLAND("Polish", "PL", 585.7f),
    AUSTRIA("Austrian", "AT", 455.3f),
    GERMANY("German", "DE", 3948),
    NORWAY("Nordic", "NO", 434.2f);

    private final String language;
    private final String code;
    private final float gdp; // in billion USD
    private static final float plnRate = .27f;

    Country(String language, String code, float gdp) {
        this.language = language;
        this.code = code;
        this.gdp = gdp;
    }

    public String getLanguage() {
        return language;
    }

    public String getCode() {
        return code;
    }

    public float getGdp() {
        return gdp;
    }

    public float getGdpInPln() {
        return gdp * plnRate;
    }
}
