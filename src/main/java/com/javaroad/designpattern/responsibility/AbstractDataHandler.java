package com.javaroad.designpattern.responsibility;

public abstract  class AbstractDataHandler<T> {
   protected abstract  T handleRequest(String qry) throws Exception;
}
