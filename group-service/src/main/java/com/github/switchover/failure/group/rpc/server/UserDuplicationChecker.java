package com.github.switchover.failure.group.rpc.server;

import java.util.LinkedHashSet;
import java.util.Set;

public class UserDuplicationChecker {
    private final Set<String> nicknames = new LinkedHashSet<>();

    public boolean contains(String nickname) {
        return nicknames.contains(nickname);
    }

    public boolean notContains(String nickname) {
        return !nicknames.contains(nickname);
    }

    public void addNickname(String nickname) {
        nicknames.add(nickname);
    }
}
