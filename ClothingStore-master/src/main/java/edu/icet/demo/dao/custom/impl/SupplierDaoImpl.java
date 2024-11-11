package edu.icet.demo.dao.custom.impl;

import com.mysql.cj.Query;
import com.mysql.cj.Session;
import edu.icet.demo.dao.custom.SupplierDao;
import edu.icet.demo.entity.SupplierEntity;
import edu.icet.demo.entity.UserEntity;
import javafx.collections.ObservableList;
import org.hibernate.mapping.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public SupplierEntity search(String s) {
        return null;
    }

    @Override
    public ObservableList<SupplierEntity> searchAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<SupplierEntity> list = session.createQuery("FROM supplier").list();
        session.close();
        ObservableList<SupplierEntity> supplierEntityList = FXCollections.observableArrayList();

        list.forEach(supplierEntity -> {
            supplierEntityList.add(supplierEntity);
        });
        return supplierEntityList;
    }

    @Override
    public boolean insert(SupplierEntity supplierEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist((SupplierEntity) supplierEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(SupplierEntity supplierEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE supplier SET name=:name ," +
                "email=:email ,company=:company WHERE id=:id");
        query.setParameter("name",supplierEntity.getName());
        query.setParameter("email",supplierEntity.getEmail());
        query.setParameter("company",supplierEntity.getCompany());
        query.setParameter("id",supplierEntity.getId());
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM supplier WHERE id=:id");
        query.getStatementExecuting("id",id);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    public String getLatestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("SELECT id FROM supplier ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    public ObservableList<String> getAllIds() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<String> list = session.createQuery("SELECT id FROM supplier").list();
        session.close();

        ObservableList<String> idList = FXCollections.observableArrayList();
        list.forEach(s -> {
            idList.add(s);
        });
        return idList;
    }

    public ObservableList<Supplier> getAllSupplierByEId(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("FROM supplier WHERE empId=:id");
        query.setParameter("id",id);
        List<SupplierEntity> list = query.list();
        session.close();
        ObservableList<Supplier> supplierEntityList = FXCollections.observableArrayList();

        list.forEach(supplierEntity -> {
            supplierEntityList.add(new ObjectMapper().convertValue(supplierEntity, Supplier.class));
        });
        return supplierEntityList;
    }
}
