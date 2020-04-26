package hxw.test.springbootunittesting.persistence.repository;

import hxw.test.springbootunittesting.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByName(String name);

    @Query("select u from UserEntity u where u.name = :name")
    UserEntity findByNameCustomQuery(@Param("name") String name);

    @Query(value = "select * from user as u where u.name = :name", nativeQuery = true)
    UserEntity findByNameNativeQuery(@Param("name") String name);
}
