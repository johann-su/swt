public class Event implements Comparable<Event> {
    private String title;
    private EventCategory category;

    public Event(String title, EventCategory category) {
        if (title.equals("")) {
            throw new IllegalArgumentException();
        }
        if (category == null) {
            throw new NullPointerException();
        }
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        // This is a sample comment
        return title;
    }

    public EventCategory getCategory() {
        return category;
    }

    public int compareTo(Event o) {
        if (title.compareTo(o.getTitle()) == 0) {
            return category.compareTo(o.getCategory());
        }
        return title.compareTo(o.getTitle());
    }
}