package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQuest {
    private long id;
    private boolean isComplete;
    private int goldReward;
}
