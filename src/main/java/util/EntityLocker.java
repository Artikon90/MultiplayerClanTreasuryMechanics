package util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EntityLocker {
    private final static Map<Long, Lock> lockClanStorage;
    private final static Long CLAN_AMOUNT = 608L;

    private final static Map<Long, Lock> lockUserStorage;
    private final static Long USER_AMOUNT = 2420L;
    static {
        lockClanStorage = new HashMap<>();
        for(long i = 0; i < CLAN_AMOUNT; i++)
            lockClanStorage.put(i + 1, new ReentrantLock(true));

        lockUserStorage = new HashMap<>();
        for(long i = 0; i < USER_AMOUNT; i++)
            lockUserStorage.put(i + 1, new ReentrantLock(true));
    }

    public static Lock getClanLock(Long id) {
        return lockClanStorage.get(id);
    }

    public static Lock getUserLock(Long id) {
        return lockUserStorage.get(id);
    }
}
