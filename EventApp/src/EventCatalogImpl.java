import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class EventCatalogImpl extends TreeMap<Event, Set<Time>> implements EventCatalog {
    private Map<Event, Set<Time>> events = new TreeMap<Event, Set<Time>>();

    @Override
    public boolean addCatalogEntry(Event e, Set<Time> tSet) {
        if (e == null || tSet == null) {
            throw new NullPointerException("No arguments can be null");
        }
        if (tSet.contains(null)) {
            throw new NullPointerException("Set cannot have null elements");
        }
        if (events.size() != 0 && events.containsKey(e)) {
            return false;
        } else {
            events.put(e, tSet);
            return true;
        }
    }

    @Override
    public boolean addTimeToEvent(Event e, Time time) {
        if (e == null || time == null) {
            throw new NullPointerException("No arguments can be null");
        }
        if (events.get(e) != null) {
            return events.get(e).add(time);
        } else {
            return false;
        }
    }

    @Override
    public Set<Time> deleteEvent(Event e) {
        if (e == null) {
            throw new NullPointerException("No arguments can be null");
        }
        return events.remove(e);
    }

    @Override
    public boolean deleteTime(Event e, Time t) {
        if (e == null || t == null) {
            throw new NullPointerException("No arguments can be null");
        }
        if (events.get(e) != null) {
            return events.get(e).remove(t);
        }
        return false;
    }

    @Override
    public Map<Event, Set<Time>> filterByEventCategory(EventCategory category) {
        if (category == null) {
            throw new NullPointerException("No arguments can be null");
        }
        Map<Event, Set<Time>> out = new HashMap<Event, Set<Time>>();
        for (Event e : events.keySet()) {
            if (e.getCategory() == category) {
                out.put(e, events.get(e));
            }
        }
        return out;
    }

    @Override
    public Set<Event> getAllEvents() {
        return events.keySet();
    }

    @Override
    public Set<Time> getTimesOfEvent(Event e) {
        return events.get(e);
    }
}
