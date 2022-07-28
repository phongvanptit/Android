package approtrain.c2002l.phongvh;

import java.util.Date;

public class Contact {
    int id;
    String name;
    boolean isImporttant;
    Date date;

    public Contact(String name,
                   boolean important,
                   Date createDate) {
        this.name = name;
        this.isImporttant = important;
        this.date = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int isImporttant() {
        return isImporttant ? 1 : 0;
    }

    public void setImporttant(boolean importtant) {
        isImporttant = importtant;
    }
}
