
package co.edu.uniandes.csw.carrito.logic.ejb;

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
import co.edu.uniandes.csw.carrito.logic.api.ICarritoLogicService;
import co.edu.uniandes.csw.carrito.persistence.CarritoPersistence;
import co.edu.uniandes.csw.carrito.persistence.api.ICarritoPersistence;
import co.edu.uniandes.csw.carrito.persistence.entity.CarritoEntity;

@RunWith(Arquillian.class)
public class CarritoLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(CarritoLogicService.class.getPackage())
				.addPackage(CarritoPersistence.class.getPackage())
				.addPackage(CarritoEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private ICarritoLogicService carritoLogicService;
	
	@Inject
	private ICarritoPersistence carritoPersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<CarritoDTO> dtos=carritoPersistence.getCarritos();
		for(CarritoDTO dto:dtos){
			carritoPersistence.deleteCarrito(dto.getId());
		}
	}

	private List<CarritoDTO> data=new ArrayList<CarritoDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			CarritoDTO pdto=new CarritoDTO();
			pdto.setName(generateRandom(String.class));
			pdto=carritoPersistence.createCarrito(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createCarritoTest(){
		CarritoDTO ldto=new CarritoDTO();
		ldto.setName(generateRandom(String.class));
		
		
		CarritoDTO result=carritoLogicService.createCarrito(ldto);
		
		Assert.assertNotNull(result);
		
		CarritoDTO pdto=carritoPersistence.getCarrito(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
	}
	
	@Test
	public void getCarritosTest(){
		List<CarritoDTO> list=carritoLogicService.getCarritos();
		Assert.assertEquals(list.size(), data.size());
        for(CarritoDTO ldto:list){
        	boolean found=false;
            for(CarritoDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getCarritoTest(){
		CarritoDTO pdto=data.get(0);
		CarritoDTO ldto=carritoLogicService.getCarrito(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
        
	}
	
	@Test
	public void deleteCarritoTest(){
		CarritoDTO pdto=data.get(0);
		carritoLogicService.deleteCarrito(pdto.getId());
        CarritoDTO deleted=carritoPersistence.getCarrito(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateCarritoTest(){
		CarritoDTO pdto=data.get(0);
		
		CarritoDTO ldto=new CarritoDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		
		
		carritoLogicService.updateCarrito(ldto);
		
		
		CarritoDTO resp=carritoPersistence.getCarrito(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
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