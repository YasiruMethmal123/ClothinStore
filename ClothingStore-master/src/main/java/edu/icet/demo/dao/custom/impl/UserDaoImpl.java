package edu.icet.demo.dao.custom.impl;

import edu.icet.demo.dao.custom.UserDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.dao.custom.UserDao;
import org.example.entity.UserEntity;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
public class UserDaoImpl<UserEntity> implements UserDao {


        public String getLatestId(){
            Session session = HibernateUtil.getSession();
            session.getTransaction().begin();

            Query query = session.createQuery("SELECT id FROM user ORDER BY id DESC LIMIT 1");
            String id = (String) query.uniqueResult();
            session.close();
            return id;
        }
        public UserEntity searchById(String id){
            Session session = HibernateUtil.getSession();
            session.getTransaction();

            Query query = session.createQuery("FROM user WHERE id=:id");
            query.setParameter("id",id);
            UserEntity userEntity = (UserEntity) query.uniqueResult();
            session.close();
            return userEntity;
        }

        @Override
        public <UserEntity> UserEntity search(String s){

            Session session = HibernateUtil.getSession();
            session.getTransaction().begin();

            Query query = session.createQuery("FROM user WHERE email=:email");
            query.setParameter("email",s);
            UserEntity userEntity = (UserEntity) query.uniqueResult();
            session.close();
            return userEntity;
        }

        @Override
        public <UserEntity> ObservableList<UserEntity> searchAll(){

            Session session = HibernateUtil.getSession();
            session.getTransaction().begin();
            List<UserEntity> userList = session.createQuery("FROM user").list();
            ObservableList<UserEntity> list= FXCollections.observableArrayList();
            session.close();
            userList.forEach(userEntity -> {
                list.add(userEntity);
            });
            return list;

        }

        @Override
        public boolean insert(UserEntity userEntity){

            Session session = HibernateUtil.getSession();
            session.getTransaction().begin();
            session.persist(userEntity);
            session.getTransaction().commit();
            session.close();
            return true;
        }

        public boolean update(String email,String password){

            Session session = HibernateUtil.getSession();
            session.getTransaction().begin();
            Query query = session.createQuery("UPDATE user SET password =:p WHERE email =:e");
            query.setParameter("p",password);
            query.setParameter("e",email);
            int i = query.executeUpdate();
            System.out.println(i);

            session.getTransaction().commit();
            session.close();
            return i>0;
        }
        @Override
        public boolean update(UserEntity userEntity){
            Session session = HibernateUtil.getSession();
            session.getTransaction().begin();
            Query query = session.createQuery("UPDATE user " +
                    "SET name =:name,address =:address,email =:email WHERE id =:id");
            query.setParameter("name",userEntity.getName());
            query.setParameter("address",userEntity.getAddress());
            query.setParameter("email",userEntity.getEmail());
            query.setParameter("id",userEntity.getId());

            int i = query.executeUpdate();
            session.getTransaction().commit();
            session.close();

            return i>0;
        }

        @Override
        public boolean delete(String id) {

            Session session = HibernateUtil.getSession();
            session.getTransaction().begin();
            Query query = session.createQuery("DELETE FROM user WHERE id=:id");
            query.setParameter("id",id);
            int i = query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            return i>0;
        }
    }}
