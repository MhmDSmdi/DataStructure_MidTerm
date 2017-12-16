package DataStructure;

import Models.Data;

import java.util.ArrayList;

public class MyNode {
    private MyNode link;
    private MyNode dLink;
    private Data data;

    public MyNode(MyNode link, MyNode dLink, Data data) {
        this.link = link;
        this.dLink = dLink;
        this.data = data;
    }

    public MyNode() { }

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

    public MyNode searchInSublist(MyNode root, String name) {
        if (root != null) {
            if (root.getData().getDataName().equals(name)) {
                return root;
            }
            if (root.isSuperNode()) {
                MyNode temp = searchInSublist(root.getdLink(), name);
                if (temp != null) return temp;
            }
            if (!root.isFPnull()) {
                MyNode temp = searchInSublist(root.getLink(), name);
                if (temp != null) return temp;
            }
        }
        return null;
    }
}
