package entity;

public class Policy {
    private int policyId;
    private String policyNumber;
    private String policyType;
    private double coverageAmount;

    // Default Constructor
    public Policy() {}

    // Parameterized Constructor
    public Policy(int policyId, String policyNumber, String policyType, double coverageAmount) {
        this.policyId = policyId;
        this.policyNumber = policyNumber;
        this.policyType = policyType;
        this.coverageAmount = coverageAmount;
    }

    // Getters and Setters
    public int getPolicyId() { return policyId; }
    public void setPolicyId(int policyId) { this.policyId = policyId; }
    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }
    public String getPolicyType() { return policyType; }
    public void setPolicyType(String policyType) { this.policyType = policyType; }
    public double getCoverageAmount() { return coverageAmount; }
    public void setCoverageAmount(double coverageAmount) { this.coverageAmount = coverageAmount; }

    // toString
    @Override
    public String toString() {
        return "Policy [policyId=" + policyId + ", policyNumber=" + policyNumber + ", policyType=" + policyType + ", coverageAmount=" + coverageAmount + "]";
    }
}