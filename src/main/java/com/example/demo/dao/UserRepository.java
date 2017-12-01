package com.example.demo.dao;

import com.example.demo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * dao只要继承JpaRepository类就可以，
 * 几乎可以不用写方法，还有一个特别有尿性的功能非常赞，
 * 就是可以根据方法名来自动的生产SQL，比如findByUserName
 * 会自动生产一个以 userName 为参数的查询方法，比如 findAlll
 * 自动会查询表里面的所有数据，比如自动分页等等。。
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Pageable pageable);

    //Page<User> findALL(Pageable pageable);

    User findByPassWord(String passWord);

    User findByUserNameOrEmail(String username, String email);

    Long deleteById(Long id);

    Long countByUserName(String userName);

    List<User> findByEmailLike(String email);

    List<User> findByUserNameIgnoreCase(String userName);

    //查询userName,通过email排序
    List<User> findByUserNameOrderByEmailDesc(String userName);

    List<User> findByUserNameAndEmail(String username,String email);

    List<User> findByNickNameIsNull();


    //涉及到删除和修改在需要加上@Modifying
    /*@Modifying
    @Query("update User u set u.userName = ?1 where u.id = ?2")
    int modifyByIdAndUserId(String  userName, Long id);*/

    @Transactional
    @Modifying
    @Query("delete from User where id = ?1")
    void deleteByUserId(Long id);

    /*@Transactional(timeout = 10)
    @Query("select u from User u where u.emailAddress = ?1")
    User findByEmailAddress(String emailAddress);*/
}