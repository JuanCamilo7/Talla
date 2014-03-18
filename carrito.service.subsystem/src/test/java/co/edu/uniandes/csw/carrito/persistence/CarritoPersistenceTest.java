
package co.edu.uniandes.csw.carrito.persistence;

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


import co.edu.uniandes.csw.carrito.logic.dto.CarritoDTO;
import co.edu.uniandes.csw.carrito.persistence.api.ICarritoPersistence;
import co.edu.uniandes.csw.carrito.persistence.entity.CarritoEntity;

@RunWith(Arquillian.class)
public class CarritoPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(CarritoPersistence.class.getPackage())
				.addPackage(CarritoEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private ICarritoPersistence carritoPersistence;

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
		em.createQuery("delete from CarritoEntity").executeUpdate();
	}

	private List<CarritoEntity> data=new ArrayList<CarritoEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			CarritoEntity entity=new CarritoEntity();
			entity.setName(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createCarritoTest(){
		CarritoDTO dto=new CarritoDTO();
		dto.setName(generateRandom(String.class));
		
		
		CarritoDTO result=carritoPersistence.createCarrito(dto);
		
		Assert.assertNotNull(result);
		
		CarritoEntity entity=em.find(CarritoEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());	
	}
	
	@Test
	public void getCarritosTest(){
		List<CarritoDTO> list=carritoPersistence.getCarritos();
		Assert.assertEquals(list.size(), data.size());
        for(CarritoDTO dto:list){
        	boolean found=false;
            for(CarritoEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getCarritoTest(){
		CarritoEntity entity=data.get(0);
		CarritoDTO dto=carritoPersistence.getCarrito(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
        
	}
	
	@Test
	public void deleteCarritoTest(){
		CarritoEntity entity=data.get(0);
		carritoPersistence.deleteCarrito(entity.getId());
        CarritoEntity deleted=em.find(CarritoEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateCarritoTest(){
		CarritoEntity entity=data.get(0);
		
		CarritoDTO dto=new CarritoDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		
		
		carritoPersistence.updateCarrito(dto);
		
		
		CarritoEntity resp=em.find(CarritoEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
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