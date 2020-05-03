package pl.fit_amam.api.enums;

public enum IngredientUnit {
    ML_OR_GRAM("ml or gram"),
    PCS("pcs");

    private String description;

    IngredientUnit( String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
