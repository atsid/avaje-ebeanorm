package com.avaje.tests.model.pview;

import com.avaje.ebean.BaseTestCase;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class TestPview extends BaseTestCase {

  @Test
  public void test() {

    Wview wview = Ebean.getReference(Wview.class, UUID.randomUUID());
        
    Query<Paggview> query = Ebean.find(Paggview.class);
    query.select("amount");
    query.where().eq("pview.wviews", wview);
    query.orderBy("pview.value");
    query.findList();
    String generatedSql = query.getGeneratedSql();
    
    Assert.assertTrue(generatedSql.contains("select distinct t0.amount c0, t1.value from paggview t0 join pp u1 on u1.id = t0.pview_id  join pp_to_ww u2z_ on u2z_.pp_id = u1.id  join wview u2 on u2.id = u2z_.ww_id  left outer join pp t1 on t1.id = t0.pview_id  where u2.id = ?  order by t1.value"));
    
  }
  
}
