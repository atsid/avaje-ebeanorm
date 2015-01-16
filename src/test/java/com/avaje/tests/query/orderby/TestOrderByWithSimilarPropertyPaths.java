package com.avaje.tests.query.orderby;

import org.junit.Assert;
import org.junit.Test;

import com.avaje.ebean.BaseTestCase;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.avaje.ebean.FetchConfig;
import com.avaje.tests.model.basic.Contact;

public class TestOrderByWithSimilarPropertyPaths extends BaseTestCase {
  
  @Test
  public void testOrderByWithSimilarPropertyPaths() {
    
    Query<Contact> query = Ebean.createQuery(Contact.class)
      .fetch("customer", new FetchConfig().query())
      .where()
      .eq("customer.id", 1)
      .orderBy("customerIsContact");
    
    query.findList();
    
    String generatedSql = query.getGeneratedSql();

    Assert.assertFalse("order by generated correctly", generatedSql.contains("order by sContact"));
  }
  
}