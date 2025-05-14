package main.Enum;
public enum ProcessState {
    Awaiting, Completed, Rejected
    ;

    public static ProcessState fromString(String state) {
        switch (state.toLowerCase()) {
            case "awaiting":
                return Awaiting;
            case "completed":
                return Completed;
            case "rejected":
                return Rejected;
            default:
                throw new IllegalArgumentException("Invalid ReceiptState: " + state);
        }
    }
}
