package routesystem.structure;

public enum Routes {
    HAMBURG_LUENBURG(49),
    HAMBURG_BERLIN(255),
    HAMBURG_FRANKFURT(398),
    HAMBURG_STUTTGART(571),
    HAMBURG_MUENCHEN(594),
    LUENBURG_BERLIN(229),
    LUENBURG_FRANKFURT(380),
    LUENBURG_STUTTGART(553),
    LUENBURG_MUENCHEN(578),
    BERLIN_FRANKFURT(424),
    BERLIN_STUTTGART(596),
    BERLIN_MUENCHEN(586),
    FRANKFURT_STUTTGART(207),
    FRANKFURT_MUENCHEN(308),
    STUTTGART_MUENCHEN(215),
	MUENCHEN_HAMBURG(215);
	
    
    private final int distance;
    
    private Routes(int distance) {
        this.distance = distance;
    }
    
    public int getDistance() {
        return distance;
    }
}