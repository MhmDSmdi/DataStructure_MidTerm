package DataStructure;

import Models.Data;

import java.util.ArrayList;

public class MyNode {
    private ArrayList<MyNode> parents;
    private MyNode link;
    private MyNode dLink;
    private Data data;
    private int childNumber;

    public MyNode(MyNode link, MyNode dLink, Data data, int childNumber) {
        parents = new ArrayList<>();
        this.link = link;
        this.dLink = dLink;
        this.data = data;
        this.childNumber = childNumber;
    }

    public MyNode() {
        parents = new ArrayList<>();
    }

    public ArrayList<MyNode> getParents() {
        return parents;
    }

    public void addParent(MyNode newParent) {
        parents.add(newParent);
    }

    public MyNode getLink() {
        return link;
    }

    public void setLink(MyNode link) {
        this.link = link;
    }

    public MyNode getdLink() {
        return dLink;
    }

    public void setdLink(MyNode dLink) {
        this.dLink = dLink;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getChiledNumber() {
        return childNumber;
    }

    public void addChiledNumber() {
        childNumber++;
    }

    public boolean isFPnull() {
        if (link == null)
            return true;
        else
            return false;
    }

    public boolean isSuperNode() {
        if (dLink == null)
            return false;
        else
            return true;
    }
}
