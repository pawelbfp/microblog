package com.microblog.dao.hibernate;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.microblog.core.Subscription;
import com.microblog.dao.SubscriptionDao;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class SubscriptionHibernateTest {

	@Autowired
	protected SubscriptionDao hbSubscription;

	@Test
	public void getSubscriptionByUserIdCorreclty() throws Exception {

		int id = 1;
		assertThat(hbSubscription).isNotNull();
		List<Subscription> subscriptionList = hbSubscription.findSubscribedByUser(id);
		assertThat(subscriptionList).isNotNull();
		assertThat(subscriptionList.get(0).getSubscriptionPK().getIdSubscriber()).isEqualTo(id);

	}

	@Test
	public void saveSubscriptionCorreclty() throws Exception {
	
		int id1 = 4;
		int id2 = 2;
		
		assertThat(hbSubscription).isNotNull();
		Subscription subscr1 = new Subscription(id1, id2); 
		hbSubscription.saveOrUpdate(subscr1);
		List<Subscription> subscriptionList = hbSubscription.findSubscribedByUser(id1);
		assertThat(subscriptionList).isNotNull();
		
		Subscription subscr2 =  subscriptionList.get(0);
		assertThat(subscr2.getSubscriptionPK().getIdSubscriber()).isEqualTo(id1);
		hbSubscription.delete(subscr1);
	}
	
	
	@Test
	public void deleteSubscriptionCorreclty() throws Exception {
	
		int id1 = 4;
		int id2 = 2;
		
		assertThat(hbSubscription).isNotNull();
		Subscription subscr1 = new Subscription(id1, id2); 
		hbSubscription.saveOrUpdate(subscr1);
		List<Subscription> subscriptionList = hbSubscription.findSubscribedByUser(id1);
		assertThat(subscriptionList).isNotNull();
		
		Subscription subscr2 =  subscriptionList.get(0);
		assertThat(subscr2.getSubscriptionPK().getIdSubscriber()).isEqualTo(id1);
		hbSubscription.delete(subscr1);
		
		List<Subscription> subscriptionList2 = hbSubscription.findSubscribedByUser(id1);
		assertThat(subscriptionList2).isEmpty();
	}	

}
