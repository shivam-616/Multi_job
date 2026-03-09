package model;

import java.util.UUID;

public interface Job {
    String getId();
    Object execute();
}
