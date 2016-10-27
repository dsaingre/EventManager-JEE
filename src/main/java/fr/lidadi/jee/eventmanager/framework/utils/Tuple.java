package fr.lidadi.jee.eventmanager.framework.utils;

/**
 * Created by damien on 24/10/2016.
 */
public class Tuple<A,B> {

    private A fst;
    private B scn;

    public Tuple(A fst, B scn) {
        this.fst = fst;
        this.scn = scn;
    }

    public A getFst() {
        return fst;
    }

    public B getScn() {
        return scn;
    }
}
