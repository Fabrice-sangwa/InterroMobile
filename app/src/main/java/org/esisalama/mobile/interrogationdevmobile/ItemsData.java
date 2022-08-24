package org.esisalama.mobile.interrogationdevmobile;

import java.util.List;

public class ItemsData {

    int totalCount;
    int totalResuls;
    List<Items> items;

    public ItemsData(){}

    public ItemsData(int totalCount, int totalResuls, List<Items> items) {
        this.totalCount = totalCount;
        this.totalResuls = totalResuls;
        this.items = items;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalResuls() {
        return totalResuls;
    }

    public List<Items> getItems() {
        return items;
    }


    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalResuls(int totalResuls) {
        this.totalResuls = totalResuls;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
