package com.vilivesoft;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by papo on 6/15/14.
 */
@Transactional(TransactionMode.ROLLBACK)
@RunWith(Arquillian.class)
public class UserEntityTest {


    @PersistenceContext(name = "test")
    EntityManager em;

    User theUser = null;

    @Before
    public  void init(){
        theUser = new User();
        theUser.setUsername("javapapo");
        em.persist(theUser);

    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(User.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }


    @Test
    public void testGetUser() {
        User testUser = em.find(User.class, theUser.getId());
        Assert.assertEquals("javapapo", testUser.getUsername());
    }
}
