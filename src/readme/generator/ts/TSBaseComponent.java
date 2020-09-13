package readme.generator.ts;

import readme.generator.RGComponent;

public abstract class TSBaseComponent implements RGComponent {
    private boolean export;

    public void setExport(boolean export){this.export = export;}

    public boolean isExport() {
        return export;
    }
}
