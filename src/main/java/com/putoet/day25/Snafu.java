package com.putoet.day25;

import org.jetbrains.annotations.NotNull;

record Snafu(String snafu) {
    public static final Snafu MINUS_TWO = new Snafu(SnafuDigit.DOUBLE_MINUS.toString());
    public static final Snafu MINUS_ONE = new Snafu(SnafuDigit.MINUS.toString());
    public static final Snafu ZERO = new Snafu(SnafuDigit.ZERO.toString());
    public static final Snafu ONE = new Snafu(SnafuDigit.ONE.toString());
    public static final Snafu TWO = new Snafu(SnafuDigit.TWO.toString());

    public long asDecimal() {
        return asDecimal(this.snafu);
    }

    @Override
    public String toString() {
        return snafu;
    }

    public static Snafu of(long decimal) {
        return decimal == -2L ? MINUS_TWO :
                decimal == -1L ? MINUS_ONE :
                        decimal == 0L ? ZERO :
                                decimal == 1L ? ONE :
                                        decimal == 2L ? TWO : new Snafu(Snafu.asSnafu(decimal));
    }

    public static long asDecimal(@NotNull String snafu) {
        var value = 0L;
        final var sb = new StringBuilder(snafu).reverse();
        for (int i = 0; i < sb.length(); i++) {
            final var snafuDigit = SnafuDigit.from(sb.charAt(i));
            value += snafuDigit.asDecimal() * (long) Math.pow(5, i);
        }

        return value;
    }

    public static String asSnafu(long decimal) {
        var pow = 0;
        while (Math.abs(decimal) > maxSnafu(pow))
            pow++;

        return asSnafu(pow, decimal);
    }

    private static String asSnafu(int pow, long decimal) {
        final var sign = decimal < 0 ? -1 : +1;

        var digit = 0;
        if (Math.abs(decimal) >= Math.pow(5, pow) * 2)
            digit = 2;
        else if (Math.abs(decimal) >= Math.pow(5, pow))
            digit = 1;

        var remainder = decimal - (sign * (long) Math.pow(5, pow) * digit);
        if (Math.abs(remainder) > maxSnafu(pow - 1)) {
            digit++;
            remainder = decimal - (sign * (long) Math.pow(5, pow) * digit);
        }

        return SnafuDigit.from(digit * sign).toString() + (pow > 0 ? asSnafu(pow - 1, remainder) : "");
    }

    private static long maxSnafu(int pow) {
        var max = 0L;
        while (pow >= 0) {
            max += 2 * (long) Math.pow(5, pow);
            pow--;
        }

        return max;
    }
}
