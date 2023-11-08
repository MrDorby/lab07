package it.unibo.inner.test.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import it.unibo.inner.api.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private final List<T> array;
    private Predicate<T> predicate;
    //private final T[] array;

    public IterableWithPolicyImpl(final T[] array){
        //this.array = array;
        //this.array = List.of(array);
        this(array, new Predicate<T>() {
            @Override
            public boolean test(T arg0) {
                return true;
            }
        });
    }

    public IterableWithPolicyImpl(final T[] array, final Predicate<T> predicate){
        this.array = List.of(array);
        this.predicate = predicate;
    }

    @Override
    public Iterator<T> iterator() {
       return new IteratorImpl();
    }

    //@Override
    public void setIterationPolicy(final Predicate<T> filter) {
        this.predicate = filter;
    }
    
    public class IteratorImpl implements Iterator<T>{

        private int i = 0;
        private List<T> list = iteratorArray();
        @Override
        public boolean hasNext() {
            return this.i < this.list.size();
            /*while(this.i < IterableWithPolicyImpl.this.array.size()){
                if(IterableWithPolicyImpl.this.predicate.test(array.get(i))){
                    return true;
                }
                i++;
            }
            return false;*/
            //return this.i < IterableWithPolicyImpl.this.array.length;
        }

        @Override
        public T next() {
            return this.list.get(i++);
            //return IterableWithPolicyImpl.this.array[i++];
        }

        private List<T> iteratorArray(){
            List<T> list1 = new ArrayList<>();
            while(i < IterableWithPolicyImpl.this.array.size()) {
                if(IterableWithPolicyImpl.this.predicate.test(array.get(i))){
                    list1.add(array.get(i));
                }
                i++;
            }
            i = 0;
            return list1;
        }
    }


    
}
