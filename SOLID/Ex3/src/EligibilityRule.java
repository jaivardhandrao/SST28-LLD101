// Each rule checks one eligibility condition; returns reason string or null if passed
public interface EligibilityRule {
    String check(StudentProfile student);
}
