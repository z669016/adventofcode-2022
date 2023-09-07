package com.putoet.day3;

record RucksackItem(char id) {
    public RucksackItem {
        assert Character.isLetter(id);
    }

    public int priority() {
        return Character.isLowerCase(id) ? id - 'a' + 1 : id - 'A' + 27;
    }
}
