package Discipline.CineHub.model;

import lombok.Getter;

@Getter
public class EmailVerificationResult {

    private final boolean verified;

    private EmailVerificationResult(boolean verified) {
        this.verified = verified;
    }

    public static EmailVerificationResult of(boolean result) {
        return new EmailVerificationResult(result);
    }
}
