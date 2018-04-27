/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rodgortfactory;

/**
 *
 * @author Bálint
 */
public enum CraftPhase {
    
    PHASE1(1, "Tier 1 phase"),
    PHASE2(2, "Tier 2 phase"),
    PHASE3(3, "Tier 3 phase"),
    PHASE4(4, "Tier 4 phase");
    
    private final int phaseNumber;
    private final String phaseName;

    private CraftPhase(int phaseNumber, String phaseName) {
        this.phaseNumber = phaseNumber;
        this.phaseName = phaseName;
    }

    public int getPhaseNumber() {
        return phaseNumber;
    }

    public String getPhaseName() {
        return phaseName;
    }
    
}
