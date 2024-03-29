package db;

class Argument {

    static void ensureNotNull(String argumentName, Object argument) {
        if (argument == null) {
            throw new IllegalArgumentException("The " + argumentName + " must not be null.");
        }
    }

    static void ensureNotNullOrEmpty(String argumentName, String argument) {
        if ((argument == null) || (argument.length() == 0)) {
            throw new IllegalArgumentException("The " + argumentName + " must not be null or empty.");
        }
    }

    static void ensureInteger(String argumentName, String argument) {
        try {
            Integer.parseInt(argument);
        } catch (Exception e) {
            throw new IllegalArgumentException("The " + argumentName + " must be an integer.");
        }
    }

    static void ensureInRange(String argumentName, int argument, int lowerBound, int upperBound) {
        if ((argument < lowerBound) || (argument > upperBound)) {
            throw new IllegalArgumentException("The " + argumentName + " must be between " + lowerBound + " and " + upperBound + ".");
        }
    }

    static void ensureContains(String argumentName, String argument, String substring) {
        if (!argument.equals("") && !argument.contains(substring)) {
            throw new IllegalArgumentException("The " + argumentName + " must contain " + substring + " .");
        }
    }

}
