package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private Map<Long , TimeEntry> repo = new HashMap<Long, TimeEntry>();
    private Long counter = 0l;
    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(++counter);
        repo.put(timeEntry.getId(),timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        return repo.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(repo.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        TimeEntry temp = repo.get(id);
        if(temp != null){
            timeEntry.setId(id);
            repo.put(id,timeEntry);
            temp = timeEntry;
        }
        return temp;
    }

    @Override
    public void delete(Long id) {
        repo.remove(id);
    }
}
