import java.util.ArrayList;
import java.util.Iterator;

class InvalidNodeException extends RuntimeError {
    public  InvalidNodeException() {
        super("Nó inválido");
    }
}

class Node {
    private Node parent;
    private ArrayList children = new ArrayList();
    private Object element;

    public Node(Node parent, Object element) {
        this.parent = parent;
        this.element = element;
    }

    public Object getElement() {
        return this.element;
    }

    public void setElement(Object o) {
        this.element = o;
    }

    public Node parent() {
        return parent;
    }

    public Iterator children() {
        return this.children.iterator();
    }

    public void addChild(Node n) {
        this.children.add(n);
    }

    public void removeChild(Node n) {
        this.children.remove(n);
    }

    public int childrenNumber() {
        return this.children.size();
    }
}

class ArvoreGenerica {
    private Node root;
    private int size;

    public ArvoreGenerica(Object o) {
        this.root = new Node(null, o);
        this.size = 1;
    }

    public Node root() {
        return this.root;
    }

    public Node parent(Node n) {
        return n.parent();
    }

    public Iterator children(Node n) {
        return n.children();
    }

    public void addChild(Node n, Object o) {
        Node newNode = new Node(o);
        n.addChild(newNode);
        this.size++;
    }

    // só pode remover nós externos e que tenham pai
    public Object removeChild(Node n) {
        if (!this.isExternal(n) || n.parent() == null) {
            throw new InvalidNodeException();
        }
        n.parent().removeChild(n);
        return n.getElement();
    }

    public void swapElements(Node n, Node o) {
        Object temp = n.getElement();
        n.setElement(o.getElement());
        o.setElement(temp);
    }

    public Object replace(Node n, Object o) {
        Object old = n.getElement();
        n.setElement(o);
        return old;
    }

    public void addElements(ArrayList elements, Node n) {
        elements.add(n.getElement());
        Iterator children = n.children();
        while (children.hasNext()) {
            addElements(elements, children.next());
        }
    }

    public void addNodes(ArrayList nodes, Node n) {
        nodes.add(n);
        Iterator children = n.children();
        while(children.hasNext()) {
            addNodes(nodes, children.next());
        }
    }

    public Iterator elements() {
        ArrayList elements = new ArrayList();
        this.addElements(elements, this.root);
        return elements.iterator();
    }

    public Iterator nodes() {
        ArrayList nodes = new ArrayList();
        addNodes(nodes, this.root);
        return nodes.iterator();
    }
    
    public int depth(Node n) {
        if (n == this.root) {
            return 0;
        }
        return 1 + depth(n.parent());
    }

    public int height() {
        int h = 0;
        Iterator nodes = this.nodes();
        while(nodes.hasNext()) {
            if (this.isExternal(nodes.next())) {
                h = max(this.depth(nodes.next()), h);
            }
        }
        return h;
    }

    public boolean isRoot(Node n) {
        return n == this.root;
    }
    
    public boolean isInternal(Node n) {
        return n.childrenNumber() > 0;
    }
    
    public boolean isExternal(Node n) {
        return n.childrenNumber() == 0;
    }
    
    // sempre falso pois o root não pode ser removido
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public int size() {
        return this.size;
    }


    
}