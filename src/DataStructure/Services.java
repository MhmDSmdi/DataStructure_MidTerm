package DataStructure;

import Models.ServiceData;

public class  Services {

    private MyNode first;
    private MyNode tempNode;

    /*
    Worst Case : O(n)
    Average Case : O(n)
    */
    public boolean addService (ServiceData newService) {
        if (first == null) {
            MyNode temp = new MyNode(null, null, newService);
            first = temp;
            return true;
        } else {
            if (getExistServiceNode(newService.getDataName()) == null) {
                MyNode p = first;
                while (!p.isFPnull()) {
                    p = p.getLink();
                }
                MyNode temp = new MyNode(null, null, newService);
                p.setLink(temp);
                return true;
            }
            else
                return false;
        }
    }

    public MyNode getExistServiceNode(String serviceName) {
        return getExistServiceNode(first, serviceName);
    }

    /*
    Complexity : O(n)
    */
    public MyNode getMainServiceNode(String serviceName) {
        MyNode root = first;
        while(root != null) {
            if (root.getData().getDataName().equals(serviceName)) {
                return root;
            }
            else {
                tempNode = root;
                root = root.getLink();
            }
        }
        return null;
    }

    //Complexity : O(n)
    public MyNode getExistServiceNode(MyNode root, String newService) {
       if (root != null)
           return root.searchInSublist(root, newService);
       else
           return null;
    }

    /*
    BestCase : O(1)
    WorstCase : O(n)
    AverageCase:
    */
    public boolean addSubToService(MyNode parent, ServiceData subData) {
      if(getExistServiceNode(subData.getDataName()) != null || getExistServiceNode(parent.getData().getDataName()) == null)
          return false;
      else {
          MyNode tempNode = new MyNode();
          tempNode.setData(subData);
          if (parent.isSuperNode()) {
              MyNode p = parent.getdLink();
              while(!p.isFPnull())
                  p = p.getLink();
              p.setLink(tempNode);
              return true;
          }
          else {
              parent.setdLink(tempNode);
              return true;
          }
      }
    }

    public void removeNode(MyNode serviceNode) {
        if (first.equals(serviceNode)){
            first = first.getLink();
            serviceNode.setLink(null);
            serviceNode.setdLink(null);
        }else {
            System.out.println();
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

    //Flag for recognize subList and parent
    public void printSubService(MyNode root) {
        System.out.print("<");
        System.out.print(root.getData().getDataName());
        if (root.isSuperNode()) {
            System.out.print(",");
            System.out.print("<");
            print(root.getdLink());
            System.out.print(">");
        }
        System.out.print(">");
    }
}