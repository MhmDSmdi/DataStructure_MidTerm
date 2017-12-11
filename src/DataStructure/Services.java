package DataStructure;

import Models.ServiceData;

public class Services {

    private MyNode first;
    private MyNode tempNode;

    public MyNode getFirst() {
        return first;
    }

    public void setFirst(MyNode first) {
        this.first = first;
    }

    public boolean addService (ServiceData newService) {
        if (first == null) {
            MyNode temp = new MyNode(null, null, newService, 0);
            first = temp;
            return true;
        } else {
            if (!isExistService(first,newService.getDataName())) {
                MyNode p = first;
                while (!p.isFPnull()) {
                    p = p.getLink();
                }
                MyNode temp = new MyNode(null, null, newService, 0);
                p.setLink(temp);
                return true;
            }
            else
                return false;
        }
    }

    public boolean isExistService(MyNode root, String serviceName) {
        if (root != null) {
            if (root.getData().getDataName().equals(serviceName)) {
                return true;
            }
            if (root.isSuperNode())
                return isExistService(root.getdLink(), serviceName);
            if (!root.isFPnull())
                return isExistService(root.getLink(), serviceName);
        }
       return false;
    }

    public MyNode getExistServiceNode(String serviceName) {
        return getExistServiceNode(first, serviceName);
    }

    public MyNode getMainServiceNode(String serviceName) {
        MyNode root = first;
        while(root != null) {
            tempNode = root;
            if (root.getData().getDataName().equals(serviceName)) {
                return root;
            }
            else
                root = root.getLink();
        }
        return null;
    }

    public MyNode getExistServiceNode(MyNode root, String newService) {
       if (root != null) {
           if (root.getData().getDataName().equals(newService)) {
               return root;
           }
           if (root.isSuperNode()) {
               MyNode temp = getExistServiceNode(root.getdLink(), newService);
               if (temp != null) return temp;
           }
           if (!root.isFPnull()) {
               MyNode temp = getExistServiceNode(root.getLink(), newService);
               if (temp != null) return temp;
           }
       }
       return null;
    }

    public boolean addSubToService(MyNode parent, ServiceData subData) {
        MyNode tempNode = new MyNode();
        tempNode.setData(subData);
        boolean isExist = false;
        if (parent.isSuperNode()) {
            MyNode p = parent.getdLink();
            while(!p.isFPnull()){
                if (p.getData().getDataName().equals(subData.getDataName())){
                    isExist = true;
                    break;
                }
                else
                    p = p.getLink();
            }
            if (!isExist && !p.getData().getDataName().equals(subData.getDataName())){
                p.setLink(tempNode);
                return true;
            }
            else
                return false;
        }
        else {
            parent.setdLink(tempNode);
            return true;
        }
    }

    private void addParentToChild(MyNode parent, MyNode child) {
        if (child != null) {
            if (!child.getParents().contains(parent)) {
                if (!parent.isSuperNode()) {
                    child.addParent(parent);
                    parent.addChiledNumber();
                    parent.setdLink(child);
                } else {
                    MyNode root = parent.getdLink();
                    while (!root.isFPnull())
                        root = root.getLink();
                    root.addParent(parent);
                    root.setLink(child);
                }
            }
        }
    }

    private boolean isChiledOf(MyNode parent, MyNode child) {
        MyNode root = parent;
        if (root.equals(child))
            return true;
        if (root.isSuperNode())
            return isChiledOf(root.getdLink(), child);
        if (!root.isSuperNode())
            return isChiledOf(root.getLink(), child);
        else
            return false;
}

    public void removeNode(String serviceName) {
        MyNode serviceNode = getExistServiceNode(serviceName);
        if (first.equals(serviceNode)){
            first = first.getLink();
            serviceNode.setLink(null);
            serviceNode.setdLink(null);
        }else {
            tempNode.setLink(serviceNode.getLink());
            serviceNode.setLink(null);
        }
    }

    public void removeNode(MyNode serviceNode) {
        if (first.equals(serviceNode)){
            first = first.getLink();
            serviceNode.setLink(null);
            serviceNode.setdLink(null);
        }else {
            tempNode.setLink(serviceNode.getLink());
            serviceNode.setLink(null);
        }
    }

    public void printServiceGList() {
        print(first);
    }

    public void print(MyNode root) {
        if (root != null) {
            System.out.print(root.getData().getDataName());
            if (root.isSuperNode()) {
                System.out.print(",");
                System.out.print("<");
                print(root.getdLink());
                System.out.print(">");
            }
            if (!root.isFPnull()) {
                System.out.print(",");
                print(root.getLink());
            }
        }
    }


}
