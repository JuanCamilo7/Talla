
package co.edu.uniandes.csw.promocion.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.promocion.logic.dto.PromocionDTO;
import co.edu.uniandes.csw.promocion.persistence.api.IPromocionPersistence;
import co.edu.uniandes.csw.promocion.persistence.entity.PromocionEntity;

@RunWith(Arquillian.class)
public class PromocionPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(PromocionPersistence.class.getPackage())
				.addPackage(PromocionEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IPromocionPersistence promocionPersistence;

	@PersistenceContext
	private EntityManager em;

	@Inject
	UserTransaction utx;

	@Before
	public void configTest() {
		System.out.println("em: " + em);
		try {
			utx.begin();
			clearData();
			insertData();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void clearData() {
		em.createQuery("delete from PromocionEntity").executeUpdate();
	}

	private List<PromocionEntity> data=new ArrayList<PromocionEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			PromocionEntity entity=new PromocionEntity();
			entity.setName(generateRandom(String.class));
			entity.setFechaInicial(generateRandom(Date.class));
			entity.setFechaFinal(generateRandom(Date.class));
			entity.setNuevoPrecio(generateRandom(Double.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createPromocionTest(){
		PromocionDTO dto=new PromocionDTO();
		dto.setName(generateRandom(String.class));
		dto.setFechaInicial(generateRandom(Date.class));
		dto.setFechaFinal(generateRandom(Date.class));
		dto.setNuevoPrecio(generateRandom(Double.class));
		
		
		PromocionDTO result=promocionPersistence.createPromocion(dto);
		
		Assert.assertNotNull(result);
		
		PromocionEntity entity=em.find(PromocionEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());	
		Assert.assertEquals(dto.getFechaInicial(), entity.getFechaInicial());	
		Assert.assertEquals(dto.getFechaFinal(), entity.getFechaFinal());	
		Assert.assertEquals(dto.getNuevoPrecio(), entity.getNuevoPrecio());	
	}
	
	@Test
	public void getPromocionsTest(){
		List<PromocionDTO> list=promocionPersistence.getPromocions();
		Assert.assertEquals(list.size(), data.size());
        for(PromocionDTO dto:list){
        	boolean found=false;
            for(PromocionEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getPromocionTest(){
		PromocionEntity entity=data.get(0);
		PromocionDTO dto=promocionPersistence.getPromocion(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getFechaInicial(), dto.getFechaInicial());
		Assert.assertEquals(entity.getFechaFinal(), dto.getFechaFinal());
		Assert.assertEquals(entity.getNuevoPrecio(), dto.getNuevoPrecio());
        
	}
	
	@Test
	public void deletePromocionTest(){
		PromocionEntity entity=data.get(0);
		promocionPersistence.deletePromocion(entity.getId());
        PromocionEntity deleted=em.find(PromocionEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updatePromocionTest(){
		PromocionEntity entity=data.get(0);
		
		PromocionDTO dto=new PromocionDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		dto.setFechaInicial(generateRandom(Date.class));
		dto.setFechaFinal(generateRandom(Date.class));
		dto.setNuevoPrecio(generateRandom(Double.class));
		
		
		promocionPersistence.updatePromocion(dto);
		
		
		PromocionEntity resp=em.find(PromocionEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getFechaInicial(), resp.getFechaInicial());	
		Assert.assertEquals(dto.getFechaFinal(), resp.getFechaFinal());	
		Assert.assertEquals(dto.getNuevoPrecio(), resp.getNuevoPrecio());	
	}
	
	public <T> T generateRandom(Class<T> objectClass){
		Random r=new Random();
		if(objectClass.isInstance(String.class)){
			String s="";
			for(int i=0;i<10;i++){
				char c=(char)(r.nextInt()/('Z'-'A')+'A');
				s=s+c;
			}
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Integer.class)){
			Integer s=r.nextInt();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Long.class)){
			Long s=r.nextLong();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(java.util.Date.class)){
			java.util.Calendar c=java.util.Calendar.getInstance();
			c.set(java.util.Calendar.MONTH, r.nextInt()/12);
			c.set(java.util.Calendar.DAY_OF_MONTH,r.nextInt()/30);
			c.setLenient(false);
			return objectClass.cast(c.getTime());
		} 
		return null;
	}
	
}