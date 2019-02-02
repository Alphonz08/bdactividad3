/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate3;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author Alphonz
 */
public class DAOAlumno {
    private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;
    public static void main(String[] args) {
			System.err.println("Iniciando" );
	      try{
	    	  	Configuration configuration = new Configuration();
				System.err.println("Leyendo configuracion." );
	    	    configuration.configure();
	    	    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
	    	    factory = configuration.buildSessionFactory(serviceRegistry);
	      }catch (Throwable ex) { 
	         System.err.println("No se puede crear la Sesion" + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
              
              DAOAlumno dao = new DAOAlumno();
              
              Ciudad ciudadID1 = dao.addCiudad("Tuxtla");
              Ciudad ciudadID2 = dao.addCiudad("Suchiapa");
              Ciudad ciudadID3 = dao.addCiudad("San cristobal");
              Ciudad ciudadID4 = dao.addCiudad("Ocosingo");
              Ciudad ciudadID5 = dao.addCiudad("Comitan");
              
              Long alumID1 = dao.addAlumno("Erik",20,ciudadID1.getId());
              Long alumID2 = dao.addAlumno("Oscar",21,ciudadID1.getId());
              Long alumID3 = dao.addAlumno("Uriel",20,ciudadID1.getId());
              Long alumID4 = dao.addAlumno("Luis",19,ciudadID1.getId());
              Long alumID5 = dao.addAlumno("Daniela",18,ciudadID1.getId());
              Long alumID6 = dao.addAlumno("Pablo",21,ciudadID2.getId());
              Long alumID7 = dao.addAlumno("Antonio",20,ciudadID2.getId());
              Long alumID8 = dao.addAlumno("Angel",18,ciudadID2.getId());
              Long alumID9 = dao.addAlumno("Eduardo",18,ciudadID2.getId());
              Long alumID10 = dao.addAlumno("Quique",19,ciudadID2.getId());
              Long alumID11 = dao.addAlumno("Esteban",20,ciudadID3.getId());
              Long alumID12 = dao.addAlumno("Alan",21,ciudadID3.getId());
              Long alumID13 = dao.addAlumno("Alberto",18,ciudadID3.getId());
              Long alumID14 = dao.addAlumno("Ana",19,ciudadID3.getId());
              Long alumID15 = dao.addAlumno("Paola",20,ciudadID4.getId());
              Long alumID16 = dao.addAlumno("Alexandra",21,ciudadID4.getId());
              Long alumID17 = dao.addAlumno("Karina",18,ciudadID4.getId());
              Long alumID18 = dao.addAlumno("Brissia",19,ciudadID5.getId());
              Long alumID19 = dao.addAlumno("Kislev",20,ciudadID5.getId());
              Long alumID120 = dao.addAlumno("EMilia",21,ciudadID5.getId());
              
              
              showCiudad(ciudadID1.getId());
    }
    
    public Ciudad addCiudad(String name){
        Session session = factory.openSession();
        Transaction tx = null;
        Long ciudadID = null;
        Ciudad ciudad = null;
        
        try{
            tx = session.beginTransaction();
            ciudad = new Ciudad(name);
            /*ciudadID = (Long)*/ session.save(ciudad);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return ciudad;
    }
    
    public Long addAlumno(String name, int edad, Long ciudad){
        Session session = factory.openSession();
        Transaction tx = null;
        Long alumnoID = null;
        try{
            tx = session.beginTransaction();
            Alumno alumno = new Alumno(name,edad,ciudad);
            alumnoID = (Long) session.save(alumno);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return alumnoID;
    }
    
    public static void showCiudad(Long CiudadID){
        Session session = factory.openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            Ciudad ciudad = (Ciudad)session.get(Ciudad.class, CiudadID);
            
            List alumnos = session.createQuery("from hibernate3.Alumno a WHERE a.ciudad='"+CiudadID+"'").list();
            ciudad.setAlumnos(alumnos);
            System.out.println("Nombre: "+ciudad.getNombre());
            List alums = ciudad.getAlumnos();
            for (Iterator iterator = alums.iterator();iterator.hasNext();){
                Alumno alumName = (Alumno) iterator.next();
                System.out.println("Alumno: "+alumName.getNombre());
            }
            //tx.commit(false);
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
    }
}
